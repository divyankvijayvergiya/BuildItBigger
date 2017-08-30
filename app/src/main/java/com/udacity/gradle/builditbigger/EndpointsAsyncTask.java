package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.dell.myapplication.backend.myApi.MyApi;


/**
 * Created by Dell on 30-08-2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Void,Void,Void> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar mProgressBar;

    public EndpointsAsyncTask(Context context, ProgressBar progressBar){

        this.context = context;
        this.mProgressBar = progressBar;
    }
    @Override
    protected Void doInBackground(Void... params) {
        if(myApiService==null){
            MyApi.Builder builder=new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
        }
        return null;
    }
}
