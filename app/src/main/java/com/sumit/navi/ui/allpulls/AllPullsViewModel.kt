package com.sumit.navi.ui.allpulls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumit.navi.models.Pull
import com.sumit.navi.other.Resource
import com.sumit.navi.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllPullsViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val _pulls = MutableLiveData<Resource<List<Pull>>>()

    val pulls: LiveData<Resource<List<Pull>>>
        get() = _pulls

     fun getPulls() = viewModelScope.launch {
        _pulls.postValue(Resource.loading(null))
        mainRepository.getPulls().let {
            if (it.isSuccessful) {
                _pulls.postValue(Resource.success(it.body()))
            } else {
                _pulls.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}