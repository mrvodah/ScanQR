package com.example.scanqr.view.ui.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blikoon.qrcodescanner.QrCodeActivity
import com.example.scanqr.databinding.FragmentHomeBinding
import com.example.scanqr.network.CheckEntity
import com.google.zxing.integration.android.IntentIntegrator

import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val REQUEST_SCAN_CHECK_IN = 101
    private val REQUEST_SCAN_CHECK_OUT = 102

    companion object {
        var isCheckIn = false
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(Manifest.permission.CAMERA),
            1
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_check_in.setOnClickListener {
            isCheckIn = true
            IntentIntegrator(activity).initiateScan()
//            startActivityForResult(Intent(activity, QrCodeActivity::class.java), REQUEST_SCAN_CHECK_IN)
        }

        button_check_out.setOnClickListener {
            isCheckIn = false
            IntentIntegrator(activity).initiateScan()
//            startActivityForResult(Intent(activity, QrCodeActivity::class.java), REQUEST_SCAN_CHECK_OUT)
        }

        button.setOnClickListener {
            tv_status.visibility = View.VISIBLE
        }
    }

}
