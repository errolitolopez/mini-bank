package com.example.minibank.app.controller;

import com.example.minibank.app.dto.request.CashInForm;
import com.example.minibank.app.dto.request.CashOutForm;
import com.example.minibank.app.dto.response.BankTransactionResource;
import com.example.minibank.app.service.BankTransactionService;
import com.example.minibank.commons.dto.response.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static com.example.minibank.commons.utils.ErrorUtils.throwFormValidationErrors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BankTransactionController {

    private final BankTransactionService bankTransactionService;

    @GetMapping
    public ServiceResponse<List<BankTransactionResource>> getAllBankingTransactionsByAccountNumber(
            @RequestParam String accountNumber) {
        return bankTransactionService.getAllBankingTransactionsByAccountNumber(accountNumber);
    }

    @PostMapping("/cash-in")
    public ServiceResponse<BankTransactionResource> cashIn(@RequestParam String accountNumber,
                                                           @Valid @RequestBody CashInForm cashInForm,
                                                           @ApiIgnore Errors errors) {
        throwFormValidationErrors(errors);
        return bankTransactionService.cashIn(accountNumber, cashInForm);

    }

    @PostMapping("/cash-out")
    public ServiceResponse<BankTransactionResource> cashOut(@RequestParam String accountNumber,
                                                            @Valid @RequestBody CashOutForm cashOutForm,
                                                            @ApiIgnore Errors errors) {
        throwFormValidationErrors(errors);
        return bankTransactionService.cashOut(accountNumber, cashOutForm);
    }

    @PutMapping("/close-account")
    public ServiceResponse<BankTransactionResource> closeAccount(@RequestParam String accountNumber) {
        return bankTransactionService.closeAccount(accountNumber);
    }
}
