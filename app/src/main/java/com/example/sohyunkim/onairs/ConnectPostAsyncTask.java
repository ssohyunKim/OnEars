package com.example.sohyunkim.onairs;

import android.content.Context;
import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.sohyunkim.onairs.model.Output;
import com.example.sohyunkim.onairs.model.UserInput;


public class ConnectPostAsyncTask extends AsyncTask<UserInput, Integer, Output> {
    private OnEventListener<Output> mCallBack;
    private Context mContext;
    private final OnEarsClient onEarsClient;
    public Exception mException;
    public ConnectPostAsyncTask(Context context, OnEventListener callback) {
        ApiClientFactory clientFatory = new ApiClientFactory();
        onEarsClient = clientFatory.build(OnEarsClient.class);
        mCallBack = callback;
        mContext = context;
    }
    @Override
    protected Output doInBackground(UserInput ... UserInputs) {
        try{
            UserInput userInput = UserInputs[0];
            return onEarsClient.connectPost(userInput);
        }catch(Exception e){
            mException = e;
        }
        return null;
    }
    @Override
    protected void onPostExecute(Output result) {
        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess(result);
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}
