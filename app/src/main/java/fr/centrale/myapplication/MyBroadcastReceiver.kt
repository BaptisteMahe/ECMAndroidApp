package fr.centrale.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.util.Log
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    private var TAG = "MyBroadcastReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("Action : ${intent.action} \n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)} \n")
            toString().also{ log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }
}
