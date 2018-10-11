package com.example.sohyunkim.onairs;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.sohyunkim.onairs.model.OnEarsFirstMessageInputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModel;



public class ConnectPostAsyncTask extends AsyncTask<OnEarsFirstMessageInputModel, Integer, OnEarsMessageOutputModel> {

    @Override
    protected OnEarsMessageOutputModel doInBackground(OnEarsFirstMessageInputModel... onEarsMessageInputModels) {
        ApiClientFactory clientFactory = new ApiClientFactory();
        final OnEarsClient onEarsClient = clientFactory.build(OnEarsClient.class);
        OnEarsFirstMessageInputModel inputModel = onEarsMessageInputModels[0];

        return onEarsClient.connectPost(inputModel);
    }
}
