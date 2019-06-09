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
		
	public static String gerarErroProgram(long line, long column) {
		return "O programa deve ser iniciado com 'program'. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroNomeProgram(long line, long column) {
		return "N�o foi encontrado um nome v�lido para o programa. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermProgram(long line, long column) {
		return "N�o foi encontrado ';' no final da declara��o do programa. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroEndProgram(long line, long column) {
		return "N�o foi encontrado 'end_prog' no final do programa. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroEndBloco(long line, long column) {
		return "N�o foi encontrado 'end' no final do bloco. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermEndProgram(long line, long column) {
		return "N�o foi encontrado ';' no final do programa. Linha " + line + ", coluna " + column + ".";
	}
	
	
	public static String gerarErroIdDeclare(long line, long column) {
		return "� necess�rio informar um nome para a vari�vel. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTypeDeclare(long line, long column) {
		return "� necess�rio informar um tipo para a vari�vel. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermDeclare(long line, long column) {
		return "N�o foi encontrado ';' no final da declara��o da v�riavel. Linha " + line + ", coluna " + column + "."; 
	}
	
	public static String gerarErroLparIf(long line, long column) {
		return "N�o foi encontrado '(' ap�s a declara��o do IF. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroRparIf(long line, long column) {
		return "N�o foi encontrado ')' na declara��o do IF. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroThenIf(long line, long column) {
		return "N�o foi encontrado 'then' na declara��o do IF. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroIdFor(long line, long column) {
		return "� necess�rio inserir um ID depois do FOR. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroAssignFor(long line, long column) {
		return "N�o foi encontrado '<-' na declara��o do FOR. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroToFor(long line, long column) {
		return "N�o foi encontrado 'to' na declara��o do FOR. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroLparWhile(long line, long column) {
		return "� necess�rio inserir '(' depois do WHILE. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroRparWhile(long line, long column) {
		return "N�o foi encontrado ')' na declara��o do WHILE. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroIdAtrib(String token, long line, long column) {
		return "'" + token + "' inv�lido para atribui��o. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTermAtrib(long line, long column) {
		return "N�o foi encontrado ';' no final da atribui��o. Linha " + line + ", coluna " + column + ".";
	}
	
	//ERROS SINTATICOS GEN�RICOS
	
	public static String gerarErroTokenNaoEsperado(String token, long line, long column) {
		return "'" + token + "' n�o esperado. Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroTokenNaoEncontrado(String token, long line, long column) {
		return "N�o foi encontrado " + token + ". Linha " + line + ", coluna " + column + ".";
	}
	
	public static String gerarErroRelop(long line, long column) {
		return "N�o foi encontrado um token do tipo RELOP. Linha " + line + ", coluna " + column + ".";
	}

}
