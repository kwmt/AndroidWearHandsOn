package net.kwmt27.livedoorweatherprovider;

import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.ComplicationManager;
import android.support.wearable.complications.ComplicationProviderService;
import android.support.wearable.complications.ComplicationText;


public class ProviderService extends ComplicationProviderService {

    @Override
    public void onComplicationUpdate(int complicationId, int dataType, ComplicationManager complicationManager) {
        ComplicationData complicationData = new ComplicationData.Builder(dataType)
                .setShortText(ComplicationText.plainText("test"))
                .build();

        complicationManager.updateComplicationData(complicationId, complicationData);
    }
}
