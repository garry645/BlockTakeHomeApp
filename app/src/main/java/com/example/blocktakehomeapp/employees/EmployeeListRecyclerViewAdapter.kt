package com.example.blocktakehomeapp.employees

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blocktakehomeapp.R
import com.example.blocktakehomeapp.databinding.EmployeeListItemBinding


class EmployeeListRecyclerViewAdapter :
    RecyclerView.Adapter<EmployeeListRecyclerViewAdapter.ViewHolder>() {

    private var values = mutableListOf<Employee>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateEmployees(employees: List<Employee>) {
        values = employees.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EmployeeListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (values.isNotEmpty()) {
            val employee = values[position]
            with(holder) {
                with(employee) {
                    //TransitionManager.beginDelayedTransition(binding.root, AutoTransition())
                    with(binding) {
                        Glide.with(this.root)
                            .load(photo_url_small)
                            .placeholder(R.drawable.placeholder)
                            .into(employeePhotoSmallIV)
                        employeeFullNameTV.text = full_name?.trim() ?: "Unavailable"
                        employeeTypeTV.text = employee_type?.trim() ?: "Unavailable"
                        employeeBiographyTV.text = biography?.trim() ?: "Unavailable"
                        employeeUUIDTV.text = uuid?.trim() ?: "Unavailable"
                        employeePhoneNumberTV.text = phone_number?.trim() ?: "Unavailable"
                        employeeEmailAddressTV.text = email_address?.trim() ?: "Unavailable"
                        expandImageButton.setOnClickListener {
                            if (!expanded) {
                                //expand
                                expandImageButton.setImageResource(R.drawable.ic_baseline_expand_less_24)
                                toggleViewVisibility(binding, false)
                                employeePhotoSmallIV.visibility = GONE
                                employeePhotoLargeIV.run {
                                    Glide.with(this)
                                        .load(photo_url_large)
                                        .placeholder(R.drawable.placeholder)
                                        .into(this)
                                    this.visibility = VISIBLE
                                }
                                expanded = true
                            } else {
                                //compress
                                collapseView(binding, employee)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    private fun collapseView(binding: EmployeeListItemBinding, employee: Employee) {
        with(binding) {
            expandImageButton.setImageResource(R.drawable.ic_baseline_expand_more_24)
            toggleViewVisibility(binding, true)
            employeePhotoLargeIV.visibility = GONE
            employeePhotoSmallIV.run {
                Glide.with(this).load(employee.photo_url_small).into(this)
                this.visibility = VISIBLE
            }
            employee.expanded = false
        }
    }

    private fun toggleViewVisibility(binding: EmployeeListItemBinding, compress: Boolean) {
        with(binding) {
            val visibility = if (compress) GONE else VISIBLE
            uuidLabel.visibility = visibility
            employeePhoneNumberLabel.visibility = visibility
            employeeEmailLabel.visibility = visibility
            biographyLabel.visibility = visibility

            employeePhotoLargeIV.visibility = visibility
            employeePhotoSmallIV.visibility = if (compress) VISIBLE else GONE
            employeeTypeTV.visibility = visibility
            employeeUUIDTV.visibility = visibility
            employeePhoneNumberTV.visibility = visibility
            employeeEmailAddressTV.visibility = visibility
            biographyLabel.visibility = visibility
            employeeBiographyTV.visibility = visibility
        }
    }

    inner class ViewHolder(val binding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

