package hr.ikvakan.betshop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ikvakan.betshop.model.BetshopLocationModel
import hr.ikvakan.betshop.repository.Repository
import hr.ikvakan.betshop.retrofit.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BetShopViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<BetshopLocationModel>> = MutableLiveData()
    val dataState: LiveData<DataState<BetshopLocationModel>>
        get() = _dataState
      fun getBetshopLocationItem() {
          viewModelScope.launch {
              repository.getBetshopLocationItem()
                  .onEach { dataState -> _dataState.value = dataState }
                  .launchIn(viewModelScope)
          }
    }
}