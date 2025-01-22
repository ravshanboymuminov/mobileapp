package com.example.firstprojwct

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager
    private val fullNameField: TextInputEditText
        get() = findViewById(R.id.fullName)

    private val fullNameLayout: TextInputLayout
        get() = findViewById(R.id.textInputFullName)

    private val emailField: TextInputEditText
        get() = findViewById(R.id.validEmail)

    private val emailLayout: TextInputLayout
        get() = findViewById(R.id.textInputValidEmail)

    private val phoneField: TextInputEditText
        get() = findViewById(R.id.phoneNumber)

    private val phoneLayout: TextInputLayout
        get() = findViewById(R.id.textInputPhoneNumber)

    private val passwordField: TextInputEditText
        get() = findViewById(R.id.strongPassword)

    private val passwordLayout: TextInputLayout
        get() = findViewById(R.id.textInputStrongPassword)

    private val termsCheckBox: CheckBox
        get() = findViewById(R.id.agreementCheck)

    private val nextButton: Button
        get() = findViewById(R.id.btnNext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nextButton.setOnClickListener { handleNextButtonClick() }
        val loginButton = findViewById<TextView>(R.id.tvRegisterNow)
        loginButton.setOnClickListener {
            val goToCreateAccount = Intent(this, CreateAccountActivity::class.java)
            startActivity(goToCreateAccount)
        }
    }

    private fun handleNextButtonClick() {
        val fullName = fullNameField.text.toString().trim()
        val email = emailField.text.toString().trim()
        val phone = phoneField.text.toString().trim()
        val password = passwordField.text.toString().trim()
        val isTermsAccepted = termsCheckBox.isChecked

        clearError(fullNameLayout)
        clearError(emailLayout)
        clearError(phoneLayout)
        clearError(passwordLayout)

        if (!credentialsManager.validateCredentialsForSignUp(
                fullName,
                email,
                phone,
                password,
                isTermsAccepted
            )
        ) {
            if (!credentialsManager.isValidFullName(fullName)) {
                setError(fullNameLayout, getString(R.string.error_name_required))
            }
            if (!credentialsManager.isEmailValid(email)) {
                setError(emailLayout, getString(R.string.error_invalid_email))
            }
            if (!credentialsManager.isValidPhoneNumber(phone)) {
                setError(phoneLayout, getString(R.string.error_phone_number_required))
            }
            if (!credentialsManager.isValidPassword(password)) {
                setError(passwordLayout, getString(R.string.error_password_invalid))
            }
            if (!isTermsAccepted) {
                showToast(getString(R.string.error_terms_and_conditions_required))
            }
            return
        }

        registerUserAndNavigate(email, password)
    }

    private fun registerUserAndNavigate(email: String, password: String) {
        if (credentialsManager.isEmailAlreadyUsed(email)) {
            setError(emailLayout, getString(R.string.error_email_already_used))
            return
        }

        if (credentialsManager.registerUser(email, password)) {
            showToast(getString(R.string.success_registration))
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setError(layout: TextInputLayout, message: String) {
        layout.error = message
    }

    private fun clearError(layout: TextInputLayout) {
        layout.error = null
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
