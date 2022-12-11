package br.com.formatura.formaturaatual.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.com.formatura.formaturaatual.R
import kotlinx.android.synthetic.main.activity_main.*

class CreateMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)

        btnVoltar.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onSendMessage(view: View?) {
        val messageView = findViewById<View>(R.id.message) as EditText
        val messageText = messageView.text.toString()

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, messageText)
        startActivity(intent)
    }
}