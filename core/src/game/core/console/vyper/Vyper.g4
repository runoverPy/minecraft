grammar Vyper;

/**
 * cmd arg$1 arg$2 arg$3 kwd$1 kwd$2 kwd$3
 */

@header {
package game.core.console.vyper;
}


file : cmd? ((EOL | ';') cmd?)* EOF ;

cmd :
    stmt |
    fn (posarg | kwdarg)* |
    cmd '|' cmd |
    cmd '&' cmd ;
stmt : WORD '=' value ;
expr : '$(' cmd (';' cmd?)* ';'? ')' | READ;
fn : WORD;

posarg : value ;
kwdarg : ARGNAME ('=' value)? | ARGFLAGS | '--' ;

value : string | PATH | NUMBER | expr ;

ARGNAME : '--' [a-zA-Z]+ ;
ARGFLAGS : '-' [a-zA-Z]+ ;
//IDENT : [a-zA-Z] [0-9a-zA-Z_]* ;

//STRING :
//    '"' (~["\\] | ESCAPE)* '"' |
//    '\'' (~['\\] | ESCAPE)* '\'' ;

string :
    DoubleQuoteString |
    SingleQuoteString |
    WORD ;

DoubleQuoteString : '"' (~["\\] | ESCAPE)* '"' ;
SingleQuoteString : '\'' (~['\\] | ESCAPE)* '\'' ;
//ZeroQuoteString : (~[ \\] | ESCAPE)+ ;
NUMBER :
    [+-]? ('0' [bB] BIN_DIGITS | '0' [oO] OCT_DIGITS | ('0' [dD])? DEC_DIGITS  | '0' [xX] HEX_DIGITS)
        ([bB] | [sS] | [iI] | [lL] | 'i8' | 'i16' | 'i32' | 'i64')? |
    [+-]? DEC_DIGITS ('.' [0-9_]* )? ([eE] [+-]? [0-9_]+)?
        ([fF] | [dD] | 'f32' | 'f64')?;
READ : '$' WORD ;
WORD :
    '`' ([ a-zA-Z0-9_] | ESCAPE)* '`' |
    ([a-zA-Z0-9_] | ESCAPE)+ ;
fragment NAME : [a-zA-Z0-9_.]+ ;
fragment GLOB : NAME? '*' NAME? ;
fragment FILE :
    NAME | '.' | '..' | GLOB ;

PATH :
    (('.' | '~')? '/'+) (FILE '/'+)* FILE? |
    (FILE '/'+)* FILE {System.out.println("PATH");} ;
RETURN_LOGIC :
    ('|' | '&') ;
REDIRECT : '£' ; // wip

fragment BIN_DIGITS : '0' | [1] [01_]* ;
fragment OCT_DIGITS : '0' | [1-7] [0-7_]* ;
fragment DEC_DIGITS : '0' | [1-9] [0-9_]* ;
fragment HEX_DIGITS : '0' | [1-9a-fA-F] [0-9a-fA-F_]* ;
fragment ESCAPE :
    '\\' (
        '0' |
        'x' HEX HEX |
        'u' HEX HEX HEX HEX |
        ['" nrtb\n\r\t\b]
    ) ;

fragment HEX : [0-9a-fA-F] ;
EOL : ('\n' | '\r\n' | '\r') ;
WS : [ \t]+ -> channel(HIDDEN) ;
