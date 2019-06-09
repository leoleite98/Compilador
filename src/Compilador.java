//Leonardo Leite - CCO 7ºs - 1510032009
import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class Compilador {

	private static Scanner entrada;

	public static void main(String[] args) throws EOFException, IOException {
		
		entrada = new Scanner(System.in);
		String localArquivo = ""; //"D:\\Projetos\\eclipse-workspace\\Compilador\\src\\ArquivoExemplo"

		boolean encontrouArquivo = false;
		
		//solicita caminho do arquivo até ser informado um caminho valido
		while(!encontrouArquivo) {
			
			System.out.printf("Digite o local do arquivo: \n" );
			localArquivo = entrada.next();
			
			try {
				Sintatico sint = new Sintatico(localArquivo);
				encontrouArquivo = true;
				
				System.out.println("-----------------LEITURA DO ARQUIVO INICIADA-----------------");
				
				//inicia o processamento do analisador sintatico
				sint.processar();
				
			}catch(Exception e) {
				System.out.printf(e.getMessage() + "\n");
			}
		}
	}

}
