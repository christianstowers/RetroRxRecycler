package com.christianstowers.retro_rx_recycler.data;

import com.christianstowers.retro_rx_recycler.data.entities.AndroidModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

//        The endpoint from which the JSON array is fetched is defined as the register() method.
//        This method returns a RxJava Observable. An Observer can subscribe this Observable.
//        This Retrofit Interface is created in the network package.

public interface RequestInterface {

    @GET("android/jsonarray")
    Observable<List<AndroidModel>> register();

}
