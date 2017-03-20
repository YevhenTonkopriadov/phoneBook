package com.lardi.validator;

import com.lardi.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;


/**
 * Created by ellik on 19.03.2017.
 */

public class RecordValidatorTest {

    private  Validator validator;
    private RecordValidator recordValidator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        recordValidator = new RecordValidator();
    }
    @Test
    public void testValidateRecordsCurrentPhoneHome(){
        Record record = new Record();
        record.setPhoneHome("+380(66)6671044");
        Errors errors = new BeanPropertyBindingResult(record, "record");
        ValidationUtils.invokeValidator(recordValidator, record, errors);
        recordValidator.validate(record,errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateRecordsPhoneNotNull () throws Exception {
        Record record = new Record();
        record.setPhone(null);
        Set<ConstraintViolation<Record>> constraintViolations = validator.validate( record );
        assertEquals( 1, constraintViolations.size() );
        assertEquals("не может быть пусто",constraintViolations.iterator().next().getMessage());
    }
    @Test
    public void testValidateRecordsCurrentPhone () throws Exception {
        Record record = new Record();
        record.setPhone("111");
        Set<ConstraintViolation<Record>> constraintViolations = validator.validate( record );
        assertEquals( 1, constraintViolations.size() );
        assertEquals("должно соответствовать шаблону \"^\\+380\\([0-9]{2}\\)[0-9]{7}$\"",constraintViolations.iterator().next().getMessage());
    }
    @Test
    public void testValidateRecordsCurrentName() throws Exception {
        Record record = new Record();
        record.setPhone("+380(66)0671044");
        record.setName("!");
        Set<ConstraintViolation<Record>> constraintViolations = validator.validate( record );
        assertEquals( 2, constraintViolations.size() );
        Iterator <ConstraintViolation<Record>> iterator = constraintViolations.iterator();
        assertEquals("должно соответствовать шаблону \"^[A-Za-z]+$\"",iterator.next().getMessage());
        assertEquals("размер должен быть между 4 и 20",iterator.next().getMessage());
    }
    @Test
    public void testValidateRecordsCurrentLastname() throws Exception {
        Record record = new Record();
        record.setPhone("+380(66)0671044");
        record.setName("Abcd");
        record.setLastname("!");
        Set<ConstraintViolation<Record>> constraintViolations = validator.validate( record );
        assertEquals( 2, constraintViolations.size() );
        Iterator <ConstraintViolation<Record>> iterator = constraintViolations.iterator();
        assertEquals("размер должен быть между 4 и 20",iterator.next().getMessage());
        assertEquals("должно соответствовать шаблону \"^[A-Za-z]+$\"",iterator.next().getMessage());
    }
    @Test
    public void testValidateRecordsCurrentSurname() throws Exception {
        Record record = new Record();
        record.setPhone("+380(66)0671044");
        record.setName("Abcd");
        record.setLastname("Abcd");
        record.setSurname("!");
        Set<ConstraintViolation<Record>> constraintViolations = validator.validate( record );
        assertEquals( 2, constraintViolations.size() );
        Iterator <ConstraintViolation<Record>> iterator = constraintViolations.iterator();
        assertEquals("должно соответствовать шаблону \"^[A-Za-z]+$\"" ,iterator.next().getMessage());
        assertEquals("размер должен быть между 4 и 20",iterator.next().getMessage());
    }
    @Test
    public void testValidateRecordsCurrentEmail() throws Exception {
        Record record = new Record();
        record.setPhone("+380(66)0671044");
        record.setName("Abcd");
        record.setLastname("Abcd");
        record.setSurname("Abcd");
        record.setEmail("!");
        Set<ConstraintViolation<Record>> constraintViolations = validator.validate( record );
        assertEquals( 1, constraintViolations.size() );
        Iterator <ConstraintViolation<Record>> iterator = constraintViolations.iterator();
        assertEquals("email определен в неверном формате",iterator.next().getMessage());
    }
}