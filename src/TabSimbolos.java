//Leonardo Leite - CCO 7ºs - 1510032009
import java.util.HashMap;
import java.util.Map;

public class TabSimbolos {
	
	private static TabSimbolos instance = new TabSimbolos();
	private Map<String, TokenType> tab; 

	private TabSimbolos() {
		
		this.tab = new HashMap<String, TokenType>();
		
		this.tab.put("true", TokenType.LOGIC_VAL);
		this.tab.put("false", TokenType.LOGIC_VAL);
		this.tab.put("not", TokenType.LOGIC_OP);
		this.tab.put("and", TokenType.LOGIC_OP);
		this.tab.put("or", TokenType.LOGIC_OP);
		this.tab.put("bool", TokenType.TYPE);
		this.tab.put("text", TokenType.TYPE);
		this.tab.put("int", TokenType.TYPE);
		this.tab.put("float", TokenType.TYPE);
		this.tab.put("program", TokenType.PROGRAM);
		this.tab.put("end_prog", TokenType.END_PROG);
		this.tab.put("begin", TokenType.BEGIN);
		this.tab.put("end", TokenType.END);
		this.tab.put("if", TokenType.IF);
		this.tab.put("then", TokenType.THEN);
		this.tab.put("else", TokenType.ELSE);
		this.tab.put("for", TokenType.FOR);
		this.tab.put("while", TokenType.WHILE);
		this.tab.put("declare", TokenType.DECLARE);
		this.tab.put("to", TokenType.TO);
		
	}
	
	public static TabSimbolos getInstance() {
		return instance;
	}

	public Map<String, TokenType> getTab() {
		return this.tab;
	}

	public void setTab(Map<String, TokenType> tab) {
		this.tab = tab;
	}
	
	public void addSimbolo(String lexema, TokenType tokenType) {
		this.tab.put(lexema, tokenType);
	}
	
	public void gerarRelatorio() {
		System.out.println("-----------------CONTEÚDO DA TABELA DE SIMBOLOS-----------------");
		for(String lexema : this.tab.keySet()) {
			System.out.println("LEXEMA: " + lexema + " - TOKEN TYPE: " + this.tab.get(lexema));
		}
	}

}
