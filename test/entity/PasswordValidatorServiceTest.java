package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordValidatorServiceTest {
    @Test
    public void testValidPassword() {
        PasswordValidatorService validatorService = new PasswordValidatorService();

        // Test a valid password (length > 5)
        assertTrue(validatorService.passwordIsValid("ValidP@ssword123"));
    }

    @Test
    public void testInvalidPassword() {
        PasswordValidatorService validatorService = new PasswordValidatorService();

        assertFalse(validatorService.passwordIsValid(null));

        assertFalse(validatorService.passwordIsValid("1234"));
    }

}