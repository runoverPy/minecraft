grammar BinaryValueTree;

file : head? value? EOF ;

head : ('#[' setting ']')+;
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
    'null'  #null |
    'false' #false |
    'true'  #true ;

tuple :
    '[' ']' | '[' value (',' value)* ','? ']' |
    '(' ')' | '(' value (',' value)* ','? ')';

array :
    binArray |
    byteArray |
    shortArray |
    intArray |
    longArray |
    floatArray |
    doubleArray ;
binArray :
    BIN_ARRAY | HEX_ARRAY ;
byteArray :
    ('B[' | 'i8[') (number (',' number)*)? ','? ']' |
    '[' ('B' | 'i8') ':' (number (',' number)*)? ','? ']' ;
shortArray :
    ('S[' | 'i16[') (number (',' number)*)? ','? ']' |
    '[' ('S' | 'i16') ':' (number (',' number)*)? ','? ']' ;
intArray :
    ('I[' | 'i32[') (number (',' number)*)? ','? ']' |
    '[' ('I' | 'i32') ':' (number (',' number)*)? ','? ']' ;
longArray :
    ('L[' | 'i64[') (number (',' number)*)? ','? ']' |
    '[' ('L' | 'i64') ':' (number (',' number)*)? ','? ']' ;
floatArray :
    ('F[' | 'f32[') (number (',' number)*)? ','? ']' |
    '[' ('F' | 'f32') ':' (number (',' number)*)? ','? ']' ;
doubleArray :
    ('D[' | 'f64[') (number (',' number)*)? ','? ']' |
    '[' ('D' | 'f64') ':' (number (',' number)*)? ','? ']' ;

object :
    '{' (kvpair (',' kvpair)*)? ','? '}' ;
kvpair :
    ident ':' value ;
ident :
    IDENT | STRING;

number :
    wholeNumber | byteNumber | shortNumber | intNumber | longNumber |
    pointNumber | floatNumber | doubleNumber ;
wholeNumber : WHOLE_NUMBER ;
byteNumber : BYTE_NUMBER ;
shortNumber : SHORT_NUMBER ;
intNumber : INT_NUMBER ;
longNumber : LONG_NUMBER ;
pointNumber : POINT_NUMBER ;
floatNumber : FLOAT_NUMBER ;
doubleNumber : DOUBLE_NUMBER ;

string : STRING ;

BIN_ARRAY : 'b[' [01_]* ']' ;
HEX_ARRAY : 'x[' [0-9a-fA-F_]* ']' ;

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

WHOLE_NUMBER :
    [+-]? ('0' [bB] BIN_DIGITS | '0' [oO] OCT_DIGITS | ('0' [dD])? DEC_DIGITS  | '0' [xX] HEX_DIGITS) ;

BYTE_NUMBER : WHOLE_NUMBER ([bB] | 'i8') ;

SHORT_NUMBER : WHOLE_NUMBER ([sS] | 'i16') ;

INT_NUMBER : WHOLE_NUMBER ([iI] | 'i32') ;

LONG_NUMBER : WHOLE_NUMBER ([lL] | 'i64') ;

POINT_NUMBER :
    [+-]? DEC_DIGITS ('.' [0-9_]* )? ([eE] [+-]? [0-9_]+)?;

FLOAT_NUMBER : POINT_NUMBER ([fF] | 'f32') ;

DOUBLE_NUMBER : POINT_NUMBER ([dD] | 'f64') ;

IDENT : [a-zA-Z] [0-9a-zA-Z_]* ;

COMMENT :
    (
        '//' .*? (EOL | EOF) |
        '/*' .*? '*/'
    ) -> skip;

EOL : ('\n' | '\r\n' | '\r') -> channel(HIDDEN) ;
WS : [ \n\t\r]+ -> channel(HIDDEN) ;
