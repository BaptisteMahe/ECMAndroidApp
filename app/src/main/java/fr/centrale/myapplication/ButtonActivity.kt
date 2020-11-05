package fr.centrale.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_button.*

class ButtonActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button18.setOnClickListener{
            Log.d("TAG", "Clicked with method set on Button in OnCreate")
        }

        button16.setOnClickListener(this)

        button21.setOnClickListener{
            Toast.makeText(this@ButtonActivity, "Je suis bien toasté !", Toast.LENGTH_SHORT).show()
        }

        button22.setOnClickListener{
            val inflater = layoutInflater
            val container: ViewGroup = findViewById(R.id.custom_toast_container)
            val layout = inflater.inflate(R.layout.custom_toat, container, false)
            val text: TextView = layout.findViewById(R.id.text)
            text.text = "Je suis encore mieux toaté"
            with(Toast(applicationContext)) {
                this.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                duration = Toast.LENGTH_LONG
                view = layout
                show()
            }
        }
    }

    fun onClickInXml(view: View) {
        Log.d("TAG", "Clicked with XML method")
    }

    override fun onClick(view: View?) {
        Log.d("TAG", "Clicked with Override method")
    }
}