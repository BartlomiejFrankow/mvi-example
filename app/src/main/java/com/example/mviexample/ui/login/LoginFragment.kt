package com.example.mviexample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mviexample.R
import com.example.mviexample.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { viewState ->
                processViewState(viewState)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentLoginBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.email.doOnTextChanged { text, _, _, _ ->
            viewModel.emailChanged(text?.toString().orEmpty())
        }

        binding.password.doOnTextChanged { text, _, _, _ ->
            viewModel.passwordChanged(text?.toString().orEmpty())
        }

        binding.loginButton.setOnClickListener {
            viewModel.signInButtonClicked()
        }
    }

    private fun processViewState(viewState: LoginViewState) {
        if (viewState.goToDetailsScreen) navigateToDetailScreen()

        binding.loader.isVisible = viewState.showLoader

        binding.emailInputLayout.error = viewState.emailError?.let { getString(it) } ?: ""

        binding.passwordInputLayout.error = viewState.passwordError?.let { getString(it) } ?: ""
    }

    private fun navigateToDetailScreen() = findNavController().navigate(R.id.loginFragment_to_profileFragment)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
