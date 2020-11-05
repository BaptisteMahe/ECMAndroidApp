package fr.centrale.myapplication

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1
    val URL = "http://persil.ovh1.ec-m.fr/my-site/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val i = intent
        if (i.getBooleanExtra("checked", false)) {
            textView2.text = i.getStringExtra("congrats")
        } else {
            textView2.text = "Débile faut chercher !"
        }

        button2.setOnClickListener {
            val monIntent = Intent(this, MainActivity::class.java)
            monIntent.putExtra("ret", "Vous avez fermé")
            setResult(Activity.RESULT_OK, monIntent)
            finish()
        }

        button5.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
            startActivity(intent);
        }

        button13.setOnClickListener{
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

        button8.setOnClickListener{
            this.dispatchTakePictureIntent()
        }

        button10.setOnClickListener{
            imageView2.setImageBitmap(null)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView2.setImageBitmap(imageBitmap)
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }
}