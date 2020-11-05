package fr.centrale.myapplication

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_share.*

class ShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        val data: Uri? = intent?.data

        if (intent?.type?.startsWith("image/") == true) {
            //image handler
        } else if (intent?.type == "text/plain") {
            Log.d("TAG", intent?.getStringExtra(Intent.EXTRA_TEXT))
            //text handler
        }

        button15.setOnClickListener{
            val sendIntent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Salut")
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            }
        }
    }
}