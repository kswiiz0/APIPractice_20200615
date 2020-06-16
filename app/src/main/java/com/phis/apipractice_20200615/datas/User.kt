package com.phis.apipractice_20200615.datas

import org.json.JSONObject

class User {

    companion object {
        fun getUserFromJson(json: JSONObject): User {

            val u = User()

//            val data = jsonObject.getJSONObject("data")
//            val user = data.getJSONObject("user")
            u.id = json.getInt("id")
            u.email = json.getString("email")
            u.nickname = json.getString("nick_name")

            return u
        }
    }

    var id: Int = 0
    var email: String = ""
    var nickname: String = ""


}