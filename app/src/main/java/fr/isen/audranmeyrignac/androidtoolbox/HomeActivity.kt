package fr.isen.audranmeyrignac.androidtoolbox

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import java.io.BufferedReader
import java.io.File

class HomeActivity : AppCompatActivity() {

    val PREFS_FILENAME = "fr.isen.audranmeyrignac.androidtoolbax.prefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)

        lifecycle_button.setOnClickListener {
            startActivity(Intent(this@HomeActivity, LifeCyleActivity::class.java))
        }
        save_button.setOnClickListener{
            startActivity(Intent(this@HomeActivity, SaveActivity::class.java))
        }
        logout.setOnClickListener{
            flushPrefs()
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        }
        profile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("popuptitle", "Error")
            intent.putExtra("popuptext", "Sorry, that email address is already used!")
            intent.putExtra("popupbtn", "OK")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }
    }

    private fun flushPrefs(){
        val editor = prefs!!.edit()
        editor.clear()
        editor.commit()
    }
}
