package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dell.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import application.example.com.androidlibrary.JokeActivity;


/**
 * Created by Dell on 30-08-2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Void,Void,String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar mProgressBar;
    InterstitialAd mInterstitilAd;

    public EndpointsAsyncTask(Context context, ProgressBar progressBar){

        this.context = context;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mProgressBar!=null) mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService==null){
            MyApi.Builder builder=new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://build-itbigger.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                    request.setDisableGZipContent(true);
                        }
                    });
            myApiService=builder.build();

        }
        try {
            return myApiService.getJokes().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        final String mResult=result;
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        mInterstitilAd=new InterstitialAd(context);
        mInterstitilAd.setAdUnitId("ca-app-pub-3126450236430335/1129759558");
        mInterstitilAd.setAdListener(new AdListener(){

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }

                mInterstitilAd.show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (mProgressBar != null) {
                    mProgressBar.setVisibility(View.GONE);
                }
                Intent intent = new Intent(context, JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, mResult);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Intent intent = new Intent(context, JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, mResult);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
             });
        AdRequest adRequest=new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitilAd.loadAd(adRequest);


    }


    }

