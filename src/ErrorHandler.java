//Leonardo Leite - CCO 7�s - 1510032009
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorHandler {
		
	private static ErrorHandler instance = new ErrorHandler();
	private List<String> errors; 
	
	private ErrorHandler() {
		this.errors = new ArrayList<String>();
	}
	
	public static ErrorHandler getInstance() {
		return instance;
	}

	public void registrarErro(String erro) {
		this.errors.add(erro);
	}
	
	//printa erros 
	public void gerarRelatorio() {
		System.out.println("-----ERRO(S) NA LEITURA DO ARQUIVO-----");
		for(String error : this.errors) {
			System.out.println(error);
		}
	}
	
	public List<String> getErrors(){
		return this.errors;
	}
	
	
	//ERROS LEXICOS
	
	//gera String para erro de fim do arquivo n�o esperado.
	public static String gerarErroFimArquivo() {
		return "Fim do arquivo n�o esperado na leitura do Token.";
	}
	
	//gera String para caracter n�o esperado
	public static String gerarErroCaracterNaoEsperado(char c, long line, long column, String tokenEsperado) {
		return "Caracter '" + c + "' n�o esperado na leitura de um " + tokenEsperado + ". Linha " + line + ", coluna " + column +". ";
	}
	
	//gera String para caracter n�o esperado inicio Token
	public static String gerarErroCaracterNaoEsperadoInicioToken(char c, long line, long column) {
			return "Caracter '" + c + "' n�o esperado no in�cio da leitura de um novo Token. Linha " + line + ", coluna " + column +". ";
	}
	
	//ERROS SINTATICOS
		
	public static String gerarErroProgram() {
		return "O programa deve ser iniciado com 'program'.";
	}
	
	public static String gerarErroNomeProgram() {
		return "N�o foi encontrado um nome v�lido para o programa.";
	}
	
	public static String gerarErroTermProgram() {
		return "N�o foi encontrado ';' no final da declara��o do programa.";
	}
	
	public static String gerarErroEndProgram() {
		return "N�o foi encontrado 'end_prog' no final do bloco.";
	}
	
	public static String gerarErroIdDeclare() {
		return "� necess�rio informar um nome para a vari�vel.";
	}
	
	public static String gerarErroTypeDeclare() {
		return "� necess�rio informar um tipo para a vari�vel.";
	}
	
	public static String gerarErroTermDeclare() {
		return "N�o foi encontrado ';' no final da declara��o da v�riavel."; 
	}
	
	public static String gerarErroLparIf() {
		return "N�o foi encontrado '(' ap�s a declara��o do IF.";
	}
	
	public static String gerarErroIdFor() {
		return "� necess�rio inserir um ID depois do FOR.";
	}
	
	public static String gerarErroAssignFor() {
		return "N�o foi encontrado '<-' na declara��o do FOR.";
	}
	
	public static String gerarErroToFor() {
		return "N�o foi encontrado 'to' na declara��o do FOR.";
	}
	
	public static String gerarErroLparWhile() {
		return "� necess�rio inserir '(' depois do WHILE.";
	}
	
	public static String gerarErroRparWhile() {
		return "N�o foi encontrado ')' na declara��o do WHILE.";
	}
	
	
	//ERROS GEN�RICOS
	
	public static String gerarErroTokenNaoEsperado(String token) {
		return "'" + token + "' n�o esperado.";
	}
	
	public static String gerarErroTokenNaoEncontrado(String token) {
		return "N�o foi encontrado " + token + ".";
	}
	
	
	
	

}
