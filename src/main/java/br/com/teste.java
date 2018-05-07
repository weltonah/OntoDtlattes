package br.com;

import br.com.converter.TratamentoDeDados;
import info.debatty.java.stringsimilarity.JaroWinkler;
import info.debatty.java.stringsimilarity.NGram;

public class teste {

	public static void main(String[] args) {
		TratamentoDeDados td = new TratamentoDeDados();
		String teste = "alberto-ferreira";
		String teste1 = "alBerto ferreíra";
		String teste2 = "alberto  Ferreira";
		String teste3 = "alberto.  ferreira";

		System.out.println(td.corrigirString(teste));
		System.out.println(td.corrigirString(teste1));
		System.out.println(td.corrigirString(teste2));
		System.out.println(td.corrigirString(teste3));
		JaroWinkler jw = new JaroWinkler();
		System.out.println(jw.similarity("vellela", "vieira"));
		
		NGram twogram = new NGram(2);
		System.out.println(twogram.distance("ABCD", "ABTUIO"));

		// produces 0.97222
		String s1 = "abe";
		String s2 = "abe";
		NGram ngram = new NGram(4);
		System.out.println(ngram.distance(s1, s2));
	}

}
