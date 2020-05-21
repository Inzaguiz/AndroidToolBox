package fr.isen.audranmeyrignac.androidtoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        lifecycle_button.setOnClickListener {
            startActivity(Intent(this@HomeActivity, LifeCyleActivity::class.java))
        }
    }
}
