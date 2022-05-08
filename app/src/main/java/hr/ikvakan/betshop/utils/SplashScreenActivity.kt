package hr.ikvakan.betshop.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import hr.ikvakan.betshop.R
import hr.ikvakan.betshop.ui.MapsActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val DELAY: Long = 3000
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        redirect()
    }
    private fun redirect() {
        lifecycleScope.launch{
            delay(DELAY)
            if (isOnline()){
                startActivity<MapsActivity>()
            }else{
                showToast("Please connect to the internet", Toast.LENGTH_LONG)
                finish()
            }
        }
    }
}