package com.example.firstprojwct


object CredentialsManager {

    private val emailPattern = ("[a-zA-Z0-9\\+\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][0-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][0-zA-Z0-9\\-]{0,25}" +
            ")+").toRegex()

    private val registeredUsers = mutableMapOf<String, String>()

    fun isEmailValid(email: String): Boolean {
        return email.matches(emailPattern)
    }

    fun isEmailAlreadyUsed(email: String): Boolean {
        return registeredUsers.containsKey(email.lowercase())
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun registerUser(email: String, password: String): Boolean {
        if (isEmailAlreadyUsed(email)) {
            return false
        }
        registeredUsers[email.lowercase()] = password
        return true
    }

    fun validateLogin(email: String, password: String): Boolean {
        return registeredUsers[email.lowercase()] == password
    }

    fun validateCredentials(email: String, password: String): Boolean {
        return isEmailValid(email) && isValidPassword(password)
    }

    fun isValidFullName(fullName: String): Boolean {
        return fullName.isNotEmpty()
    }

    fun isHardcodedCredentials(email: String, password: String): Boolean {
        val hardcodedEmail = "test@te.st"
        val hardcodedPassword = "1234"
        return email == hardcodedEmail && password == hardcodedPassword
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val phonePattern = "^[0-9]{9,}$".toRegex()
        return phoneNumber.matches(phonePattern)
    }

    fun isTermsAccepted(isChecked: Boolean): Boolean {
        return isChecked
    }

    fun validateCredentialsForSignUp(
        fullName: String,
        email: String,
        phoneNumber: String,
        password: String,
        isTermsAccepted: Boolean
    ): Boolean {
        return isValidFullName(fullName) &&
                isEmailValid(email) &&
                isValidPhoneNumber(phoneNumber) &&
                isValidPassword(password) &&
                isTermsAccepted(isTermsAccepted)
    }
}
