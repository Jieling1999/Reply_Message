package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create an event handler for buttonSend
        buttonSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage() {
        //Create an Explicit Intent for the SecondActivity
        val intent = Intent(this, SecondActivity::class.java)

        //Prepare extra
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)

        //Start an activity with no return value
        startActivity(intent)
        //Start an activity with return value(s)/result(s)
        startActivityForResult(intent, REQUEST_REPLY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_REPLY){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(MainActivity.EXTRA_REPLY)
                textViewReply.text = String.format("%s : %s", "Reply", reply)
            }else{
                textViewReply.text = String.format("%s : %s", getString(R.string.reply), "No reply")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        const val EXTRA_MESSAGE = "com.example.myapplication.MESSAGE"
        const val EXTRA_REPLY = "com.example.myapplication.REPLY"
        const val REQUEST_REPLY = 1
    }
}//End of Class
