package fr.centrale.myapplication

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {

    var myService: MyBoundService? = null
    var isBound = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        button4.setOnClickListener{
            val monIntent = Intent(this, MainActivity::class.java)
            monIntent.putExtra("val", textInput.text.toString())
            setResult(Activity.RESULT_OK, monIntent)
            finish()
        }

        button6.setOnClickListener{
            val monIntent = Intent(this, MyIntentService::class.java)
            startService(monIntent)
        }

        button7.setOnClickListener{
            val monIntent = Intent(this, MyIntentService::class.java)
            stopService(monIntent)
        }

        button9.setOnClickListener{
            if (isBound) {
                val num = myService!!.getRandomNumber()
                Toast.makeText(this@MainActivity3, num.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val myConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName,
                                        service: IBinder
        ) {
            val binder = service as MyBoundService.LocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyBoundService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        isBound = false
    }

}