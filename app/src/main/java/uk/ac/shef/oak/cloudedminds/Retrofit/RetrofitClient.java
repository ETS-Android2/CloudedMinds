package uk.ac.shef.oak.cloudedminds.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * This class initiates the Retrofit Builder and sets the url of the web application
 * to where the requests will be sent. The main url is changed from "localhost" to that
 * which the emulator or device recognises.
 *
 * Based on tutorial by EMDT Dev on Youtube: https://www.youtube.com/watch?v=4Xq2FUJvE-c
 */
public class RetrofitClient {
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if(instance == null)
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.18:3000/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance;
    }
}
