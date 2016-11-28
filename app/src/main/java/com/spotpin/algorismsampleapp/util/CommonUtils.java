package com.spotpin.algorismsampleapp.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

/**
 * Created by Jedidiah on 28/11/2016.
 */

public class CommonUtils {

    public static LatLng mDefaultLocation = new LatLng(37.422535, -122.084804);

    public static final int DEFAULT_ZOOM = 15;

    // The desired interval for location updates. Inexact. Updates may be more or less frequent.
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // The fastest rate for active location updates. Exact. Updates will never be more frequent
    // than this value.
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getAddressFromLatLng(LatLng latLng, Context context) {
        Geocoder geocoder = new Geocoder(context);

        Address address = null;
        try {
            address = geocoder
                    .getFromLocation( latLng.latitude, latLng.longitude, 1 )
                    .get( 0 );
        } catch (IOException ignored) {}

        if (address != null)
            return address.getAddressLine(0) + ", " +address.getLocality();
        else
            return "";
    }

    public static LatLng covertLocation(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
