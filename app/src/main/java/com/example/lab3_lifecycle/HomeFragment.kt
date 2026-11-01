package com.example.lab3_lifecycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    private val TAG = "LC_Home"

    override fun onAttach(context: android.content.Context) { super.onAttach(context); Log.d(TAG, "onAttach") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d(TAG, "onCreate") }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_detail, bundleOf("itemId" to 5))
        }
    }
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroyView() { super.onDestroyView(); Log.d(TAG, "onDestroyView") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
    override fun onDetach() { super.onDetach(); Log.d(TAG, "onDetach") }
}