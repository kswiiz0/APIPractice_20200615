package com.phis.apipractice_20200615.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

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
    lateinit var writer: User
    var createAt = Calendar.getInstance()


    var replyCount = 0
    var likeCount = 0
    var dislikeCount = 0


    lateinit var selectedSide : TopicSide


    companion object {

        fun getTopicReplyFromJson(json: JSONObject): TopicReply {
            val tr = TopicReply()
            tr.id = json.getInt("id")
            tr.content = json.getString("content")
            tr.topicId = json.getInt("topic_id")
            tr.sideId = json.getInt("side_id")
            tr.userId = json.getInt("user_id")
            tr.writer = User.getUserFromJson(json.getJSONObject("user"))
//            tr. = User.getUserFromJson(json.getJSONObject("user"))


            val createAtStr = json.getString("created_at")
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            tr.createAt.time = sdf.parse(createAtStr)!!

            //시차보정
            val myPhoneTimeZone = tr.createAt.timeZone
            val timeOffset = myPhoneTimeZone.rawOffset / 1000 / 60 / 60
            tr.createAt.add(Calendar.HOUR, timeOffset)

            tr.replyCount = json.getInt("reply_count")
            tr.likeCount = json.getInt("like_count")
            tr.dislikeCount = json.getInt("dislike_count")

            tr.selectedSide = TopicSide.getTopicSideFromJson(json.getJSONObject("selected_side"))



            return tr
        }
    }

}