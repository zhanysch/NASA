package com.baish.skyscanner.ui.main.techproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.baish.skyscanner.databinding.TechprojecktLayoutBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class TechProjectFragment : Fragment() {

    var binding: TechprojecktLayoutBinding? = null
    private val vm by viewModel<TechProjectViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TechprojecktLayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTechProjects()
        vm.loadProjects()
        vm.projectFiles
        vm.files
    }

    private fun setupTechProjects() {
        vm.project.observe(viewLifecycleOwner, Observer {
            binding?.techProTitle?.text = it.title
            binding?.techProExplanation?.text = it.description
            binding?.techProBenefits?.text = it.benefits
            binding?.techName?.text = it.leadOrganization.name
            binding?.techCity?.text = it.leadOrganization.city
            binding?.techCoutnry?.text = it.leadOrganization.country
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}