package com.phis.apipractice_20200615.datas

import org.json.JSONObject

class TopicReply {
/*
    "id":124,
    "content":"ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㄹ",
    "topic_id":2,
    "side_id":6,
    "user_id":49,
    */

    var id = 0
    var content = ""
    var topicId = 0
    var sideId = 0
    var userId = -1
    lateinit var user:User




    companion object{

        fun getTopicReplyFromJson(json:JSONObject) : TopicReply{
            val tr = TopicReply()
            tr.id = json.getInt("id")
            tr.content = json.getString("content")
            tr.topicId = json.getInt("topic_id")
            tr.sideId = json.getInt("side_id")
            tr.userId = json.getInt("user_id")

            tr.user = User.getUserFromJson(json.getJSONObject("user"))




            return tr
        }
    }

}