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


public class UserInput {
    @com.google.gson.annotations.SerializedName("userId")
    private String userId = null;
    @com.google.gson.annotations.SerializedName("age")
    private String age = null;
    @com.google.gson.annotations.SerializedName("gender")
    private String gender = null;
    @com.google.gson.annotations.SerializedName("concern")
    private String concern = null;

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
     * Gets age
     *
     * @return age
     **/
    public String getAge() {
        return age;
    }

    /**
     * Sets the value of age.
     *
     * @param age the new value
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Gets gender
     *
     * @return gender
     **/
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of gender.
     *
     * @param gender the new value
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets concern
     *
     * @return concern
     **/
    public String getConcern() {
        return concern;
    }

    /**
     * Sets the value of concern.
     *
     * @param concern the new value
     */
    public void setConcern(String concern) {
        this.concern = concern;
    }

}
