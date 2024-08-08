package uz.self.multi_display_application

import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var presentation: SecondaryPresentation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        secondaryDisplay()
    }

    private fun secondaryDisplay() {
      getCustomerDisplay(this)?.let {
        presentation = SecondaryPresentation(it, this)
      }
        Log.d("TAG", "secondaryDisplay: $display")
    }

    fun getCustomerDisplay(context: Context): Display? {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val displays = displayManager.displays

        if (displays.size <= 1) {
            return null
        }
        // We take the first additional screen
        return displays[1]
    }

    override fun onResume() {
        super.onResume()
        presentation?.show()
        val buttonShow = findViewById<MaterialButton>(R.id.btn_show)
        buttonShow.setOnClickListener {
            presentation?.showQr()
        }
        val buttonHide = findViewById<MaterialButton>(R.id.btn_hide)
        buttonHide.setOnClickListener {
            presentation?.hideQr()
        }

    }

    override fun onPause() {
        super.onPause()
        presentation?.hide()
    }
}