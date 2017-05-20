package net.kwmt27.livedoorweatherprovider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String INTENT_KEY = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if(intent.hasExtra(INTENT_KEY)) {
            ((TextView)findViewById(R.id.text1)).setText(intent.getStringExtra(INTENT_KEY));
        }
    }

    public static Intent createChooser(Context context, String text) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(INTENT_KEY, text);
        return intent;
    }


}
