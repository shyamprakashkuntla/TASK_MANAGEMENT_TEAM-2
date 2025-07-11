package com.ve.task_management.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClientRequest {

    @NotBlank(message = "client name required")
    @Pattern(regexp = "^[A-Za-z ]{2,50}$",
            message = "Client name must contain only letters and spaces (2–50 characters)")
    private String clientName;

    private String industry;

    @Pattern(
            regexp = "^\\d{2}[A-Z]{5}\\d{4}[A-Z][1-9A-Z]Z[0-9A-Z]$",
            message = "Invalid GST number format"
    )
    private String gstNumber;

    @Pattern(regexp = "^[A-Za-z0-9 ,.-]{2,100}$",
            message = "Address must be 2–100 chars and can contain letters, numbers, commas, dots")
    private String address;

    @Pattern(regexp = "^[A-Za-z ]{2,50}$",
            message = "City must contain only letters and spaces")
    private String city;

    @NotBlank(message = "contact person is required")
    @Pattern(regexp = "^[A-Za-z ]{2,50}$",
            message = "Contact person must contain only letters and spaces (2–50 chars)")
    private String contactPerson;

    @Pattern(
            regexp = "^\\+\\d{1,3}\\d{10,15}$",
            message = "Contact number must be valid, starting with '+' and country code"
    )
    private String contactNumber;


    @Email(message = "Invalid email format")
    private String contactEmail;

    private String remarks;
}
