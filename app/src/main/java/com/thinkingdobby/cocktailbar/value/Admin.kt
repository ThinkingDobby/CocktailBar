package com.thinkingdobby.cocktailbar.value

class Admin() {
    private var admin = true // 추후 변경

    fun getAdminValue(): Boolean {
        return admin
    }

    fun setAdminValue() {
        admin = true
    }
}