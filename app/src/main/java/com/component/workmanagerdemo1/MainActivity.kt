package com.component.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
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

    private fun setOneTimeRequest() {
        val workManagerInstance = WorkManager.getInstance(applicationContext)
        val constraint: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .setRequiresDeviceIdle(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimeUploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java).setConstraints(constraint).build()

        workManagerInstance.enqueue(oneTimeUploadRequest)

        workManagerInstance.getWorkInfoByIdLiveData(oneTimeUploadRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name

            })
    }
}