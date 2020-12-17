package com.animsh.retrofitparsing;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<DataModel> dataModelList = new ArrayList<>();
    RecyclerView myR;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myR = findViewById(R.id.myR);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyRetrofitCalls myRetrofitCalls = retrofit.create(MyRetrofitCalls.class);
        Call<DataModel> call = myRetrofitCalls.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.code() != 200) {
                    Log.e("DATA_CALL", "onResponse: null");
                    return;
                }
                Log.d("DATA_CALL: ", "onResponse: " + response.body().getId() + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyRetrofitCalls myRetrofitCalls = retrofit.create(MyRetrofitCalls.class);
        Call<List<DataModel>> call = myRetrofitCalls.getData();

        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                Log.d("DATA_CALL", "onResponse: " + response.body().size());
                for (int i = 0; i < response.body().size(); i++) {
                    Log.d("DATA_CALL", "onResponse: " + response.body().get(i).getTitle());
                }
                dataModelList.addAll(response.body());
                MyAdapter myAdapter = new MyAdapter(dataModelList, MainActivity.this);
                myR.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                myR.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

            }
        });


    }
}