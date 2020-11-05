package fr.centrale.myapplication

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "fr.centrale.myapplication.action.FOO"
private const val ACTION_BAZ = "fr.centrale.myapplication.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "fr.centrale.myapplication.extra.PARAM1"
private const val EXTRA_PARAM2 = "fr.centrale.myapplication.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        try {
            val dataString = intent!!.dataString
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("IntentService", "Destroying intent service")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("IntentService", "Creating intent service")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("IntentService", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}
