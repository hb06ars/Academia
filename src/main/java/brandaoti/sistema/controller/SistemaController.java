package brandaoti.sistema.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import brandaoti.sistema.dao.AulaDao;
import brandaoti.sistema.dao.AvaliacaoDao;
import brandaoti.sistema.dao.ContratoDao;
import brandaoti.sistema.dao.ParcelaDao;
import brandaoti.sistema.dao.PerfilDao;
import brandaoti.sistema.dao.PlanoDao;
import brandaoti.sistema.dao.PresencaDao;
import brandaoti.sistema.dao.TreinoDao;
import brandaoti.sistema.dao.UsuarioDao;
import brandaoti.sistema.excel.ProcessaExcel;
import brandaoti.sistema.excel.Tabela;
import brandaoti.sistema.model.Aula;
import brandaoti.sistema.model.Avaliacao;
import brandaoti.sistema.model.Contrato;
import brandaoti.sistema.model.Horario;
import brandaoti.sistema.model.Objeto;
import brandaoti.sistema.model.Parcela;
import brandaoti.sistema.model.Perfil;
import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Presenca;
import brandaoti.sistema.model.Treino;
import brandaoti.sistema.model.Usuario;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SistemaController extends HttpServlet {
		
		private static final long serialVersionUID = 1L;
		@Autowired
		private UsuarioDao usuarioDao;
		@Autowired
		private PerfilDao perfilDao;
		@Autowired
		private TreinoDao treinoDao;
		@Autowired
		private PlanoDao planoDao;
		@Autowired
		private ContratoDao contratoDao;
		@Autowired
		private ParcelaDao parcelaDao;
		@Autowired
		private AulaDao aulaDao;
		@Autowired
		private PresencaDao presencaDao;
		@Autowired
		private AvaliacaoDao avaliacaoDao;
		
		//public static Usuario usuarioSessao;
		//public static String atualizarPagina = null;
		//public static Boolean logado = false;
		//public static String itemMenu = "home";
		//public static String paginaAtual = "Dashboard";
		//public static String iconePaginaAtual = "fa fa-home";
		
		
		public String gerarMatricula() {
			Random gerador = new Random();
	    	Calendar data = Calendar.getInstance();
	    	int ano = data.get(Calendar.YEAR);
	    	int mes = data.get(Calendar.MONTH);
	    	mes++;
	    	int m = mes;
	    	int dia = data.get(Calendar.DAY_OF_MONTH);
	        int hora = data.get(Calendar.HOUR_OF_DAY); 
	        int min = data.get(Calendar.MINUTE);
	        int seg = data.get(Calendar.SECOND);
	        int numero = gerador.nextInt(100);
	        String matricula = ""+ano+m+dia+hora+min+seg+numero;
	        return matricula;
		}
		
		@RequestMapping(value = {"/","/login"}, produces = "text/plain;charset=UTF-8", method = RequestMethod.GET) // Pagina de Vendas
		public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "nome", required = false, defaultValue = "Henrique Brandão") String nome) throws SQLException, ServletException, IOException { //Funcao e alguns valores que recebe...
			//Caso não haja registros
			HttpSession session = request.getSession();
			List<Usuario> u = usuarioDao.buscarTudo();
			List<Perfil> p = perfilDao.buscarTudo();
			List<Plano> pl = planoDao.buscarTudo();
			
			if(p == null || p.size() == 0) {
				Perfil perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("1");
				perfil.setNome("adm");
				perfil.setAdmin(true);
				perfil.setFuncionario(true);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("2");
				perfil.setNome("Aluno");
				perfil.setAdmin(false);
				perfil.setFuncionario(false);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("3");
				perfil.setNome("Funcionário");
				perfil.setAdmin(false);
				perfil.setFuncionario(true);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("4");
				perfil.setNome("Professor");
				perfil.setAdmin(false);
				perfil.setFuncionario(true);
				perfil.setProfessor(true);
				perfil.setAluno(true);
				perfilDao.save(perfil);
			}
			if(pl == null || pl.size() == 0) {
				Plano plano = new Plano();
				plano.setCodigo("1");
				plano.setNome("A");
				plano.setAtivo(true);
				plano.setValor(1200.0);
				plano.setDescricao("Plano Básico");
				planoDao.save(plano);
				plano = new Plano();
				plano.setCodigo("2");
				plano.setNome("B");
				plano.setValor(2000.0);
				plano.setAtivo(true);
				plano.setDescricao("Plano Intermediário");
				planoDao.save(plano);
				plano = new Plano();
				plano.setCodigo("3");
				plano.setNome("C");
				plano.setValor(2500.0);
				plano.setAtivo(true);
				plano.setDescricao("Plano Avançado");
				planoDao.save(plano);
			}
			
			
			
			if(u == null || u.size() == 0) {
				/* Descomentar.
				Usuario usu = new Usuario();
				usu.setAtivo(true);
				usu.setMatricula("adm");
				usu.setSenha("adm");
				usu.setNome("Admnistrador");
				usu.setPerfil(perfilDao.buscarAdm().get(0));
				usuarioDao.save(usu);
				*/
				
				// Excluir ----------------------------------------------------------------------------------------------------
				// Henrique
				Usuario h = new Usuario();
				h.setAtivo(true);
				h.setMatricula("587");
				h.setCpf("22233344455");
				h.setEmail("adm@adm.com");
				h.setSenha("adm");
				h.setNome("Henrique");
				h.setTelefone("(11)98931-6271");
				h.setCelular("(11)98931-6271");
				h.setEndereco("Teste...");
				h.setCep("00000-000");
				h.setBairro("Jd da Alegria");
				h.setBairro("São Paulo");
				h.setEstado("SP");
				h.setPerfil(perfilDao.buscarAdm().get(0));
				usuarioDao.save(h);
				
				Usuario d = new Usuario();
				d.setAtivo(true);
				d.setMatricula("123");
				d.setCpf("11122233344");
				d.setEmail("teste2@teste.com");
				d.setSenha("123");
				d.setNome("Douglas");
				d.setTelefone("(11)99999-9999");
				d.setCelular("(11)99999-9999");
				d.setEndereco("Teste...");
				d.setCep("00000-000");
				d.setBairro("Jd da Alegria");
				d.setBairro("São Paulo");
				d.setEstado("SP");
				d.setPerfil(perfilDao.buscarProfessor().get(0));
				d.setPathImagem("https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/2048px-User_icon_2.svg.png");
				usuarioDao.save(d);
				
				// Rafael
				Usuario r = new Usuario();
				r.setAtivo(true);
				r.setMatricula("456");
				r.setCpf("11122233355");
				r.setEmail("teste3@teste.com");
				r.setSenha("456");
				r.setNome("Rafael");
				r.setTelefone("(11)99999-9999");
				r.setCelular("(11)99999-9999");
				r.setEndereco("Teste...");
				r.setCep("00000-000");
				r.setBairro("Jd da Alegria");
				r.setBairro("São Paulo");
				r.setEstado("SP");
				r.setPathImagem("https://ksarquitetos.com.br/wp-content/uploads/2014/12/user.png");
				r.setPerfil(perfilDao.buscarProfessor().get(0));
				usuarioDao.save(r);
				
				
				// Rafael
				Usuario a = new Usuario();
				a.setAtivo(true);
				a.setMatricula("343");
				a.setCpf("22332222111");
				a.setEmail("teste4@teste.com");
				a.setSenha("343");
				a.setNome("Aluno Teste");
				a.setTelefone("(11)99999-9999");
				a.setCelular("(11)99999-9999");
				a.setEndereco("Teste...");
				a.setCep("00000-000");
				a.setBairro("Jd da Alegria");
				a.setBairro("São Paulo");
				a.setEstado("SP");
				a.setPathImagem("https://ksarquitetos.com.br/wp-content/uploads/2014/12/user.png");
				a.setHashDigital("123");
				a.setPerfil(perfilDao.buscarSomenteAluno().get(0));
				a.setPlano(planoDao.findAll().get(0));
				usuarioDao.save(a);
				
				//al.setPerfil(perfilDao.buscarSomenteAluno().get(0));
				//usuarioDao.save(al);
				
				// Excluir ----------------------------------------------------------------------------------------------------

			}
			
			
			String link = "index";
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response);
		}
		
		@RequestMapping(value = "/deslogar", method = {RequestMethod.POST, RequestMethod.GET}) // Link que irÃ¡ acessar...
		public void deslogar(HttpServletRequest request, HttpServletResponse response ) throws IOException { //Funcao e alguns valores que recebe...
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("/");
		}
		
		@RequestMapping(value = "/deletando", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
		public void deletando(HttpServletRequest request, HttpServletResponse response, String tabela,Integer id) throws ServletException, IOException { //Função e alguns valores que recebe...
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String atualizarPagina = "";
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Alunos";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/alunos";
				itemMenu = link;
				
				
				//Caso esteja logado.
				if(tabela.equals("usuario")) {
					
					paginaAtual = "Alunos";
					try {
						List<Parcela> parcelas = parcelaDao.buscarCliente(""+id);
						for(Parcela p : parcelas) {
							p.setAtivo(false);
							parcelaDao.save(p);
						}
						List<Contrato> contratosCli = contratoDao.buscarIdCliente(""+id);
						for(Contrato co : contratosCli) {
							co.setAtivo(false);
							contratoDao.save(co);
						}
					}catch(Exception e) {}
					Usuario objeto = usuarioDao.findById(id).get();
					objeto.setAtivo(false);
					usuarioDao.save(objeto);
					List<Usuario> usuarios = usuarioDao.buscarTudo();
					request.setAttribute("usuarios", usuarios);
					List<Plano> planos = planoDao.buscarTudo();
					request.setAttribute("planos", planos);
					atualizarPagina = "/alunos";
					request.setAttribute("atualizarPagina", atualizarPagina);
				}
				if(tabela.equals("funcionario")) {
					
					paginaAtual = "Funcionários";
					
					try {
						List<Contrato> contratosFunc = contratoDao.buscarIdCliente(""+id);
						System.out.println("erros: "+contratosFunc.size());
						List<Parcela> parcelas = parcelaDao.buscarCliente(""+id);
						for(Parcela p : parcelas) {
							p.setAtivo(false);
							parcelaDao.save(p);
						}
						for(Contrato co : contratosFunc) {
							co.setAtivo(false);
							contratoDao.save(co);
						}
					}catch(Exception e) {}
					Usuario objeto = usuarioDao.findById(id).get();
					objeto.setAtivo(false);
					usuarioDao.save(objeto);
					List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
					request.setAttribute("usuarios", usuarios);
					atualizarPagina = "/funcionarios";
					request.setAttribute("atualizarPagina", atualizarPagina);
				}
				if(tabela.equals("parcela")) {
					
					paginaAtual = "Pendências";
					Parcela objeto = parcelaDao.findById(id).get();
					Contrato c = contratoDao.findById(objeto.getContrato().getId()).get();
					objeto.setAtivo(false);
					parcelaDao.save(objeto);
					
					Integer parcelasDoContrato = parcelaDao.buscarPorContrato(c.getId()).size();
					if(parcelasDoContrato <= 0) {
						c.setAtivo(false);
						contratoDao.save(c);
					}
					List<Parcela> pendencias = parcelaDao.buscarPendencias();
					request.setAttribute("pendencias", pendencias);
					atualizarPagina = "/pendencias";
					request.setAttribute("atualizarPagina", atualizarPagina);
				}
				if(tabela.equals("treino")) {
					link = "pages/cadastrarTreinos";
					
					paginaAtual = "Cadastrar Treinos";
					Treino objeto = treinoDao.findById(id).get();
					Usuario us = usuarioDao.buscarMatricula(objeto.getAluno().getMatricula());
					objeto.setAtivo(false);
					treinoDao.save(objeto);
					try {
						List<Treino> treinos = treinoDao.buscarMatricula(us.getMatricula());
						request.setAttribute("treinos", treinos);
					} catch(Exception e) {
						System.out.println("Erro: "+e);
					}
					request.setAttribute("usuario", us);
				}
				if(tabela.equals("planos")) {
					link = "pages/planos";
					
					paginaAtual = "Cadastrar novo Plano";
					Plano objeto = planoDao.findById(id).get();
					objeto.setAtivo(false);
					planoDao.save(objeto);
					List<Plano> pl = planoDao.buscarTudo();
					request.setAttribute("planos", pl);
				}
				if(tabela.equals("avaliacao")) {
					link = "pages/cadastrarAvaliacao";
					
					paginaAtual = "Avaliação";
					Avaliacao objeto = avaliacaoDao.findById(id).get();
					objeto.setAtivo(false);
					avaliacaoDao.save(objeto);
					List<Avaliacao> pl = avaliacaoDao.buscarTudo();
					request.setAttribute("avaliacao", pl);
				}
			}
			request.setAttribute("usuario", usuarioSessao);
			request.setAttribute("paginaAtual", paginaAtual); 
			request.setAttribute("iconePaginaAtual", iconePaginaAtual);
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		
		/* SALVAR EXCEL */
		@RequestMapping(value = "/upload/excel", method = {RequestMethod.POST, RequestMethod.GET}) // Pagina de Alteração de Perfil
		public void uploadExcel(HttpServletRequest request, HttpServletResponse response, Model model, String tabelaUsada, @ModelAttribute MultipartFile file) throws Exception, IOException { //Função e alguns valores que recebe...
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				System.out.println("file: "+file);
				paginaAtual = "Financeiro";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/home";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//Caso esteja logado.
				ProcessaExcel processaExcel = new ProcessaExcel();
				List<Tabela> tabelas = processaExcel.uploadExcel(file);
		    	String conteudo="";
		   		Integer finalLinha = 0;
		   		Aula a = new Aula();
		   		int coluna = 0;
				switch (tabelaUsada) {  
				 case "aulas" : // CASO SEJA AULAS ---------------------
					 	link = "pages/aulas";
					 	
					 	paginaAtual = "Aulas";
						iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
					 	try {
					 		aulaDao.deleteAll();
					 		String inicio="";
			   				String fim="";
				   			for(int i=0; i < tabelas.size(); i++) {
				   				coluna = tabelas.get(i).getColuna();
				   				conteudo = tabelas.get(i).getConteudo();
				   				if(coluna == 0) inicio = conteudo;
				   				if(coluna == 1) fim = conteudo;
				   				
				   				if(coluna == 2) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Segunda");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 3) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Terça");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 4) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Quarta");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 5) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Quinta");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 6) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Sexta");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 7) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Sábado");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 8) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Domingo");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				
				   				if(finalLinha >= 8) {
				   					finalLinha = -1;
				   				}
				   				finalLinha++;
				   			}
				   		} catch(Exception e) {
				   			System.out.println("Erro: "+ e);
				   		}
					 	List<Aula> aulas = aulaDao.buscarTudo();
					 	request.setAttribute("aulas", aulas);
					 	List<Aula> h = aulaDao.buscarhorarios();
					 	List<Horario> horarios = new ArrayList<Horario>();
					 	String ultimoHorarioInicio = "";
					 	String ultimoHorarioFim = "";
					 	for(Aula aul : h) {
					 		if(!((ultimoHorarioInicio.equals(aul.getInicio())) && (ultimoHorarioFim.equals(aul.getFim())))) {
					 			ultimoHorarioInicio = aul.getInicio();
					 			ultimoHorarioFim = aul.getFim();
					 			Horario hr = new Horario();
					 			hr.setInicio(aul.getInicio());
					 			hr.setFim(aul.getFim());
					 			horarios.add(hr);
					 		}
					 	}
					 	request.setAttribute("horarios", horarios);
					}
			}
			System.out.println("Validado");
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
			
		}
		
		
		@RequestMapping(value = "/home", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void home(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "usuarioVal", defaultValue = "") String usuarioVal, @RequestParam(value = "senhaVal", defaultValue = "") String senhaVal) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(usuarioSessao.getId() == null) {
				Usuario u = usuarioDao.fazerLogin(usuarioVal, senhaVal);
				usuarioSessao = u;
			}
			if((usuarioSessao != null && usuarioSessao.getId() != null ) || logado) {
				link = "home";
				itemMenu = link;
				logado=true;
				session.setAttribute("usuarioSessao",usuarioSessao);
				session.setAttribute("logado",logado);
				
				if(usuarioSessao != null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
					paginaAtual = "Financeiro";
					iconePaginaAtual = "fa fa-money"; //Titulo do menuzinho.
					link = "pages/financeiro"; //Colocar regra se for ADM ou Aluno.
				} else {
					paginaAtual = "Meu Treino";
					iconePaginaAtual = "fa fa-edit"; //Titulo do menuzinho.
					link = "pages/treino"; //Colocar regra se for ADM ou Aluno.
				}
			} else {
				logado=false;
				link = "pages/deslogar"; 
			}
			if(logado) {
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				if(usuarioSessao != null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
					Integer presentesOntem = presencaDao.presentesOntem().size();
					Integer todosAlunos = usuarioDao.buscarAlunos().size();
					List<Parcela> alunosPendentesLista = parcelaDao.buscarPendencias();
					Integer alunosPendentes = 0;
					Usuario a = new Usuario();
					for(Parcela p: alunosPendentesLista) {
						if(a == null || p.getContrato().getCliente() != a) {
							a = p.getContrato().getCliente();
							alunosPendentes++;
						}
					}
					Integer alunosAniversariantes = usuarioDao.buscarAniversariantes().size();
					Integer novosDoMes = usuarioDao.novosDoMes().size();
					List<Objeto> mesesTodos = new ArrayList<Objeto>();
					List<Objeto> mesesManha= new ArrayList<Objeto>();
					List<Objeto> mesesTarde = new ArrayList<Objeto>();
					List<Objeto> mesesNoite = new ArrayList<Objeto>();
					String mesStr = "";
					String valStr = "";
					for(int i = 1; i <= 12; i++) {
						switch(i) {
						  case 1: mesStr ="Jan"; break;
						  case 2: mesStr ="Fev"; break;
						  case 3: mesStr ="Mar"; break;
						  case 4: mesStr ="Abr"; break;
						  case 5: mesStr ="Mai"; break;
						  case 6: mesStr ="Jun"; break;
						  case 7: mesStr ="Jul"; break;
						  case 8: mesStr ="Ago"; break;
						  case 9: mesStr ="Set"; break;
						  case 10: mesStr ="Out"; break;
						  case 11: mesStr ="Nov"; break;
						  case 12: mesStr ="Dez"; break;
						  default:
						}
						Objeto mTodos = new Objeto();
						mTodos.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMes(i).size();
						valStr = valStr.replace(".0", "");
						mTodos.setValor1(valStr);
						mesesTodos.add(mTodos);
						
						Objeto mManha = new Objeto();
						mManha.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesManha(i).size();
						valStr = valStr.replace(".0", "");
						mManha.setValor1(valStr);
						mesesManha.add(mManha);
						
						Objeto mTarde = new Objeto();
						mTarde.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesTarde(i).size();
						valStr = valStr.replace(".0", "");
						mTarde.setValor1(valStr);
						mesesTarde.add(mTarde);
						
						Objeto mNoite = new Objeto();
						mNoite.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesNoite(i).size();
						valStr = valStr.replace(".0", "");
						mNoite.setValor1(valStr);
						mesesNoite.add(mNoite);
					}
					request.setAttribute("presentesOntem", presentesOntem);
					request.setAttribute("todosAlunos", todosAlunos);
					request.setAttribute("alunosPendentes", alunosPendentes);
					request.setAttribute("alunosAniversariantes", alunosAniversariantes);
					request.setAttribute("novosDoMes", novosDoMes);
					request.setAttribute("mesesTodos", mesesTodos);
					request.setAttribute("mesesManha", mesesManha);
					request.setAttribute("mesesTarde", mesesTarde);
					request.setAttribute("mesesNoite", mesesNoite);
				}
				if(usuarioSessao != null && usuarioSessao.getPerfil() != null && !usuarioSessao.getPerfil().getAdmin()) {
					List<Treino> treinos = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
					request.setAttribute("treinos", treinos);
				}
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}

		
		@RequestMapping(value = "/financeiro", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void financeiro(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Financeiro";
				iconePaginaAtual = "fa fa-money"; //Titulo do menuzinho.
				link = "pages/financeiro";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				if(usuarioSessao != null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
					Integer presentesOntem = presencaDao.presentesOntem().size();
					Integer todosAlunos = usuarioDao.buscarAlunos().size();
					
					List<Parcela> alunosPendentesLista = parcelaDao.buscarPendencias();
					Integer alunosPendentes = 0;
					Usuario a = new Usuario();
					for(Parcela p: alunosPendentesLista) {
						if(a == null || p.getContrato().getCliente() != a) {
							a = p.getContrato().getCliente();
							alunosPendentes++;
						}
					}
					
					Integer alunosAniversariantes = usuarioDao.buscarAniversariantes().size();
					Integer novosDoMes = usuarioDao.novosDoMes().size();
					
					List<Objeto> mesesTodos = new ArrayList<Objeto>();
					List<Objeto> mesesManha= new ArrayList<Objeto>();
					List<Objeto> mesesTarde = new ArrayList<Objeto>();
					List<Objeto> mesesNoite = new ArrayList<Objeto>();
					String mesStr = "";
					String valStr = "";
					for(int i = 1; i <= 12; i++) {
						switch(i) {
						  case 1: mesStr ="Jan"; break;
						  case 2: mesStr ="Fev"; break;
						  case 3: mesStr ="Mar"; break;
						  case 4: mesStr ="Abr"; break;
						  case 5: mesStr ="Mai"; break;
						  case 6: mesStr ="Jun"; break;
						  case 7: mesStr ="Jul"; break;
						  case 8: mesStr ="Ago"; break;
						  case 9: mesStr ="Set"; break;
						  case 10: mesStr ="Out"; break;
						  case 11: mesStr ="Nov"; break;
						  case 12: mesStr ="Dez"; break;
						  default:
						}
						Objeto mTodos = new Objeto();
						mTodos.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMes(i).size();
						valStr = valStr.replace(".0", "");
						mTodos.setValor1(valStr);
						mesesTodos.add(mTodos);
						
						Objeto mManha = new Objeto();
						mManha.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesManha(i).size();
						valStr = valStr.replace(".0", "");
						mManha.setValor1(valStr);
						mesesManha.add(mManha);
						
						Objeto mTarde = new Objeto();
						mTarde.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesTarde(i).size();
						valStr = valStr.replace(".0", "");
						mTarde.setValor1(valStr);
						mesesTarde.add(mTarde);
						
						Objeto mNoite = new Objeto();
						mNoite.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesNoite(i).size();
						valStr = valStr.replace(".0", "");
						mNoite.setValor1(valStr);
						mesesNoite.add(mNoite);
					}
					request.setAttribute("presentesOntem", presentesOntem);
					request.setAttribute("todosAlunos", todosAlunos);
					request.setAttribute("alunosPendentes", alunosPendentes);
					request.setAttribute("alunosAniversariantes", alunosAniversariantes);
					request.setAttribute("novosDoMes", novosDoMes);
					request.setAttribute("mesesTodos", mesesTodos);
					request.setAttribute("mesesManha", mesesManha);
					request.setAttribute("mesesTarde", mesesTarde);
					request.setAttribute("mesesNoite", mesesNoite);
				}
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		
		@RequestMapping(value = "/treino", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void treino(HttpServletRequest request, HttpServletResponse response, Integer proximo, Integer anterior) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Treino";
				iconePaginaAtual = "fa fa-edit"; //Titulo do menuzinho.
				link = "pages/treino";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				String treinoSelecionado = "A";
				if(proximo != null && proximo > 0) {
					Integer maiorTreino = 0;
					try { maiorTreino =  treinoDao.maiorTreino(usuarioSessao.getMatricula()); } catch(Exception e) {}
					List<Treino> t = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
					for(Treino tr : t) {
						if(tr.getultimoTreinoExecutado() >= maiorTreino) {
							tr.setultimoTreinoExecutado(0);
						} else {
							tr.setultimoTreinoExecutado(tr.getultimoTreinoExecutado()+1);
						}
						treinoDao.save(tr);
					}
				}
				if(anterior != null && anterior > 0) {
					List<Treino> t = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
					for(Treino tr : t) {
						if(tr.getultimoTreinoExecutado() <= 0) {
							tr.setultimoTreinoExecutado(0);
						} else {
							tr.setultimoTreinoExecutado(tr.getultimoTreinoExecutado()-1);
						}
						treinoDao.save(tr);
					}
				}

				List<Treino> treinos = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
				if(treinos.size() > 0) {
					switch (treinos.get(0).getultimoTreinoExecutado()) {
						case 0: treinoSelecionado = "A"; break;
						case 1: treinoSelecionado = "B"; break;
						case 2: treinoSelecionado = "C"; break;
						case 3: treinoSelecionado = "D"; break;
						case 4: treinoSelecionado = "E"; break;
						case 5: treinoSelecionado = "F"; break;
						case 6: treinoSelecionado = "G"; break;
						default:
							break;
					}
				}
				
				request.setAttribute("treinoSelecionado", treinoSelecionado);
				request.setAttribute("treinos", treinos);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/alunos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void alunos(HttpServletRequest request, HttpServletResponse response, Usuario aluno, String acao, String contrato_obs, Integer contrato_vencimento, String contrato_inicio, String contrato_fim, String contrato_totalContrato, Double contrato_sinal, Double contrato_desconto, Double contrato_total, Integer contrato_parcelas, Double contrato_valorDaParcela) throws SQLException, ParseException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado && usuarioSessao != null && usuarioSessao.getId() != null) {
				String atualizarPagina = "";
				Double vlrContrato_totalContrato = 0.0;
				if(contrato_totalContrato != null) {
					vlrContrato_totalContrato = Double.parseDouble(contrato_totalContrato);
				}
				System.out.println("vlrContrato_totalContrato: "+vlrContrato_totalContrato);
				paginaAtual = "Alunos";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/alunos";
				itemMenu = link;
				
				
				List<Plano> planos = planoDao.buscarTudo();
				request.setAttribute("planos", planos);			
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//Gerando matrícula aleatória
				String matriculaPadrao = gerarMatricula();
				request.setAttribute("matriculaPadrao", matriculaPadrao);
				
				Boolean repetido = false;
				if(usuarioDao.buscarAlunosRepetidos(aluno.getMatricula(), aluno.getCpf()).size() > 0) {
					repetido = true;
				}
				if(aluno.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
						atualizarPagina = "/alunos";
				    	List<Contrato> contratos = new ArrayList<>();
				    	try{
				    		contratos = contratoDao.buscarCliente(""+aluno.getMatricula());
				    	} catch(Exception e) {}
				    	
						Usuario a = new Usuario();
						a = aluno;
						a.setSenha(aluno.getCpf().replace(".", "").replace("-", ""));
						a.setPerfil(perfilDao.buscarSomenteAluno().get(0));
						usuarioDao.save(a);
						
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
						Date inicioContrato = formato.parse(contrato_inicio);
						Date fimContrato = formato.parse(contrato_fim);
						Contrato c = new Contrato();
						c.setAtivo(true);
						c.setFim(fimContrato);
						c.setInicio(inicioContrato);
						c.setNome(aluno.getMatricula());
						c.setObservacoes(contrato_obs);
						c.setValor(contrato_total);
						c.setValorBruto(vlrContrato_totalContrato);
						c.setSinal(contrato_sinal);
						c.setDesconto(contrato_desconto);
						c.setValor(contrato_total);
						c.setParcelas(contrato_parcelas);
						c.setValorDaParcela(contrato_valorDaParcela);
						c.setVencimento(contrato_vencimento);
						c.setCliente(a);
						contratoDao.save(c);
						contratos.add(c);
						
						a.setContrato(contratos);
						usuarioDao.save(a);
						
						Date hoje = new Date();
					    Calendar cal = Calendar.getInstance();
				        cal.setTime(hoje);
				        cal.add(Calendar.MONTH, 1);
				        int dia = cal.get(Calendar.DAY_OF_MONTH);
				        int ano = cal.get(Calendar.YEAR);
				        int mes = cal.get(Calendar.MONTH);
				        mes++;
				        String strDia = contrato_vencimento+"";
				        String strMes = mes+"";
				        String strAno = ano+"";
				        if(dia < 10) strDia = "0"+strDia;
				        if(mes < 10) strMes = "0"+strMes;
				        if(ano < 10) strAno = "0"+strAno;
				        
						for(int i = 0 ; i < contrato_parcelas; i++) {
							Parcela p = new Parcela();
							p.setContrato(c);
							p.setIndice((i+1)+"");
							p.setPago(false);
							p.setValor(contrato_valorDaParcela);
							//Vencimento
							cal.add(Calendar.MONTH, 1);
							mes = cal.get(Calendar.MONTH);
							ano = cal.get(Calendar.YEAR);
							dia = cal.get(Calendar.DAY_OF_MONTH);
							mes++;
							strDia = dia+"";
					        strMes = mes+"";
					        strAno = ano+"";
					        if(contrato_vencimento < 10) strDia = "0"+contrato_vencimento;
					        if(mes < 10) strMes = "0"+strMes;
					        if(ano < 10) strAno = "0"+strAno;
					        p.setVencimento(LocalDate.parse(strAno+"-"+strMes+"-"+strDia));
					    	parcelaDao.save(p);
						}

						
					} catch(Exception e) {
						request.setAttribute("erro", e);
					}
				} else if (aluno.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.buscarMatricula(aluno.getMatricula());
					a.setNome(aluno.getNome());
					a.setDataNascimento(aluno.getDataNascimento());
					a.setTelefone(aluno.getTelefone());
					a.setCelular(aluno.getCelular());
					a.setEndereco(aluno.getEndereco());
					a.setEmail(aluno.getEmail());
					a.setPathImagem(aluno.getPathImagem());
					a.setCep(aluno.getCep());
					a.setBairro(aluno.getBairro());
					a.setCidade(aluno.getCidade());
					a.setEstado(aluno.getEstado());
					a.setPlano(aluno.getPlano());
					usuarioDao.save(a);
					
					if(contratoDao.buscarCliente(aluno.getMatricula()).size() <= 0) {
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
						Date inicioContrato = formato.parse(contrato_inicio);
						Date fimContrato = formato.parse(contrato_fim);
						Contrato c = new Contrato();
						c.setAtivo(true);
						c.setFim(fimContrato);
						c.setInicio(inicioContrato);
						c.setNome(aluno.getMatricula());
						c.setObservacoes(contrato_obs);
						c.setValor(contrato_total);
						c.setValorBruto(vlrContrato_totalContrato);
						c.setSinal(contrato_sinal);
						c.setDesconto(contrato_desconto);
						c.setValor(contrato_total);
						c.setParcelas(contrato_parcelas);
						c.setValorDaParcela(contrato_valorDaParcela);
						c.setVencimento(contrato_vencimento);
						c.setCliente(a);
						contratoDao.save(c);
						
						Date hoje = new Date();
					    Calendar cal = Calendar.getInstance();
				        cal.setTime(hoje);
				        cal.add(Calendar.MONTH, 1);
				        int dia = cal.get(Calendar.DAY_OF_MONTH);
				        int ano = cal.get(Calendar.YEAR);
				        int mes = cal.get(Calendar.MONTH);
				        mes++;
				        String strDia = contrato_vencimento+"";
				        String strMes = mes+"";
				        String strAno = ano+"";
				        if(dia < 10) strDia = "0"+strDia;
				        if(mes < 10) strMes = "0"+strMes;
				        if(ano < 10) strAno = "0"+strAno;
				        
						for(int i = 0 ; i < contrato_parcelas; i++) {
							Parcela p = new Parcela();
							p.setContrato(c);
							p.setIndice((i+1)+"");
							p.setPago(false);
							p.setValor(contrato_valorDaParcela);
							//Vencimento
							cal.add(Calendar.MONTH, 1);
							mes = cal.get(Calendar.MONTH);
							ano = cal.get(Calendar.YEAR);
							dia = cal.get(Calendar.DAY_OF_MONTH);
							mes++;
							strDia = dia+"";
					        strMes = mes+"";
					        strAno = ano+"";
					        if(contrato_vencimento < 10) strDia = "0"+contrato_vencimento;
					        if(mes < 10) strMes = "0"+strMes;
					        if(ano < 10) strAno = "0"+strAno;
					        p.setVencimento(LocalDate.parse(strAno+"-"+strMes+"-"+strDia));
					    	parcelaDao.save(p);
					    	List<Contrato> contratos = new ArrayList<>();
							contratos.add(c);
							a.setContrato(contratos);
							usuarioDao.save(a);
						}
					}
				} else if(aluno.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					request.setAttribute("erro", "Já existe este CPF / Matrícula.");
				}
				List<Usuario> usuarios = usuarioDao.buscarAlunos();
				request.setAttribute("usuarios", usuarios);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/funcionarios", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void funcionarios(HttpServletRequest request, HttpServletResponse response, Usuario funcionario, String acao, String perfil_codigo) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Funcionários";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/funcionarios";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//Gerando matrícula aleatória
				String matriculaPadrao = gerarMatricula();
				request.setAttribute("matriculaPadrao", matriculaPadrao);
				
				Boolean repetido = false;
				if(usuarioDao.buscarFuncionariosRepetidos(funcionario.getMatricula(), funcionario.getCpf()).size() > 0) {
					repetido = true;
				}
				if(funcionario.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
						atualizarPagina = "/funcionarios";
						Usuario a = new Usuario();
						a = funcionario;
						a.setSenha(funcionario.getCpf().replace(".", "").replace("-", ""));
						a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
						usuarioDao.save(a);
						request.setAttribute("atualizarPagina", atualizarPagina);
					} catch(Exception e) {
						request.setAttribute("erro", e);
						System.out.println("Erro: "+e);
					}
				} else if (funcionario.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.buscarMatricula(funcionario.getMatricula());
					a.setNome(funcionario.getNome());
					a.setDataNascimento(funcionario.getDataNascimento());
					a.setTelefone(funcionario.getTelefone());
					a.setCelular(funcionario.getCelular());
					a.setEndereco(funcionario.getEndereco());
					a.setEmail(funcionario.getEmail());
					a.setPathImagem(funcionario.getPathImagem());
					a.setCep(funcionario.getCep());
					a.setBairro(funcionario.getBairro());
					a.setCidade(funcionario.getCidade());
					a.setEstado(funcionario.getEstado());
					a.setPlano(funcionario.getPlano());
					a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
					usuarioDao.save(a);
				} else if(funcionario.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					request.setAttribute("erro", "Já existe este CPF / Matrícula.");
				}
				List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
				request.setAttribute("usuarios", usuarios);
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/aulas", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void aulas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Aulas";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/aulas";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//Caso esteja logado.
				List<Aula> aulas = aulaDao.buscarTudo();
			 	request.setAttribute("aulas", aulas);
			 	List<Aula> h = aulaDao.buscarhorarios();
			 	List<Horario> horarios = new ArrayList<Horario>();
			 	String ultimoHorarioInicio = "";
			 	String ultimoHorarioFim = "";
			 	for(Aula a : h) {
			 		if(!((ultimoHorarioInicio.equals(a.getInicio())) && (ultimoHorarioFim.equals(a.getFim())))) {
			 			ultimoHorarioInicio = a.getInicio();
			 			ultimoHorarioFim = a.getFim();
			 			Horario hr = new Horario();
			 			hr.setInicio(a.getInicio());
			 			hr.setFim(a.getFim());
			 			horarios.add(hr);
			 		}
			 	}
			 	request.setAttribute("horarios", horarios);
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/pendencias", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void pendencias(HttpServletRequest request, HttpServletResponse response, String acao, String cpf) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Pendências";
				iconePaginaAtual = "fa fa-money"; //Titulo do menuzinho.
				link = "pages/pendencias";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				if(usuarioSessao != null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
					if(acao == null) {
						List<Parcela> pendencias = parcelaDao.buscarPendencias();
						request.setAttribute("pendencias", pendencias);
					} else {
						if(acao.equals("pesquisar")) {
							List<Parcela> pendencias = parcelaDao.buscarMatricula(cpf);
							request.setAttribute("pendencias", pendencias);
						}
					}
				} else {
					List<Parcela> pendencias = parcelaDao.buscarMatricula(usuarioSessao.getCpf());
					request.setAttribute("pendencias", pendencias);
				}
				request.setAttribute("usuario", usuarioSessao);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/aniversariantes", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void aniversariantes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Aniversariantes";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/aniversariantes";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//Caso esteja logado.
				List<Usuario> usuarios = usuarioDao.buscarAniversariantes();
				request.setAttribute("usuarios", usuarios);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/presencaAlunos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void presencaAlunos(HttpServletRequest request, HttpServletResponse response ) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Presença dos Alunos";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/presencaAlunos";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				GregorianCalendar calendar = new GregorianCalendar();
				int ano = calendar.get(GregorianCalendar.YEAR);
				int mes = calendar.get(GregorianCalendar.MONTH);
				int limiteDias = calendar.getActualMaximum (Calendar.DAY_OF_MONTH);  
				String mesStr ="Janeiro";
				mes++;
				
				switch(mes) {
				  case 1: mesStr ="Janeiro"; break;
				  case 2: mesStr ="Fevereiro"; break;
				  case 3: mesStr ="Março"; break;
				  case 4: mesStr ="Abril"; break;
				  case 5: mesStr ="Maio"; break;
				  case 6: mesStr ="Junho"; break;
				  case 7: mesStr ="Julho"; break;
				  case 8: mesStr ="Agosto"; break;
				  case 9: mesStr ="Setembro"; break;
				  case 10: mesStr ="Outubro"; break;
				  case 11: mesStr ="Novembro"; break;
				  case 12: mesStr ="Dezembro"; break;
				  default:
				}
				
				List<String> dias = new ArrayList<String>();
				String d = "";
				for(int i = 1; i <= limiteDias; i++) {
					if(i < 10) {
						d = "0"+i;
					} else {
						d = ""+i;
					}
					dias.add(d);
				}
				
				//Caso esteja logado.
				List<Usuario> usuarios = usuarioDao.buscarAlunos();
				List<Presenca> presenca = presencaDao.buscarMesAlunos();
				request.setAttribute("usuarios", usuarios);
				request.setAttribute("presenca", presenca);
				request.setAttribute("ano", ano);
				request.setAttribute("mes", mesStr);
				request.setAttribute("dias", dias);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/presencaFuncionarios", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void presencaFuncionarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Presença dos Funcionários";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/presencaFuncionarios";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				GregorianCalendar calendar = new GregorianCalendar();
				int ano = calendar.get(GregorianCalendar.YEAR);
				int mes = calendar.get(GregorianCalendar.MONTH);
				int limiteDias = calendar.getActualMaximum (Calendar.DAY_OF_MONTH);  
				String mesStr ="Janeiro";
				mes++;
				
				switch(mes) {
				  case 1: mesStr ="Janeiro"; break;
				  case 2: mesStr ="Fevereiro"; break;
				  case 3: mesStr ="Março"; break;
				  case 4: mesStr ="Abril"; break;
				  case 5: mesStr ="Maio"; break;
				  case 6: mesStr ="Junho"; break;
				  case 7: mesStr ="Julho"; break;
				  case 8: mesStr ="Agosto"; break;
				  case 9: mesStr ="Setembro"; break;
				  case 10: mesStr ="Outubro"; break;
				  case 11: mesStr ="Novembro"; break;
				  case 12: mesStr ="Dezembro"; break;
				  default:
				}
				
				List<String> dias = new ArrayList<String>();
				String d = "";
				for(int i = 1; i <= limiteDias; i++) {
					if(i < 10) {
						d = "0"+i;
					} else {
						d = ""+i;
					}
					dias.add(d);
				}
				
				List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
				List<Presenca> presenca = presencaDao.buscarMesFuncionarios();
				request.setAttribute("usuarios", usuarios);
				request.setAttribute("presenca", presenca);
				request.setAttribute("ano", ano);
				request.setAttribute("mes", mesStr);
				request.setAttribute("dias", dias);
				
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/cadastrarTreinos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void cadastrarTreinos(HttpServletRequest request, HttpServletResponse response, Integer salvar,String matricula, String vlrTreinoA,String vlrTreinoB,String vlrTreinoC,String vlrTreinoD,String vlrTreinoE,String vlrTreinoF,String vlrTreinoG) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Cadastrar Treinos";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/cadastrarTreinos";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//... Salvando dados.
				if(salvar != null) {
					if(salvar > 0) {
						String valor="";
						for(int tr = 0 ; tr < 7; tr++) {
							if(tr == 0) valor = vlrTreinoA;
							if(tr == 1) valor = vlrTreinoB;
							if(tr == 2) valor = vlrTreinoC;
							if(tr == 3) valor = vlrTreinoD;
							if(tr == 4) valor = vlrTreinoE;
							if(tr == 5) valor = vlrTreinoF;
							if(tr == 6) valor = vlrTreinoG;
							
							valor = valor.replace("<br>","#");
						    String[] splitado = valor.split("#");
						    String descanso="";
						    String descricao="";
						    String series="";
						    String repeticoes="";
					        for (int i = 1; i < splitado.length; i++) {
					            String s = "#"+splitado[i].replace("[","#").replace("]","#");
					            String[] splitadoB = s.split("#");
					            Integer cont = 0;
					            for (int j = 0; j < splitadoB.length; j++) {
					                if(cont == 1) {
					                	descricao = splitadoB[j];
					                }
					                if(cont == 2) {
					                	repeticoes = splitadoB[j];
					                	String vl = repeticoes;
					                	vl = vl.replace("X","#");
					            	    String[] splitadoV = vl.split("#");
					                    for (int iv = 1; iv < splitadoV.length; iv++) {
					                    	series = splitadoV[0];
					                    	repeticoes = splitadoV[1];
					                    }
					                }
					                if(cont == 3) {
					                	descanso = splitadoB[j].replace(" - ", "");
					                }
					                cont++;
					                if(cont > 3) cont = 0;
					            }
					            Treino t = new Treino();
					            t.setAtivo(true);
					            t.setTipoOrdem(tr); //Treino A,B,C,D...
					            t.setOrdemDoDia(i);
					            t.setDescricao(descricao);
					            t.setSeries(Integer.parseInt(series));
					            t.setRepeticoes(Integer.parseInt(repeticoes));
					            t.setDescanso(descanso);
					            t.setAluno(usuarioDao.buscarMatricula(matricula));
					            treinoDao.save(t);   
					        }
						}
						
						
					}
				}

				// Carregando a tela...
				if(matricula != null && !matricula.equals("")) {
					Usuario u = new Usuario();
					try {
						u = usuarioDao.buscarMatricula(matricula);
						request.setAttribute("usuarioAlterar", u);
						request.setAttribute("matricula", matricula);
					} catch(Exception e) {}
					try {
						List<Treino> treinos = treinoDao.buscarMatricula(u.getMatricula());
						request.setAttribute("treinos", treinos);
					} catch(Exception e) {}
				}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		@RequestMapping(value = "/planos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void planos(HttpServletRequest request, HttpServletResponse response, String acao, Plano plano) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Planos";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/planos";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//... Salvando dados.
				if(acao != null) {
					Plano p = new Plano();
					if(acao.equals("atualizar")) {
						p = planoDao.buscarCodigo(plano.getCodigo()).get(0);
					}
					p.setAtivo(true);
					p.setCodigo(plano.getCodigo());
					p.setDescricao(plano.getDescricao());
					p.setNome(plano.getNome());
					p.setValor(plano.getValor());
					planoDao.save(p);
				}
				atualizarPagina = "/planos";
				List<Plano> planos = planoDao.buscarTudo();
				request.setAttribute("planos", planos);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		@RequestMapping(value = "/alterarSenha", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void alterarSenha(HttpServletRequest request, HttpServletResponse response, Integer acao, String matricula,String senha,String novaSenha,String confirmaSenha) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Alterar Senha";
				iconePaginaAtual = "fa fa-key"; //Titulo do menuzinho.
				link = "pages/alterarSenha";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				String msg = "";
				//... Salvando dados.
				if(acao != null) {
					if(acao > 0) {
						Usuario u = usuarioDao.fazerLogin(matricula, senha);
						if(u != null && (novaSenha.equals(confirmaSenha)) ) {
							u.setSenha(novaSenha);
							usuarioDao.save(u);
							msg = "Alterado com sucesso.";
							request.setAttribute("msgOk", msg);
						} else {
							msg = "Usuário ou senha inválidos.";
							request.setAttribute("msg", msg);
						}
					}
				}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		@RequestMapping(value = "/avaliacao", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void avaliacao(HttpServletRequest request, HttpServletResponse response, Integer acao, String matricula) throws SQLException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Avaliação";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/avaliacao";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				// Carregando a tela...
				try {
					List<Avaliacao> avaliacao = avaliacaoDao.buscarMatricula(usuarioSessao.getMatricula());
					System.out.println("Size(): " + avaliacao.size());
					request.setAttribute("avaliacao", avaliacao);
				} catch(Exception e) {System.out.println("Erro: " + e);}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		@RequestMapping(value = "/cadastrarAvaliacao", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public void cadastrarAvaliacao(HttpServletRequest request, HttpServletResponse response, Integer acao, String matricula, Avaliacao av, String inicio_avaliacao, String fim_avaliacao) throws SQLException, ParseException, ServletException, IOException {
			HttpSession session = request.getSession();
			Boolean logado = false;
			String paginaAtual = "";
			String atualizarPagina = "";
			String iconePaginaAtual = ""; //Titulo do menuzinho.
			String link = "pages/deslogar";
			String itemMenu = link;
			
			Usuario usuarioSessao = new Usuario();
			if(session.getAttribute("usuarioSessao") != null) {
				usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			}
			if(session.getAttribute("logado") != null) {
				logado = (Boolean) session.getAttribute("logado");
			}
			if(logado) {
				paginaAtual = "Avaliação";
				iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
				link = "pages/cadastrarAvaliacao";
				itemMenu = link;
				
				request.setAttribute("usuario", usuarioSessao);
				request.setAttribute("paginaAtual", paginaAtual); 
				request.setAttribute("iconePaginaAtual", iconePaginaAtual);
				
				//... Salvando dados.
				if(acao != null) {
					if(acao > 0) {
						// Acao salvar.
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
						Date inicio_av = formato.parse(inicio_avaliacao);
						Date fim_av = formato.parse(fim_avaliacao);
						Avaliacao a = new Avaliacao();
						if(acao == 2) {
							a = avaliacaoDao.buscarMatricula(matricula).get(0);
						}
						a.setCodigo(matricula);
						a.setInicio(inicio_av);
						a.setFim(fim_av);
						a.setAluno(usuarioDao.buscarMatricula(matricula));
						a.setAtivo(true);
						a.setAvaliador(av.getAvaliador());
						a.setObservacoes(av.getObservacoes());
						
						a.setIdade(av.getIdade());
						a.setPeso(av.getPeso());
						a.setAltura(av.getAltura());
						a.setTriceps(av.getTriceps());
						a.setAbdomenDobra(av.getAbdomenDobra());
						a.setBracoDir(av.getBracoDir());
						a.setBracoEsq(av.getBracoEsq());
						a.setAntiBracoDir(av.getAntiBracoDir());
						a.setAntiBracoEsq(av.getAntiBracoEsq());
						a.setAbdomenCirc(av.getAbdomenCirc());
						a.setQuadril(av.getQuadril());
						a.setCintura(av.getCintura());
						a.setCoxaDir(av.getCoxaDir());
						a.setCoxaEsq(av.getCoxaEsq());
						a.setPernaDir(av.getPernaDir());
						a.setPernaEsq(av.getPernaEsq());
						a.setRadio(av.getRadio());
						a.setFemur(av.getFemur());
						a.setGorduraCorporal(av.getGorduraCorporal());
						a.setClassificacao(av.getClassificacao());
						a.setMassaMagra(av.getMassaMagra());
						a.setMassaGorda(av.getMassaGorda());
						a.setMassaMuscular(av.getMassaMuscular());
						a.setMassaOssea(av.getMassaOssea());
						a.setMassaResidual(av.getMassaResidual());
						a.setIcq(av.getIcq());
						a.setImc(av.getImc());
						a.setClassificacaoIMC(av.getClassificacaoIMC());
						a.setClassificacaoICQ(av.getClassificacaoICQ());
						
						
						a.setLimite_peso(av.getLimite_peso());
						a.setLimite_altura(av.getLimite_altura());
						a.setLimite_triceps(av.getLimite_triceps());
						a.setLimite_abdomenDobra(av.getLimite_abdomenDobra());
						a.setLimite_bracoDir(av.getLimite_bracoDir());
						a.setLimite_bracoEsq(av.getLimite_bracoEsq());
						a.setLimite_antiBracoDir(av.getLimite_antiBracoDir());
						a.setLimite_antiBracoEsq(av.getLimite_antiBracoEsq());
						a.setLimite_abdomenCirc(av.getLimite_abdomenCirc());
						a.setLimite_quadril(av.getLimite_quadril());
						a.setLimite_cintura(av.getLimite_cintura());
						a.setLimite_coxaDir(av.getLimite_coxaDir());
						a.setLimite_coxaEsq(av.getLimite_coxaEsq());
						a.setLimite_pernaDir(av.getLimite_pernaDir());
						a.setLimite_pernaEsq(av.getLimite_pernaEsq());
						a.setLimite_radio(av.getLimite_radio());
						a.setLimite_femur(av.getLimite_femur());
						a.setLimite_gorduraCorporal(av.getLimite_gorduraCorporal());
						a.setLimite_massaMagra(av.getLimite_massaMagra());
						a.setLimite_massaGorda(av.getLimite_massaGorda());
						a.setLimite_massaMuscular(av.getLimite_massaMuscular());
						a.setLimite_massaOssea(av.getLimite_massaOssea());
						a.setLimite_massaResidual(av.getLimite_massaResidual());
						a.setLimite_icq(av.getLimite_icq());
						a.setLimite_imc(av.getLimite_imc());
						
						
						
						a.setAvaliador(usuarioSessao);
						avaliacaoDao.save(a);
						List<Avaliacao> avaliacao = avaliacaoDao.buscarMatricula(matricula);
						request.setAttribute("avaliacao", avaliacao);
					} else {
						List<Avaliacao> avaliacao = avaliacaoDao.buscarMatricula(matricula);
						request.setAttribute("avaliacao", avaliacao);
					}
				} else {
					List<Avaliacao> avaliacao = avaliacaoDao.buscarMatricula(matricula);
					request.setAttribute("avaliacao", avaliacao);
				}
				// Carregando a tela...
				if(matricula != null && !matricula.equals("")) {
					Usuario u = new Usuario();
					try {
						u = usuarioDao.buscarMatricula(matricula);
						request.setAttribute("usuarioAlterar", u);
						request.setAttribute("matricula", matricula);
					} catch(Exception e) {}
				}
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		
		@RequestMapping(value = {"/entrada"}, produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET}) // Pagina de Vendas
		public void entrada(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ServletException, IOException {
			HttpSession session = request.getSession();
			String link = "pages/entrada";
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
		}
		
		
		
		@RequestMapping(value = "/entrada_{digital}", produces = "text/plain;charset=UTF-8", method = {RequestMethod.POST}) // Pagina de Vendas
		public String entrada_post(HttpServletRequest request, HttpServletResponse response, @PathVariable("digital") String digital) throws SQLException, ParseException, ServletException, IOException {
			digital = digital.replace("{", "");
			digital = digital.replace("}", "");
			
			HttpSession session = request.getSession();
			String link = "pages/entrada";
			String msg =  "Usuário não cadastrado.";
			//LEITURA DA DIGITAL
				if (digital != null && !digital.equals("")) {
					Usuario u = new Usuario();
					try {
						u = usuarioDao.buscarDigital(digital);
						Boolean liberado = false;
						if(u.getId() != null && !u.getPerfil().getAdmin() && !u.getPerfil().getFuncionario() && !u.getPerfil().getProfessor()) {
							if(presencaDao.presencaAluno(u).size() == 0 || u.getPlano().getRepetir()) {
								presencaDao.presencaAluno(u);
								Presenca p = new Presenca();
								p.setDataPresenca(LocalDate.now().atStartOfDay());
								p.setPresenca(LocalDateTime.now());
								p.setUsuario(u);
								presencaDao.save(p);
								liberado = true;
								msg =  "Bem-Vindo "+u.getNome()+"!";
							} else {
								System.out.println("Ja veio");
								msg = "Você já veio hoje.";
							}
						} else {
							liberado = true;
							msg =  "Bem-Vindo "+u.getNome()+"!";
						}
						
						if(liberado) {
							//LIBERAR A CATRACA
						}
						
					} catch(Exception e) {
						System.out.println("Erro: "+e);
					}
					if(u == null || u.getId() == null) {
						msg =  "Usuário não cadastrado.";
					}
				}
				return msg;
		}
		
}
	
	
	




