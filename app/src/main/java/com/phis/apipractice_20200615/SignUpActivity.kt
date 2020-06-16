package com.phis.apipractice_20200615

import android.os.Bundle
import android.widget.Toast
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setValues()
        setupEvent()

    }

    override fun setValues() {

    }

    override fun setupEvent() {

        emailCheckBtn.setOnClickListener {
            val email = emailEdt.text.toString()

            ServerUtil.getRequestDuplicatedCheck(
                mContext,
                "EMAIL",
                email,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        val code = json.getInt("code")
                        val message = json.getString("message")
                        runOnUiThread {
                            if (code == 200) {

                                Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()


                            } else {

                            }
                        }


                    }
                })
        }


        nickNameBtn.setOnClickListener {
            val nickName = nickNameEdt.text.toString()

            ServerUtil.getRequestDuplicatedCheck(mContext,"NICK_NAME",nickName, object :ServerUtil.JsonResponseHandler{

                override fun onResponse(json: JSONObject) {
                    val code = json.getInt("code")
                    val message = json.getString("message")
                    runOnUiThread {
                        if (code == 200) {
                            Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                            nickNameCheckResultTxt.text = "사용해도 좋습니다."


                        } else {
                            Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                            nickNameCheckResultTxt.text = "사용할 수 없는 닉네임입니다."
                        }
                    }
                }


            } )


        }


    }

}
