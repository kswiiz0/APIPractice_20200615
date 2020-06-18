package com.phis.apipractice_20200615.datas

import org.json.JSONObject

class Topic {

    var id = 0
    var title = ""
    var imageUrl = ""
    var sides = ""


    companion object {
        fun getTopicFromJson(json: JSONObject): Topic {
/*
            topic":{
                 "id":2,
                 "title":"민트는 음식인가 아닌가",
                 "img_url":"https://t1.daumcdn.net/cfile/tistory/217A9546576F76690C",
                 "start_date":"2020-05-10 00:00:00",
                 "end_date":"2021-05-16 23:59:59",
                 "sides":[],
                 "my_side_id":-1,
                 "replies":[],
                 "reply_count":13
            }
*/

            val t = Topic()
            t.id = json.getInt("id")
            t.title = json.getString("title")
            t.imageUrl = json.getString("img_url")

            val sides = json.getJSONObject("sides")
            for (i in 0..sides.length() - 1) {
                
            }



            return t
        }
    }

}