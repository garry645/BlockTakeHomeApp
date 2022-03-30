package com.example.blocktakehomeapp.employees

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.blocktakehomeapp.retrofit.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EmployeesListViewModel @Inject constructor() : ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    var employeeList: MutableList<Employee>? = mutableListOf()
    val employeeListFilter = MutableLiveData<List<Employee>>()
    val loaded = MutableLiveData(false)
    val loading = MutableLiveData(false)
    private val retrofitService = RetrofitService.getInstance()

    val toast = MutableLiveData<String?>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        toast.postValue("Exception handled: ${throwable.localizedMessage}")
        loading.postValue(false)
    }

    fun getAllEmployees() {
        loading.postValue(true)
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofitService.getAllEmployees()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        employeeList = it.employees as MutableList<Employee>
                        employeeListFilter.postValue(it.employees)
                        loaded.postValue(true)
                    }
                } else {
                    errorMessage.postValue(response.errorBody().toString())
                    employeeList = mutableListOf()
                    employeeListFilter.postValue(mutableListOf())
                }
                loading.postValue(false)
            }
        }
    }

    fun filterEmployees(filterString: String) {
        employeeListFilter.value = employeeList?.filter {
            if (!it.full_name.isNullOrEmpty()) {
                return@filter it.full_name.toLowerCase(Locale.ROOT)
                    .contains(filterString.toLowerCase(Locale.ROOT))
            } else return@filter false
        }
    }
}