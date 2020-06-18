package com.phis.apipractice_20200615.datas

import org.json.JSONObject

class Topic {

    var id = 0
    var title = ""
    var imageUrl = ""
    val sideList = ArrayList<TopicSide>()
    var mySideId = 0
    var mySelectedSideIndex = -1



    val replyList = ArrayList<TopicReply>()


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
            t.mySideId = json.getInt("my_side_id")


            val sides = json.getJSONArray("sides")

            for (i in 0..sides.length() - 1) {
                val side = sides.getJSONObject(i)

                val topicSide = TopicSide.getTopicSideFromJson(side)
                t.sideList.add(topicSide)

                if ( topicSide.id == t.mySideId){
                    t.mySelectedSideIndex = i
                }
            }

            if ( t.mySideId == -1 ){
                t.mySelectedSideIndex = -1
            }



            val replyes = json.getJSONArray("replies")
            for ( i in 0..replyes.length()-1){
                t.replyList.add(TopicReply.getTopicReplyFromJson(replyes.getJSONObject(i)))
            }


            return t
        }
    }

}