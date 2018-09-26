package com.osamaomar.akhbarak.Helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Wasltec on 23/12/2015.
 */
public class LocationAddressHelper {

    public static String getLocationAddress(Context context , Double Lat, Double Lng){
             String address="";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(Lat,Lng, 1);
           // List<Address> listAddresses = getManualLocation(latitude, longitude);
            if (null != listAddresses && listAddresses.size() > 0) {

                String _Location = "";
                if(listAddresses.get(0).getMaxAddressLineIndex()>0){
                    for (int i = 0; i < listAddresses.get(0).getMaxAddressLineIndex(); i++) {
                        _Location += listAddresses.get(0).getAddressLine(i) + ", ";
                    }
                }else {
                    _Location += listAddresses.get(0).getAddressLine(0);
                }
                address = _Location;
            }
            return address;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }




    public static String getCityName(Context context , Double Lat, Double Lng){
        String address="";
        Locale lHebrew = new Locale("ar");
        Geocoder geocoder = new Geocoder(context,lHebrew);
        try {

            List<Address> addresses = geocoder.getFromLocation(Lat, Lng, 5);
            String cityName = addresses.get(0).getAdminArea();
            String stateName = addresses.get(0).getFeatureName();
            String countryName = addresses.get(0).getCountryName();
            return stateName+","+cityName;

        }

        catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }





    public static String getCountryCode(Context context , Double Lat, Double Lng){
        String countryName="null";
        String countryCode="null";

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(Lat,Lng, 1);
            if (null != listAddresses && listAddresses.size() > 0) {

                  countryCode= listAddresses.get(0).getCountryCode();
                countryName= listAddresses.get(0).getCountryName();

            }
          //  return countryName;
            return countryCode;

        } catch (Exception e) {
            e.printStackTrace();
           // return countryName;
            return countryCode;
        }


    }

}
