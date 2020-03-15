package com.goldengoals.roomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_delete_by_id.*

class DeleteByIdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_by_id)


        btnDelete.setOnClickListener {
            var replayIntent = Intent()

            if (TextUtils.isEmpty(txtDeleteID.text)) {
                txtDeleteID.error = "Enter Product Id"

                setResult(Activity.RESULT_CANCELED, replayIntent)
            } else {
                var delId=txtDeleteID.text.toString()
                replayIntent.putExtra(DeleteByIdActivity.EXTRA_REPLY,delId)
                setResult(Activity.RESULT_OK,replayIntent)
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_REPLY = "REPLY_DATA"
    }
}
