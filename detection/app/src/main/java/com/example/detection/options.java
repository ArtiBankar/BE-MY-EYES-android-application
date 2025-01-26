package com.example.detection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class options extends AppCompatActivity {



    Button compose_mail, read_mail;
    private TextToSpeech tts;
    private boolean IsInitialVoiceFinshed;
    private int numberOfClicks;
    StringBuffer sb, sb1,sb3;
    String result;
    String blevel,currentDate,currentTime;

    TextView txtview ;
    private BroadcastReceiver batterylevel = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
          // txtview.setText(String.valueOf(level)+"%");
           blevel = String.valueOf(level);
            txtview.setText(blevel+"%");

        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

         currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
         currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        txtview = findViewById(R.id.textView);


        this.registerReceiver(this.batterylevel,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //Toast.makeText(getApplicationContext(),blevel,Toast.LENGTH_LONG).show();
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Welcome To  Be My Eyes app  ");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            IsInitialVoiceFinshed = true;
                            listen();
                        }
                    }, 5000);
                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });
    }

    private void speak(String text) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void layoutClicked(View view) {
        if (IsInitialVoiceFinshed) {
            numberOfClicks++;
//            listen();
        }
    }

    private void listen() {

        speak("Speak your option ");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your option");

                try {
                    startActivityForResult(i, 100);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(), "Your device doesn't support Speech Recognition", Toast.LENGTH_SHORT).show();
                }
                IsInitialVoiceFinshed = true;
            }
        }, 2000);

    }


    @Override
    public void onDestroy() {
        if (tts != null) {

            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    private void exitFromApp() {
        this.finishAffinity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && IsInitialVoiceFinshed) {
            IsInitialVoiceFinshed = false;
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result.get(0).equals("battery")) {
                    speak(blevel);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exitFromApp();
                        }
                    }, 4000);

                } else {
                    if (result.get(0).equals("SMS")) {


                        SmsManager smgr1 = SmsManager.getDefault();
                        smgr1.sendTextMessage("++919130485592",null,"Emergency",null,null);
                        SmsManager smgr = SmsManager.getDefault();
                        smgr.sendTextMessage("+918421736838",null,"Emergency",null,null);
                        speak("Sms Send");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 4000);
                    }else {
                        if (result.get(0).equals("call"))
                        {
                            speak("Calling");
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:8421736838"));
                            startActivity(intent);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }, 4000);
                        }else if(result.get(0).equals("date"))
                        {
                            speak(currentDate);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }, 4000);
                        }else if(result.get(0).equals("time"))
                        {
                            speak(currentTime);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }, 4000);
                        }

                    }


                }
                IsInitialVoiceFinshed = true;
            }
        }
    }
}