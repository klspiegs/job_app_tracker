package com.example.jobtracker_mypart

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

    private lateinit var radioApplied: RadioButton
    private lateinit var editJobName: EditText
    // Add EditText variables for location, deadline, etc.
    private lateinit var checkboxApplied: CheckBox
    private lateinit var buttonAddData: Button
    private lateinit var editDeadline: EditText
    private lateinit var buttonPickDeadline: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        // Initialize views
        editJobName = findViewById(R.id.jobTitle)
        // Initialize other EditText views
        checkboxApplied = findViewById(R.id.checkbox_applied)
        buttonAddData = findViewById(R.id.button_add_data)
        editDeadline = findViewById(R.id.edit_deadline)
        buttonPickDeadline = findViewById(R.id.button_pick_deadline)

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
        val jobName = editJobName.text.toString()
        val applied = radioApplied.isChecked
        val location = "" // Get location from EditText
        val deadline = "" // Get deadline from EditText
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
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                editDeadline.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }
}


