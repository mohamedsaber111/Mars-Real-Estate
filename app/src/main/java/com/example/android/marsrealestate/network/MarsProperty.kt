/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import android.os.Parcelable
import androidx.lifecycle.LiveData
import com.example.android.marsrealestate.overview.MarsApiStatus
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Gets Mars real estate property information from the Mars API Retrofit service and updates the
 * [MarsProperty] and [MarsApiStatus] [LiveData]. The Retrofit service returns a coroutine
 * Deferred, which we await to get the result of the transaction.
 * @param filter the [MarsApiFilter] that is sent as part of the web server request
 */
//moshi needs to have a class so that it can use to store the parts to JSON result
/*
when converting the parse JSON response to kotlin object Moshi will match the property name from marsProperty()
class with a property names from the JSON response
 */
//id , imgSrcUrl, type are String because in like (JSON) those values in double cot("")

//those JSON name (id,img_src,type,price) may make confusing kotlin properties or not match with our code style
// to use property name different from the attribute in JSON you use (@Json) annotation on property
@Parcelize
data class MarsProperty(
        val id: String,
        // used to map img_src from the JSON to imgSrcUrl in our class
        @Json(name = "img_src") val imgSrcUrl: String,
        val type: String,
        val price: Double) : Parcelable {
    // we add isRent() which return type equal rent
    val isRental
        get() = type == "rent"

    //complex object can be store in a parcel and implement by :Parcelable interface and they becomes parcelable objects
    // using Parcelable to share object between processes
}
