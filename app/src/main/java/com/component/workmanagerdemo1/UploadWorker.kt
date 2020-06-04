package com.component.workmanagerdemo1

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UploadWorker(context : Context, params : WorkerParameters) : Worker(context,params) {

    companion object{
        const val KEY_WORKER = "worker_completion_status"
    }

    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        try{
            val data1 : Data = inputData
            val countLimit : Int = data1.getInt(MainActivity.KEY_COUNT_VALUE,10)

            for(i in 0 until countLimit){
                Log.i("MYTAG", "Uploading : $i ")
            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            val outPutData = Data.Builder().putString(KEY_WORKER,currentDate).build()

            return Result.success(outPutData)
        }catch (e : Exception){
            return Result.failure()
        }


    }
}