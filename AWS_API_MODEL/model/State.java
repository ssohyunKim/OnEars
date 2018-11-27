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


public class State {
    @com.google.gson.annotations.SerializedName("depth")
    private Integer depth = null;
    @com.google.gson.annotations.SerializedName("mainCategory")
    private String mainCategory = null;
    @com.google.gson.annotations.SerializedName("subCategory")
    private String subCategory = null;
    @com.google.gson.annotations.SerializedName("title")
    private String title = null;
    @com.google.gson.annotations.SerializedName("url")
    private String url = null;

    /**
     * Gets depth
     *
     * @return depth
     **/
    public Integer getDepth() {
        return depth;
    }

    /**
     * Sets the value of depth.
     *
     * @param depth the new value
     */
    public void setDepth(Integer depth) {
        this.depth = depth;
    }


    /**
     * Gets mainCategory
     *
     * @return mainCategory
     **/
    public String getMainCategory() {
        return mainCategory;
    }

    /**
     * Sets the value of mainCategory.
     *
     * @param mainCategory the new value
     */
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    /**
     * Gets subCategory
     *
     * @return subCategory
     **/
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Sets the value of subCategory.
     *
     * @param subCategory the new value
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * Gets title
     *
     * @return title
     **/
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of title.
     *
     * @param title the new value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets url
     *
     * @return url
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of url.
     *
     * @param url the new value
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
