package com.goldengoals.roomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.roomwithviewhw.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        btnSave.setOnClickListener {
            var replayIntent = Intent()

            if (TextUtils.isEmpty(txtID.text) ||
                TextUtils.isEmpty(txtName.text) ||
                TextUtils.isEmpty(txtPrice.text) ||
                TextUtils.isEmpty(txtQuantity.text))
            {
                if (TextUtils.isEmpty(txtID.text)){ txtID.error = "Please fill word" }
                if (TextUtils.isEmpty(txtName.text)) { txtName.error = "Please fill word" }
                if (TextUtils.isEmpty(txtPrice.text)) { txtPrice.error = "Please fill word" }
                if (TextUtils.isEmpty(txtQuantity.text)) { txtQuantity.error = "Please fill word" }

                setResult(Activity.RESULT_CANCELED,replayIntent)
            } else {
                var product: Array<String> = arrayOf(txtID.text.toString(),
                    txtName.text.toString(),
                    txtPrice.text.toString(),
                    txtQuantity.text.toString())
                replayIntent.putExtra(AddActivity.EXTRA_REPLY,product)
                setResult(Activity.RESULT_OK,replayIntent)
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_REPLY = "REPLY_DATA"
    }
}
