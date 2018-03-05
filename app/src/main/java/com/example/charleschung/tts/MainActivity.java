package com.example.charleschung.tts;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.util.Log;
import android.content.res.Resources;
import android.util.TypedValue;

import java.util.Locale;

public class MainActivity extends Activity
{
    private EditText et;

    /** TTS 物件 */
    private TextToSpeech tts;

    private static final String TAG = "MessageLog";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");

        // 建立 TTS
        createLanguageTTS();

        RelativeLayout index = new RelativeLayout(this);

        // 輸入文字
        et = new EditText(this);
        et.setId(2);

        // 按鈕
        Button b = new Button(this);
        b.setId(1);
        b.setText("Speech");
        b.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View arg0)
            {
                //【英文】發音
                tts.speak( et.getText().toString(), TextToSpeech.QUEUE_FLUSH, null );
            }
        });

        //建立Container
        RelativeLayout.LayoutParams editTextContainer = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        RelativeLayout.LayoutParams buttonContainer = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        //建立container rules
        editTextContainer.addRule(RelativeLayout.ABOVE,b.getId());
        editTextContainer.addRule(RelativeLayout.CENTER_IN_PARENT);
        editTextContainer.setMargins(0,0,0,50);

        buttonContainer.addRule(RelativeLayout.CENTER_IN_PARENT);

        //Convert DIP to pixel
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());

        et.setWidth(px);

        //add widgets to the container
        index.addView(et, editTextContainer);
        index.addView(b,buttonContainer);
        setContentView(index);


        /* 版面配置
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView( et, 320, 200 );
        ll.addView( b, 320, 100 );
        addContentView( ll, new LinearLayout.LayoutParams(320, 480) );
        */
    }

    @Override
    protected void onDestroy()
    {
        Log.i(TAG, "onDestroy");
        // 釋放 TTS
        if( tts != null ) tts.shutdown();

        super.onDestroy();
    }

    private void createLanguageTTS()
    {
        if( tts == null )
        {
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
                @Override
                public void onInit(int arg0)
                {
                    // TTS 初始化成功
                    if( arg0 == TextToSpeech.SUCCESS )
                    {
                        // 指定的語系: 英文(美國)
                        Locale l = Locale.US;  // 不要用 Locale.ENGLISH, 會預設用英文(印度)

                        // 目前指定的【語系+國家】TTS, 已下載離線語音檔, 可以離線發音
                        if( tts.isLanguageAvailable( l ) == TextToSpeech.LANG_COUNTRY_AVAILABLE )
                        {
                            tts.setLanguage( l );
                        }
                    }
                }}
            );
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
