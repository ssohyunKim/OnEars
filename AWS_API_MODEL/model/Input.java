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

package com.example.sohyunkim.onairs.model;

import com.example.sohyunkim.onairs.model.InputMessage;
import com.example.sohyunkim.onairs.model.State;

public class Input {
    @com.google.gson.annotations.SerializedName("userId")
    private String userId = null;
    @com.google.gson.annotations.SerializedName("state")
    private State state = null;
    @com.google.gson.annotations.SerializedName("message")
    private InputMessage message = null;

    /**
     * Gets userId
     *
     * @return userId
     **/
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of userId.
     *
     * @param userId the new value
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets state
     *
     * @return state
     **/
    public State getState() {
        return state;
    }

    /**
     * Sets the value of state.
     *
     * @param state the new value
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Gets message
     *
     * @return message
     **/
    public InputMessage getMessage() {
        return message;
    }

    /**
     * Sets the value of message.
     *
     * @param message the new value
     */
    public void setMessage(InputMessage message) {
        this.message = message;
    }

}
