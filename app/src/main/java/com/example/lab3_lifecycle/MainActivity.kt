package com.example.lab3_lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LogObserver : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) { Log.d("LC_OBS", "start") }
    override fun onStop(owner: LifecycleOwner) { Log.d("LC_OBS", "stop") }
}

class CounterViewModel(private val state: SavedStateHandle) : ViewModel() {
    var count: Int
        get() = state["count"] ?: 0
        set(value) { state["count"] = value }

    init { Log.d("LC_VM", "ViewModel created") }
    override fun onCleared() { Log.d("LC_VM", "ViewModel destroyed") }
}

class MainActivity : LoggingActivity() {
    private val vm: CounterViewModel by viewModels()

    private fun ticker() = flow {
        var i = 0
        while (true) {
            emit(i++)
            delay(1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycle.addObserver(LogObserver())

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticker().collect {
                    Log.d("LC_FLOW", "tick $it")
                }
            }
        }

        val savedPrefValue = getSharedPreferences("lab", MODE_PRIVATE).getInt("saved", -1)
        Log.d("LC_PREF", "saved=$savedPrefValue")

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnPlus = findViewById<Button>(R.id.btnOnPlus)
        val btnDialog = findViewById<Button>(R.id.btnOnDialog)
        val btnSecond = findViewById<Button>(R.id.btnOnSecond)
        val btnOnTransparent = findViewById<Button>(R.id.btnOnTransparent)
        val btnClose = findViewById<Button>(R.id.btnOnFinish)

        tvCount.text = "Count: ${vm.count}"

        btnPlus.setOnClickListener {
            vm.count++
            tvCount.text = "Count: ${vm.count}"
        }

        btnDialog.setOnClickListener {
            AlertDialog.Builder(this).setTitle("Dialog")
                .setMessage("See Logcat Area").setPositiveButton("Ok", null).show()
        }

        btnSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btnOnTransparent.setOnClickListener {
            val intent = Intent(this, TransparentActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        getSharedPreferences("lab", MODE_PRIVATE)
            .edit()
            .putInt("saved", vm.count)
            .apply()
    }
}