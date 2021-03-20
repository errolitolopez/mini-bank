package com.example.minibank.app.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CreateAccountForm {

    @NotNull(message = "Name is required.")
    @Length(max = 255, min = 1, message = "Name length is out of range.")
    private String name;

    @NotNull(message = "E-mail address is required.")
    @Length(max = 255, min = 6,
            message = "E-mail address length is out of range.")
    @Email(regexp = "^(?!.*?\\.\\.)([\\d\\w_\\.\\+]+)@([\\d\\w_\\.]+)\\.([\\d\\w]{1,})$",
            message = "Invalid E-mail address format.")
    private String email;
}
