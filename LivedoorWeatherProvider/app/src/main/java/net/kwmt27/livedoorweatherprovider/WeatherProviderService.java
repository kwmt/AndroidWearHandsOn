package net.kwmt27.livedoorweatherprovider;

import android.graphics.drawable.Icon;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;

import com.squareup.moshi.Moshi;

import net.kwmt27.livedoorweatherprovider.weather.Api;
import net.kwmt27.livedoorweatherprovider.weather.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class WeatherProviderService extends ComplicationProviderService {
    @Override
    public void onComplicationUpdate(final int complicationId, final int dataType, final ComplicationManager complicationManager) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(Api.ENDPOINT)
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build()))
                .build();
        adapter.create(Api.class).getCoupon("280010")
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {

                        ComplicationData.Builder builder = new ComplicationData.Builder(dataType);

                        if (dataType == ComplicationData.TYPE_LONG_TEXT) {
                            ComplicationData complicationData = builder
                                    .setLongTitle(ComplicationText.plainText(response.body().forecasts.get(0).dateLabel))
                                    .setLongText(ComplicationText.plainText(response.body().forecasts.get(0).telop))
                                    .build();
                            complicationManager.updateComplicationData(complicationId, complicationData);
                        } else if (dataType == ComplicationData.TYPE_SHORT_TEXT) {
                            ComplicationData complicationData = builder
                                    .setIcon(Icon.createWithResource(getApplicationContext(),R.drawable.ic_sun))
                                    .setShortText(ComplicationText.plainText(response.body().forecasts.get(0).telop))
                                    .build();
                            complicationManager.updateComplicationData(complicationId, complicationData);

                        }

                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }

                });


    }

}
