package com.example.blocktakehomeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.blocktakehomeapp.employees.Employee
import com.example.blocktakehomeapp.employees.EmployeesList
import com.example.blocktakehomeapp.employees.EmployeesListViewModel
import com.example.blocktakehomeapp.retrofit.RetrofitService
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

open class EmployeesListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var employeesListViewModel: EmployeesListViewModel

    private lateinit var retrofitService: RetrofitService
    private lateinit var mockResponse: Response<EmployeesList>

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
        employeesListViewModel = spyk()

        retrofitService = mockk(relaxed = true)
        mockResponse = mockk(relaxed = true)

    }

    @Test
    fun testGetAllEmployeesEmpty() {
        every { mockResponse.body() } returns EmployeesList(listOf())
        coEvery { retrofitService.getAllEmployees() } returns mockResponse

        employeesListViewModel.employeeListFilter.observeForever {}
        employeesListViewModel.getAllEmployees()

        assert(employeesListViewModel.employeeList != null)
        assert(employeesListViewModel.employeeList!!.isEmpty())
    }

    @Test
    fun testGetAllEmployeesNotEmpty() {
        coEvery { mockResponse.body() } returns EmployeesList(getMockEmployeeList())
        coEvery { retrofitService.getAllEmployees() } returns mockResponse

        employeesListViewModel.employeeListFilter.observeForever {}
        employeesListViewModel.getAllEmployees()

        assert(employeesListViewModel.employeeList != null)
    }

    private fun getMockEmployeeList(): MutableList<Employee> {
        val mockEmployeesList = mutableListOf<Employee>()

        for (i in 0..10) {
            mockEmployeesList.add(Employee(
                "UUid: $i",
                "name: $i",
                "phone_number: $i",
                "emai_address: $i",
                "biography: $i",
                "url_small: $i",
                "url_large: $i",
                "team: $i",
                "type: $i",
                false
            ))
        }

        return mockEmployeesList
    }
}