grammar ChemicalEquation;

equation: expression WS? ('=' | '->' | '-->' | '=>') WS? expression EOF;
expression: term (WS? '+' WS? term)*;
term: component+;
component: Atom Quantity? | '(' component+ ')' Quantity? | '[' component+ ']' Quantity?;

Atom: 'A'..'Z'('a'..'z')*;
Quantity: ('0'..'9')+;
WS: (' ' | '\t')+;