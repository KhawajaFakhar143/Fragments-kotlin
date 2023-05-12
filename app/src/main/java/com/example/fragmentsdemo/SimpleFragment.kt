package com.example.fragmentsdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SimpleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SimpleFragment : Fragment(R.layout.fragment_simple) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val YES = 0
    private val NO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment.
        val rootView: View =
            inflater.inflate(R.layout.fragment_simple, container, false);
        val radioGroup: RadioGroup = rootView.findViewById(R.id.radio_group);

        val ratingBar: RatingBar = rootView.findViewById(R.id.rating_bar)

        ratingBar.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, _ ->
                ratingBar.rating = rating
            }
        radioGroup.setOnCheckedChangeListener { _, checkId ->
            val radioButton: View = radioGroup.findViewById(checkId)
            val llRatingBar: LinearLayout = rootView.findViewById(R.id.ll_rating)
            val index: Int = radioGroup.indexOfChild(radioButton)
            val textView: TextView = rootView.findViewById(R.id.fragment_header)
            when (index) {
                YES -> {
                    textView.setText(R.string.yes_message)
                    llRatingBar.visibility = View.VISIBLE

                }
                NO -> {
                    textView.setText(R.string.no_message)
                    llRatingBar.visibility = View.GONE

                }
                else -> {}
            }
        };
// Return the View for the fragment's UI.
        return rootView;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SimpleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SimpleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}