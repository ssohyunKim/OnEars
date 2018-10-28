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


public class MessageDocumentData {
    @com.google.gson.annotations.SerializedName("text")
    private String text = null;
    @com.google.gson.annotations.SerializedName("korSummary")
    private String korSummary = null;
    @com.google.gson.annotations.SerializedName("enSummary")
    private String enSummary = null;
    @com.google.gson.annotations.SerializedName("korAudioUrl")
    private String korAudioUrl = null;
    @com.google.gson.annotations.SerializedName("enAudioUrl")
    private String enAudioUrl = null;

    /**
     * Gets text
     *
     * @return text
     **/
    public String getText() {
        return text;
    }

    /**
     * Sets the value of text.
     *
     * @param text the new value
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets korSummary
     *
     * @return korSummary
     **/
    public String getKorSummary() {
        return korSummary;
    }

    /**
     * Sets the value of korSummary.
     *
     * @param korSummary the new value
     */
    public void setKorSummary(String korSummary) {
        this.korSummary = korSummary;
    }

    /**
     * Gets enSummary
     *
     * @return enSummary
     **/
    public String getEnSummary() {
        return enSummary;
    }

    /**
     * Sets the value of enSummary.
     *
     * @param enSummary the new value
     */
    public void setEnSummary(String enSummary) {
        this.enSummary = enSummary;
    }

    /**
     * Gets korAudioUrl
     *
     * @return korAudioUrl
     **/
    public String getKorAudioUrl() {
        return korAudioUrl;
    }

    /**
     * Sets the value of korAudioUrl.
     *
     * @param korAudioUrl the new value
     */
    public void setKorAudioUrl(String korAudioUrl) {
        this.korAudioUrl = korAudioUrl;
    }

    /**
     * Gets enAudioUrl
     *
     * @return enAudioUrl
     **/
    public String getEnAudioUrl() {
        return enAudioUrl;
    }

    /**
     * Sets the value of enAudioUrl.
     *
     * @param enAudioUrl the new value
     */
    public void setEnAudioUrl(String enAudioUrl) {
        this.enAudioUrl = enAudioUrl;
    }

}
