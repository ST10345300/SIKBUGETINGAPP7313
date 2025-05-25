package com.example.sikbugetingapp7313.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sikbugetingapp7313.R
import com.example.sikbugetingapp7313.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: OnboardingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onboardingItems = listOf(
            OnboardingItem(R.drawable.ic_onboard1, "Track Your Budget", "Stay on top of your spending."),
            OnboardingItem(R.drawable.ic_onboard2, "Set Goals", "Achieve your financial goals."),
            OnboardingItem(R.drawable.ic_onboard3, "Stay Informed", "Get reports and insights.")
        )

        adapter = OnboardingAdapter(onboardingItems)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabIndicator, binding.viewPager) { _, _ -> }.attach()
        binding.btnContinue.setOnClickListener {
            if (binding.viewPager.currentItem < onboardingItems.size - 1) {
                binding.viewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

