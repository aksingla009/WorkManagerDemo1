package com.component.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn.setOnClickListener {
            setOneTimeRequest()
        }
    }

    private fun setOneTimeRequest(){
        val oneTimeUploadRequest  =  OneTimeWorkRequest.Builder(UploadWorker::class.java).build()

        WorkManager.getInstance(applicationContext).enqueue(oneTimeUploadRequest)

    }
}