package io.github.dmi3coder.roommy

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity

/**
 * Created by dim3coder on 6/28/17.
 */
class SensorListener : LifecycleObserver, SensorEventListener {


    val context: Context
    val lifecycle: Lifecycle
    val sensorManager: SensorManager
    val sensor: Sensor
    val callback: ((SensorEvent?) -> Unit)

    constructor(context: Context, lifecycle: Lifecycle, callback: (SensorEvent?) -> Unit) {
        this.context = context
        this.lifecycle = lifecycle
        this.callback = callback
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
    }

    var enabled = false


    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (enabled) {
            connect()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        callback.invoke(event)
    }

    @SuppressLint("MissingPermission")
    fun enable() {
        enabled = true
        lifecycle.addObserver(this)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            connect()
        }
    }

    @SuppressLint("MissingPermission")
    private fun connect(){
        sensorManager.registerListener(this, sensor, 100);
        Log.d(this.javaClass.simpleName,"connected!");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        sensorManager.unregisterListener(this,sensor);
    }
}