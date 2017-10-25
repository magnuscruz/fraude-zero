package br.gov.dataprev.fraudezero.service.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.gov.dataprev.fraudezero.service.model.Criterio;

public class Util {
	private static final List<Criterio> CRITERIOS = new ArrayList<>();
	private static final List<String> ESTADOS_CIVIS = new ArrayList<>();
	
	static {
		CRITERIOS.add(new Criterio("Critério de Confiabilidade da GFIP"));
		CRITERIOS.add(new Criterio("Critério de Licitude das Remunerações"));
		CRITERIOS.add(new Criterio("Critério de Confiabilidade da GFIP"));
		CRITERIOS.add(new Criterio("Critério de Pagamento de Resíduo"));
		CRITERIOS.add(new Criterio("Critério de Prescrição em 5 Anos"));
		CRITERIOS.add(new Criterio("Critério de Caso Já Avaliado Anteriormente ou Em Avaliação"));
		CRITERIOS.add(new Criterio("Critério de múltipla atividade"));
		CRITERIOS.add(new Criterio("Critério do Salário Maternidade"));
		
		ESTADOS_CIVIS.add("Desconhecido");
		ESTADOS_CIVIS.add("Solteiro");
		ESTADOS_CIVIS.add("Casado");
		ESTADOS_CIVIS.add("Viúvo");
		ESTADOS_CIVIS.add("Divorciado");
		ESTADOS_CIVIS.add("Separado Judicialmente");
		ESTADOS_CIVIS.add("União Estável");
	}
	
	public static int random() {
		return random(1, 100);
	}
	
	public static int random(int min, int max) {
		int number = new Random().nextInt(max);
		return number < min ? min : number;
	}
	
	public static List<Criterio> mockCriterios() {
		Collections.shuffle(CRITERIOS);
		
		List<Criterio> selecionados = CRITERIOS.subList(0, random(2, CRITERIOS.size()));
		for (Criterio criterio : selecionados) {
			criterio.setPercentualProbabilidade(random());
		}
		
		Collections.sort(selecionados);
		
		return selecionados;
	}
}
