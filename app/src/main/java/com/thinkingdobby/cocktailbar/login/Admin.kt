package com.thinkingdobby.cocktailbar.login

import com.thinkingdobby.cocktailbar.data.MyApplication

class Admin {
    companion object {
        private var admin = false
    }
    private fun checkPassword(input: String): Boolean {
        return input.hashSHA256() == MyApplication.prefs.getString("password")
    }


    private fun setAdminValue() {
        admin = true
    }

    fun unsetAdminValue() {
        admin = false
    }

    fun getAdminValue(): Boolean {
        return admin
    }


    fun setPassword(password: String) {
        MyApplication.prefs.setString("password", password.hashSHA256())
    }

    fun login(input: String): Boolean {
        if (checkPassword(input)) {
            setAdminValue()
            return true
        } else {
            return false
        }
    }
}