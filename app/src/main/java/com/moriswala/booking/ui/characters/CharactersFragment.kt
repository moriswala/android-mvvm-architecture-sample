package com.moriswala.booking.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.data.local.CharacterDao
import com.moriswala.booking.data.remote.CharacterRemoteDataSource
import com.moriswala.booking.databinding.CharactersFragmentBinding
import com.moriswala.booking.ui.characters.CharactersAdapter
import com.moriswala.booking.ui.characters.CharactersViewModel
import com.moriswala.booking.utils.Resource
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : BaseFragment(), CharactersAdapter.CharacterItemListener {

    override fun getLayoutResId(): Int = R.layout.characters_fragment

    private var binding: CharactersFragmentBinding by autoCleared()
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = CharactersFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        hideToolbar()
        binding.btnLogout.setOnClickListener{
            viewModel.auth().signOut()
            getNavController().navigate(R.id.action_charactersFragment_to_loginFragment)
        }
        binding.issueNewTicketFab.setOnClickListener{
            getNavController().navigate(R.id.action_charactersFragment_to_newTicketFramgnet)
        }
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        getNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to characterId)
        )
    }
}
