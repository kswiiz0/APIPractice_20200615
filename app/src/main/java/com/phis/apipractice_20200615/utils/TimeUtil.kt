package com.phis.apipractice_20200615.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtil {

    companion object {
        // 표시 시간을 재료로 주면 상황에 맞는 String으로 반환
        fun getTimeAgoFromCalendar(datetime: Calendar): String {
            /*
            10초이내 : 방금전
            1분이내 : 몇초전
            1시간이내 : 몇분전
            12이간이내 : 몇시간전
            그이상 : 몇년몇원몇일 오전/오후 몇시몇분
             */
            val now = Calendar.getInstance()
            //현재시간 - datetime > 밀리세컨즈 단위로 차이를 계산
            val msDiff = now.timeInMillis - datetime.timeInMillis
            if (msDiff < 10 * 1000) {
                return "방금전"
            } else if (msDiff < 1 * 60 * 1000) {
                return "${msDiff / 1000}초전"

            } else if (msDiff < 1 * 60 * 60 * 1000) {
                return "${msDiff / 1000 / 60}분전"

            } else if (msDiff < 12 * 60 * 60 * 1000) {
                return "${msDiff / 1000 / 60 / 60}시간전"
            } else {
                val sdf = SimpleDateFormat("yyyy년 M월 d일 a h시 m분")
                return sdf.format(datetime.time)
            }
        }
    }
}