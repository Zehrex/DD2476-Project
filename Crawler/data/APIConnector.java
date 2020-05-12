1
https://raw.githubusercontent.com/harvestcore/ipmdroid/master/app/src/main/java/com/agm/ipmanager/API/APIConnector.java
package com.agm.ipmanager.API;

import android.content.Context;

import com.agm.ipmanager.Service;
import com.agm.ipmanager.credentials.CredentialsManager;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class APIConnector {
    private static APIConnector connector;

    private RequestQueue requestQueue;
    private Context context;

    private APIConnector() {}

    public static APIConnector getInstance() {
        if (connector == null) {
            connector = new APIConnector();
        }

        return connector;
    }

    public void setContext(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void login() {
        if (CredentialsManager.getInstance().hasCredentials()) {
            String url = CredentialsManager.getInstance().getCredentials().hostname + "/api/login";

            CustomRequest request = new CustomRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String token = response.getString("token");

                        if (!token.isEmpty()) {
                            CredentialsManager.getInstance().setToken(token);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            requestQueue.add(request);
        }
    }

    public HashMap<Service, Boolean> getServiceStatus() {
        final HashMap<Service, Boolean> output = new HashMap<>();
        if (CredentialsManager.getInstance().hasCredentials()) {
            String url = CredentialsManager.getInstance().getCredentials().hostname + "/api/status";

            CustomRequest request = new CustomRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject mongo = response.getJSONObject("mongo");
                        JSONObject docker = response.getJSONObject("docker");

                        output.put(Service.MONGO, mongo.getBoolean("is_up"));
                        output.put(Service.DOCKER, docker.getBoolean("is_up"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            requestQueue.add(request);
        }

        return output;
    }
}
