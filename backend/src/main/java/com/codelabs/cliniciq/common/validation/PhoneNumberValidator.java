package com.codelabs.cliniciq.common.validation;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isBlank()) return false;

        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // Use "ZZ" as region to infer from the phone number
            var numberProto = phoneUtil.parse(phoneNumber, "ZZ");
            return phoneUtil.isValidNumber(numberProto);
        } catch (NumberParseException e) {
            return false;
        }
    }
}