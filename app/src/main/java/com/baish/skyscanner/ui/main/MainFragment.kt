package com.baish.skyscanner.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.baish.skyscanner.R
import com.baish.skyscanner.data.common.BaseFragment
import com.baish.skyscanner.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null
    private val vm by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTechProjects()
        setupListeners()
        vm.loadProjects()
        vm.projectFiles
    }

    private fun setupListeners() {
        binding?.apod?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_apodFragment)
        }
        menuClicks()
    }

    private fun setupTechProjects() {
        vm.project.observe(viewLifecycleOwner, Observer {
            binding?.textofImage?.text = it.title
        })
        vm.projectFiles.observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.type).into(binding?.imageTwo)
        })
    }

    private fun menuClicks() {
        binding?.toolbar?.toolbar?.setOnMenuItemClickListener {
            when(it.itemId){

                R.id.conact ->{
                    val client = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("zhanysch@gmai.com")
                    }
                    startActivity(Intent.createChooser(client,"contact"))
                    return@setOnMenuItemClickListener true
                }

                R.id.FAQ ->{
                    findNavController().navigate(R.id.action_mainFragment_to_faqFragment)
                    return@setOnMenuItemClickListener true
                }

                R.id.terms ->{
                    findNavController().navigate(R.id.action_mainFragment_to_termsConditionsFragment)
                    return@setOnMenuItemClickListener true
                }

                R.id.Privacy -> {
                    findNavController().navigate(R.id.action_mainFragment_to_privacyFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


