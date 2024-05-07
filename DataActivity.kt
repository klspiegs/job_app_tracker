package com.example.job_app_tracker

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

import android.app.DatePickerDialog
import android.widget.DatePicker
import java.util.*


class DataActivity : AppCompatActivity() {

    // Add EditText variables for location, deadline, etc.
    private lateinit var checkboxApplied: CheckBox
    private lateinit var buttonAddData: Button
    private lateinit var editDeadline: EditText
    private lateinit var editJobName: EditText
    private lateinit var editCompanyName: EditText
    private lateinit var editLocation: EditText

    private lateinit var buttonPickDeadline: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        // Initialize other EditText views
        checkboxApplied = findViewById(R.id.checkbox_applied)
        buttonAddData = findViewById(R.id.button_add_data)
        editDeadline = findViewById(R.id.edit_deadline)
        editJobName = findViewById(R.id.jobTitle)
        editCompanyName = findViewById(R.id.companyName)
        editLocation = findViewById(R.id.jobLocation)
        buttonPickDeadline = findViewById(R.id.buttonPickDeadline)

        // Set onClickListener for buttonPickDeadline to show DatePicker dialog
        buttonPickDeadline.setOnClickListener {
            showDatePickerDialog()
        }
        buttonAddData.setOnClickListener {
            addData()
        }
    }

    private fun addData() {
        // Retrieve data from views
        val deadline = editDeadline.text.toString()
        val jobName = editJobName.text.toString()
        val companyName = editCompanyName.text.toString()
        val location = editLocation.text.toString()
        val didApply = checkboxApplied.isChecked


    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display the selected date in the EditText field
                val selectedDate = "${monthOfYear + 1}/$dayOfMonth/$year"
                editDeadline.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }
}


