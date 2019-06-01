import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

//Leonardo Leite - CCO 7ºs - 1510032009
public class Sintatico {
	
	public Lexico lexico;
	private Token token;
	
	//defini o lexico em seu construtor, passando o nome do arquivo
	public Sintatico(String fileName) throws FileNotFoundException{
		this.lexico = new Lexico(fileName); 
	}
	
	//processa a leitura do arquivo, solicitando os Token para o lexico até encontrar o EOF
	//chama o relatorio de erros caso exista algum 
	public void processar() throws EOFException, IOException {
		
		boolean lendoArquivo = true;
		
		while(lendoArquivo){
			Token token = this.lexico.nextToken();
			
			if(token.getTokenType() == TokenType.EOF) {
				lendoArquivo = false;
			}
			
			printToken(token);		
		}
		
		TabSimbolos.getInstance().gerarRelatorio();
		
		if(!this.lexico.helperError.getErrors().isEmpty()) {
			this.lexico.helperError.gerarRelatorio();
		}
	}
	
	private void s() throws EOFException, IOException {
		
		this.token = this.lexico.nextToken();
		
		if(this.token.getTokenType() == TokenType.PROGRAM) {
			this.token = this.lexico.nextToken();
		}else {
			//ERROR
		}
		
		if(this.token.getTokenType() == TokenType.ID) {
			this.token = this.lexico.nextToken();
		}else {
			//ERROR
		}
		
		if(this.token.getTokenType() == TokenType.TERM) {
			this.token = this.lexico.nextToken();
		}else {
			//ERROR
		}
		
		bloco();
		
		if(this.token.getTokenType() == TokenType.END_PROG) {
			this.token = this.lexico.nextToken();
		}else {
			//ERROR
		}

		
		if(this.token.getTokenType() == TokenType.TERM) {
			this.token = this.lexico.nextToken();
		}else {
			//ERROR
		}
	}
	
	private void bloco() throws EOFException, IOException {
				
		if(this.token.getTokenType() == TokenType.BEGIN) {
			
			cmds();
			
			if(this.token.getTokenType() == TokenType.END) {
				this.token = this.lexico.nextToken();
			}else {
				//ERROR
			}
			
		}else {
			cmd();
		}
		
	}
	
	private void cmds() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.DECLARE) {
			dcl();
			cmds();
		
		}else if(this.token.getTokenType() == TokenType.IF) {
			cond();
			cmds();
		
		}else if(this.token.getTokenType() == TokenType.FOR) {
			repf();
			cmds();
		
		}else if(this.token.getTokenType() == TokenType.WHILE) {
			repw();
			cmds();
		
		}else if(this.token.getTokenType() == TokenType.ID) {
			atrib();
			cmds();
		
		}//TODO PALAVRA VAZIA{
		//
		//
		//}
		
		//regra com vazio no final, testar first, se não fazer testar follow, se não erro
	}
	
	private void cmd() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.DECLARE) {
			dcl();
		
		}else if(this.token.getTokenType() == TokenType.IF) {
			cond();
		
		}else if((this.token.getTokenType() == TokenType.FOR) || (this.token.getTokenType() == TokenType.WHILE)) {
			rep();
		
		}else if(this.token.getTokenType() == TokenType.ID) {
			atrib();
		}
	}
	
	private void dcl() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.DECLARE) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		if(this.token.getTokenType() == TokenType.ID) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		if(this.token.getTokenType() == TokenType.TYPE) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		if(this.token.getTokenType() == TokenType.TERM) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
	}
	
	private void cond() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.IF) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		if(this.token.getTokenType() == TokenType.L_PAR) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		explo();
		
		if(this.token.getTokenType() == TokenType.R_PAR) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		if(this.token.getTokenType() == TokenType.THEN) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		bloco();
		
		cndb();
	}
	
	private void repf() {
		
	}
	
	private void repw() {
		
	}
	
	private void atrib() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.IF) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		if(this.token.getTokenType() == TokenType.ASSIGN) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
		exp();
		
		if(this.token.getTokenType() == TokenType.TERM) {
			this.token = this.lexico.nextToken();
		}else {
			//ERRO
		}
		
	}
	
	private void rep() {
		
	}
	
	private void explo() {
		
	}
	
	private void cndb() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.ELSE) {
			this.token = this.lexico.nextToken();
			bloco();	
		
		}//ELSE ou ELSE IF{
		//	VAZIO();
		//}
		else {
			//ERRO
		}
	}
	
	private void exp() throws EOFException, IOException {
		
		if(this.token.getTokenType() == TokenType.LOGIC_VAL) {
			
			this.token = this.lexico.nextToken();
			fvallog();
			
		}else if(this.token.getTokenType() == TokenType.ID){
			
			this.token = this.lexico.nextToken();
			fid();
		
		}else if(this.token.getTokenType() == TokenType.NUM_INT){
			
			this.token = this.lexico.nextToken();
			fnumint();
			
		}else if(this.token.getTokenType() == TokenType.NUM_FLOAT){
		
			this.token = this.lexico.nextToken();
			fnumfloat();
		
		}else if(this.token.getTokenType() == TokenType.L_PAR){
			
			this.token = this.lexico.nextToken();
			flpar();
			
		}else if(this.token.getTokenType() == TokenType.LITERAL) {
			this.token = this.lexico.nextToken();
			
		}else {
			//ERRO
		}
	}
	
	private void fvallog(){
		
	}
	
	private void fid(){
		
	}
	
	private void fnumint(){
		
	}
	
	private void fnumfloat(){
		
	}
	
	private void flpar() {
		// TODO Auto-generated method stub
		
	}
	
	//printa os tokens 
	private void printToken(Token t) {
		
		if(t.getTokenType() != TokenType.EOF) {
			System.out.println("TOKEN -> Tipo: " + t.getTokenType() + " - Lexema: " + t.getLexema());
		}else {
			System.out.println("TOKEN -> Tipo: " + t.getTokenType());
		}
	}
}
