package com.hema.dictionaryapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hema.dictionaryapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: WordInfoViewModel by viewModels()
    private val wordInfoAdapter = WordInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this observe on viewModel when the data change
//        vm.state.observe(this@MainActivity) {
//
//            wordInfoAdapter.listOfCoins = it.wordInfoItem
//        }
//
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            vm.onSearch(text.toString())

        }

        // launch not lifecycleawer ,launchWhenStarted lifrcycleawer
        lifecycleScope.launchWhenStarted {
            vm.stateFlow.collect {
                wordInfoAdapter.listOfCoins = it.wordInfoItem
            }
        }


        bindRecyclerView()
    }

    private fun bindRecyclerView() {
        binding.apply {
            rcWordInfo.apply {
                adapter = wordInfoAdapter
                layoutManager = LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL, false,
                )
                itemAnimator = null

            }
        }

    }

}
