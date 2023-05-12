package com.example.fragmentsdemo

import android.app.Activity
import android.app.ActivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private var buttonOpen: Button? = null
    private var isFragmentDisplayed = false
    private val STATE_FRAGMENT = "state+of+fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpen = findViewById(R.id.open)

        if(savedInstanceState != null){
            isFragmentDisplayed =savedInstanceState.getBoolean(STATE_FRAGMENT)
            if(isFragmentDisplayed){
                buttonOpen?.setText(R.string.close)
            }
        }
        buttonOpen?.setOnClickListener {
            if (isFragmentDisplayed) {
                closeFragment()
            } else {
                displayFragment()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(outState)
    }



    private fun displayFragment() {
        val simpleFragment: SimpleFragment = SimpleFragment.newInstance("1", "2")
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(
            R.id.fragment_container,
            simpleFragment
        ).addToBackStack(null).commit();
        buttonOpen?.setText(R.string.close)
        isFragmentDisplayed = true

    }

    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
            buttonOpen?.setText(R.string.open)
            isFragmentDisplayed = false
        }
    }

}



