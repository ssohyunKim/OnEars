package com.example.sohyunkim.onairs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.sohyunkim.onairs.model.Input;
import com.example.sohyunkim.onairs.model.InputMessage;
import com.example.sohyunkim.onairs.model.Output;
import com.google.gson.Gson;

public class ChatbotPostAsyncTask extends AsyncTask<Input,Integer, Output> {
    private OnEventListener<Output> mCallBack;
    private Context     mContext;
    private final OnEarsClient onEarsClient;
    public Exception mException;
    public ChatbotPostAsyncTask(Context context, OnEventListener callback) {
        ApiClientFactory clientFatory = new ApiClientFactory();
        onEarsClient = clientFatory.build(OnEarsClient.class);
        mCallBack = callback;
        mContext = context;
    }
    @Override
    protected Output doInBackground(Input... inputs) {
        try{
            Input input = inputs[0];
            Gson gson = new Gson();
            String json =  gson.toJson(input);
            return onEarsClient.chatbotPost(input);
        }catch(Exception e){
            mException  = e;
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
