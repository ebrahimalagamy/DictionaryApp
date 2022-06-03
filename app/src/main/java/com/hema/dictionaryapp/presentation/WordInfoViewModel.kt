package com.hema.dictionaryapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hema.dictionaryapp.domain.usecase.WordInfoUsecase
import com.hema.dictionaryapp.utlis.ObjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val wordInfoUsecase: WordInfoUsecase
) : ViewModel() {

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _state = MutableLiveData(WordInfoState())
    val state: LiveData<WordInfoState> = _state


    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            wordInfoUsecase(query).onEach { result ->
                when (result) {
                    is ObjectState.Success -> {
                        _state.value = state.value?.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = false
                        )
                        Log.e("Tag",result.data.toString())
                    }
                    is ObjectState.Loading -> {
                        _state.value = state.value?.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is ObjectState.Error -> {
                        _state.value = state.value?.copy(
                            wordInfoItem = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(this)
        }


    }
}