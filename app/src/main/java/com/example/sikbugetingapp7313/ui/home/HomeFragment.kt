package com.example.sikbugetingapp7313.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.sikbugetingapp7313.R
import com.example.sikbugetingapp7313.databinding.FragmentHomeBinding
import com.example.sikbugetingapp7313.ui.budget.AddBudgetFragment
import com.example.sikbugetingapp7313.ui.settings.SettingsFragment
import com.example.sikbugetingapp7313.ui.stats.StatsFragment
import com.example.sikbugetingapp7313.ui.achievements.AchievementsFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Load the default screen (Stats)
        if (savedInstanceState == null) {
            loadFragment(StatsFragment(), "StatsFragment")
        }

        // Handle bottom navigation selections
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.nav_stats -> StatsFragment()
                R.id.nav_add -> AddBudgetFragment()
                R.id.nav_achievements -> AchievementsFragment()
                R.id.nav_settings -> SettingsFragment()
                else -> StatsFragment()
            }

            loadFragment(selectedFragment, selectedFragment::class.simpleName ?: "Fragment")
            true
        }

        return binding.root
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        childFragmentManager.commit {
            replace(R.id.fragmentContainer, fragment, tag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
