import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Leonardo Leite - CCO 7ºs - 1510032009
public class TabFirstFollow {
	
	private static TabFirstFollow instance = new TabFirstFollow();
	private Map<String, List<TokenType>> first;
	private Map<String, List<TokenType>> follow;
	
	private TabFirstFollow() {
		this.first = new HashMap<String, List<TokenType>>();		
		this.follow = new HashMap<String, List<TokenType>>();
		
		
		this.first.put("S", Arrays.asList(TokenType.PROGRAM ));
		this.first.put("CMDS", Arrays.asList(TokenType.DECLARE, TokenType.IF, TokenType.FOR, TokenType.WHILE, TokenType.ID));		
		this.first.put("CMD", Arrays.asList(TokenType.DECLARE, TokenType.IF, TokenType.ID, TokenType.FOR, TokenType.WHILE));
		this.first.put("DECL", Arrays.asList(TokenType.DECLARE));
		this.first.put("COND", Arrays.asList(TokenType.IF));
		this.first.put("CNDB", Arrays.asList(TokenType.ELSE));
		this.first.put("ATRIB", Arrays.asList(TokenType.ID));
		this.first.put("EXP", Arrays.asList(TokenType.LOGIC_VAL, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT, TokenType.L_PAR, TokenType.LITERAL));		
		this.first.put("FID", Arrays.asList(TokenType.LOGIC_OP, TokenType.ARIT_AS, TokenType.ARIT_MD));
		this.first.put("FOPNUM", Arrays.asList(TokenType.L_PAR, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT));
		this.first.put("FEXPNUM1", Arrays.asList(TokenType.RELOP));
		this.first.put("FNUMINT", Arrays.asList(TokenType.ARIT_AS, TokenType.ARIT_MD));
		this.first.put("FOPNUM1", Arrays.asList(TokenType.L_PAR, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT));
		this.first.put("FEXPNUM2", Arrays.asList(TokenType.RELOP));
		this.first.put("FNUMFLOAT", Arrays.asList(TokenType.ARIT_AS, TokenType.ARIT_MD));
		this.first.put("FOPNUM2", Arrays.asList(TokenType.L_PAR, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT));
		this.first.put("FEXPNUM3", Arrays.asList(TokenType.RELOP));
		this.first.put("FLPAR", Arrays.asList(TokenType.L_PAR, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT));
		this.first.put("FEXPNUM", Arrays.asList(TokenType.R_PAR));
		this.first.put("FRPAR", Arrays.asList(TokenType.RELOP));
		this.first.put("FID1", Arrays.asList(TokenType.RELOP, TokenType.LOGIC_OP, TokenType.ARIT_AS, TokenType.ARIT_MD));
		this.first.put("FVALLOG", Arrays.asList(TokenType.LOGIC_OP));
		this.first.put("XEXPNUM", Arrays.asList(TokenType.ARIT_AS, TokenType.ARIT_MD));
		this.first.put("OPNUM", Arrays.asList(TokenType.ARIT_AS, TokenType.ARIT_MD));
		this.first.put("VAL", Arrays.asList(TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT));
		this.first.put("REP", Arrays.asList(TokenType.FOR, TokenType.WHILE));
		this.first.put("REPF", Arrays.asList(TokenType.FOR));
		this.first.put("EXPNUM", Arrays.asList(TokenType.L_PAR, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT));
		this.first.put("REPW", Arrays.asList(TokenType.WHILE));
		this.first.put("EXPLO", Arrays.asList(TokenType.LOGIC_VAL, TokenType.ID, TokenType.NUM_INT, TokenType.NUM_FLOAT, TokenType.L_PAR));
		this.first.put("BLOCO", Arrays.asList(TokenType.BEGIN, TokenType.DECLARE, TokenType.ID, TokenType.FOR, TokenType.WHILE));
	}
	
	public static TabFirstFollow getInstance() {
		return instance;
	}

	public Map<String, List<TokenType>> getFirst() {
		return this.first;
	}

	public Map<String, List<TokenType>> getFollow() {
		return this.follow;
	}
}
