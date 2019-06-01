//Leonardo Leite - CCO 7ºs - 1510032009
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
	
	public List<String> errors;
	
	public ErrorHandler() {
		this.errors = new ArrayList<String>();
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
	
}
