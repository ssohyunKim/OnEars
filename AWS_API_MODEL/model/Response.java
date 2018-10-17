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

import com.example.sohyunkim.onairs.model.Message;
import com.example.sohyunkim.onairs.model.State;

public class Response {
    @com.google.gson.annotations.SerializedName("message")
    private Message message = null;
    @com.google.gson.annotations.SerializedName("state")
    private State state = null;

    /**
     * Gets message
     *
     * @return message
     **/
    public Message getMessage() {
        return message;
    }

    /**
     * Sets the value of message.
     *
     * @param message the new value
     */
    public void setMessage(Message message) {
        this.message = message;
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

}
