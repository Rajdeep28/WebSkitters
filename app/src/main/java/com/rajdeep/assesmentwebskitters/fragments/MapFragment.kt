package com.rajdeep.assesmentwebskitters.fragments

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions

import android.content.Context

import com.google.android.gms.maps.model.CameraPosition

import android.content.pm.PackageManager

import androidx.core.app.ActivityCompat

import android.util.Log

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import kotlinx.android.synthetic.main.fragment_map.*
import java.lang.Exception
import android.content.Context.LOCATION_SERVICE
import android.location.*
import androidx.test.core.app.ApplicationProvider

import android.widget.Toast
import java.util.*


class MapFragment : Fragment(),OnMapReadyCallback,LocationListener {

    private lateinit var mMap: GoogleMap
    lateinit var supportMapFragment: SupportMapFragment
    lateinit var client: FusedLocationProviderClient
    var locationManager: LocationManager? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        frg.onCreate(savedInstanceState)
        frg.onResume()



        frg.getMapAsync(this)

    }
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            mMap = it
            getLocation()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(com.rajdeep.assesmentwebskitters.R.layout.fragment_map, container, false)




        return rootView
    }

    private fun getLocation() {
        try {
            locationManager = ApplicationProvider.getApplicationContext<Context>().getSystemService(
                LOCATION_SERVICE
            ) as LocationManager

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onLocationChanged(location: Location?) {
        Toast.makeText(
            activity,
            "" + location!!.latitude.toString() + "," + location.longitude,
            Toast.LENGTH_SHORT
        ).show()
        try {
            val geocoder = Geocoder(activity, Locale.getDefault())
            val addresses: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            val address: String = addresses[0].getAddressLine(0)

            mMap.addMarker(MarkerOptions().position(LatLng(location.latitude, location.longitude)))

            Log.d("MAP", "onLocationChanged: $address")
            // textView_location.setText(address)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}


