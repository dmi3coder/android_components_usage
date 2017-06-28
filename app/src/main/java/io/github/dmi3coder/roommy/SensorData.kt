package io.github.dmi3coder.roommy

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

/**
 * Created by dim3coder on 6/28/17.
 */
class SensorData : LiveData<SensorEvent?>, SensorEventListener {


    val context: Context
    val lifecycle: Lifecycle
    val sensorManager: SensorManager
    val sensor: Sensor

    constructor(context: Context, lifecycle: Lifecycle) {
        this.context = context
        this.lifecycle = lifecycle
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        value = event;
    }

    override fun onActive() {
        connect()
    }

    override fun onInactive() {
        stop()
    }

    @SuppressLint("MissingPermission")
    private fun connect() {
        sensorManager.registerListener(this, sensor, 100);
        Log.d(this.javaClass.simpleName, "connected!");
    }

    fun stop() {
        sensorManager.unregisterListener(this, sensor);
    }
}