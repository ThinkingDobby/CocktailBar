package com.thinkingdobby.cocktailbar.login

class Admin {
    private var admin = false // 추후 변경
    private var password = ""

    private fun checkPassword(input: String): Boolean {
        return input.hashSHA256() == password
    }


    private fun setAdminValue() {
        admin = true
    }

    fun getAdminValue(): Boolean {
        return admin
    }

    fun checkPasswordExist(): Boolean {
        return password != ""
    }

    fun setPassword(password: String) {

        this.password = password.hashSHA256()
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