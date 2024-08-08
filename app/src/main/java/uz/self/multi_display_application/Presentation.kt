package uz.self.multi_display_application

import android.app.Presentation
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.ImageView

class SecondaryPresentation(
    display: Display, context: Context
) : Presentation(context, display) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun showQr() {
        val imageView = findViewById<ImageView>(R.id.iv_qr_code)
        imageView.visibility  = View.VISIBLE
    }

    fun hideQr() {
        val imageView = findViewById<ImageView>(R.id.iv_qr_code)
        imageView.visibility  = View.INVISIBLE
    }
}