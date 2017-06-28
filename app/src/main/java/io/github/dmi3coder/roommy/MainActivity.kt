package io.github.dmi3coder.roommy

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : LifecycleActivity() {
    var lifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        useLifecycle()
        sample_text.text = stringFromJNI()
    }

    private fun useLifecycle(){
        val locationData = SensorListener(context = this, lifecycle = lifecycle){
            Log.d(this.javaClass.simpleName, "${it?.values?.get(0)} \n ${it?.values?.get(1)}")
            sample_text.setText("${it?.values?.get(0)} \n ${it?.values?.get(1)}")
        }
        locationData.enable();
        lifecycleRegistry.addObserver(locationData)

    }

    private fun useLiveData(){
        val locationData = SensorData(context = this, lifecycle = lifecycle)

        locationData.observe(this::getLifecycle) {
            Log.d(this.javaClass.simpleName, "${it?.values?.get(0)} \n ${it?.values?.get(1)}")
            sample_text.setText("${it?.values?.get(0)} \n ${it?.values?.get(1)}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
