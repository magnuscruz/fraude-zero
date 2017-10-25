package br.gov.dataprev.sibeweb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Util {
	private static final List<String> ESTADOS_CIVIS = new ArrayList<>();
	
	static {
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
	
	public static String formatCpf(String cpf) {
		cpf = String.format("%011d", Long.parseLong(cpf));
		cpf = new StringBuffer(cpf).insert(3, '.').insert(7, '.').insert(11, '-').toString();
		return cpf;
	}
	
	public static Date parseData(String data) {
		try {
			return data.isEmpty() || data.equals("0") ? null : new SimpleDateFormat("yyyyMMdd").parse(data);
		}
		catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String mockEstadoCivil() {
		return ESTADOS_CIVIS.get(random(0, ESTADOS_CIVIS.size() - 1));
	}
}
