package fr.isen.audranmeyrignac.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validate.setOnClickListener {
            if (id.text.toString().equals("Admin") and password.text.toString().equals("123")) {
                toastId("sucess")
                goToHomeActivity()
            }
            else
                toastId("failure")
        }
    }
    private fun goToHomeActivity () {

        val goHome = Intent(this@LoginActivity, HomeActivity::class.java)
        goHome.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(goHome)
    }

    private fun toastId(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }
}
