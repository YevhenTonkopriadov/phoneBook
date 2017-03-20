package com.lardi.validator;

import com.lardi.model.User;
import com.lardi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by ellik on 20.03.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserValidator userValidator;

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        userValidator = new UserValidator();
    }

    @Test
    public void testValidate() throws Exception {
        User userTest = new User();
        String username = "test";
        userTest.setUsername(username);
        userTest.setPassword(username);
        userTest.setConfirmPassword(username);
        when(userService.findByUsername(userTest.getUsername())).thenReturn(userTest);
        Errors errors = new BeanPropertyBindingResult(userTest, "user");
        ValidationUtils.invokeValidator(userValidator, userTest, errors);
        userValidator.validate(userTest,errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testValidateUserFio () throws Exception {
        User user = new User();
        user.setFio("!");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertEquals( 2, constraintViolations.size() );
        Iterator <ConstraintViolation<User>> iterator = constraintViolations.iterator();
        assertEquals("должно соответствовать шаблону \"^[A-Za-z]+$\"",iterator.next().getMessage());
        assertEquals("размер должен быть между 5 и 255",iterator.next().getMessage());
    }
    @Test
    public void testValidateUserName () throws Exception {
        User user = new User();
        user.setUsername("!");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertEquals( 2, constraintViolations.size() );
        Iterator <ConstraintViolation<User>> iterator = constraintViolations.iterator();
        assertEquals("должно соответствовать шаблону \"^[A-Za-z]+$\"",iterator.next().getMessage());
        assertEquals("размер должен быть между 5 и 20",iterator.next().getMessage());
    }
    @Test
    public void testValidateUserPassword () throws Exception {
        User user = new User();
        user.setPassword("!");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertEquals( 1, constraintViolations.size() );
        assertEquals("размер должен быть между 5 и 20",constraintViolations.iterator().next().getMessage());
    }
}