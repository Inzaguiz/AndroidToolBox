package fr.isen.audranmeyrignac.androidtoolbox

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_permissions.*


class PermissionsActivity : AppCompatActivity() {

    private val permissionRequestReadContacts = 1
    private val permissionRequestReadGallery = 2
    private val permissionRequestCamera = 3
    private val requestedCode = 4
    private val permissionLocation= 5
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)
        capture.setOnClickListener{cameraGallery()}
    }

    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), permissionRequestReadContacts)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), permissionRequestReadGallery)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), permissionRequestCamera)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION), permissionLocation)
        }
        else {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            valueLongitude.text=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.longitude.toString()
            valueLatitude.text=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.latitude.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        requestPermissions()
        getAllContacts()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            permissionRequestReadGallery -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    requestPermissions()
                }
                else if (grantResults.isNotEmpty()){
                    permissionDenied()
                }
                return
            }
        }
        when (requestCode) {
            permissionRequestReadContacts -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getAllContacts()
                    requestPermissions()
                }
                else if (grantResults.isNotEmpty()){
                    permissionDenied()
                }
                return
            }
        }
        when (requestCode) {
            permissionRequestCamera -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    requestPermissions()
                }
                else if (grantResults.isNotEmpty()){
                    permissionDenied()
                }
                return
            }
        }
        when (requestCode) {
            permissionLocation -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    requestPermissions()
                }
                else if (grantResults.isNotEmpty()){
                    permissionDenied()
                }
                return
            }
        }
        return
    }

    private fun permissionDenied() {
        Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun getAllContacts() {
        val nameList = ArrayList<String>()
        val contentResolver = contentResolver
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        if ((cursor != null) && (cursor.count>0)){
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                nameList.add(name)
            }
            val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nameList)
            contactList.adapter = adapter
        }
        cursor?.close()
        return
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((resultCode == Activity.RESULT_OK)&&(requestCode == requestedCode)) {
            if (data?.data != null) {
                capture.setImageURI(data.data)
            }
            else {
                val imageBitmap = data?.extras?.get("data") as? Bitmap
                capture.setImageBitmap(imageBitmap)
            }
        }
    }

    private fun cameraGallery(){
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/"

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val intentChoose = Intent.createChooser(galleryIntent, "Gallery")
        intentChoose.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        startActivityForResult(intentChoose, requestedCode)
    }

}