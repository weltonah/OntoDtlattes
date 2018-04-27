package br.com;

import java.util.ArrayList;

import br.com.modelo.OntoClass;
import br.com.modelo.OntoPessoa;

public class teste {

	public static void main(String[] args) {
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoClass teste = new OntoClass("nome Projeto", "", 0);
		OntoClass teste2 = new OntoClass("nome ttt", "", 0);
		OntoClass testep = new OntoClass("teste2", "", 0);
		OntoPessoa pessoa = new OntoPessoa("nome1", "", "", "");
		OntoPessoa pessoa3 = new OntoPessoa("nome2", "", "", "");
		OntoPessoa pessoa4 = new OntoPessoa("nome2", "", "", "");
		pessoa.AddListOntoBanca(teste);
		pessoa3.AddListOntoBanca(testep);
		OntoPessoa pessoa2 = new OntoPessoa("nome1", "", "", "");
		pessoa2.AddListOntoOrientacao(teste2);
		listaPessoa.add(pessoa);
		listaPessoa.add(pessoa2);
		listaPessoa.add(pessoa3);
		listaPessoa.add(pessoa4);

		int j = 0;
		for (int i = 0; i < listaPessoa.size(); i++) {
			String nome = listaPessoa.get(i).getNomeCompleto();
			// for (j = i + 1; j < listaPessoa.size(); j++) {
			while (i + 1 < listaPessoa.size()) {
				j = i + 1;
				if (nome.contentEquals(listaPessoa.get(j).getNomeCompleto())) {
					listaPessoa.get(i).Copiar(listaPessoa.get(j));
					listaPessoa.get(i).cont();
					listaPessoa.remove(j);
				} else {
					break;
				}
			}
		}
//		System.out.println(listaPessoa.get(0).getNomeCompleto());
//		System.out.println(listaPessoa.size());
//		listaPessoa.get(0).getListOntoBanca().forEach(u -> System.out.println(u.getTitulo()));
//		listaPessoa.get(0).getListOntoOrientacao().forEach(u -> System.out.println(u.getTitulo()));
//		System.out.println(listaPessoa.get(1).getNomeCompleto());
//		listaPessoa.get(1).getListOntoBanca().forEach(u -> System.out.println(u.getTitulo()));
		
		
		String aux = "ola";
		String aux2 = "aola mundo";
		if (aux2.contains(aux))
			System.out.println(aux2);
	}

}
