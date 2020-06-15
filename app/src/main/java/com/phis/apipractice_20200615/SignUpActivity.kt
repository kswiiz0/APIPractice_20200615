package com.phis.apipractice_20200615

import androidx.appcompat.app.AppCompatActivity
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


    }

}
