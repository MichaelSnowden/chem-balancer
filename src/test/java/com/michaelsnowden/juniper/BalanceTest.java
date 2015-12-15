package com.michaelsnowden.juniper;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author michael.snowden
 */
public class BalanceTest {
    @Test
    public void testBalanceOfMethaneCombustion() throws Exception {
        String s = new EquationFactory().getBalancedEquation(getClass().getResourceAsStream("/methane_combustion" +
                ".equation")).asPlainText();
        assertEquals(s, "CH4 + 2O2 = CO2 + 2H2O");
    }
}
