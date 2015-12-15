package com.michaelsnowden.juniper;

import java.util.ArrayList;

/**
 * @author michael.snowden
 */
public class Equation {
    private final ArrayList<String> leftTerms;
    private final ArrayList<String> rightTerms;

    public Equation(ArrayList<String> leftTerms, ArrayList<String> rightTerms) {
        this.leftTerms = leftTerms;
        this.rightTerms = rightTerms;
    }

    String asPlainText() {
        return String.join(" + ", getLeftTerms()) + " = " + String.join(" + ", getRightTerms());
    }

    public ArrayList<String> getLeftTerms() {
        return leftTerms;
    }

    public ArrayList<String> getRightTerms() {
        return rightTerms;
    }
}
