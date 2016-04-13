package com.example.admin.w3d2volleyhelloworld;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by admin on 4/13/2016.
 */
public class WebTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "TAG_"
            ;
    private TextView mTextView;
    private MainActivity mMainActivity;

    private String webStr;

    public WebTask(TextView mWv, MainActivity mMa){
        mTextView = mWv;
        mMainActivity = mMa;
    }
    @Override
    protected Void doInBackground(Void... params) {
        doVolley();
        return null;

    }

    private void doVolley(){
        RequestQueue queue = Volley.newRequestQueue(mMainActivity);
        String url = "http://api.duckduckgo.com/?q=dragon+ball+characters&format=json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        webStr = response.substring(0, 50);
                        Log.d(TAG, "onResponse: " + webStr);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onResponse: " + "Failed");
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        mTextView.setText(webStr);
    }
}
