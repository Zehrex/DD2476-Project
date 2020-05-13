2
https://raw.githubusercontent.com/LakshyaKhatri/Lifetime/master/app/src/main/java/com/compiletales/lifetime/ShowTimerFragment.java
package com.compiletales.lifetime;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.Date;
import java.util.Random;

import static com.compiletales.lifetime.MainActivity.DESTINATION_TIME_KEY;
import static com.compiletales.lifetime.MainActivity.SHARED_PREFERENCE_NAME;


public class ShowTimerFragment extends Fragment {

    NumberPicker croreDigit;
    NumberPicker tenLakhDigit;
    NumberPicker lakhDigit;
    NumberPicker tenThousandDigit;
    NumberPicker thousandDigit;
    NumberPicker hundredDigit;
    NumberPicker tensDigit;
    NumberPicker onesDigit;
    TextView minRemainingTextView;
    TextView secRemainingTextView;
    SharedPreferences pref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_timer, container, false);

        croreDigit = view.findViewById(R.id.crore_digit);
        tenLakhDigit = view.findViewById(R.id.ten_lakh_digit);
        lakhDigit = view.findViewById(R.id.lakh_digit);
        tenThousandDigit = view.findViewById(R.id.ten_thousand_digit);
        thousandDigit = view.findViewById(R.id.thousand_digit);
        hundredDigit = view.findViewById(R.id.hundred_digit);
        tensDigit = view.findViewById(R.id.tens_digit);
        onesDigit = view.findViewById(R.id.ones_digit);
        minRemainingTextView = view.findViewById(R.id.min_remaining_text_view);
        secRemainingTextView = view.findViewById(R.id.sec_remaining_text_view);

        try {
            pref = getActivity().getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        } catch(Exception e){
            Toast.makeText(getActivity(), "Oops! Please restart the app", Toast.LENGTH_SHORT).show();
        }

        Long destinationTime = pref.getLong(DESTINATION_TIME_KEY, 0);
        startMinsSecTimer(destinationTime);
        startHrsTimer(destinationTime);
        return view;
    }

    private void startHrsTimer(final Long destinationTime){

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try{
                    Long currentTime = new Date().getTime();
                    Long remainingTime = destinationTime - currentTime;
                    if(remainingTime > 0){
                        Long forCalculatingTime = remainingTime;

                        Long days = forCalculatingTime / (24 * 60 * 60 * 1000);
                        forCalculatingTime -= days * (24 * 60 * 60 * 1000);

                        Long hrs = forCalculatingTime / (60 * 60 * 1000);
                        forCalculatingTime -= hrs * (60 * 60 * 1000);
                        //we don't have to display days.
                        //hence converting days to hrs/
                        hrs = hrs + days * 24;

                        String hrsString = hrs + "";
                        int hrsStringLength = hrsString.length();

                        switch(hrsStringLength){
                            case 1:
                                tensDigit.smoothScrollToPosition(0);
                            case 2:
                                hundredDigit.smoothScrollToPosition(0);
                            case 3:
                                thousandDigit.smoothScrollToPosition(0);
                            case 4:
                                tenThousandDigit.smoothScrollToPosition(0);
                            case 5:
                                lakhDigit.smoothScrollToPosition(0);
                            case 6:
                                tenLakhDigit.smoothScrollToPosition(0);
                            case 7:
                                croreDigit.smoothScrollToPosition(0);
                        }

                        switch(hrsStringLength) {
                            case 8:
                                croreDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 8)));
                            case 7:
                                tenLakhDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 7)));
                            case 6:
                                lakhDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 6)));
                            case 5:
                                tenThousandDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 5)));
                            case 4:
                                thousandDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 4)));
                            case 3:
                                hundredDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 3)));
                            case 2:
                                tensDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 2)));
                            case 1:
                                onesDigit.smoothScrollToPosition(Character.getNumericValue(hrsString.charAt(hrsStringLength - 1)));
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        handler.postDelayed(runnable, 1 * 1000);

    }

    private void startMinsSecTimer(final Long destinationTime){
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try{
                    Long currentTime = new Date().getTime();
                    Long remainingTime = destinationTime - currentTime;
                    if(remainingTime > 0){
                        Long forCalculatingTime = remainingTime;

                        Long days = forCalculatingTime / (24 * 60 * 60 * 1000);
                        forCalculatingTime -= days * (24 * 60 * 60 * 1000);

                        Long hrs = forCalculatingTime / (60 * 60 * 1000);
                        forCalculatingTime -= hrs * (60 * 60 * 1000);

                        Long min = forCalculatingTime / (60 * 1000);
                        forCalculatingTime -= min * (60 * 1000);

                        Long sec = forCalculatingTime / 1000;

                        if (sec < 10){
                            secRemainingTextView.setText(String.format("0%s", sec));
                        } else {
                            secRemainingTextView.setText(String.format("%s", sec));
                        }
                        if (min < 10) {
                            minRemainingTextView.setText(String.format("0%s", min));
                        } else {
                            minRemainingTextView.setText(String.format("%s", min));
                        }

                    } else {
                        Toast.makeText(getActivity(), "Timer ended", Toast.LENGTH_LONG).show();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container_frame_layout, new SetTimerFragment()).commit();
                        pref.edit().putLong(DESTINATION_TIME_KEY, Long.parseLong("-1")).apply();
                        startActivity(new Intent(getActivity(), TimerEndActivity.class));
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        handler.postDelayed(runnable, 1 * 1000);
    }

}
