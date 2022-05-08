package hr.ikvakan.betshop.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import hr.ikvakan.betshop.model.BetshopLocationModel

fun View.applyAnimation(resourceId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, resourceId))

inline fun <reified T : Activity> Context.startActivity() =
    Intent(this, T::class.java).also { startActivity(it) }

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    if (activeNetwork != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            )
        }
    }
    return false;
}

fun Context.showToast(message:String?,duration: Int){
    Toast.makeText(this,message,duration).show()
}