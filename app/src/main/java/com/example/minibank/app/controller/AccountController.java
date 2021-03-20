package com.example.minibank.app.controller;

import com.example.minibank.account.dto.response.AccountResponse;
import com.example.minibank.app.dto.request.CreateAccountForm;
import com.example.minibank.app.dto.request.UpdateAccountForm;
import com.example.minibank.app.service.AccountService;
import com.example.minibank.commons.constant.SearchType;
import com.example.minibank.commons.dto.request.PageRequestParam;
import com.example.minibank.commons.dto.response.PageDTO;
import com.example.minibank.commons.dto.response.ServiceResponse;
import com.example.minibank.commons.exception.NotValidException;
import com.example.minibank.commons.utils.SearchTypeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;

import static com.example.minibank.commons.utils.ConverterUtils.convertToLong;
import static com.example.minibank.commons.utils.ErrorUtils.throwFormValidationErrors;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public PageDTO<AccountResponse> getAllAccounts(@RequestParam(defaultValue = "1", required = false) int page,
                                                   @RequestParam(defaultValue = "10", required = false) int size) {
        return accountService.getAllAccounts(new PageRequestParam(page, size));
    }

    @GetMapping("/search")
    public PageDTO<AccountResponse> searchAccounts(@RequestParam(defaultValue = "1", required = false) int page,
                                                   @RequestParam(defaultValue = "10", required = false) int size,
                                                   @RequestParam String searchKey) {
        return accountService.searchAccounts(searchKey, new PageRequestParam(page, size));
    }

    @GetMapping("/{id}")
    public ServiceResponse<AccountResponse> getAccountById(@PathVariable long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/search/{type}")
    public ServiceResponse<AccountResponse> getAccountByTypeAndSearchKey(@PathVariable String type,
                                                                         @RequestParam String searchKey) {

        final Optional<SearchType> searchTypeOptional = SearchTypeUtils.getType(type);

        if (searchTypeOptional.isPresent()) {
            switch (searchTypeOptional.get()) {
                case ID:
                    final long id = convertToLong(searchKey);
                    return accountService.getAccountById(id);
                case REFERENCE_NO:
                    return accountService.getAccountByReferenceNo(searchKey);

                case EMAIL:
                    return accountService.getAccountByEmail(searchKey);

                case NAME:
                    return accountService.getAccountByName(searchKey);
            }
        }

        throw new NotValidException("Invalid search type [" + type + "]. " +
                "Please select ID, REFERENCE_NO, EMAIL or NAME or it's index 1, 2, 3 or 4.");
    }

    @PostMapping
    public ServiceResponse<AccountResponse> createAccount(@Valid @RequestBody CreateAccountForm createAccountForm,
                                                          @ApiIgnore Errors errors) {
        throwFormValidationErrors(errors);
        return accountService.createAccount(createAccountForm);
    }

    @DeleteMapping("/{id}")
    public ServiceResponse<AccountResponse> deleteAccount(@PathVariable long id) {
        return accountService.deleteAccount(id);
    }

    @PutMapping
    public ServiceResponse<AccountResponse> updateAccount(@RequestParam long id,
                                                          @RequestBody UpdateAccountForm updateAccountForm) {
        return accountService.updateAccount(id, updateAccountForm);
    }
}
