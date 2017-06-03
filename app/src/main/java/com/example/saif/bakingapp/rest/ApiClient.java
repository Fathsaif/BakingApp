package com.example.saif.bakingapp.rest;

import com.example.saif.bakingapp.model.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mosaad on 20/05/2017.
 */

public class ApiClient {
   public static final String BaseUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static Retrofit retrofit = null;
    public static Services services;
    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static Retrofit getClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        return retrofit;
    }
    public static Services getServices(){
        Retrofit retrofit = getClient();
        services = retrofit.create(Services.class);

        return services;
    }

}
