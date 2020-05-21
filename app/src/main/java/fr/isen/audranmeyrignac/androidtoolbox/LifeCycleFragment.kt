package fr.isen.audranmeyrignac.androidtoolbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_life_cycle.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LifeCycleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LifeCycleFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onStart() {
        super.onStart()
        logs_frag.setText(logs_frag.text.toString() + "\n" + "Fragment started")
    }

    override fun onResume() {
        super.onResume()
        logs_frag.setText(logs_frag.text.toString() + "\n" + "Fragment resumed")
    }

    override fun onStop() {
        super.onStop()
        logs_frag.setText(logs_frag.text.toString() + "\n" + "Fragment stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("Fragment destroyed")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logs_frag.setText("Fragment created")
    }

    private fun toast(str: String) {
        Toast.makeText(activity, str, Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LifeCycleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            LifeCycleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
