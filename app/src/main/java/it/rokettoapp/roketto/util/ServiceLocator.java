package it.rokettoapp.roketto.util;

import it.rokettoapp.roketto.service.AgencyApiService;
import it.rokettoapp.roketto.service.AstronautApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLocator {

    private static ServiceLocator sInstance = null;

    private ServiceLocator() { }

    public static ServiceLocator getsInstance() {

        if (sInstance == null) {
            synchronized (ServiceLocator.class) {
                sInstance = new ServiceLocator();
            }
        }
        return sInstance;
    }

    public AstronautApiService getAstronautApiService() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(AstronautApiService.class);
    }

    public AgencyApiService getAgencyApiService() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(AgencyApiService.class);
    }
}
