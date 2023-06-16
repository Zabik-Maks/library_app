package com.mirea.kt.libraryapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @POST("/coursework/login.php")
    @FormUrlEncoded
    Call<DataResponse> getStudentInfoAll(@Field("lgn")String lgn, @Field("pwd")String pwd, @Field("g")String g);
}