package com.example.scanqr.view.ui.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.carmanager.view.ui.login.LoginNavigator
import com.example.carmanager.view.ui.login.LoginViewModel
import com.example.scanqr.R
import com.example.scanqr.databinding.FragmentLoginBinding
import com.example.scanqr.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), LoginNavigator {
    override fun onErrorEmail(s: String) {
        input_email.error = s
        input_email.requestFocus()
    }

    override fun onErrorPassword(s: String) {
        input_password.error = s
        input_password.requestFocus()
    }

    override fun onSuccess(user: Any?) {
        hideSoftKeyboard(activity!!)
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment)
    }

    override fun onFailure(s: String?) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun onRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        viewModel.setNavigator(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.hide()

        initClick()
    }

    private fun initClick() {
        scroll_view.setOnClickListener {
            hideSoftKeyboard(activity!!)
        }
    }

}
