package com.example.scanqr.view.ui.home

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scanqr.MyItemRecyclerViewAdapter
import com.example.scanqr.databinding.FragmentHomeBinding
import com.example.scanqr.network.Employee
import com.example.scanqr.network.QrApi
import com.google.zxing.integration.android.IntentIntegrator

import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val REQUEST_SCAN_CHECK_IN = 101
    private val REQUEST_SCAN_CHECK_OUT = 102
    private lateinit var pd : ProgressDialog

    companion object {
        var isCheckIn = false
        var employeeCode = ""
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
        pd = ProgressDialog(context)

        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(Manifest.permission.CAMERA),
            1
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)

        button_check_in.setOnClickListener {
            if(!isCheckIn)
                IntentIntegrator(activity).initiateScan()
            else
                Toast.makeText(context, "Bạn đã checkin", Toast.LENGTH_SHORT).show()
//            startActivityForResult(Intent(activity, QrCodeActivity::class.java), REQUEST_SCAN_CHECK_IN)
        }

        button_check_out.setOnClickListener {
            if(isCheckIn)
                IntentIntegrator(activity).initiateScan()
            else
                Toast.makeText(context, "Bạn chưa checkin!", Toast.LENGTH_SHORT).show()
//            startActivityForResult(Intent(activity, QrCodeActivity::class.java), REQUEST_SCAN_CHECK_OUT)
        }

        Btn_History.setOnClickListener {
            if(isCheckIn){
                val getPropertiesDeferred = QrApi.retrofitService
                pd.show()
                getPropertiesDeferred.GetEmployee(
                    employeeCode,
                    "IwAR22qW3XVBJOXi5qGhbcgiMrVp0UJlkoRtq1_xFBrNEGB0tzNato2NG4PT8"
                )
                    .enqueue(object : Callback<List<Employee>> {
                        override fun onResponse(
                            call: Call<List<Employee>>,
                            response: Response<List<Employee>>
                        ) {
                            list.adapter = MyItemRecyclerViewAdapter(response.body()!!)

                            pd.dismiss()
                        }

                        override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                        }
                    })
            }
            else{
                Toast.makeText(context, "Phải checkin trước!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
