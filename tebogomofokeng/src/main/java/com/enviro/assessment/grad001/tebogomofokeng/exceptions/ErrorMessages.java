package com.enviro.assessment.grad001.tebogomofokeng.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    DISPOSAL_GUIDELINE_NOT_FOUND("Disposal Guideline Not Found"),
    DISPOSAL_GUIDELINE_ALREADY_EXIST("Disposal Guideline Already Exist"),
    RECYCLE_TIPS_NOT_FOUND("Recycling Tip Not Found"),
    RECYCLE_TIPS_ALREADY_EXIST("Recycling Tip Already Exist"),
    WASTE_CATEGORY_NOT_FOUND("Waste Category Not Found"),
    WASTE_CATEGORY_ALREADY_EXIST("Waste Category Already Exist");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
