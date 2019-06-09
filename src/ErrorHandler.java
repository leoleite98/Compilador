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
		
	public static String gerarErroProgram(long line, long column) {
		return "O programa deve ser iniciado com 'program'. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroNomeProgram(long line, long column) {
		return "Não foi encontrado um nome válido para o programa. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermProgram(long line, long column) {
		return "Não foi encontrado ';' no final da declaração do programa. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroEndProgram(long line, long column) {
		return "Não foi encontrado 'end_prog' no final do programa. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroEndBloco(long line, long column) {
		return "Não foi encontrado 'end' no final do bloco. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermEndProgram(long line, long column) {
		return "Não foi encontrado ';' no final do programa. Linha " + line + ", coluna " + column + ".";
	}
	
	
	public static String gerarErroIdDeclare(long line, long column) {
		return "É necessário informar um nome para a variável. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTypeDeclare(long line, long column) {
		return "É necessário informar um tipo para a variável. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermDeclare(long line, long column) {
		return "Não foi encontrado ';' no final da declaração da váriavel. Linha " + line + ", coluna " + column + "."; 
	}
	
	public static String gerarErroLparIf(long line, long column) {
		return "Não foi encontrado '(' após a declaração do IF. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroRparIf(long line, long column) {
		return "Não foi encontrado ')' na declaração do IF. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroThenIf(long line, long column) {
		return "Não foi encontrado 'then' na declaração do IF. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroIdFor(long line, long column) {
		return "É necessário inserir um ID depois do FOR. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroAssignFor(long line, long column) {
		return "Não foi encontrado '<-' na declaração do FOR. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroToFor(long line, long column) {
		return "Não foi encontrado 'to' na declaração do FOR. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroLparWhile(long line, long column) {
		return "É necessário inserir '(' depois do WHILE. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroRparWhile(long line, long column) {
		return "Não foi encontrado ')' na declaração do WHILE. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroIdAtrib(String token, long line, long column) {
		return "'" + token + "' inválido para atribuição. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermAtrib(long line, long column) {
		return "Não foi encontrado ';' no final da atribuição. Linha " + line + ", coluna " + column + ".";
	}
	
	//ERROS SINTATICOS GENÉRICOS
	
	public static String gerarErroTokenNaoEsperado(String token, long line, long column) {
		return "'" + token + "' não esperado. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTokenNaoEncontrado(String token, long line, long column) {
		return "Não foi encontrado " + token + ". Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroRelop(long line, long column) {
		return "Não foi encontrado um token do tipo RELOP. Linha " + line + ", coluna " + column + ".";
	}

}
