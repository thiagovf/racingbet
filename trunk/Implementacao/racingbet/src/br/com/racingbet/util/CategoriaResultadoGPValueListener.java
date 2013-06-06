package br.com.racingbet.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import br.com.racingbet.controle.ManterResultadoGrandePremioMB;
import br.com.racingbet.entidade.Categoria;

public class CategoriaResultadoGPValueListener  implements ValueChangeListener {
	
	/*@Inject
	private GrandePremioServico gpServico;*/
	
	@Override
	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		Categoria categoria = (Categoria) FacesContext.getCurrentInstance().
				getExternalContext().getSessionMap().get("categoriaId");
		//gpServico.recuperarPorCategoria(categoria);
		System.out.println(categoria);
		Map<String, String> gp = new LinkedHashMap<String, String>();
		gp.put("Selecione", "");
		gp.put("ZZZZ", "1");
		gp.put("XXXX", "2");
		gp.put("YYYY", "3");
		gp.put("AAAA", "4");
		ManterResultadoGrandePremioMB.grandePremios = gp;
		
		Map<String, String> pilotos = new LinkedHashMap<String, String>();
		pilotos.put("Selecione", "");
		pilotos.put("Sebastian Vettel", "1");
		pilotos.put("Kimi Räikkönen", "2");
		pilotos.put("Lewis Hamilton", "3");
		pilotos.put("Fernando Alonso", "4");
		ManterResultadoGrandePremioMB.pilotos = pilotos;
		
	}

}
