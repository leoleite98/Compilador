//Leonardo Leite - CCO 7ºs - 1510032009

//objeto criado para tratar a solicitacao de um novo token
public class RetornoSolicitacaoChar {
	
	//caso seja verdadeiro, foi possivel obter o nextChar() do fileloader
	private boolean sucesso;
	//valor obtido do nextChar() do fileloader
	private char c;
	
	public RetornoSolicitacaoChar() {
		
	}
	
	public RetornoSolicitacaoChar(boolean sucesso, char c) {
		this.setSucesso(sucesso);
		this.setC(c);
	}
	
	public RetornoSolicitacaoChar(boolean sucesso) {
		this.setSucesso(sucesso);
	}

	public boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}
}
