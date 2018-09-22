package br.com;

import info.debatty.java.stringsimilarity.NGram;

public class mainHash {
	public static void main(String[] args) throws Exception {
		NGram ngram = new NGram(4);
		System.out.println(ngram.distance("meet", "meett"));
		System.out.println(ngram.distance("po", "aac"));
		System.out.println(ngram.distance("cabeç", "cabeçpp"));
		System.out.println(ngram.distance("ab", "abab"));
		System.out.println(ngram.distance("asdasdasd", "uuuyurtyrtytfgh"));
		System.out.println(ngram.distance("asdasdasd", "asdasdasdasdasdasdasdasdasd"));

	}

}
