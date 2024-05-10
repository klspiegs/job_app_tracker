package com.example.job_app_tracker

import java.util.Date

// location is a String storing the state of the job
// deadline is a Triple of Ints formatted like <Day, Month, Year>

class Job {

    private lateinit var jobName : String
    private lateinit var companyName : String
    private lateinit var location : String
    private lateinit var deadline : Triple<Int, Int, Int>
    private var applied : Boolean = true

    // getters and setters
    fun getJobName() : String {
        return jobName
    }

    fun setJobName(jobName : String) {
        this.jobName = jobName
    }

    fun getCompanyName() : String {
        return companyName
    }

    fun setCompanyName(companyName : String) {
        this.companyName = companyName
    }

    fun getLocation() : String {
        return location
    }

    fun setLocation(location : String) {
        this.location = location
    }

    fun getDeadline() : Triple<Int, Int, Int> {
        return deadline
    }

    fun setDeadline(deadline : Triple<Int, Int, Int>) {
        this.deadline = deadline
    }

    fun getApplied() : Boolean {
        return applied
    }

    fun setApplied(applied : Boolean) {
        this.applied = applied
    }

}
