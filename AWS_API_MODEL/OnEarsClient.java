/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.sohyunkim.onairs;

import java.util.*;

import com.example.sohyunkim.onairs.model.Input;
import com.example.sohyunkim.onairs.model.Output;
import com.example.sohyunkim.onairs.model.Empty;
import com.example.sohyunkim.onairs.model.UserIdInput;
import com.example.sohyunkim.onairs.model.UserInput;
import com.example.sohyunkim.onairs.model.MessageList;


@com.amazonaws.mobileconnectors.apigateway.annotation.Service(endpoint = "https://lu8da3cuo2.execute-api.ap-northeast-2.amazonaws.com/version2")
public interface OnEarsClient {


    /**
     * A generic invoker to invoke any API Gateway endpoint.
     * @param request
     * @return ApiResponse
     */
    com.amazonaws.mobileconnectors.apigateway.ApiResponse execute(com.amazonaws.mobileconnectors.apigateway.ApiRequest request);
    
    /**
     * 
     * 
     * @param body 
     * @return Output
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/chatbot", method = "POST")
    Output chatbotPost(
            Input body);
    
    /**
     * 
     * 
     * @param body 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/chatbot", method = "DELETE")
    Empty chatbotDelete(
            UserIdInput body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/chatbot", method = "OPTIONS")
    Empty chatbotOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return Output
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/connect", method = "POST")
    Output connectPost(
            UserInput body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/connect", method = "OPTIONS")
    Empty connectOptions();
    
    /**
     * 
     * 
     * @param body 
     * @return MessageList
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/history", method = "POST")
    MessageList historyPost(
            UserIdInput body);
    
    /**
     * 
     * 
     * @return Empty
     */
    @com.amazonaws.mobileconnectors.apigateway.annotation.Operation(path = "/history", method = "OPTIONS")
    Empty historyOptions();
    
}

