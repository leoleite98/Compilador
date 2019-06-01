//Leonardo Leite - CCO 7ºs - 1510032009

//enum com todos os tipos de token
public enum TokenType {
	NUM_INT(1),
	NUM_FLOAT(2),
	LITERAL(3),
	ID(4),
	RELOP(5),
	ARIT_AS(6),
	ARIT_MD(7),
	ASSIGN(8),
	TERM(9),
	L_PAR(10),
	R_PAR(11),
	LOGIC_VAL(12),
	LOGIC_OP(13), 
	TYPE(14), 
	PROGRAM(15), 
	END_PROG(16), 
	BEGIN(17), 
	END(18), 
	IF(19), 
	THEN(20), 
	ELSE(21), 
	FOR(22), 
	WHILE(23), 
	DECLARE(24), 
	TO(25), 
	EOF(26);
	
	private int type;
	
	TokenType(int code) {
		this.type = code;
	}
	
	public int getType() {
		return this.type;
	}
}
