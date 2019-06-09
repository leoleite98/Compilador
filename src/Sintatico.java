import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//Leonardo Leite - CCO 7ºs - 1510032009
public class Sintatico {
	
	public Lexico lexico;
	private Token token;
	private Set<String> idsDeclarados;
	
	//defini o lexico em seu construtor, passando o nome do arquivo
	//instancia o set de idsDeclarados
	public Sintatico(String fileName) throws FileNotFoundException{
		this.lexico = new Lexico(fileName); 
		this.idsDeclarados = new HashSet<String>();
	}
	
	//inicia a derivacao dos tokens
	public void processar() throws EOFException, IOException {
		
		s();
		
		TabSimbolos.getInstance().gerarRelatorio();
		
		ErrorHandler.getInstance().gerarRelatorio();
	}
	
	private void s(){
		
		this.token = this.lexico.nextToken();
		
		if(this.token.getTokenType() != TokenType.PROGRAM) {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroProgram(this.token.getLin(), this.token.getCol()));
		}
		
		this.token = this.lexico.nextToken();
		
		if(this.token.getTokenType() != TokenType.ID) {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroNomeProgram(this.token.getLin(), this.token.getCol()));
		
		}else {
			this.idsDeclarados.add(this.token.getLexema());
		}
		
		this.token = this.lexico.nextToken();
		
		if(this.token.getTokenType() != TokenType.TERM) {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTermProgram(this.token.getLin(), this.token.getCol()));
		}
		
		this.token = this.lexico.nextToken();
		
		bloco();
		
		if(this.token.getTokenType() != TokenType.END_PROG) {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroEndProgram(this.token.getLin(), this.token.getCol()));
		}
		
		this.token = this.lexico.nextToken();

		
		if(this.token.getTokenType() != TokenType.TERM) {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTermEndProgram(this.token.getLin(), this.token.getCol()));
		}
		
		this.token = this.lexico.nextToken();
		
		while(this.token.getTokenType() != TokenType.EOF) {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
			this.token = this.lexico.nextToken();
		}
	}
	
	private void bloco(){
		
		if(TabFirstFollow.getInstance().getFirst().get("BLOCO").contains(this.token.getTokenType())) {
			
			if(this.token.getTokenType() == TokenType.BEGIN) {
				
				this.token = this.lexico.nextToken();
				cmds();
				
				if(this.token.getTokenType() != TokenType.END) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroEndBloco(this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
			
			}else{
				cmd();
			}
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void cmds(){
		
		if(TabFirstFollow.getInstance().getFirst().get("CMDS").contains(this.token.getTokenType())) {
			
			if(TabFirstFollow.getInstance().getFirst().get("DECL").contains(this.token.getTokenType())) {
				
				decl();
				cmds();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("COND").contains(this.token.getTokenType())) {
				
				cond();
				cmds();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("REPF").contains(this.token.getTokenType())) {
				
				repf();
				cmds();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("REPW").contains(this.token.getTokenType())) {
				
				repw();
				cmds();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("ATRIB").contains(this.token.getTokenType())) {
				
				atrib();
				cmds();
			}
			
		}else if(!(TabFirstFollow.getInstance().getFollow().get("CMDS").contains(this.token.getTokenType()))){
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void cmd(){
		
		if(TabFirstFollow.getInstance().getFirst().get("CMD").contains(this.token.getTokenType())) {
		
			if(TabFirstFollow.getInstance().getFirst().get("DECL").contains(this.token.getTokenType())) {
				decl();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("COND").contains(this.token.getTokenType())) {
				cond();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("REP").contains(this.token.getTokenType())) {
				rep();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("ATRIB").contains(this.token.getTokenType())) {
				atrib();
			}
		
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void decl(){
		
		if(TabFirstFollow.getInstance().getFirst().get("DECL").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
						
			if(this.token.getTokenType() != TokenType.ID) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdDeclare(this.token.getLin(), this.token.getCol()));
			
			}else {
				
				if(!this.idsDeclarados.contains(this.token.getLexema())) {
					this.idsDeclarados.add(this.token.getLexema());
				
				}else {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdExistenteDeclare(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
				}
			}
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.TYPE) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTypeDeclare(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.TERM) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTermDeclare(this.token.getLin(), this.token.getCol()));
			}
						
			this.token = this.lexico.nextToken();
				
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void cond(){
		
		if(TabFirstFollow.getInstance().getFirst().get("COND").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.L_PAR) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroLparIf(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			explo();
			
			if(this.token.getTokenType() != TokenType.R_PAR) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroRparIf(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.THEN) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroThenIf(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			bloco();
			
			cndb();
		
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}

	}
	
	private void repf(){
		
		if(TabFirstFollow.getInstance().getFirst().get("REPF").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.ID) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdFor(this.token.getLin(), this.token.getCol()));
			
			}else if(!this.idsDeclarados.contains(this.token.getLexema())) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdNaoDeclarado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.ASSIGN) { //TODO VERIFICAR SE REALMENTE É ASSIGN
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroAssignFor(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			expnum();
			
			if(this.token.getTokenType() != TokenType.TO) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroToFor(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			expnum();
			
			bloco();
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void repw(){
		
		if(TabFirstFollow.getInstance().getFirst().get("REPW").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.L_PAR) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroLparWhile(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			explo();
			
			if(this.token.getTokenType() != TokenType.R_PAR) {	
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroRparWhile(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
						
			bloco();
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}	
	}
	
	private void atrib(){
		
		if(TabFirstFollow.getInstance().getFirst().get("ATRIB").contains(this.token.getTokenType())) {
			
			if(this.token.getTokenType() != TokenType.ID) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdAtrib(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
			
			}else if(!this.idsDeclarados.contains(this.token.getLexema())) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdNaoDeclarado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
			
			if(this.token.getTokenType() != TokenType.ASSIGN) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEncontrado("<-", this.token.getLin(), this.token.getCol()));
			}				
			
			this.token = this.lexico.nextToken();
			
			exp();
			
			if(this.token.getTokenType() != TokenType.TERM) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTermAtrib(this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();

		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void rep(){
		
		if(TabFirstFollow.getInstance().getFirst().get("REP").contains(this.token.getTokenType())) {
			
			if(TabFirstFollow.getInstance().getFirst().get("REPW").contains(this.token.getTokenType())) {
				repw();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("REPF").contains(this.token.getTokenType())) {
				repf();
			}
		
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}		
	}
	
	private void explo(){
		
		if(TabFirstFollow.getInstance().getFirst().get("EXPLO").contains(this.token.getTokenType())) {
			
			if(this.token.getTokenType() == TokenType.LOGIC_VAL){
				this.token = this.lexico.nextToken();
				fvallog();
			
			}else if(this.token.getTokenType() == TokenType.ID){
				
				if(!this.idsDeclarados.contains(this.token.getLexema())) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdNaoDeclarado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				fid_1();
			
			}else if(this.token.getTokenType() == TokenType.NUM_INT){
				
				this.token = this.lexico.nextToken();
				
				opnum();
				
				expnum();
				
				if(this.token.getTokenType() != TokenType.RELOP) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroRelop(this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				
				expnum();
				
			}else if(this.token.getTokenType() == TokenType.NUM_FLOAT){
				
				this.token = this.lexico.nextToken();
				
				opnum();
				
				expnum();
				
				if(this.token.getTokenType() != TokenType.RELOP) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroRelop(this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				
				
				expnum();
			
			}else if(this.token.getTokenType() == TokenType.L_PAR) {
				
				this.token = this.lexico.nextToken();
				
				expnum();
				
				if(this.token.getTokenType() != TokenType.R_PAR) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(")", this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				
				if(this.token.getTokenType() != TokenType.RELOP) {	
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroRelop(this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				
				expnum();
			}
				
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void cndb(){
		
		if(TabFirstFollow.getInstance().getFirst().get("CNDB").contains(this.token.getTokenType())) {
			
			if(this.token.getTokenType() != TokenType.ELSE) {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
			}
			
			this.token = this.lexico.nextToken();
						
			bloco();
			
		}else if(!(TabFirstFollow.getInstance().getFollow().get("CNDB").contains(this.token.getTokenType()))){
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void exp(){
		
		if(TabFirstFollow.getInstance().getFirst().get("EXP").contains(this.token.getTokenType())) {
			
			if(this.token.getTokenType() == TokenType.LOGIC_VAL) {
				
				this.token = this.lexico.nextToken();
				fvallog();
				
			}else if(this.token.getTokenType() == TokenType.ID){
				
				if(!this.idsDeclarados.contains(this.token.getLexema())) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroIdNaoDeclarado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				fid();
			
			}else if(this.token.getTokenType() == TokenType.NUM_INT || this.token.getTokenType() == TokenType.NUM_FLOAT){
				
				this.token = this.lexico.nextToken();
				fnumint_float();
				
			}else if(this.token.getTokenType() == TokenType.L_PAR){
				
				this.token = this.lexico.nextToken();
				flpar();
				
			}else if(this.token.getTokenType() == TokenType.LITERAL) {
				this.token = this.lexico.nextToken();
			}
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void fvallog(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FVALLOG").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
			explo();
		
		}else if(!(TabFirstFollow.getInstance().getFollow().get("FVALLOG").contains(this.token.getTokenType()))){
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}		
	}
	
	private void fid(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FID").contains(this.token.getTokenType())) {
			
			if(TabFirstFollow.getInstance().getFirst().get("FVALLOG").contains(this.token.getTokenType())) {
				
				fvallog();
				
			}else if(TabFirstFollow.getInstance().getFirst().get("OPNUM").contains(this.token.getTokenType())) {
				
				opnum();
				fopnum();
			}
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void fnumint_float(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FNUMINT").contains(this.token.getTokenType())
		   ||TabFirstFollow.getInstance().getFirst().get("FNUMFLOAT").contains(this.token.getTokenType())) { //TODO melhoria, pois fnumfloat possuem o mesmo conjunto first
			
			opnum();
			fopnum();	
			
		}else if(!(TabFirstFollow.getInstance().getFollow().get("FNUMINT").contains(this.token.getTokenType())
				||TabFirstFollow.getInstance().getFollow().get("FNUMFLOAT").contains(this.token.getTokenType()))){ //TODO melhoria, pois fnumfloat possuem o mesmo conjunto follow
			
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
		
	private void flpar(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FLPAR").contains(this.token.getTokenType())) {
		
			expnum();
			fexpnum();
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void frpar(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FRPAR").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
			
			expnum();
			
		}else if(!(TabFirstFollow.getInstance().getFollow().get("FRPAR").contains(this.token.getTokenType()))){
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void opnum(){
		
		if(TabFirstFollow.getInstance().getFirst().get("OPNUM").contains(this.token.getTokenType())) {
			this.token = this.lexico.nextToken();
		
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
		
	}
	
	private void fid_1(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FID_1").contains(this.token.getTokenType())) {
			
			if(TabFirstFollow.getInstance().getFirst().get("FVALLOG").contains(this.token.getTokenType())) {
				fvallog();
			
			}else if(TabFirstFollow.getInstance().getFirst().get("OPNUM").contains(this.token.getTokenType())) {
				opnum();
				
				expnum();
				
				if(this.token.getTokenType() != TokenType.RELOP) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroRelop(this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
				
				expnum();
			
			}else if(this.token.getTokenType() == TokenType.RELOP) {
				
				this.token = this.lexico.nextToken();
				expnum();
			}
			
		}else if(!(TabFirstFollow.getInstance().getFollow().get("FID_1").contains(this.token.getTokenType()))){
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
	
	private void expnum(){
		
		if(TabFirstFollow.getInstance().getFirst().get("EXPNUM").contains(this.token.getTokenType())) {
			
			if(TabFirstFollow.getInstance().getFirst().get("VAL").contains(this.token.getTokenType())) {
				
				val();
				xexpnum();
			
			}else if(this.token.getTokenType() == TokenType.L_PAR) {
				
				this.token = this.lexico.nextToken();
				expnum();
				
				if(this.token.getTokenType() != TokenType.R_PAR) {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEncontrado(")", this.token.getLin(), this.token.getCol()));
				}
				
				this.token = this.lexico.nextToken();
			}

		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
		
	}
	
	private void val(){
		
		if(TabFirstFollow.getInstance().getFirst().get("VAL").contains(this.token.getTokenType())) {
			this.token = this.lexico.nextToken();
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
		
	}
	
	private void xexpnum(){
		
		if(TabFirstFollow.getInstance().getFirst().get("XEXPNUM").contains(this.token.getTokenType())) {

			opnum();
			expnum();
		
		}else if(!(TabFirstFollow.getInstance().getFollow().get("XEXPNUM").contains(this.token.getTokenType()))){
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
		
	}
	
	private void fopnum(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FOPNUM_1").contains(this.token.getTokenType())) {
			
			expnum();
			frpar();
			
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}

	}

	private void fexpnum(){
		
		if(TabFirstFollow.getInstance().getFirst().get("FEXPNUM").contains(this.token.getTokenType())) {
			
			this.token = this.lexico.nextToken();
			
			frpar();
		
		}else {
			ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroTokenNaoEsperado(this.token.getLexema(), this.token.getLin(), this.token.getCol()));
		}
	}
}
