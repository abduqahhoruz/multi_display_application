package uz.self.multi_display_application

import android.app.Application
import android.content.Context
import android.hardware.display.DisplayManager
import android.util.Log
import android.view.Display

class App: Application() {
    private var presentation: SecondaryPresentation? = null
    override fun onCreate() {
        super.onCreate()
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

}
