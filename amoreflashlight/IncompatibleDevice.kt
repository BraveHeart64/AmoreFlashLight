package com.example.amoreflashlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class IncompatibleDevice : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incompatible_device)



    }

        fun EndProgram(){

            finish()
        }
    }
