package fr.isen.audranmeyrignac.androidtoolbox

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_save.*
import java.io.File
import java.io.PrintWriter
import java.lang.Exception
import java.util.*

class SaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        birthdate.setOnClickListener {
            var str:String = ""
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    if (day < 10)
                        str += "0$dayOfMonth/"
                    else
                        str += "$dayOfMonth/"
                    if (month < 10)
                        str += "0" + (monthOfYear+1) + "/"
                    else
                        str += "" + (monthOfYear+1) + "/"
                    str += "$year"

                    birthdate.text = str
                }, year, month, day
            )
            dpd.show()
        }
        submit.setOnClickListener {
            val post = Post(name.text.toString(), surname.text.toString(), birthdate.text.toString())
            val gson = Gson()
            val jsonString: String = gson.toJson(post)

            val file = File(cacheDir.absolutePath + "/details.json")
            file.writeText(jsonString)
            toast("User data sent")
        }
    }

    private fun toast(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }
}
