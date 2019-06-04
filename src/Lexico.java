//Leonardo Leite - CCO 7ºs - 1510032009
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Lexico {
	
	public FileLoader fileLoader;	
	
	public Lexico(String fileName) throws FileNotFoundException {
		this.fileLoader = new FileLoader(fileName);
	}
	
	//valida os token, caso seja processado com sucesso retorna o Token, caso ocorra falhas insere no ErrorHandler o tipo do erro encontrado.
	public Token nextToken() throws EOFException, IOException {
		
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		char c;
		
		solicitacao = solicitarNextChar();
		
		if(solicitacao.getSucesso()) {
			c = solicitacao.getC();	
			
			//ignora os espaços
			if(!Character.isWhitespace(c)) {
				
				//processa comentario
				if(c == '{') {
					return processarComentario(c);
					
				//verifica se o token está na tabela de Single Token, se estiver é retornado o token	
				}else if(TabSingleToken.getInstance().getTab().containsKey(c)) {
					return new Token(TabSingleToken.getInstance().getTab().get(c), String.valueOf(c), this.fileLoader.getLine(), this.fileLoader.getColumn());
				
				//verifica relop
				}else if(c == '$') {
					return processarRelop(c);
				
				//verifica assing
				}else if(c == '<') {
					return processarAssign(c);
					
				//verifica literal
				}else if(c == '"'){
					return processarLiterar(c);
					
				//verifica alphabetic (ID ou palavra reservada)
				}else if(Character.isAlphabetic(c) || c == '_') {
					return processarAlphabetic(c);
				
				//verifica digit (INT ou FLOAT)	
				}else if(Character.isDigit(c)) {	
					return processarDigit(c);
				
				//insere erro de caracter não esperado
				}else {
					ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperadoInicioToken(c, this.fileLoader.getLine(), this.fileLoader.getColumn()));
					return nextToken();
				}
			
			}else {
				return nextToken();
			}
		
		//retorna EOF quando a solicitação do nextChar() falha
		}else{
			return new Token(TokenType.EOF, "", this.fileLoader.getLine(), this.fileLoader.getColumn());
		}
	}
	
	//processa a leitura de comentario, chama nextToken novamente em caso de sucesso
	private Token processarComentario(char c) throws EOFException, IOException {
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		solicitacao = solicitarNextChar();
		
		if(solicitacao.getSucesso()) {
			c = solicitacao.getC();
			
			if(c == '#') {
				boolean processandoComentario = true;
				
				while(processandoComentario){
					solicitacao = solicitarNextChar();
					
					if(solicitacao.getSucesso()) {
						c = solicitacao.getC();
						
						if(c == '#') {
							solicitacao = solicitarNextChar();
							
							if(solicitacao.getSucesso()) {
								c = solicitacao.getC();
								
								if(c == '}') {
									processandoComentario = false;
								}
							}else {
								return retornarEOFcomErro();
							}
						}
					}else {
						return retornarEOFcomErro();
					}
				}
				
				return nextToken();
				
			}else {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "comentário"));
				return nextToken();
			}
			
		}else {
			return retornarEOFcomErro();
		}
		
		
	}
	
	//processa os possíveis valores para o token do tipo relop
	private Token processarRelop(char c) throws EOFException, IOException {
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		String lexema = String.valueOf(c);
		
		solicitacao = solicitarNextChar();
		
		if(solicitacao.getSucesso()){
			c = solicitacao.getC();
			
			if(c == 'l' || c == 'g' || c == 'e' || c == 'd') {
				
				solicitacao = solicitarNextChar();
				lexema = lexema + c;
				
				if(solicitacao.getSucesso()) {
					c = solicitacao.getC();
					
					if(c == 'e' || c == 't' || c == 'q' || c == 'f') {
						lexema = lexema + c;
						return new Token(TokenType.RELOP, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
					
					}else {
						ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "RELOP"));
						return nextToken();
					}
					
				}else {
					return retornarEOFcomErro();
				}
				
			}else {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "RELOP"));
				return nextToken();
			}
		
		}else {
			return retornarEOFcomErro();
		}
		
	}
	
	//processa a leitura de um Assing
	private Token processarAssign(char c) throws EOFException, IOException {
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		String lexema = String.valueOf(c);
		
		solicitacao = solicitarNextChar();
		
		if(solicitacao.getSucesso()) {
			c = solicitacao.getC();
			lexema = lexema + c;
			if(c == '-') {
				return new Token(TokenType.ASSIGN, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
			}else {
				ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "ASSIGN"));
				return nextToken();
			}
		
		}else {
			return retornarEOFcomErro();
		}
	}
	
	//processa a leitura de um literal até encontrar '"'
	private Token processarLiterar(char c) {
		
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		String lexema = String.valueOf(c);
		
		solicitacao = solicitarNextChar();
		
		if(solicitacao.getSucesso()) {
			c = solicitacao.getC();
		}else {
			return retornarEOFcomErro();
		}
		
		
		while(c != '"') {
			lexema = lexema + c;
			
			solicitacao = solicitarNextChar();
			
			if(solicitacao.getSucesso()) {
				c = solicitacao.getC();
			}else {
				return retornarEOFcomErro();
			}
		}
		
		lexema = lexema + c;
		return new Token(TokenType.LITERAL, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
		
	}
	
	//processa a leitura de um ID ou palavra reservada
	private Token processarAlphabetic(char c) throws IOException {
		
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		String lexema = "";
		
		while(Character.isAlphabetic(c) || Character.isDigit(c) || c == '_') {
			lexema = lexema + c;
			
			solicitacao = solicitarNextChar();
			
			if(solicitacao.getSucesso()) {
				c = solicitacao.getC();
			}else {
				break;
			}
		}
		
		this.fileLoader.resetLastChar();
		
		//verifica se o lexema encontrado está na tabela de simbolo, caso esteja retorna o tipo do token da palavra reservada, se não estiver retorna ID
		if(TabSimbolos.getInstance().getTab().containsKey(lexema)) {
			return new Token(TabSimbolos.getInstance().getTab().get(lexema), lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
		}else {
			TabSimbolos.getInstance().addSimbolo(lexema, TokenType.ID);//adiciona novo Id na TabSimbolos
			return new Token(TokenType.ID, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
		}	
	}
	
	//processa a leitura de um digit e suas possíveis variações, caso contenha vírgulha é retornado um FLOAT, se não um INT 
	private Token processarDigit(char c) throws IOException {
		
		RetornoSolicitacaoChar solicitacao = new RetornoSolicitacaoChar();
		boolean isFloat = false;
		String lexema = String.valueOf(c);		
		
		while(Character.isDigit(c)) {
			solicitacao = solicitarNextChar();
			
			if(solicitacao.getSucesso()) {
				
				c = solicitacao.getC();
				
				if(Character.isDigit(c)) {
					lexema = lexema + c;
					continue;
					
				//quando é encontrado um ponto a variavel isFloat é setada para true, não permitindo outro ponto na leitura	
				}else if(c == '.' && !isFloat) {
					lexema = lexema + c;
					isFloat = true;
					
					solicitacao = solicitarNextChar();
					
					if(solicitacao.getSucesso()) {
						
						c = solicitacao.getC();
						
						if(Character.isDigit(c)) {
							lexema = lexema + c;
							continue;
							
						}else {
							ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "NUM"));
							return nextToken();
						}
						
					}else {
						return retornarEOFcomErro();
					}
					
				
				//quando o 'E' é encontrado, a leitura do digit é feita até o formar o Token
				}else if(c == 'E') {
					lexema = lexema + c;
					solicitacao = solicitarNextChar();
					
					if(solicitacao.getSucesso()) {
						c = solicitacao.getC();
						
						if(c == '+' || c == '-') {
							lexema = lexema + c;
							solicitacao = solicitarNextChar();
							
							if(solicitacao.getSucesso()) {
								c = solicitacao.getC();
								
								if(Character.isDigit(c)) {
									
									while(Character.isDigit(c)) {
										lexema = lexema + c;
										solicitacao = solicitarNextChar();
										
										if(solicitacao.getSucesso()) {
											c = solicitacao.getC();
										}else {
											break;
										}
									}
									
									this.fileLoader.resetLastChar();
									
									if(isFloat) {
										return new Token(TokenType.NUM_FLOAT, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
									}else {
										return new Token(TokenType.NUM_INT, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
									}
									
								}else {
									ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "NUM"));
									return nextToken();
								}
							}else {
								return retornarEOFcomErro();
							}
						}else {
							ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroCaracterNaoEsperado(c, this.fileLoader.getLine(), this.fileLoader.getColumn(), "NUM"));
							return nextToken();
						}
					}else {
						return retornarEOFcomErro();
					}
				}else {
					this.fileLoader.resetLastChar();
					break;
				}
				
			}else {
				break;
			}
		}
			
		//verifica se é isFLoat é verdadeiro para determinaer o tipo do Token
		//quando o Token é retornado nesse IF ou ELSE, a lexema não possui o 'E+' ou 'E-'
		if(isFloat) {
			return new Token(TokenType.NUM_FLOAT, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
		}else {
			return new Token(TokenType.NUM_INT, lexema, this.fileLoader.getLine(), this.fileLoader.getColumn());
		}
	}
	
	//solicita o próxima caracter do fileloader, tratando o sucesso e as exceções no objeto RetornoSolicitacaoChar.
	private RetornoSolicitacaoChar solicitarNextChar() {
		try {
			char c = this.fileLoader.getNextChar();
			return new RetornoSolicitacaoChar(true, c);
		}catch(Exception e) {
			return new RetornoSolicitacaoChar(false); 
		}
	}
	
	//adiciona erro de fim de arquivo não esperado e o token do fim de arquivo
	private Token retornarEOFcomErro() {
		ErrorHandler.getInstance().registrarErro(ErrorHandler.gerarErroFimArquivo());
		return new Token(TokenType.EOF, "", this.fileLoader.getLine(), this.fileLoader.getColumn());
	}
		
}
