package com.example.goldenmanagerv10.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goldenmanagerv10.MainActivity
import com.example.goldenmanagerv10.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,MainActivity::class.java))

    }
}