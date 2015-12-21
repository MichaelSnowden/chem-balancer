package com.michaelsnowden.juniper;

import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * @author michael.snowden
 */
public class BalanceTest {
    @Test
    public void testBalanceOfMethaneCombustion() throws Exception {
        verify("/unnecessary_term_coefficients.equation", "CH4 + 2O2 = CO2 + 2H2O");
    }

    @Test
    public void testBalanceOfEquationWithUnnecessaryTermCoefficients() throws Exception {
        verify("/unnecessary_term_coefficients.equation", "CH4 + 2O2 = CO2 + 2H2O");
    }

    private void verify(String inputFile, String balancedEquation) throws IOException {
        String equation = new EquationFactory().getBalancedEquation(getClass().getResourceAsStream
                (inputFile)).asPlainText();
        assertEquals(equation, balancedEquation);
    }
}
