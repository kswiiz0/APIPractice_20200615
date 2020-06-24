package com.phis.apipractice_20200615.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Notification {

    var id = 0
    var receiveUserId = 0
    var actUserId = 0
    var title = ""
    var type = ""
    var message = ""
    var referenceUi = ""
    var focusObjectId = 0

    var createAt = Calendar.getInstance()

    companion object {
        fun getNotificationFromJson(json: JSONObject): Notification {
            val noti = Notification()

            noti.id = json.getInt("id")
            noti.receiveUserId = json.getInt("receive_user_id")
            noti.actUserId = json.getInt("act_user_id")
            noti.title = json.getString("title")
            noti.type = json.getString("type")
            noti.message = json.getString("message")
            noti.referenceUi = json.getString("reference_ui")
            noti.focusObjectId = json.getInt("focus_object_id")

            //서버의 String 시간 > Kotlin Date 으로 반영.
            val createdAt = json.getString("created_at")
            val parsingFormat = SimpleDateFormat("YYYY-MM-DD HH:mm:ss")

            noti.createAt.time = parsingFormat.parse(createdAt)

            //시차보정
            val myPhoneTimeZone = noti.createAt.timeZone
            val timeOffset = myPhoneTimeZone.rawOffset / 1000 / 60 / 60
            noti.createAt.add(Calendar.HOUR, timeOffset)





            return noti
        }
    }
}