package com.example.blocktakehomeapp.employees

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blocktakehomeapp.R
import com.example.blocktakehomeapp.databinding.FragmentEmployeeListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    private lateinit var binding: FragmentEmployeeListBinding

    private val employeesListViewModel: EmployeesListViewModel by viewModels()

    private lateinit var employeeListAdapter: EmployeeListRecyclerViewAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentEmployeeListBinding.inflate(layoutInflater)

        employeeListAdapter = EmployeeListRecyclerViewAdapter()

        employeesListViewModel.employeeListFilter.observe(viewLifecycleOwner) { employees ->
            if (!employees.isNullOrEmpty()) {
                binding.employeeEmptyTV.visibility = GONE
            } else {
                binding.employeeEmptyTV.visibility = VISIBLE
            }
            employeeListAdapter.updateEmployees(employees)
        }

        employeesListViewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressCircular.visibility = VISIBLE else binding.progressCircular.visibility = GONE
        }

        employeesListViewModel.toast.observe(viewLifecycleOwner) { string ->
            string?.let {
                employeesListViewModel.toast.value = null
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            employeesListViewModel.getAllEmployees()
        }

        binding.searchBarET.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                employeesListViewModel.filterEmployees(p0?.toString() ?: "")
            }
        })

        binding.employeeRV.run {
            layoutManager = LinearLayoutManager(context)
            adapter = employeeListAdapter
        }

        employeesListViewModel.loaded.observe(viewLifecycleOwner) { loaded ->
            if (loaded) employeeListAdapter.notifyDataSetChanged()
            else employeesListViewModel.getAllEmployees()
        }

        return binding.root
    }
}