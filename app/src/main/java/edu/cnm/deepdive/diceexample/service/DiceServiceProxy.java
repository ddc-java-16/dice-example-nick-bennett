package edu.cnm.deepdive.diceexample.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.diceexample.model.Roll;
import io.reactivex.rxjava3.core.Single;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiceServiceProxy {

  @GET("api")
  Single<Roll[]> rollDice(@Query("nbde") int numberOfDice, @Query("tpde") int numberOfSides);

  static DiceServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final DiceServiceProxy INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://www.dejete.com/")
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
          .client(client)
          .build();
      INSTANCE = retrofit.create(DiceServiceProxy.class);
    }

  }

}
