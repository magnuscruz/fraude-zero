package br.gov.dataprev.sibeweb;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;

public class Teste {
	public static void main(String[] args) {
		String probabilidadeFraudeJson = Client
				.create()
				.resource("http://localhost:4000/?nit=1253029893")
				.accept(MediaType.TEXT_PLAIN)
				.get(String.class);
		
		System.out.println(probabilidadeFraudeJson);
		
		ProbabilidadeFraudeModelo probabilidadeFraude = new Gson().fromJson(probabilidadeFraudeJson, ProbabilidadeFraudeModelo.class);
		System.out.println(probabilidadeFraude.getPercentualProbabilidade());
	}
}
