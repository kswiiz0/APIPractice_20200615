package com.phis.apipractice_20200615

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.phis.apipractice_20200615.utils.ContextUtil
import com.phis.apipractice_20200615.utils.ServerUtil
import org.json.JSONObject

class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setValues()
        setupEvent()

    }


    override fun setValues() {


        Handler().postDelayed({
            val isAutoLogin = ContextUtil.isAutoLogin(mContext)
            val token = ContextUtil.getUserToken(mContext)

            if (isAutoLogin && token != "") {

                ServerUtil.getRequestUserInfo(mContext, object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {

                        val code = json.getInt("code")
                        if (code == 200) {

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)
                            finish()

                        } else {
                            val myIntent = Intent(mContext, LoginActivity::class.java)
                            startActivity(myIntent)
                            finish()


                        }
                    }
                })
            } else {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()

            }
        }, 2000)


    }

    override fun setupEvent() {
    }
}