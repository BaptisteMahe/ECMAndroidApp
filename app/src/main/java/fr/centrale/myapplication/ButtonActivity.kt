package fr.centrale.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_button.*

class ButtonActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button18.setOnClickListener{
            Log.d("TAG", "Clicked with method set on Button in OnCreate")
        }

        button16.setOnClickListener(this)
    }

    fun onClickInXml(view: View) {
        Log.d("TAG", "Clicked with XML method")
    }

    override fun onClick(view: View?) {
        Log.d("TAG", "Clicked with Override method")
    }
}