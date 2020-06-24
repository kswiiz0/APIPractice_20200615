package com.phis.apipractice_20200615

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
        setTitle("회원가입")

    }

    override fun setupEvent() {

        var isEmailOk = false
        var isNickNameOk = false

        emailEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            //문구가 바꼈을때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("바뀐이메일", s.toString())
                emailCheckResultTxt.text = "이메일 중복검사를 해주세요."
                isEmailOk = false
            }

        })

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


        nickNameEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            //문구가 바꼈을때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("바뀐닉네임", s.toString())
                nickNameCheckResultTxt.text = "닉네임 중복검사를 해주세요."
                isNickNameOk = false
            }

        })


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

            if (!isEmailOk) {
                Toast.makeText(mContext, "이메일 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isNickNameOk) {
                Toast.makeText(mContext, "닉네임 중복검사를 통과해야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val password = passwordEdt.text.toString().trim()
            if (password.length < 8) {
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
