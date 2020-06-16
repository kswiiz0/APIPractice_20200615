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

        var isEmailOk = false
        var isNickNameOk = false

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
                                isEmailOk = true
                                Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                                emailCheckResultTxt.text = "사용해도 좋습니다."
                            } else {
                                isEmailOk = false
                                emailCheckResultTxt.text = "사용할 수 없는 email입니다."
                            }
                        }


                    }
                })
        }


        nickNameBtn.setOnClickListener {
            val nickName = nickNameEdt.text.toString()

            ServerUtil.getRequestDuplicatedCheck(
                mContext,
                "NICK_NAME",
                nickName,
                object : ServerUtil.JsonResponseHandler {

                    override fun onResponse(json: JSONObject) {
                        val code = json.getInt("code")
                        val message = json.getString("message")
                        runOnUiThread {
                            if (code == 200) {
                                Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                                nickNameCheckResultTxt.text = "사용해도 좋습니다."
                                isNickNameOk = true


                            } else {
                                Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                                nickNameCheckResultTxt.text = "사용할 수 없는 닉네임입니다."
                                isNickNameOk = false
                            }
                        }
                    }
                })
        }
        signupBtn.setOnClickListener {

            if ( !isEmailOk){
                Toast.makeText(mContext, "이메일 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if ( !isNickNameOk){
                Toast.makeText(mContext, "닉네임 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val password = passwordEdt.text.toString().trim()
            if ( password.length < 8 ){
                Toast.makeText(mContext, "비밀번호는 8자리 이상이어야합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val email = emailEdt.text.toString()
            val nickname = nickNameEdt.text.toString()


            ServerUtil.putRequestSignUp(
                mContext,
                email,
                password,
                nickname,
                object : ServerUtil.JsonResponseHandler {

                    override fun onResponse(json: JSONObject) {
                        val code = json.getInt("code")
                        val message = json.getString("message")
                        runOnUiThread {
                            if (code == 200) {
                                Toast.makeText(mContext, "회원가입성공!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(mContext, "회원가입실패!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })



        }


    }

}
