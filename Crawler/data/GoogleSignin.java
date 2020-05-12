1
https://raw.githubusercontent.com/GEMSEDU/cordova-google-signin-android/master/src/android/GoogleSignin.java
package com.plugins;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.apache.cordova.*;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignin extends CordovaPlugin
implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = GoogleSignin.class.getSimpleName();

    private static final int RC_SIGN_IN = 9001;

    private static final String ACTION_LOGIN = "glogin";
    private static final String ACTION_SET_SERVER_CLIENT_ID = "setServerClientId";
    private static final String ACTION_SILENT_LOGIN = "silentLogin";
    private static final String ACTION_LOGOUT = "logout";
    private static final String ACTION_REVOKE_ACCESS = "revoke";
    private GoogleApiClient mGoogleApiClient;

    private CallbackContext mCurrentLoginCallback;

    private String mServerClientId = ACTION_SET_SERVER_CLIENT_ID;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        //mServerClientId = ACTION_SET_SERVER_CLIENT_ID;
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {

        if (action.equals(ACTION_LOGIN)) {
            login(callbackContext);
        } else if (action.equals(ACTION_LOGOUT)) {
            logout(callbackContext);
        } else if (action.equals(ACTION_SET_SERVER_CLIENT_ID)) {
            setServerClientId(args.optString(0), callbackContext);
        } else if (action.equals(ACTION_SILENT_LOGIN)) {
            silentLogin(callbackContext);
        } else {
            Log.i(TAG, "This action doesn't exist");
            return false;
        }
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "result signin" + result);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        Log.d(TAG, "Status Code:" + result.getStatus().getStatusCode());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            JSONObject responseJson = new JSONObject();
            GoogleSignInAccount acct = result.getSignInAccount();
            if (acct == null) {
                mCurrentLoginCallback.error("account object was null");
                return;
            }
            try {
                responseJson.put("displayName", acct.getDisplayName());
                responseJson.put("email", acct.getEmail());
                responseJson.put("familyName", acct.getFamilyName());
                responseJson.put("givenName", acct.getGivenName());
                responseJson.put("id", acct.getId());
                responseJson.put("idToken", acct.getIdToken());
                responseJson.put("photoUrl", acct.getPhotoUrl());
                responseJson.put("serverAuthCode", acct.getServerAuthCode());
            } catch (JSONException ex) {
                Log.e(TAG, "failed to create response object", ex);
            }

            if (mCurrentLoginCallback != null) {
                mCurrentLoginCallback.success(responseJson);
                mCurrentLoginCallback = null;
            }
        } else {
            Status status = result.getStatus();
            JSONObject response = new JSONObject();
            try {
                response.put("message", status.getStatusMessage());
                response.put("code", status.getStatusCode());
                Log.d(TAG, "Sucess Plugin" + status.getStatusMessage());
                mCurrentLoginCallback.error(response);
                mCurrentLoginCallback = null;
            } catch (JSONException ex) {
                Log.d(TAG, "Exception Plugin" + ex.toString());
                mCurrentLoginCallback.error(ex.toString());
                mCurrentLoginCallback = null;
            }
        }
    }

    private void setServerClientId(String id, CallbackContext callbackContext) {
        if (mServerClientId != null) {
            mServerClientId =id;
            callbackContext.success();
        } else {
            callbackContext.error("invalid server client id");
        }
    }

    private void createGoogleApiClient() {
        //create builder we can manipulate
        GoogleSignInOptions.Builder optionsBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail();
        //.requestProfile();

        //if the user setup a server client id, add it
        if (mServerClientId != null) {
            Log.d(TAG, "mServerClientId :- " + mServerClientId);
            optionsBuilder = optionsBuilder.requestIdToken(mServerClientId);
        }

        GoogleSignInOptions gso = optionsBuilder.build();

        //refresh the client
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
            mGoogleApiClient = null;
        }

        //create client with our options
        mGoogleApiClient = new GoogleApiClient.Builder(webView.getContext())
            .addOnConnectionFailedListener(this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();
    }

    private void login(CallbackContext callbackContext) {
        if (mCurrentLoginCallback != null) {
            //callbackContext.error("a login is currently in progress");
            // mCurrentLoginCallback = null;
            callbackContext.error("a login is currently in progress");
            return;
        }

        //store the callback for after the activity finishes
        mCurrentLoginCallback = callbackContext;
        //register for activity callback events
        cordova.setActivityResultCallback(this);

        createGoogleApiClient();

        //start the signin activity
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        cordova.getActivity().startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void logout(final CallbackContext callbackContext) {
        if (mGoogleApiClient == null) {
            callbackContext.error("GoogleApiClient was never initialized");
            return;
        }
        ConnectionResult apiConnect = mGoogleApiClient.blockingConnect();

        if (apiConnect.isSuccess()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback < Status > () {
                    @Override
                    public void onResult(Status status) {
                        JSONObject response = new JSONObject();
                        try {
                            response.put("message", status.getStatusMessage());
                            response.put("code", status.getStatusCode());

                            if (status.isSuccess()) {
                                //callbackContext.success(response);
				callbackContext.success("Logged out successfully");
                            } else {
                                callbackContext.error(response);
                            }
                        } catch (JSONException ex) {
                            callbackContext.error(ex.toString());
                        }
                    }
                });
        }else
	{
	            Log.d(TAG, "ConnectionResult Failed :- "  );
	}

    }

    private void silentLogin(CallbackContext callbackContext) {
        if (mCurrentLoginCallback != null) {
            //callbackContext.error("a login is currently in progress");
            mCurrentLoginCallback = null;
        }

        mCurrentLoginCallback = callbackContext;

        createGoogleApiClient();

        OptionalPendingResult < GoogleSignInResult > pendingResult = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        if (pendingResult.isDone()) {
            // There's immediate result available.
            handleSignInResult(pendingResult.get());
        } else {
            // There's no immediate result ready, displays some progress indicator and waits for the
            // async callback.
            pendingResult.setResultCallback(new ResultCallback < GoogleSignInResult > () {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}
