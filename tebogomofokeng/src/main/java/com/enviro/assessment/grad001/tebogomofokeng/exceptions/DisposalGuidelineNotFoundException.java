package com.enviro.assessment.grad001.tebogomofokeng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DisposalGuidelineNotFoundException extends RuntimeException{
    public DisposalGuidelineNotFoundException() {
        super(ErrorMessages.DISPOSAL_GUIDELINE_NOT_FOUND.getMessage());
    }
}
