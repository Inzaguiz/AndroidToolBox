package fr.isen.audranmeyrignac.androidtoolbox

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_web_services.*

class WebServicesActivity : AppCompatActivity() {

    private val permissionInternet = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)
        makeRequest()
    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://randomuser.me/api/?results=25"
        val stringRequest = StringRequest(
            Request.Method.GET, url, Response.Listener<String> { response ->
                val gson = Gson()
                val result = gson.fromJson(response, Result::class.java)
                var contactList = ArrayList<OutputAPI>()
                result.results?.let {
                    it.forEach {
                        var contactModel = OutputAPI()
                        contactModel.displayName = it.name?.first + " " + it.name?.last
                        contactModel.displayMail = it.email
                        contactModel.displayPictureMedium = it.picture?.large
                        contactModel.displayGender = it.gender
                        contactModel.displayAge = it.dob?.age
                        contactModel.displayDob = it.dob?.date

                        contactModel.displayStreet = it.location?.street?.number + " " + it.location?.street?.nameStreet
                        contactModel.displayCity = it.location?.city
                        contactModel.displayState = it.location?.state
                        contactModel.displayCountry = it.location?.country
                        contactModel.displayPostCode = it.location?.postcode
                        contactModel.displayCoordinates = "Long : " + it.location?.coordinates?.longitude + " Lat : " +  it.location?.coordinates?.latitude
                        contactModel.displayTimezone = it.location?.timezone?.description + " Offset : " + it.location?.timezone?.offset

                        contactModel.displayCell = it.cell
                        contactModel.displayPhone = it.phone

                        contactModel.displayNat = it.nat
                        contactModel.displayDateRegistration = it.registered?.date

                        contactList.add(contactModel)
                    }
                }
                userList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                userList.adapter = RecycleViewAdapter(contactList, this)
            },
            Response.ErrorListener { Toast.makeText(this, "Couldn't Load contact list", Toast.LENGTH_LONG).show()})
        queue.add(stringRequest)
    }
    private fun permissionDenied() {
        Toast.makeText(this, "Aucune connexion", Toast.LENGTH_LONG).show()
        finish()
    }

}
