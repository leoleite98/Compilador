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
	
	private void dcl() {
		
	}
	
	private void cond() {
		
	}
	
	private void repf() {
		
	}
	
	private void repw() {
		
	}
	
	private void atrib() {
		
	}
	
	private void rep() {
		
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
