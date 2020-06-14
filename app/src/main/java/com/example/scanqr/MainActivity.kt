package com.example.scanqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.example.scanqr.databinding.ActivityMainBinding
import com.example.scanqr.network.CheckEntity
import com.example.scanqr.view.ui.home.HomeFragment
import com.example.scanqr.view.ui.home.HomeViewModel
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        this.findNavController(R.id.nav_host_fragment)
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val topDestination = setOf(R.id.loginFragment, R.id.homeFragment)

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        appBarConfiguration = AppBarConfiguration.Builder(topDestination)
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        viewModel.properties.observe(this, Observer {
            if (it.IsSuccess) {
                Toast.makeText(this, "Quét mã thành công!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Quét mã thất bại!", Toast.LENGTH_LONG).show()
            }
            Log.d("TAG", "value: $it")
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {

                if (HomeFragment.isCheckIn) {
                    viewModel.sendRequest(CheckEntity(result.contents, "1"))
                } else {
                    viewModel.sendRequest(CheckEntity(result.contents, "2"))
                }
            }
        }


    }

}
