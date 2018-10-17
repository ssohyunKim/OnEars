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

import com.example.sohyunkim.onairs.model.MessageDocumentData;

public class Message {
    @com.google.gson.annotations.SerializedName("data")
    private String data = null;
    @com.google.gson.annotations.SerializedName("date")
    private String date = null;
    @com.google.gson.annotations.SerializedName("audioUrl")
    private String audioUrl = null;
    @com.google.gson.annotations.SerializedName("documentUrl")
    private String documentUrl = null;
    @com.google.gson.annotations.SerializedName("documentData")
    private MessageDocumentData documentData = null;

    /**
     * Gets data
     *
     * @return data
     **/
    public String getData() {
        return data;
    }

    /**
     * Sets the value of data.
     *
     * @param data the new value
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Gets date
     *
     * @return date
     **/
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of date.
     *
     * @param date the new value
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets audioUrl
     *
     * @return audioUrl
     **/
    public String getAudioUrl() {
        return audioUrl;
    }

    /**
     * Sets the value of audioUrl.
     *
     * @param audioUrl the new value
     */
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    /**
     * Gets documentUrl
     *
     * @return documentUrl
     **/
    public String getDocumentUrl() {
        return documentUrl;
    }

    /**
     * Sets the value of documentUrl.
     *
     * @param documentUrl the new value
     */
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    /**
     * Gets documentData
     *
     * @return documentData
     **/
    public MessageDocumentData getDocumentData() {
        return documentData;
    }

    /**
     * Sets the value of documentData.
     *
     * @param documentData the new value
     */
    public void setDocumentData(MessageDocumentData documentData) {
        this.documentData = documentData;
    }

}
