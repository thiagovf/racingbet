package br.com.racingbet.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.racingbet.dao.CategoriaDAO;
import br.com.racingbet.dao.EquipeDAO;
import br.com.racingbet.dao.GrandePremioDAO;
import br.com.racingbet.dao.PalpiteDAO;
import br.com.racingbet.dao.PilotoDAO;
import br.com.racingbet.dao.PontuacaoDAO;
import br.com.racingbet.dao.ResultadoGrandePremioDAO;
import br.com.racingbet.dao.UsuarioDAO;
import br.com.racingbet.entidade.Categoria;
import br.com.racingbet.entidade.Equipe;
import br.com.racingbet.entidade.GrandePremio;
import br.com.racingbet.entidade.Palpite;
import br.com.racingbet.entidade.Piloto;
import br.com.racingbet.entidade.Pontuacao;
import br.com.racingbet.entidade.ResultadoGrandePremio;
import br.com.racingbet.entidade.Usuario;


@Stateless
@LocalBean
public class PontuacaoServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PontuacaoDAO pontuacaoDAO;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Inject 
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private PalpiteDAO palpiteDAO;
	
	@Inject
	private GrandePremioDAO grandePremioDAO;
	
	@Inject
	private PilotoDAO pilotoDAO;
	
	@Inject
	private ResultadoGrandePremioDAO resultadoGrandePremioDAO;
	
	@Inject
	private EquipeDAO equipeDAO;	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluir(Pontuacao pontuacao) {
		
		pontuacaoDAO.incluir(pontuacao);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterar(Pontuacao pontuacao) {
		pontuacaoDAO.alterar(pontuacao);		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(Pontuacao pontuacao) {
		pontuacaoDAO.remover(pontuacao.getId());
	}
	
	public List<Pontuacao> recuperarTodos() {
		return pontuacaoDAO.recuperarTodos();
	}
	
	public Pontuacao recuperarPorId(Long id) {
		return pontuacaoDAO.recuperar(id);
	}
	
	/*
	public void gerarDadosTeste()
	{
		
		//Gerar Usuarios		
		Date dtHoje = new Date();		
		Usuario usuario1 = new Usuario();
		usuario1.setNome("usuario1");
		usuario1.setEmail("usuario1@email.com");
		usuario1.setSenha("123");
		usuario1.setData_cadastro(dtHoje);
		usuarioDAO.incluir(usuario1);	
	
		Usuario usuario2 = new Usuario();
		usuario2.setNome("usuario2");
		usuario2.setEmail("usuario2@email.com");
		usuario2.setSenha("123");
		usuario2.setData_cadastro(dtHoje);
		usuarioDAO.incluir(usuario2);
		
		Usuario usuario3 = new Usuario();
		usuario3.setNome("usuario3");
		usuario3.setEmail("usuario3@email.com");
		usuario3.setSenha("123");
		usuario3.setData_cadastro(dtHoje);
		usuarioDAO.incluir(usuario3);
		
		Usuario usuario4 = new Usuario();
		usuario4.setNome("usuario4");
		usuario4.setEmail("usuario4@email.com");
		usuario4.setSenha("123");
		usuario4.setData_cadastro(dtHoje);
		usuarioDAO.incluir(usuario4);
		
		Usuario usuario5 = new Usuario();
		usuario5.setNome("usuario5");
		usuario5.setEmail("usuario5@email.com");
		usuario5.setSenha("123");
		usuario5.setData_cadastro(dtHoje);
		usuarioDAO.incluir(usuario5);

		//Gerar Categoria		
		Categoria categoria1 = new Categoria();
		categoria1.setNome("Formula 1");
		categoria1.setDescricao("Formula 1");
		categoria1.setTipoveiculo("Carro");
		categoriaDAO.incluir(categoria1);
		
		//Gerar Grande Premio
		GrandePremio grandePremio1 = new GrandePremio();
		//grandePremio1.setIdCategoria((long) 1);
		grandePremio1.setNome("Grande Premio do Brasil");
		grandePremio1.setAutodromo("Interlagos");
		grandePremio1.setCidade("Sao Paulo");
		grandePremio1.setData(dtHoje);
		grandePremio1.setPais("Brasil");
		grandePremioDAO.incluir(grandePremio1);
		
		//Gerar Equipes
		Equipe equipe1 = new Equipe();
		equipe1.setNome("Equipe1");
		equipe1.setNacionalidade("Pais1");
		equipe1.setData_fundacao(dtHoje);
	/*	equipeDAO.incluir(equipe1);
		
		Equipe equipe2 = new Equipe();
		equipe2.setNome("Equipe2");
		equipe2.setNacionalidade("Pais2");
		equipe2.setData_fundacao(dtHoje);
		equipeDAO.incluir(equipe2);
		
		//Gerar Pilotos
		Piloto piloto1 = new Piloto();
		piloto1.setNome("Piloto1");
		piloto1.setNacionalidade("Pais1");
		//piloto1.setIdEquipe((long) 1);
		piloto1.setNumeroDoCarro("1");
		pilotoDAO.incluir(piloto1);
		
		Piloto piloto2 = new Piloto();
		piloto2.setNome("Piloto2");
		piloto2.setNacionalidade("Pais2");
		//piloto2.setIdEquipe((long) 1);
		piloto2.setNumeroDoCarro("2");
		pilotoDAO.incluir(piloto2);
		
		Piloto piloto3 = new Piloto();
		piloto3.setNome("Piloto1");
		piloto3.setNacionalidade("Pais1");
		//piloto3.setIdEquipe((long) 2);
		piloto3.setNumeroDoCarro("1");
		pilotoDAO.incluir(piloto3);

		Piloto piloto4 = new Piloto();
		piloto4.setNome("Piloto4");
		piloto4.setNacionalidade("Pais4");
		piloto4.setIdEquipe((long) 2);
		piloto4.setNumeroDoCarro("2");
		pilotoDAO.incluir(piloto4);
		
		//Gerar Palpites
		Palpite palpite1 = new Palpite();
		palpite1.setGrandePremio(grandePremio)
		palpite1.setRepostaPerguntaPole(repostaPerguntaPole)
		
		Equipe equipe1 = new Equipe();
		equipe1.setCategoria(categoria)
		
		
		
		palpite1.setGrandePremio("Grande Premio do Brasil");
		palpite1.setIdGrandePremio((long) 1);
		palpite1.setAutorPergunta("usuario1");
		palpite1.setIdUsuario((long) 1);
		palpite1.setData(dtHoje);
		palpite1.setRespostaPole("Piloto1");
		palpite1.setRespostaPrimeiro("Piloto2");
		palpite1.setIdPilotoResp1((long) 1);
		palpite1.setIdPilotoResp2((long) 2);
		palpiteDAO.incluir(palpite1);	
		
		Palpite palpite2 = new Palpite();
		palpite2.setGrandePremio("Grande Premio do Brasil");
		palpite2.setIdGrandePremio((long) 1);
		palpite2.setAutorPergunta("usuario2");
		palpite2.setIdUsuario((long) 2);
		palpite2.setData(dtHoje);
		palpite2.setRespostaPole("Piloto1");
		palpite2.setRespostaPrimeiro("Piloto1");
		palpite2.setIdPilotoResp1((long) 1);
		palpite2.setIdPilotoResp2((long) 1);
		palpiteDAO.incluir(palpite2);
		
		/*
		
		//Gerar Resultado do Grande Premio
		ResultadoGrandePremio resultadoGrandePremio1 = new ResultadoGrandePremio(); 
		//resultadoGrandePremio1.setIdGrandePremio((long) 1);
		resultadoGrandePremio1.setIdPilotoPergunta1((long) 1);
		resultadoGrandePremio1.setIdPilotoPergunta2((long) 2);
		//resultadoGrandePremio1.setIdCategoria((long) 1);
//		resultadoGrandePremio1.setNomeGrandePremio("GP do BRASIL");
//		resultadoGrandePremio1.setRepostaPerguntaPole("Piloto 1");
//		resultadoGrandePremio1.setRepostaPerguntaPrimeiro("Piloto 2");
 * 
 
		resultadoGrandePremioDAO.incluir(resultadoGrandePremio1);
	}
	*/
	public void calcularPontuacao(ResultadoGrandePremio resultadoGrandePremio, Palpite palpite)
	{
		long idPilotoGabaritoPerguntaPole, idPilotoGabaritoPerguntaPrimeiro;
		long idPilotoPalpitePerguntaPole, idPilotoPalpitePerguntaPrimeiro, idUsuario;
		long pontos = 0;
		
		
		idPilotoGabaritoPerguntaPole = resultadoGrandePremio.getRepostaPerguntaPole().getId();
		idPilotoGabaritoPerguntaPrimeiro = resultadoGrandePremio.getRepostaPerguntaPrimeiro().getId();
		
		idPilotoPalpitePerguntaPole = palpite.getRepostaPerguntaPole().getId();
		idPilotoPalpitePerguntaPrimeiro = palpite.getRepostaPerguntaPrimeiro().getId();
	
		idUsuario = palpite.getUsuario().getId();
		
		//Pergunta: Quem Vence ? (10 Pontos)
		if (idPilotoGabaritoPerguntaPrimeiro == idPilotoPalpitePerguntaPrimeiro)
		{
			pontos = pontos + 10;
		}
		
		//Pergunta: Quem Larga na Pole? (5 Pontos)
		if (idPilotoGabaritoPerguntaPole == idPilotoPalpitePerguntaPole)
		{
			pontos = pontos + 5;
		}
		
		Pontuacao pontuacao = new Pontuacao();		
		pontuacao.setIdCategoria(resultadoGrandePremio.getGrandePremio().getCategoria().getId());
		pontuacao.setIdUsuario(idUsuario);
		pontuacao.setPontos(pontos);		
		pontuacaoDAO.incluir(pontuacao);
	}
	
	public void gerarPontuacao(long idGrandePremio)
	{
		ResultadoGrandePremio res = new ResultadoGrandePremio();
		res = resultadoGrandePremioDAO.recuperar(idGrandePremio);
		
		List<Palpite> palpites = new ArrayList<Palpite>();
		palpites = palpiteDAO.recuperarTodos();
		
		for (Palpite palpite : palpites) {
			if (palpite.getGrandePremio().getId() == idGrandePremio)
			{
				calcularPontuacao(res, palpite);
			}						
		}		
	}
}
