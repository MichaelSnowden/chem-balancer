package com.michaelsnowden.juniper;

import org.antlr.v4.runtime.*;
import org.apache.commons.math3.fraction.Fraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author michael.snowden
 */
public class Balancer {
    public static void main(String[] args) throws IOException {
        balance(Balancer.class.getResourceAsStream
                ("/example.equation"));
        balance(Balancer.class.getResourceAsStream
                ("/harder-example.equation"));
        balance(Balancer.class.getResourceAsStream
                ("/hardest.equation"));

    }

    public static String balance(InputStream inputStream) throws IOException {
        ChemicalEquationLexer l = new ChemicalEquationLexer(new ANTLRInputStream(inputStream));
        ChemicalEquationParser p = new ChemicalEquationParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int
                    charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        final AtomicReference<ArrayList<Map<String, Integer>>> terms = new AtomicReference<>();
        ArrayList<String> leftTerms = new ArrayList<>();
        ArrayList<String> rightTerms = new ArrayList<>();
        Map<String, Integer> allAtoms = new HashMap<>();
        p.addParseListener(new ChemicalEquationBaseListener() {

            @Override
            public void exitEquation(ChemicalEquationParser.EquationContext ctx) {
                super.exitEquation(ctx);
                terms.set(new ArrayList<>(ctx.expression(0).term().size() + ctx.expression(1).term()
                        .size()));
                handleEquation(ctx, 0, 1);
                handleEquation(ctx, 1, -1);
            }

            private void handleEquation(ChemicalEquationParser.EquationContext ctx, int expression, int multiple) {
                for (ChemicalEquationParser.TermContext termContext : ctx.expression(expression).term()) {
                    if (expression == 0) {
                        leftTerms.add(termContext.getText());
                    } else {
                        rightTerms.add(termContext.getText());
                    }
                    Map<String, Integer> atoms = new HashMap<>();
                    for (ChemicalEquationParser.ComponentContext componentContext : termContext.component()) {
                        handleComponent(componentContext, atoms, multiple);
                    }
                    terms.get().add(atoms);
                }
            }

            void handleComponent(ChemicalEquationParser.ComponentContext component, Map<String, Integer> atoms, int
                    multiple) {
                int quantity = multiple;
                if (component.Quantity() != null) {
                    quantity *= Integer.parseInt(component.Quantity().getText());
                }
                if (component.Atom() != null) {
                    final String atom = component.Atom().getText();
                    if (allAtoms.get(atom) == null) {
                        allAtoms.put(atom, allAtoms.size());
                    }
                    atoms.put(atom, atoms.getOrDefault(atom, 0) + quantity);
                } else {
                    for (ChemicalEquationParser.ComponentContext componentContext : component.component()) {
                        handleComponent(componentContext, atoms, quantity);
                    }
                }
            }
        });
        p.equation();

        final int n = terms.get().size();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (String atom : terms.get().get(i).keySet()) {
                matrix[allAtoms.get(atom)][i] = terms.get().get(i).get(atom);
            }
        }

        Matrix A = new Matrix(matrix);
        System.out.println(A);
        A.reducedRowEchelonForm();
        System.out.println(A);

        A.getCoordinate(new Matrix.Coordinate(0, 0));
        double lcm = 1;
        for (int i = 0; i < n - 1; i++) {
            lcm = lcm(lcm, A.getCoordinate(new Matrix.Coordinate(i, n - 1)).abs().getDenominator());
        }
        ArrayList<Integer> coefficients = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            coefficients.add(A.getCoordinate(new Matrix.Coordinate(i, n - 1)).abs().multiply(new Fraction
                    ((int) lcm)).getNumerator());
        }
        coefficients.add((int) lcm);
        int i = 0;
        for (int j = 0; j < leftTerms.size(); j++) {
            final Integer coefficient = coefficients.get(i);
            if (coefficient != 1) {
                leftTerms.set(j, coefficient + leftTerms.get(j));
            }
            ++i;
        }
        for (int j = 0; j < rightTerms.size(); j++) {
            final Integer coefficient = coefficients.get(i);
            if (coefficient != 1) {
                rightTerms.set(j, coefficient + rightTerms.get(j));
            }
            ++i;
        }
        return String.join(" + ", leftTerms) + " = " + String.join(" + ", rightTerms);
    }

    private static double gcd(double a, double b) {
        while (b > 0) {
            double temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static double lcm(double a, double b) {
        return a * (b / gcd(a, b));
    }
}


// 1 element, 2 terms   S = S8
// [1 -8]
// 2 elements, 2 terms  OH = H202
// [1 -2]
// [1 -2]
// 3 elements, 2 terms  CHO = C4H4O4
// [1 -4]
// [1 -4]
// [1 -4]