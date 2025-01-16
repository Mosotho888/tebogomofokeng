package com.enviro.assessment.grad001.tebogomofokeng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecyclingTipAlreadyExist extends RuntimeException{
    public RecyclingTipAlreadyExist() {
        super(ErrorMessages.RECYCLE_TIPS_ALREADY_EXIST.getMessage());
    }
}
