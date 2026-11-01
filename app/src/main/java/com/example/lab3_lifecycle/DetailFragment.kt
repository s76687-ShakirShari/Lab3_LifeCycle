package com.example.lab3_lifecycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {
    private val TAG = "LC_Detail"

    override fun onAttach(context: android.content.Context) { super.onAttach(context); Log.d(TAG, "onAttach") }
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); Log.d(TAG, "onCreate") }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        val itemId = arguments?.getInt("itemId") ?: 0
        view.findViewById<TextView>(R.id.tvItemId).text = "Item ID: $itemId"
    }
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroyView() { super.onDestroyView(); Log.d(TAG, "onDestroyView") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
    override fun onDetach() { super.onDetach(); Log.d(TAG, "onDetach") }
}