package com.phis.apipractice_20200615.datas

import org.json.JSONObject

class TopicSide {
/*
    "id": 5,
    "topic_id": 2,
    "title": "음식이 맞다",
    "vote_count": 9,
    "best_reply": null
*/

    var id = 0
    var topicId = 0
    var title = ""
    var voteCount = 0


    companion object {
        fun getTopicSideFromJson(json: JSONObject): TopicSide {
            var ts = TopicSide()
            ts.id = json.getInt("id")
            ts.topicId = json.getInt("topic_id")
            ts.title = json.getString("title")
            ts.voteCount = json.getInt("vote_count")



            return ts
        }
    }


}