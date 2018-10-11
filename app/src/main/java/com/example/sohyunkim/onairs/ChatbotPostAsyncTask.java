package com.example.sohyunkim.onairs;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.sohyunkim.onairs.model.OnEarsMessageInputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModelResponse;

public class ChatbotPostAsyncTask extends AsyncTask<OnEarsMessageInputModel,Integer, OnEarsMessageOutputModel> {


    @Override
    protected OnEarsMessageOutputModel doInBackground(OnEarsMessageInputModel... onEarsMessageInputModels) {
        ApiClientFactory clientFactory = new ApiClientFactory();
        final OnEarsClient onEarsClient = clientFactory.build(OnEarsClient.class);
        OnEarsMessageInputModel inputModel = onEarsMessageInputModels[0];

        return onEarsClient.chatbotPost(inputModel);
    }
}
