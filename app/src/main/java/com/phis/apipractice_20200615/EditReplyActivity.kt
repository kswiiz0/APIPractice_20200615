package com.phis.apipractice_20200615

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_edit_reply.*
import org.json.JSONObject

class EditReplyActivity : BaseActivity() {

    var mTopicId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reply)

        setValues()
        setupEvent()
    }

    override fun setValues() {
        mTopicId = intent.getIntExtra("topicId", -1)
        topicTitleTxt.text = intent.getStringExtra("topicTitle")
        mySideTitleTxt.text = intent.getStringExtra("mySideTitle")


    }

    override fun setupEvent() {

        regReplyBtn.setOnClickListener {
            val myReply = myReplyEdt.text.toString().trim()
            if (myReply.length < 5) {
                Toast.makeText(mContext, "의견이 최소 5자는 되어야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("의견등록")
            alert.setMessage("정말 의견을 등록하시겠습니까?\n한번 의견을 등록하면 투료를 변경할 수 없고,\n내용을 수정할수없습니다")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
               ServerUtil.postRequestTopicReply(
                    mContext,
                    mTopicId,
                    myReply,
                    0,
                    object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(json: JSONObject) {
                            var code = json.getInt("code")
                            if (code == 200) {
                                finish()

                            } else {

                                runOnUiThread {
                                    Toast.makeText(mContext, "의견 남기기 실패!", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }
                    })
            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }


    }
}