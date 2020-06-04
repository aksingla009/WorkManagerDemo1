package com.component.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBtn.setOnClickListener {
            setOneTimeRequest()
        }
    }

    companion object {
        const val KEY_COUNT_VALUE = "AmanKeyCount"
    }

    private fun setOneTimeRequest() {
        val workManagerInstance = WorkManager.getInstance(applicationContext)

//        val data : Data = Data.Builder().putInt("AmanKeyCount",1750).build()
        val data: Data = Data.Builder().putInt(KEY_COUNT_VALUE, 1750).build()

        val constraint: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .setRequiresDeviceIdle(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimeUploadRequest = OneTimeWorkRequest
            .Builder(UploadWorker::class.java)
            .setInputData(data)
            .build()
        //.setConstraints(constraint)

        workManagerInstance.enqueue(oneTimeUploadRequest)

        workManagerInstance.getWorkInfoByIdLiveData(oneTimeUploadRequest.id)
            .observe(this, Observer {
                textView.text = it.state.name

                //We Can not get the Output data from worker task until the task is finished
                if(it.state.isFinished){
                    val data2 : Data = it.outputData
                    val msg : String? = data2.getString(UploadWorker.KEY_WORKER)
                    Toast.makeText(applicationContext,msg,Toast.LENGTH_LONG).show()
                }

            })
    }
}