package com.lardi.validator;

import com.lardi.model.Record;
import com.lardi.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;
@Component
public class RecordValidator implements Validator {

    @Autowired
    private RecordService recordService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Record.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Record record = (Record) o;
        if (record.getPhoneHome().isEmpty()) {
            return;
            }
        if (!Pattern.compile("^\\+380\\([0-9]{2}\\)[0-9]{7}$").matcher(record.getPhoneHome()).matches()){
                errors.rejectValue("phoneHome", "Pattern.record.phoneHome");
        }
    }
}