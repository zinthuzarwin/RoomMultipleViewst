package com.goldengoals.roomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_get_by_price_range.*
import java.util.*

class GetByPriceRangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_by_price_range)

        btnGetPrice.setOnClickListener {
            var replayIntent = Intent()

            if (TextUtils.isEmpty(txtMinPrice.text) || TextUtils.isEmpty(txtMaxPrice.text)) {

                if (TextUtils.isEmpty(txtMinPrice.text)){ txtMinPrice.error = "Enter Product Min Price" }
                if (TextUtils.isEmpty(txtMaxPrice.text)){ txtMaxPrice.error = "Enter Product Max Price" }

                setResult(Activity.RESULT_CANCELED, replayIntent)
            } else {
                var arrayD:Array<String> = arrayOf(txtMinPrice.text.toString(),txtMaxPrice.text.toString())
                Log.e("Error","<<<<<<<<${Arrays.toString(arrayD)}>>>>>>>>")
                replayIntent.putExtra(EXTRA_REPLY,arrayD)
                setResult(Activity.RESULT_OK,replayIntent)
                finish()
            }
        }
    }

    companion object{
        const val EXTRA_REPLY = "REPLY_DATA"
    }
}
