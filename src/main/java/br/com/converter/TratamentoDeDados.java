package br.com.converter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;

import org.semanticweb.owlapi.model.OWLOntology;

import br.com.modelo.OntoClass;
import br.com.modelo.OntoParceiro;
import br.com.modelo.OntoPessoa;
import br.com.modelo.TrabalhoEvento;
import br.com.modelo.TriplaOwl;
import info.debatty.java.stringsimilarity.NGram;

public class TratamentoDeDados {

	public String corrigirString(String text) {
		// Retira acentuacao
		text = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		text = text.replaceAll("-", " ");
		text = text.replaceAll("\\s+", "_");
		text = text.replaceAll("[^\\w^\\;]", "");
		text = text.toLowerCase();
		return text;
	}

	public void ExpansaoMembros(ArrayList<OntoPessoa> listapessoa) {
		ArrayList<OntoPessoa> listaAux = new ArrayList<>();
		for (OntoPessoa ontoPessoa : listapessoa) {
			for (OntoClass ontoClass : ontoPessoa.getListOntoBanca()) {
				for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
					OntoPessoa ont = new OntoPessoa(ontoParceiro.getNome(), ontoParceiro.getId(), "",
							ontoParceiro.getCitacao());
					OntoClass bancaAux = new OntoClass(ontoClass.getTitulo(), ontoClass.getTipo(), ontoClass.getAno());
					ont.AddListOntoBanca(bancaAux);
					listaAux.add(ont);
				}
				ontoClass.setListAutores(new ArrayList<>());
			}
			for (OntoClass ontoClass : ontoPessoa.getListOntoFormacao()) {
				for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
					OntoPessoa ont = new OntoPessoa(ontoParceiro.getNome(), ontoParceiro.getId(), "",
							ontoParceiro.getCitacao());
					OntoClass formacaoAux = new OntoClass(ontoClass.getTitulo(), ontoClass.getTipo(),
							ontoClass.getAno());
					ontoClass.setFlagFormacaoOrientacao(true);
					ont.AddListOntoFormacao(formacaoAux);
					listaAux.add(ont);
				}
				ontoClass.setListAutores(new ArrayList<>());
			}
			for (OntoClass ontoClass : ontoPessoa.getListOntoProjetoPesquisa()) {
				for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
					OntoPessoa ont = new OntoPessoa(ontoParceiro.getNome(), ontoParceiro.getId(), "",
							ontoParceiro.getCitacao());
					OntoClass pesquisa = new OntoClass(ontoClass.getTitulo(), ontoClass.getTipo(), ontoClass.getAno());
					ont.AddListOntoProjetoPesquisa(pesquisa);
					listaAux.add(ont);
				}
				ontoClass.setListAutores(new ArrayList<>());
			}
		}
		listapessoa.addAll(listaAux);
	}

	public static void EventoeTrabalho(OntoPessoa pessoa) {
		int antes = pessoa.getListOntoEvento().size();
		ArrayList<OntoClass> aux = new ArrayList<>();
		pessoa.getListOntoEvento().forEach(ev -> {
			pessoa.getListOntoTrabalhoEvento().forEach(tr -> {
				if (ev.getTitulo().contentEquals(tr.getEvento().getTitulo()))
					aux.add(ev);
			});
		});
		aux.forEach(ev -> pessoa.getListOntoEvento().remove(ev));

		int depois = pessoa.getListOntoEvento().size();
		// if (antes != depois)
		// System.out.println("antes: " + antes + " depois: " + depois);
	}

	public static ArrayList<TriplaOwl> listaEventoDesnecessario(OWLOntology ontology) {
		ArrayList<TriplaOwl> listDelete = new ArrayList<>();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Evento"))
				.forEach(w -> {
					if (ontology.objectPropertyAssertionAxioms(w).count() == 1) {
						TriplaOwl triplaOwl = new TriplaOwl(w.getIRI());
						listDelete.add(triplaOwl);
					}
				});
		return listDelete;
	}

	public static ArrayList<TriplaOwl> listaPessoaDesnecessario(OWLOntology ontology) {
		ArrayList<TriplaOwl> listDelete = new ArrayList<>();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.forEach(w -> {
					if (ontology.objectPropertyAssertionAxioms(w).count() == 1) {
						TriplaOwl triplaOwl = new TriplaOwl(w.getIRI());
						// System.out.println(w.getIRI());
						listDelete.add(triplaOwl);
					} else {
						// System.out.println("-------------------------------------");
						// System.out.println(w.getIRI());
					}
				});
		return listDelete;
	}

	public void JuncaoMembros(ArrayList<OntoPessoa> listaPessoa) {
		int antes = listaPessoa.size();
		BaterNomeComIdLattes(listaPessoa);
		System.out.println("1=== " + (antes - listaPessoa.size()));
		antes = listaPessoa.size();
		BaterNomeComNome(listaPessoa);
		System.out.println("2=== " + (antes - listaPessoa.size()));
		antes = listaPessoa.size();
		BaterListCitacaoComNome(listaPessoa);
		System.out.println("3=== " + (antes - listaPessoa.size()));
		antes = listaPessoa.size();
		BaterNomeContidoEmOutro(listaPessoa);
		System.out.println("4=== " + (antes - listaPessoa.size()));
		antes = listaPessoa.size();
		BaterCitacaoPorCitacao(listaPessoa);
		System.out.println("5=== " + (antes - listaPessoa.size()));
		antes = listaPessoa.size();
		BaterNomeComNomeAlgoritmoNGram(listaPessoa);
		System.out.println("6=== " + (antes - listaPessoa.size()));
		antes = listaPessoa.size();
		// listaPessoa.stream()
		// .filter(p -> p.getNomeCompleto().contentEquals("ciro_de_barros_barbosa"))
		// .forEach(u -> System.out.println(u.getNomeCompleto() + " " + u.getIdLattes()
		// + " " + u.getCont()));

	}

	public void tratarEventos(ArrayList<OntoPessoa> listaPessoa) {
		NGram ngram = new NGram(4);
		int cont = 0;
		int totalcont = 0;
		for (int i = 0; i < listaPessoa.size(); i++) {
			for (int j = i + 1; j < listaPessoa.size(); j++) {
				if (!(i == j)) {
					OntoPessoa ontoPessoa = listaPessoa.get(i);
					OntoPessoa ontoPessoa2 = listaPessoa.get(j);
					for (int k = 0; k < ontoPessoa.getListOntoEvento().size(); k++) {

						OntoClass evento = ontoPessoa.getListOntoEvento().get(k);
						for (int t = 0; t < ontoPessoa2.getListOntoEvento().size(); t++) {
							OntoClass evento2 = ontoPessoa2.getListOntoEvento().get(t);
							double aux = ngram.distance(evento.getTitulo(), evento2.getTitulo());
							if (aux > 0 && aux < 0.25) {
								// System.out.println("$$$$$###");
								// System.out.println(evento.getTitulo());
								// System.out.println(evento2.getTitulo());
								cont++;
								evento.setTitulo(evento2.getTitulo());
								// System.out.println(ontoPessoa.getListOntoEvento().get(k).getTitulo());
							}
						}
						for (int t = 0; t < ontoPessoa2.getListOntoTrabalhoEvento().size(); t++) {
							TrabalhoEvento evento2 = ontoPessoa2.getListOntoTrabalhoEvento().get(t);
							double aux = ngram.distance(evento.getTitulo(), evento2.getEvento().getTitulo());
							if (aux > 0 && aux < 0.20) {
								// System.out.println("$$$$$###");
								// System.out.println(evento.getTitulo());
								// System.out.println(evento2.getEvento().getTitulo());
								cont++;
								evento.setTitulo(evento2.getEvento().getTitulo());
								// System.out.println(ontoPessoa.getListOntoEvento().get(k).getTitulo());
							}

						}

					}
				}
			}
		}
		for (int i = 0; i < listaPessoa.size(); i++) {
			totalcont = totalcont + listaPessoa.get(i).getListOntoEvento().size();
		}
		System.out.println("numero de evento que foram combinadas " + cont);
		System.out.println("numero total de evento " + totalcont);
	}

	public void BaterNomeComIdLattes(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getIdLattes()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			String idlattes = listaPessoa.get(i).getIdLattes();
			if (!idlattes.contentEquals("")) {
				for (int j = 0; j < listaPessoa.size(); j++) {
					if (i != j) {
						if (idlattes.contentEquals(listaPessoa.get(j).getIdLattes())) {
							listaPessoa.get(i).Copiar(listaPessoa.get(j));
							listaPessoa.get(i).cont();
							listaPessoa.remove(j);
							j--;
						}
					}

				}
			}
		}
	}

	public void BaterNomeComNome(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			String nome = listaPessoa.get(i).getNomeCompleto();
			for (int j = 0; j < listaPessoa.size(); j++) {
				if (i != j) {
					if (nome.contentEquals(listaPessoa.get(j).getNomeCompleto())) {
						listaPessoa.get(i).Copiar(listaPessoa.get(j));
						listaPessoa.get(i).cont();
						listaPessoa.remove(j);
						j--;
					}
				}
			}
		}
	}

	public void BaterNomeComNomeAlgoritmoNGram(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		NGram ngram = new NGram(4);
		for (int i = 0; i < listaPessoa.size(); i++) {
			String nome = listaPessoa.get(i).getNomeCompleto();
			for (int j = 0; j < listaPessoa.size(); j++) {
				if (!(i == j)) {

					if (ngram.distance(nome, listaPessoa.get(j).getNomeCompleto()) < 0.25) {
						// System.out.println("%%%%%%%%%%%%%%%%%%%%%");
						// System.out.println(nome);
						// System.out.println(listaPessoa.get(j).getNomeCompleto());
						listaPessoa.get(i).Copiar(listaPessoa.get(j));
						listaPessoa.get(i).cont();
						listaPessoa.remove(j);
						j--;
					}
				}
			}
		}
	}

	public void BaterCitacaoPorCitacao(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			ArrayList<String> listcitacaoPivo = listaPessoa.get(i).getCitacaoList();
			for (int j = 0; j < listaPessoa.size(); j++) {
				if (!(i == j)) {
					for (int k = 0; k < listaPessoa.get(j).getCitacaoList().size(); k++) {
						String unidadelist = listaPessoa.get(j).getCitacaoList().get(k);
						if (listcitacaoPivo.contains(unidadelist) && (!unidadelist.contentEquals(""))) {
							if (listaPessoa.get(j).getNomeCompleto().length() > listaPessoa.get(i).getNomeCompleto()
									.length()) {
								listaPessoa.get(i).setNomeCompleto(listaPessoa.get(j).getNomeCompleto());
							}
							listaPessoa.get(i).Copiar(listaPessoa.get(j));
							listaPessoa.get(i).cont();
							listaPessoa.remove(j);
							break;
						}
					}
				}
			}
		}
	}

	public void BaterListCitacaoComNome(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			ArrayList<String> listcitacaoPivo = listaPessoa.get(i).getCitacaoList();
			if (!(listcitacaoPivo.get(0).contentEquals("") && listcitacaoPivo.size() == 1)) {
				for (int j = 0; j < listaPessoa.size(); j++) {
					if (!(i == j)) {
						for (int p = 0; p < listcitacaoPivo.size(); p++) {
							if (listcitacaoPivo.get(p).contentEquals(listaPessoa.get(j).getNomeCompleto())) {
								listaPessoa.get(i).Copiar(listaPessoa.get(j));
								listaPessoa.get(i).cont();
								listaPessoa.remove(j);
								j--;
								break;
							}
						}
					}
				}
			}

		}
	}

	public void BaterNomeContidoEmOutro(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			String nome = listaPessoa.get(i).getNomeCompleto();
			for (int j = 0; j < listaPessoa.size(); j++) {
				if (!(i == j)) {
					if (listaPessoa.get(j).getNomeCompleto().contains(nome)) {
						listaPessoa.get(j).Copiar(listaPessoa.get(i));
						listaPessoa.get(j).cont();
						nome = listaPessoa.get(j).getNomeCompleto();
						listaPessoa.remove(i);
						j--;
					}
				}
			}
		}
	}

}
