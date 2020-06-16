package com.phis.apipractice_20200615.datas

import org.json.JSONObject

class Topic {

    var id = 0
    var title = ""
    var imageUrl = ""


    companion object {
        fun getTopicFromJson(json: JSONObject): Topic {
/*
            "id":2,
            "title":"민트는 음식인가 아닌가",
            "img_url":"https:\/\/t1.daumcdn.net\/cfile\/tistory\/217A9546576F76690C",s
*/

            val t = Topic()
            t.id = json.getInt("id")
            t.title = json.getString("title")
            t.imageUrl = json.getString("img_url")

            return t
        }
    }

}