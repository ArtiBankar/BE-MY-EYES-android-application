package com.example.detection

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.telephony.SmsManager
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class home : AppCompatActivity() {

    var tts: TextToSpeech? = null

    private var IsInitialVoiceFinshed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)




        //Toast.makeText(getApplicationContext(),blevel,Toast.LENGTH_LONG).show();
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts!!.setLanguage(Locale.getDefault())
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "This Language is not supported")
                }
                speak("Welcome To  Be My Eyes app  ")
                Handler().postDelayed({
                    IsInitialVoiceFinshed = true
                    listen()
                }, 5000)
            } else {
                Log.e("TTS", "Initilization Failed!")
            }
        }
    }

    private fun listen() {
        speak("Speak your option ")
        Handler().postDelayed({
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your option")
            try {
                startActivityForResult(i, 100)
            } catch (a: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    "Your device doesn't support Speech Recognition",
                    Toast.LENGTH_SHORT
                ).show()
            }
            IsInitialVoiceFinshed = true
        }, 2000)
    }

    private fun speak(text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && IsInitialVoiceFinshed) {
            IsInitialVoiceFinshed = false
            if (resultCode == RESULT_OK && null != data) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                if (result!![0] == "Press Up Key For Diaease Predication") {
                    speak(blevel)
                    Handler().postDelayed({ exitFromApp() }, 4000)
                } else { 
                    if (result!![0] == "SMS") {
                        val smgr1 = SmsManager.getDefault()
                        smgr1.sendTextMessage("++919130485592", null, "Emergency", null, null)
                        val smgr = SmsManager.getDefault()
                        smgr.sendTextMessage("+918421736838", null, "Emergency", null, null)
                        speak("Sms Send")
                        Handler().postDelayed({ }, 4000)
                    } else {
                        if (result!![0] == "call") {
                            speak("Calling")
                            val intent = Intent(Intent.ACTION_CALL)
                            intent.data = Uri.parse("tel:8421736838")
                            startActivity(intent)
                            Handler().postDelayed({ }, 4000)
                        } else if (result!![0] == "date") {
                            speak(currentDate)
                            Handler().postDelayed({ }, 4000)
                        } else if (result!![0] == "time") {
                            speak(currentTime)
                            Handler().postDelayed({ }, 4000)
                        }
                    }
                }
                IsInitialVoiceFinshed = true
            }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {


        val i = Intent(applicationContext,MainActivity::class.java)
        val myi = Intent(applicationContext,diease_predication::class.java)
        val option = Intent(applicationContext,options::class.java)
        when(keyCode)
        {
            KeyEvent.KEYCODE_VOLUME_UP ->  startActivity(myi)
            KeyEvent.KEYCODE_VOLUME_DOWN -> startActivity(i)
            KeyEvent.KEYCODE_BACK ->  startActivity(option)

        }
        return true
    }



}