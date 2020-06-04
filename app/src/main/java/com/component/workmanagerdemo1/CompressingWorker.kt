package com.component.workmanagerdemo1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CompressingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {

            for (i in 0..450) {
                Log.i("MYTAG", "Compressing : $i ")
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }


    }
}