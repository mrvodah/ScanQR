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

import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val REQUEST_SCAN_CHECK_IN = 101
    private val REQUEST_SCAN_CHECK_OUT = 102

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
            startActivityForResult(Intent(activity, QrCodeActivity::class.java), REQUEST_SCAN_CHECK_IN)
        }

        button_check_out.setOnClickListener {
            startActivityForResult(Intent(activity, QrCodeActivity::class.java), REQUEST_SCAN_CHECK_OUT)
        }

        button.setOnClickListener {
            tv_status.visibility = View.VISIBLE
        }

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            if (it.IsSuccess) {
                Toast.makeText(activity, "Quét mã thành công!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Quét mã thất bại!", Toast.LENGTH_LONG).show()
            }
            Log.d("TAG", "value: $it")
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_SCAN_CHECK_IN) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    val value = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult")
                    value?.let {
                        viewModel.sendRequest(CheckEntity(it, "1"))
                        Log.d("TAG", "CheckIn: $it")
                    }
                }
            }
        } else if(requestCode == REQUEST_SCAN_CHECK_OUT) {
            if(resultCode == Activity.RESULT_OK) {
                if(data != null) {
                    val value = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult")
                    value?.let {
                        viewModel.sendRequest(CheckEntity(it, "2"))
                        Log.d("TAG", "CheckOut: $it")
                    }
                }
            }
        }
    }

}
