//Leonardo Leite - CCO 7ºs - 1510032009
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
	
	//gera String para erro de fim do arquivo não esperado.
	public static String gerarErroFimArquivo() {
		return "Fim do arquivo não esperado na leitura do Token.";
	}
	
	//gera String para caracter não esperado
	public static String gerarErroCaracterNaoEsperado(char c, long line, long column, String tokenEsperado) {
		return "Caracter '" + c + "' não esperado na leitura de um " + tokenEsperado + ". Linha " + line + ", coluna " + column +". ";
	}
	
	//gera String para caracter não esperado inicio Token
	public static String gerarErroCaracterNaoEsperadoInicioToken(char c, long line, long column) {
			return "Caracter '" + c + "' não esperado no início da leitura de um novo Token. Linha " + line + ", coluna " + column +". ";
	}
	
	//ERROS SINTATICOS
		
	public static String gerarErroProgram() {
		return "O programa deve ser iniciado com 'program'.";
	}
	
	public static String gerarErroNomeProgram() {
		return "Não foi encontrado um nome válido para o programa.";
	}
	
	public static String gerarErroTermProgram() {
		return "Não foi encontrado ';' no final da declaração do programa.";
	}
	
	public static String gerarErroEndProgram() {
		return "Não foi encontrado 'end_prog' no final do bloco.";
	}
	
	public static String gerarErroIdDeclare() {
		return "É necessário informar um nome para a variável.";
	}
	
	public static String gerarErroTypeDeclare() {
		return "É necessário informar um tipo para a variável.";
	}
	
	public static String gerarErroTermDeclare() {
		return "Não foi encontrado ';' no final da declaração da váriavel."; 
	}
	
	public static String gerarErroLparIf() {
		return "Não foi encontrado '(' após a declaração do IF.";
	}
	
	public static String gerarErroIdFor() {
		return "É necessário inserir um ID depois do FOR.";
	}
	
	public static String gerarErroAssignFor() {
		return "Não foi encontrado '<-' na declaração do FOR.";
	}
	
	public static String gerarErroToFor() {
		return "Não foi encontrado 'to' na declaração do FOR.";
	}
	
	public static String gerarErroLparWhile() {
		return "É necessário inserir '(' depois do WHILE.";
	}
	
	public static String gerarErroRparWhile() {
		return "Não foi encontrado ')' na declaração do WHILE.";
	}
	
	
	//ERROS GENÉRICOS
	
	public static String gerarErroTokenNaoEsperado(String token) {
		return "'" + token + "' não esperado.";
	}
	
	public static String gerarErroTokenNaoEncontrado(String token) {
		return "Não foi encontrado " + token + ".";
	}
	
	
	
	

}
