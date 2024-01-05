package com.tridya.foodrecipeblog.models

object Validator {
    fun validateFirstName(fName: String): ValidationResult {
        return ValidationResult(
            (!fName.isNotEmpty() && fName.length >= 6)
        )
    }

    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (!lName.isNotEmpty() && lName.length >= 4)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNotEmpty() && password.length >= 6)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false,
)

