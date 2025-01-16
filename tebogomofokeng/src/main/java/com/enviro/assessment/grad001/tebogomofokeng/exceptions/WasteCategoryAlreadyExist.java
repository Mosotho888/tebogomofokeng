package com.enviro.assessment.grad001.tebogomofokeng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WasteCategoryAlreadyExist extends RuntimeException{
    public WasteCategoryAlreadyExist() {
        super(ErrorMessages.WASTE_CATEGORY_ALREADY_EXIST.getMessage());
    }
}
