package hr.ikvakan.betshop.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import dagger.hilt.android.AndroidEntryPoint
import hr.ikvakan.betshop.R
import hr.ikvakan.betshop.model.BetshopLocationModel
import hr.ikvakan.betshop.retrofit.DataState
import hr.ikvakan.betshop.utils.showToast
import hr.ikvakan.betshop.viewModel.BetShopViewModel



const val START_LAT = 48.137154
const val START_LNG = 11.576124

@AndroidEntryPoint
class MapsActivity() : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var mapReady = false
    private var locations: BetshopLocationModel? = null
    private var selectedMarker: Marker? = null
    private val viewModel: BetShopViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.getBetshopLocationItem()
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<BetshopLocationModel> -> {
                    locations = dataState.data
                    updateMap()
                }
                is DataState.Error -> {
                    showToast(dataState.exception.message, Toast.LENGTH_LONG)
                    finish()
                }
                else -> {
                    return@observe
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.host_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuExit ->{
                exitApp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun updateMap() {
        if (mapReady) {
            locations?.betshops?.forEach { item ->
                val itemPosition = LatLng(item.location.lat, item.location.lng)
                val marker = mMap.addMarker(MarkerOptions().position(itemPosition).title(item.name))
                marker.tag = item
            }
            val startLocation = LatLng(START_LAT, START_LNG)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 15f))
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mapReady = true
        updateMap()
        mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                selectedMarker?.setIcon(BitmapDescriptorFactory.defaultMarker())
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pin_active))
                selectedMarker = marker

                val betshopInfo = marker.tag as BetshopLocationModel.BetshopsModel
                BottomModalFragment(betshopInfo).apply {
                    show(supportFragmentManager, BottomModalFragment.TAG)
                }
                return true
            }
        })
    }
    private fun exitApp() {
        AlertDialog.Builder(this).apply {
            setTitle("Exit")
            setMessage("Do you want to exit ?")
            setIcon(R.drawable.ic_baseline_exit_to_app_24)
            setPositiveButton("Ok") { _, _ -> finish() }
            setNegativeButton("Cancel", null)
            show()
        }
    }
}