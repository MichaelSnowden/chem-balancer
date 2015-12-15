package com.michaelsnowden.juniper;

import org.antlr.v4.runtime.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author michael.snowden
 */
public class SyntaxTest {
    @Test
    public void testSyntaxOfMethaneCombustion() throws Exception {
        verify("/methane_combustion.equation");
    }

    private void verify(String file) throws IOException {
        ChemicalEquationLexer l = new ChemicalEquationLexer(new ANTLRInputStream(getClass().getResourceAsStream(file)));
        ChemicalEquationParser p = new ChemicalEquationParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        p.equation();
    }
}
