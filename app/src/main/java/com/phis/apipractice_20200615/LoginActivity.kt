package com.phis.apipractice_20200615

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.phis.apipractice_20200615.utils.ContextUtil
import com.phis.apipractice_20200615.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setValues()
        setupEvent()
    }


    override fun setValues() {

    }

    override fun setupEvent() {

        loginBtn.setOnClickListener {
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()

            ServerUtil.postRequestLogin(
                mContext,
                email,
                password,
                object : ServerUtil.JsonResponseHandler{
                    override fun onResponse(json: JSONObject) {
                        val codeNumber = json.getInt("code")
                        if ( codeNumber == 200){
                            //로그인 성공
                            val data = json.getJSONObject("data")
                            val token = data.getString("token")

                            ContextUtil.setUserToken(mContext,token)

                        }else{
                            //백그라운드 쓰레드는 UI를 직접적으로 컨트롤 할수 없다.
                            runOnUiThread {
                                //로그인 실패
                                Toast.makeText(mContext,"${json.getString("message")}",Toast.LENGTH_SHORT).show()
                            }



                        }
                    }

                }
            )

        }

        signupBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }



    }

}
