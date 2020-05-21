package fr.isen.audranmeyrignac.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_life_cyle.*

class LifeCyleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cyle)
        logs.setText("Activity created")
    }

    override fun onStart() {
        super.onStart()
        logs.setText(logs.text.toString() + "\n" + "Activity started")
    }
    override fun onResume() {
        super.onResume()
        logs.setText(logs.text.toString() + "\n" + "Activity resumed")
        val frg = LifeCycleFragment.newInstance()
        frg.arguments = intent.extras
        supportFragmentManager.beginTransaction().add(R.id.frag_ctn, frg).commit()
    }

    override fun onStop() {
        super.onStop()
        logs.setText(logs.text.toString() + "\n" +"Activity stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Activity Destroyed")
    }
    private fun toast(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }
}
