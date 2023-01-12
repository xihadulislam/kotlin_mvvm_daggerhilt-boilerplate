package com.xihadulislam.kotlin_mvvm_daggerhiltboilerplate.utils

object EndPoints {

    // Dev
    const val NODE_BASE_URL = "https://dummyjson.com/"

    /**
     * End Points
     */
    private const val NODE_API_LIMIT = 800
    const val FETCH_PRODUCTS_API = "products"

    /**
     * Header part
     */

    const val SERVER_AUTH_HEADER = "Bearer"
    const val SERVER_JSON_HEADER = "application/json"
    const val SERVER_FORM_HEADER = "application/x-www-form-urlencoded"
    const val SERVER_MULTIPART_HEADER = "multipart/form-data"

    /**
     * Token Related
     */
    const val AUTH_TOKEN = "AUTH_TOKEN"
    const val AUTH_CONTENT_TYPE = "AUTH_CONTENT_TYPE"
    const val TIME_OUT = 5000L


}

