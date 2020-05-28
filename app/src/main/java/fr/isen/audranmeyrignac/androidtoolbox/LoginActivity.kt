package fr.isen.audranmeyrignac.androidtoolbox

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    val PREFS_FILENAME = "fr.isen.audranmeyrignac.androidtoolbax.prefs"
    val USER_ID = "userid"
    val USER_PASSWORD = "userpassword"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val prefs = getSharedPreferences(PREFS_FILENAME, 0)

        if (prefs.getString(USER_ID, "").equals("Admin") and prefs.getString(USER_PASSWORD, "").equals("123")) {
            goToHomeActivity()
        }

        validate.setOnClickListener {
            if (id.text.toString().equals("Admin") and password.text.toString().equals("123")) {
                toastId("You are connected")
                saveUserInfos(id.text.toString(), password.text.toString())
                goToHomeActivity()
            }
            else
                toastId("Wrong Id or Password")
        }
    }

    private fun saveUserInfos(id: String, pass: String){
        val prefs = getSharedPreferences(PREFS_FILENAME, 0)

        val editor = prefs.edit()
        editor.putString(USER_ID, id)
        editor.putString(USER_PASSWORD, pass)
        editor.apply()
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
