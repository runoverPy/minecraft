grammar BinaryValueTree;

@header {
package game.core.binary.parser;
}

file : head? value EOF ;

head : ('#[' setting ']')*;
setting : typeset | typedef | enumdef | coerce ;
typeset :
    'itype(' type=('BYTE' | 'SHORT' | 'INT' | 'LONG' | 'i8' | 'i16' | 'i32' | 'i64') ')' |
    'ftype(' type=('FLOAT' | 'DOUBLE' | 'f32' | 'f64') ')' ;
typedef :
    'type' name=IDENT '{' '}' ;
enumdef :
    'enum' name=IDENT '{' (IDENT (',' IDENT)*)? ','? '}' ;
coerce :
    'coerce(' ('true' | 'false') ')' ;


value : literal | array | tuple | object | number | string ;
literal :
    'null' # Null |
    'false' # False |
    'true' # True ;
tuple :
    '[' ']' | '[' value (',' value)* ','? ']' |
    '(' ')' | '(' value (',' value)* ','? ')';
array :
    binarray |
    bytearray |
    shortarray |
    intarray |
    longarray |
    floatarray |
    doublearray ;
binarray : BIN_ARRAY | HEX_ARRAY ;
bytearray : '[' (number (',' number)*)? ','? (']B' | ']i8') ;
shortarray : '[' (number (',' number)*)? ','? (']S' | ']i16') ;
intarray : '[' (number (',' number)*)? ','? (']I' | ']i32') ;
longarray : '[' (number (',' number)*)? ','? (']L' | ']i64') ;
floatarray : '[' (number (',' number)*)? ','? (']F' | ']f32') ;
doublearray : '[' (number (',' number)*)? ','? (']D' | ']f64') ;
object : '{' (kvpair (',' kvpair)*)? ','? '}' ;
kvpair : ident ':' value ;
ident : IDENT | STRING;
number : NUMBER ;
string : STRING ;

BIN_ARRAY : '[' [01_]* ']b' ;
HEX_ARRAY : '[' [0-9a-fA-F_]* (']x' | ']bx') ;

STRING :
    '"' (~["\\] | ESCAPE)* '"' |
    '\'' (~['\\] | ESCAPE)* '\'' ;

fragment BIN : [01] ;
fragment HEX : [0-9a-fA-F] ;

fragment ESCAPE :
    '\\' (
        '0' |
        'x' HEX HEX |
        'u' HEX HEX HEX HEX |
        EOL |
        ['"nrtb]
    ) ;

fragment BIN_DIGITS : '0' | [1] [01_]* ;
fragment OCT_DIGITS : '0' | [1-7] [0-7_]* ;
fragment DEC_DIGITS : '0' | [1-9] [0-9_]* ;
fragment HEX_DIGITS : '0' | [1-9a-fA-F] [0-9a-fA-F_]* ;

NUMBER :
    [+-]? ('0' [bB] BIN_DIGITS | '0' [oO] OCT_DIGITS | ('0' [dD])? DEC_DIGITS  | '0' [xX] HEX_DIGITS)
        ([bB] | [sS] | [iI] | [lL] | 'i8' | 'i16' | 'i32' | 'i64')? |
    [+-]? DEC_DIGITS ('.' [0-9_]* )? ([eE] [+-]? [0-9_]+)?
        ([fF] | [dD] | 'f32' | 'f64')?;

IDENT : [a-zA-Z] [0-9a-zA-Z_]* ;

COMMENT :
    (
        '//' .*? (EOL | EOF) |
        '/*' .*? '*/'
    ) -> skip;

EOL : ('\n' | '\r\n' | '\r') -> channel(HIDDEN) ;
WS : [ \n\t\r]+ -> channel(HIDDEN) ;
