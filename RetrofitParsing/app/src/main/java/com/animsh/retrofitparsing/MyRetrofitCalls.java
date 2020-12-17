package com.animsh.retrofitparsing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyRetrofitCalls {
    // https://jsonplaceholder.typicode.com/todos/1

    @GET("todos")
    Call<List<DataModel>> getData();
}
