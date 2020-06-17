package com.phis.apipractice_20200615.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException


class ServerUtil {

    //java의 static에 대응되는 개념.
    companion object {
        //host address/ip
        val BASE_URL = "http://15.165.177.142"

        //로그인 기능을 요청하는 post로 요청하는 함수
        fun postRequestLogin(
            context: Context,
            email: String,
            pw: String,
            handler: JsonResponseHandler?
        ) {
            val client = OkHttpClient()
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()
//                .header() API에서 헤더를 요구하면 여기서 첨부
            val request = Request.Builder().url(urlString).post(formData).build()



            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    //연결 자체에 실패한 경우

                }

                override fun onResponse(call: Call, response: Response) {
                    //정상적인 응답의 경우
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())

                    handler?.onResponse(json)
                }
            })
        }

        //중복체크를 get으로 요청하는 함수
        fun getRequestDuplicatedCheck(
            context: Context,
            type: String,
            input: String,
            handler: JsonResponseHandler?
        ) {
            val client = OkHttpClient()

            //가공된 주소를 가지고 newBuilder로 변환 (파라미터 첨부 준비)
            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("type", type)
                .addEncodedQueryParameter("value", input)
            val urlString = urlBuilder.build().toString()
            val request = Request.Builder().url(urlString).get().build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    //연결 자체에 실패한 경우

                }

                override fun onResponse(call: Call, response: Response) {
                    //정상적인 응답의 경우
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())

                    handler?.onResponse(json)
                }
            })


        }

        fun putRequestSignUp(
            context: Context,
            email: String,
            password: String,
            nick_name: String,
            handler: JsonResponseHandler?
        ) {

            val client = OkHttpClient()
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("nick_name", nick_name)
                .build()

            val request = Request.Builder().url(urlString).put(formData).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())

                    handler?.onResponse(json)
                }
            })
        }


        fun getRequestUserInfo(
            context: Context,
            handler: JsonResponseHandler?
        ) {
            val client = OkHttpClient()

            val urlBuilder = "${BASE_URL}/user_info".toHttpUrlOrNull()!!.newBuilder()

            val urlString = urlBuilder.build().toString()
            val request =
                Request.Builder()
                    .url(urlString)
                    .get()
                    .header("X-Http-Token", ContextUtil.getUserToken(context))
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    //정상적인 응답의 경우
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())

                    handler?.onResponse(json)
                }
            })


        }


        fun getRequestMainInfo(
            context: Context,
            handler: JsonResponseHandler?
        ) {
            val client = OkHttpClient()

            val urlBuilder = "${BASE_URL}/v2/main_info".toHttpUrlOrNull()!!.newBuilder()

            val urlString = urlBuilder.build().toString()
            val request =
                Request.Builder()
                    .url(urlString)
                    .get()
                    .header("X-Http-Token", ContextUtil.getUserToken(context))
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    //정상적인 응답의 경우
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())

                    handler?.onResponse(json)
                }
            })


        }


        fun getRequestTopicDetail(
            context: Context,
            topicId: Int,
            handler: JsonResponseHandler?
        ) {
            val client = OkHttpClient()

            val urlBuilder = "${BASE_URL}/topic/${topicId}".toHttpUrlOrNull()!!.newBuilder()

            val urlString = urlBuilder.build().toString()
            val request =
                Request.Builder()
                    .url(urlString)
                    .get()
                    .header("X-Http-Token", ContextUtil.getUserToken(context))
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    //정상적인 응답의 경우
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)
                    Log.d("JSON응답", json.toString())

                    handler?.onResponse(json)
                }
            })


        }


    }

    //서버 통신의 응답 내용을 Activity에 전달해주는 인터페이스
    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

}