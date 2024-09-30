package com.llm.walraimer.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email
        @Pattern(regexp = "^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+\\.([a-z]+)?$", message = "Email must have an valid syntax.")
        String email,
        @NotBlank
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}-[0-9]{4}$")
        String phone,

        @NotBlank(message = "Password cannot be blank")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%¨|&*()_+=;:?.^])[A-Za-z\\d!@#$%¨|&*()_+=;:?.^]{8,}$",
                message = "Password must contain at least one lowercase character, one uppercase character, one special character, one number, and must be at least 8 characters long"
        )
        String password
        ) {
}
