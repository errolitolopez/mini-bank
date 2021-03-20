package com.example.minibank.app.controller;


import com.example.minibank.app.dto.request.BankAccountForm;
import com.example.minibank.app.dto.response.BankAccountResource;
import com.example.minibank.app.service.BankAccountService;
import com.example.minibank.commons.dto.response.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static com.example.minibank.commons.utils.ErrorUtils.throwFormValidationErrors;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ServiceResponse<BankAccountResource> createBankAccount(@RequestParam String referenceNo,
                                                                  @Valid @RequestBody BankAccountForm bankAccountForm,
                                                                  @ApiIgnore Errors errors) {
        throwFormValidationErrors(errors);
        return bankAccountService.createBankAccount(referenceNo, bankAccountForm);
    }
}
