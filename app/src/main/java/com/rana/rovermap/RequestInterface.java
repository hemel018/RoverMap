package com.rana.rovermap;

import com.rana.rovermap.models.RequestBody;
import com.rana.rovermap.models.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by rana on 5/25/17.
 */

public interface RequestInterface {
    @POST("devices")
    Call<ResponseBody> registerDevice(@Body RequestBody responseBody);

}
