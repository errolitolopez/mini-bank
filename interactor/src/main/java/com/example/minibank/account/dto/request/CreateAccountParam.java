package com.example.minibank.account.dto.request;

import com.example.minibank.account.dto.request.base.AccountBaseParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAccountParam extends AccountBaseParam {
}
