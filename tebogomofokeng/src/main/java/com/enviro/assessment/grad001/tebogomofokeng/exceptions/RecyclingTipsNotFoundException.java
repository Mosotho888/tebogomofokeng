package com.enviro.assessment.grad001.tebogomofokeng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecyclingTipsNotFoundException extends RuntimeException{
    public RecyclingTipsNotFoundException() {
        super(ErrorMessages.RECYCLE_TIPS_NOT_FOUND.getMessage());
    }
}
