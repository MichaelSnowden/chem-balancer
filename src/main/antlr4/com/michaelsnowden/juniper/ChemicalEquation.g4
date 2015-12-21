grammar ChemicalEquation;

equation: expression WS? ('=' | '->' | '-->' | '=>') WS? expression EOF;
expression: Quantity? term (WS? '+' WS? Quantity? term)*;
term: component+;
component: Atom Quantity? | '(' component+ ')' Quantity? | '[' component+ ']' Quantity?;

Atom: 'A'..'Z'('a'..'z')*;
Quantity: ('0'..'9')+;
WS: (' ' | '\t')+;