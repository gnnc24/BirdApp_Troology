package com.troology.birdapp.Api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ParkingServicesApi {
   // private static final String key = "application";
   private static final String baseUrl = "http://fairelamour.in/airportvaletoflax/api/";
    private static final String FlightUrl = baseUrl+"get_parking_services";
    private static final String AdditionalUrl = baseUrl+"get_additional_services";

  //  public static ValetParkingService valetParkigService = null;
//    public static ValetParkingService getService()
//    {
//        if (valetParkigService == null)
//        {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            valetParkigService = retrofit.create(ValetParkingService.class);
//        }
//        return valetParkigService;
//    }


//    public interface ValetParkingService {
//        @GET(FlightUrl)
//        Call<ParkingServices> getParkingServices();
//        @GET(AdditionalUrl)
//        Call<ParkingAdditionalServices> getAdditionalParkingServices();
//        Call<PCThemeListModel> getDatum();
//
//    }
}

