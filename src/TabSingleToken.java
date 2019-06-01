//Leonardo Leite - CCO 7ºs - 1510032009
import java.util.HashMap;
import java.util.Map;

public class TabSingleToken {
	
	private static TabSingleToken instance = new TabSingleToken();
	private Map<Character, TokenType> tab; 

	private TabSingleToken() {
		
		this.tab = new HashMap<Character, TokenType>();
				
		this.tab.put('+', TokenType.ARIT_AS);
		this.tab.put('-', TokenType.ARIT_AS);
		this.tab.put('*', TokenType.ARIT_MD);
		this.tab.put('/', TokenType.ARIT_MD);
		this.tab.put(';', TokenType.TERM);
		this.tab.put('(', TokenType.L_PAR);
		this.tab.put(')', TokenType.R_PAR);
		
	}
	
	public static TabSingleToken getInstance() {
		return instance;
	}

	public Map<Character, TokenType> getTab() {
		return this.tab;
	}

	public void setTab(Map<Character, TokenType> tab) {
		this.tab = tab;
	}
}
