package com.sudansh.deliveries.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sudansh.deliveries.R
import com.sudansh.deliveries.databinding.ActivityMapsBinding
import com.sudansh.deliveries.repository.local.db.entity.DeliveryItem

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var delivery: DeliveryItem? = null
    private lateinit var binding: ActivityMapsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        delivery = intent.getParcelableExtra(KEY_DELIVERY)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        delivery?.let {
            val markerLocation = LatLng(it.location.lat, it.location.lng)
            mMap.addMarker(MarkerOptions().position(markerLocation))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLocation, 16.0f))
            binding.view!!.data = delivery
        }
    }

    companion object {
        const val KEY_DELIVERY = "KEY_DELIVERY"

        fun start(context: Context, location: DeliveryItem) {
            Intent(context, MapsActivity::class.java).apply {
                putExtra(KEY_DELIVERY, location)
            }.also { context.startActivity(it) }
        }
    }

}
