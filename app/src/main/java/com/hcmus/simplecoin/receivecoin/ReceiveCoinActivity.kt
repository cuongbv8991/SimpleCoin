package com.hcmus.simplecoin.receivecoin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.hcmus.simplecoin.R
import com.hcmus.simplecoin.utils.CoinManager
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_receive_coin.*

class ReceiveCoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_coin)

        val multiFormatWriter = MultiFormatWriter()
        try {
            CoinManager.pubKeyHash?.let {
                val bitMatrix = multiFormatWriter.encode(it, BarcodeFormat.QR_CODE, 200, 200)
                val barcodeEncoder = BarcodeEncoder()
                val bitmap = barcodeEncoder.createBitmap(bitMatrix)
                imgBarcode.setImageBitmap(bitmap)
            }
        } catch (e: WriterException) {

        }
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, ReceiveCoinActivity::class.java)
        }
    }
}
