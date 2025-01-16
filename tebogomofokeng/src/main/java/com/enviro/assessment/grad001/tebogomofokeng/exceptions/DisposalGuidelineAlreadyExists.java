package com.enviro.assessment.grad001.tebogomofokeng.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DisposalGuidelineAlreadyExists extends RuntimeException{
    public DisposalGuidelineAlreadyExists() {
        super(ErrorMessages.DISPOSAL_GUIDELINE_ALREADY_EXIST.getMessage());
    }
}
