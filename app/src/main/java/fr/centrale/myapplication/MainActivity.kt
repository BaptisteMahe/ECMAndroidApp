package fr.centrale.myapplication

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    val forResultActivity_3 = 100
    val forResultActivity_2 = 101
    val CHANNEL_ID = "fr.centrale.myapplication.MY_NOTIFICATION_CHANNEL"
    val nGenerator = java.util.Random()

    var broadcastReceiver: MyBroadcastReceiver? = null

    var builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("My New Notification")
        .setContentText("The Notification Content")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Je suis dans onCreate")
        button.setOnClickListener{
            val monIntent = Intent(this, MainActivity2::class.java)
            monIntent.putExtra("congrats", "Bravo, tu a trouvé un stage")
            monIntent.putExtra("checked", checkBox.isChecked)
            startActivityForResult(monIntent, forResultActivity_2)
        }

        button3.setOnClickListener{
            val monIntent = Intent(this, MainActivity3::class.java)
            startActivityForResult(monIntent, forResultActivity_3)
        }

        button11.setOnClickListener{
            Intent().also { intent ->
                intent.setAction("fr.centrale.myapplication.MY_NOTIFICATION")
                intent.putExtra("data", "Call Button")
                sendBroadcast(intent)
            }
        }

        button12.setOnClickListener{
            val ID = nGenerator.nextInt(10000000) // To create multiple notif while spamming the button
            NotificationManagerCompat.from(this).notify(ID, builder.build())
        }

        configureReceiver()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun configureReceiver() {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction("fr.centrale.myapplication.MY_NOTIFICATION")
        }
        broadcastReceiver = MyBroadcastReceiver()
        registerReceiver(broadcastReceiver, filter)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == forResultActivity_2 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this@MainActivity, "ret: " + data?.getStringExtra("ret"), Toast.LENGTH_SHORT).show()
            data?.also{

            }
        } else if (requestCode == forResultActivity_3 && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this@MainActivity, "val: " + data?.getStringExtra("val"), Toast.LENGTH_SHORT).show()
            data?.also{

            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Je suis dans onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Je suis dans onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Je suis dans onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Je suis dans onPause")
    }

    override fun finish() {
        super.finish()
        Log.d(TAG, "Je suis dans finish")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Je suis dans onDestroy")
    }
}