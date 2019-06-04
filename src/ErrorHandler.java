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
	
}
