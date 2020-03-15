package com.goldengoals.roomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_get_by_name.*

class GetByNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_by_name)

        btnSearch.setOnClickListener {
            var replayIntent = Intent()

            if (TextUtils.isEmpty(txtGetByName.text)) {
                txtGetByName.error = "Enter Product Name"

                setResult(Activity.RESULT_CANCELED, replayIntent)
            } else {
                var name=txtGetByName.text.toString()
                replayIntent.putExtra(GetByNameActivity.EXTRA_REPLY,name)
                setResult(Activity.RESULT_OK,replayIntent)
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_REPLY = "REPLY_DATA"
    }
}
