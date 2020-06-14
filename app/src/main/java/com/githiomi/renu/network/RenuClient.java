package com.githiomi.renu.network;

import com.githiomi.renu.models.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RenuClient {

    public static Retrofit retrofit = null;

    public static synchronized RenuApi getRenuApi(){

        if ( retrofit == null ){

            Interceptor interceptor = new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().build();
                    return chain.proceed(request);
                }
            };

            OkHttpClient.Builder okhttp3Client = new OkHttpClient.Builder().addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .client(okhttp3Client.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        return retrofit.create(RenuApi.class);
    }
}
