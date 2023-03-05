package com.example.healthifyapp.model.apiclient;

import com.example.healthifyapp.BuildConfig;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAccountclient {
    public static String BASE_URL=("https://easywaygst.theumangsociety.org");
//    private static Retrofit retrofit;
//    public static Retrofit getClient(){
//        if(retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
private static Retrofit retrofit;

    public static Retrofit getClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if(BuildConfig.DEBUG)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);


        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(2,TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .addInterceptor(logging).build();
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }return retrofit;
    }

}
