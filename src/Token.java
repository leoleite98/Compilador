//Leonardo Leite - CCO 7ºs - 1510032009

//objeto que representa o token e suas propriedades identificado pela analisador lexico
public class Token {
	private TokenType tokenType; 
	private String lexema; 
	private long lin; 
	private long col; 
	
	public Token() {
		
	}
	
	public Token(TokenType tokenType, String lexema, long lin, long col) {
		this.tokenType = tokenType;
		this.lexema = lexema;
		this.lin = lin;
		this.col = col;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public long getLin() {
		return lin;
	}

	public void setLin(long lin) {
		this.lin = lin;
	}

	public long getCol() {
		return col;
	}

	public void setCol(long col) {
		this.col = col;
	}
	
}
