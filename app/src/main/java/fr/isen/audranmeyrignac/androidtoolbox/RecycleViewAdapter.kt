package fr.isen.audranmeyrignac.androidtoolbox

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler.view.*

class RecycleViewAdapter(private val users: ArrayList<outputAPI>, var mContext: Context): RecyclerView.Adapter<RecycleViewAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = users[position]
        holder.bind(users)
    }

    inner class UserViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(user: outputAPI) {
            view.name.text = user.displayName
            view.mail.text = user.displayMail
            Picasso.get().load(user.displayPictureMedium).into(view.avatar)
            view.details_button.setOnClickListener{
                val intent = Intent(mContext,DetailsActivity::class.java)
                intent.putExtra(Username, user.displayName)
                intent.putExtra(Gender, user.displayGender)
                intent.putExtra(Age, user.displayAge)
                intent.putExtra(Dob, user.displayDob)

                intent.putExtra(Mail, user.displayMail)
                intent.putExtra(Cell, user.displayCell)
                intent.putExtra(Phone, user.displayPhone)

                intent.putExtra(Address,user.displayStreet + ", " + user.displayPostCode + " " + user.displayCity + ", " + user.displayState + ", " + user.displayCountry)
                intent.putExtra(Coordinate, user.displayCoordinates)
                intent.putExtra(Timezone, user.displayTimezone)

                intent.putExtra(Nat,user.displayNat)
                intent.putExtra(Registration, user.displayDateRegistration)
                mContext.startActivity(Intent(intent))
            }
        }
    }

    companion object  {
        const val Username = "Username"
        const val Gender = "Gender"
        const val Age = "Age"
        const val Dob = "Dob"
        const val Mail = "Mail"
        const val Cell = "Cell"
        const val Phone = "Phone"
        const val Address = "Address"
        const val Coordinate = "Coordinate"
        const val Timezone = "Timezone"
        const val Nat = "Nat"
        const val Registration = "Registration"
    }
}