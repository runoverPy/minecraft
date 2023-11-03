grammar RSON;

file : value ;

anno : '#[' ']' ;
value : anno? (bool | number | string | struct) ;
bool : 'false' | 'true' ;
number : ;
string : ;
struct : type? (tuple | array | object) ;
tuple : '(' ')' | '(' value (',' value)* ','? ')' ;
array : '[' ']' | '[' value (',' value)* ','? ']' ;
object : '{' '}' | '{' field (',' field)* ','? '}' ;
field : ident ':' value ;
ident : IDENT ;
type : IDENT ('::' IDENT)* ;

IDENT : [0-9a-zA-Z_]+ ;