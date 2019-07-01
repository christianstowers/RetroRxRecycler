package com.christianstowers.retro_rx_recycler.ui.androidListActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.christianstowers.retro_rx_recycler.R;
import com.christianstowers.retro_rx_recycler.data.RequestInterface;
import com.christianstowers.retro_rx_recycler.data.entities.AndroidModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AndroidListActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.learn2crack.com/";

    private RecyclerView mRecyclerView;
    private CompositeDisposable mCompositeDisposable;
    private AndroidListAdapter mAndroidListAdapter;
    private ArrayList<AndroidModel> mAndroidArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);

        mCompositeDisposable = new CompositeDisposable();

        initRecyclerView();
        loadJSON();
    }

    // initialize RecyclerView
    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.gear_recycler);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }


    private void loadJSON() {

        // define Retrofit request
        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);

        // carry out network operation
        mCompositeDisposable.add(requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));

    }

    private void handleResponse(List<AndroidModel> androidList) {
        mAndroidArrayList = new ArrayList<>(androidList);
        mAndroidListAdapter = new AndroidListAdapter(mAndroidArrayList);
        mRecyclerView.setAdapter(mAndroidListAdapter);
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Oops. Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // clear the disposables
        mCompositeDisposable.clear();
    }


}
