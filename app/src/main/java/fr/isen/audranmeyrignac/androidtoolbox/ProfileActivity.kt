package fr.isen.audranmeyrignac.androidtoolbox

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import androidx.core.graphics.ColorUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.BufferedReader
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ProfileActivity : AppCompatActivity() {

    private var darkStatusBar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_profile)

        popup_window_text.text = readProfile("/details.json")

        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // If you want dark status bar, set darkStatusBar to true
                if (darkStatusBar) {
                    this.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                this.window.statusBarColor = Color.TRANSPARENT
                setWindowFlag(this, false)
            }
        }

        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        popup_window_view_with_border.alpha = 0f
        popup_window_view_with_border.animate().alpha(1f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        popup_window_button.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            popup_window_background.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        popup_window_view_with_border.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }

    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }

    private fun readProfile(file : String): String {
        val gson = Gson()
        val bufferedReader: BufferedReader = File(cacheDir.absolutePath + file).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val post = gson.fromJson(inputString, Post::class.java)
        val currentTime: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        val day: String =  "" + currentTime.get(0) + currentTime.get(1)
        val month: String =  "" + currentTime.get(3) + currentTime.get(4)
        val year: String =  "" + currentTime.get(6) + currentTime.get(7) + currentTime.get(8) + currentTime.get(9)

        val bDay = "" + post.postDate.get(0) + post.postDate.get(1)
        val bMonth = "" + post.postDate.get(3) + post.postDate.get(4)
        val bYear = "" + post.postDate.get(6) + post.postDate.get(7) + post.postDate.get(8) + post.postDate.get(9)

        val age : Int
        if (bMonth.toInt() >= month.toInt())
            if (bDay.toInt() >= day.toInt())
                age = year.toInt() - bYear.toInt()
            else
                age = year.toInt() - bYear.toInt() - 1
        else
            age = year.toInt() - bYear.toInt() - 1

        val stringBuilder = StringBuilder()
        stringBuilder.append("Nom: " + post.postName)
        stringBuilder.append("\nPrénom: " + post.postSurname)
        stringBuilder.append("\nAge: " + age.toString() + " ans")

        return stringBuilder.toString()
    }
}
