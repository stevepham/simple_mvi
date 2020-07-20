package com.ht117.demo.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ht117.demo.R
import com.ht117.demo.adapter.UserAdapter
import com.ht117.demo.screen.base.IIntent
import com.ht117.demo.screen.base.IState
import com.ht117.demo.screen.base.IView
import com.ht117.domain.User
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

data class HomeState(val users: List<User> = listOf(),
                     val isLoading: Boolean = false,
                     val message: String? = null): IState

sealed class HomeIntent: IIntent {
    object RefreshUser: HomeIntent()
    object FetchUser: HomeIntent()
}

class HomeFragment: Fragment(R.layout.fragment_home), IView<HomeState> {

    private val viewModel: HomeViewModel by viewModel()
    private val adapter = UserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUsers.adapter = adapter

        btnRefresh.setOnClickListener {
            rvUsers.adapter = null
            lifecycleScope.launch {
                viewModel.intents.send(HomeIntent.RefreshUser)
            }
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            render(it)
        })

        lifecycleScope.launch {
            viewModel.intents.send(HomeIntent.RefreshUser)
        }
    }

    override fun render(state: HomeState) {
        with(state) {
            loaderBar.isVisible = isLoading
            rvUsers.isVisible = !isLoading
            btnRefresh.isEnabled = !isLoading
            if (!isLoading) {
                rvUsers.adapter = adapter
                adapter.submitList(users)
            }
            if (message != null) {
                Toast.makeText(requireContext(), "Failed $message", Toast.LENGTH_SHORT).show()
            }
        }
    }

}