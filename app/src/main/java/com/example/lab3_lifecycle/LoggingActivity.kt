package com.example.lab3_lifecycle

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class LoggingActivity : AppCompatActivity() {
    private val tag get() = "LC" + javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        Log.d(tag, "On Create (saved state = ${savedInstanceState != null}")
    }

    override fun onStart(){
        super.onStart()
        Log.d(tag, "On Start")
    }
    override fun onPause(){
        super.onPause()
        Log.d(tag, "On Pause")
    }
    override fun onStop(){
        super.onStop()
        Log.d(tag, "On Stop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(tag, "ondestroy (isFinishing = $isFinishing, changingConfig = $isChangingConfigurations")
    }

}