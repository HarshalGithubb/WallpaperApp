package com.example.wallpaper2_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SrcModel> srcModelArrayList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mNature,mBus,mCar,mTrain,mTrending;
    EditText editText;
    ImageButton imageButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.RV_ResultsDisplay);
        mNature=findViewById(R.id.nature);
        mBus=findViewById(R.id.bus);
        mCar=findViewById(R.id.car);
        mTrain=findViewById(R.id.train);
        mTrending=findViewById(R.id.trending);
        editText=findViewById(R.id.idETSearchBar);
        imageButton=findViewById(R.id.idIVSearchIcon);


        srcModelArrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),srcModelArrayList);
        recyclerView.setAdapter(adapter);
        
        findphotos();

        mNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query= "nature";
                getSearchImage(query);
            }
        });
        mCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query= "car";
                getSearchImage(query);
            }
        });
        mTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query= "train";
                getSearchImage(query);
            }
        });
        mBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query= "bus";
                getSearchImage(query);
            }
        });

        mTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findphotos();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString().trim().toLowerCase();

                if (query.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Enter Something", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    getSearchImage(query);
                }
            }
        });



    }

    private void getSearchImage(String query) {

        ApiUtilities.getApiInterface().getSearchImage(query,1,60).enqueue(new Callback<PhotosModel>() {
            @Override
            public void onResponse(Call<PhotosModel> call, Response<PhotosModel> response) {
                srcModelArrayList.clear();
                if (response.isSuccessful())
                {
                    srcModelArrayList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Not Able To Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PhotosModel> call, Throwable t) {

            }
        });

    }

    private void findphotos() {

    ApiUtilities.getApiInterface().getImage(1,60).enqueue(new Callback<PhotosModel>() {
        @Override
        public void onResponse(Call<PhotosModel> call, Response<PhotosModel> response) {
            if (response.isSuccessful())
            {
                srcModelArrayList.addAll(response.body().getPhotos());
                adapter.notifyDataSetChanged();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Not Able To Get", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFailure(Call<PhotosModel> call, Throwable t) {

        }
    });


    }
}