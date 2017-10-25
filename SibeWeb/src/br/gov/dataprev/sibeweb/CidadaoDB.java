package br.gov.dataprev.sibeweb;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CidadaoDB {
	private static final Map<String, Cidadao> CIDADAOS_POR_NIT = new HashMap<>();
	
	static {
		try {
			Path path = Paths.get("C:\\Users\\Reinaldo\\Desktop\\Hackathon 2017", "dados_rei.csv");
			List<String> lines = Files.readAllLines(path);
			
			for (String line : lines.subList(1, lines.size())) {
				String[] splitted = line.split("\\|");
				
				Cidadao cidadao = new Cidadao();
				cidadao.setNit(splitted[1]);
				cidadao.setCpf(Util.formatCpf(splitted[2]));
				cidadao.setNome(splitted[3]);
				cidadao.setNomeMae(splitted[4]);
				cidadao.setDataNascimento(Util.parseData(splitted[5]));
				cidadao.setRg(splitted[6]);
				cidadao.setSexo(splitted[13].equals("M") ? "Masculino" : splitted[13].equals("F") ? "Feminino" : "Desconhecido");
				cidadao.setEstadoCivil(Util.mockEstadoCivil());
				cidadao.setQuantidadeVinculos(Integer.parseInt(splitted[27]));
				cidadao.setNumeroBeneficio(splitted[30]);
				
				CIDADAOS_POR_NIT.put(cidadao.getNit(), cidadao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Cidadao findByNit(String nit) {
		return CIDADAOS_POR_NIT.get(nit);
	}
}
