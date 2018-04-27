package br.com.converter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;

import org.semanticweb.owlapi.model.OWLOntology;

import br.com.modelo.OntoClass;
import br.com.modelo.OntoParceiro;
import br.com.modelo.OntoPessoa;
import br.com.modelo.TriplaOwl;

public class TratamentoDeDados {

	public String corrigirString(String text) {
		String result = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		result = result.replaceAll(">", "").replaceAll("<", "").replaceAll("#", "").replaceAll("\t", "")
				.replaceAll("-", "").replaceAll("&", "").replaceAll(":", "").replaceAll("\n", " ").replaceAll(" ", "_")
				.replaceAll("'", "").toLowerCase();
		return result;
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
			}
			for (OntoClass ontoClass : ontoPessoa.getListOntoFormacao()) {
				for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
					OntoPessoa ont = new OntoPessoa(ontoParceiro.getNome(), ontoParceiro.getId(), "",
							ontoParceiro.getCitacao());
					OntoClass formacaoAux = new OntoClass(ontoClass.getTitulo(), ontoClass.getTipo(),
							ontoClass.getAno());
					ont.AddListOntoOrientacao(formacaoAux);
					listaAux.add(ont);
				}
			}
			for (OntoClass ontoClass : ontoPessoa.getListOntoProjetoPesquisa()) {
				for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
					OntoPessoa ont = new OntoPessoa(ontoParceiro.getNome(), ontoParceiro.getId(), "",
							ontoParceiro.getCitacao());
					OntoClass pesquisa = new OntoClass(ontoClass.getTitulo(), ontoClass.getTipo(), ontoClass.getAno());
					ont.AddListOntoOrientacao(pesquisa);
					listaAux.add(ont);
				}
			}
		}
		listapessoa.addAll(listaAux);
	}

	public void TratarNomes(ArrayList<OntoPessoa> listapessoa) {
		for (OntoPessoa ontoPessoa : listapessoa) {
			String nome = ontoPessoa.getNomeCompleto();
			ArrayList<String> listcitacao = ontoPessoa.getCitacaoList();
			String idLattes = ontoPessoa.getIdLattes();

			listapessoa.stream().filter(u -> !u.equals(ontoPessoa)).forEach(pe -> {
				// System.out.println("nome analisado " + nome);
				// System.out.println("lista autor analisado " + pe.getNomeCompleto());
				for (OntoClass ontoClass : pe.getListOntoBanca()) {
					for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
						if (nome.contentEquals(ontoParceiro.getNome())) {
							System.out.println("------------BANCA----------------");
							System.out.println("id analisado " + idLattes);
							System.out.println("nome analisado " + nome);
							System.out.println("lista autor analisado " + pe.getNomeCompleto());
							System.out.println("nome citacao " + ontoParceiro.getNome());
							System.out.println("id citacao " + ontoParceiro.getId());
							System.out.println("abre citacao " + ontoParceiro.getCitacao());

							System.out.println("#");
							ontoParceiro.setNome(nome);
							ontoParceiro.setId(idLattes);
						} else {
							if (listcitacao.contains(ontoParceiro.getNome())) {
								System.out.println("-----------BANCA-----------------");
								System.out.println("nome analisado " + nome);
								System.out.println("lista autor analisado " + pe.getNomeCompleto());
								System.out.println("nome citacao " + ontoParceiro.getNome());
								System.out.println("id citacao " + ontoParceiro.getId());
								System.out.println("abre citacao " + ontoParceiro.getCitacao());
								System.out.println(ontoParceiro.getNome());
								System.out.println("---");
								System.out.println("&");
								ontoParceiro.setNome(nome);
								ontoParceiro.setId(idLattes);
							} else {
								if (listcitacao.contains(ontoParceiro.getCitacao())) {

									System.out.println("----------BANCA------------------");
									System.out.println("nome analisado " + nome);
									System.out.println("lista autor analisado " + pe.getNomeCompleto());
									System.out.println("nome citacao " + ontoParceiro.getNome());
									System.out.println("id citacao " + ontoParceiro.getId());
									System.out.println("abre citacao " + ontoParceiro.getCitacao());
									System.out.println(ontoParceiro.getCitacao());
									System.out.println("@");
									System.out.println("&");
									ontoParceiro.setNome(nome);
									ontoParceiro.setId(idLattes);
								}
							}
						}
					}
				}
				for (OntoClass ontoClass : pe.getListOntoFormacao()) {
					for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
						if (nome.contentEquals(ontoParceiro.getNome())) {
							System.out.println("-------------FORMACAO---------------");
							System.out.println("id analisado " + idLattes);
							System.out.println("nome analisado " + nome);
							System.out.println("lista autor analisado " + pe.getNomeCompleto());
							System.out.println("nome citacao " + ontoParceiro.getNome());
							System.out.println("id citacao " + ontoParceiro.getId());
							System.out.println("abre citacao " + ontoParceiro.getCitacao());

							System.out.println("#");
							ontoParceiro.setNome(nome);
							ontoParceiro.setId(idLattes);
						} else {
							if (listcitacao.contains(ontoParceiro.getNome())) {
								System.out.println("-------------FORMACAO---------------");
								System.out.println("nome analisado " + nome);
								System.out.println("lista autor analisado " + pe.getNomeCompleto());
								System.out.println("nome citacao " + ontoParceiro.getNome());
								System.out.println("id citacao " + ontoParceiro.getId());
								System.out.println("abre citacao " + ontoParceiro.getCitacao());
								System.out.println(ontoParceiro.getNome());
								System.out.println("---");
								System.out.println("&");
								ontoParceiro.setNome(nome);
								ontoParceiro.setId(idLattes);
							} else {
								if (listcitacao.contains(ontoParceiro.getCitacao())) {
									System.out.println("-----------FORMACAO-----------------");
									System.out.println("nome analisado " + nome);
									System.out.println("lista autor analisado " + pe.getNomeCompleto());
									System.out.println("nome citacao " + ontoParceiro.getNome());
									System.out.println("id citacao " + ontoParceiro.getId());
									System.out.println("abre citacao " + ontoParceiro.getCitacao());
									System.out.println(ontoParceiro.getCitacao());
									System.out.println("@");
									System.out.println("&");
									ontoParceiro.setNome(nome);
									ontoParceiro.setId(idLattes);
								}
							}
						}
					}

				}
				for (OntoClass ontoClass : pe.getListOntoProjetoPesquisa()) {
					for (OntoParceiro ontoParceiro : ontoClass.getListAutores()) {
						if (nome.contentEquals(ontoParceiro.getNome())) {
							System.out.println("-------------ProjetoPesquisa---------------");
							System.out.println("id analisado " + idLattes);
							System.out.println("nome analisado " + nome);
							System.out.println("lista autor analisado " + pe.getNomeCompleto());
							System.out.println("nome citacao " + ontoParceiro.getNome());
							System.out.println("id citacao " + ontoParceiro.getId());
							System.out.println("abre citacao " + ontoParceiro.getCitacao());

							System.out.println("#");
							ontoParceiro.setNome(nome);
							ontoParceiro.setId(idLattes);
						} else {
							if (listcitacao.contains(ontoParceiro.getNome())) {
								System.out.println("-------------ProjetoPesquisa---------------");
								System.out.println("nome analisado " + nome);
								System.out.println("lista autor analisado " + pe.getNomeCompleto());
								System.out.println("nome citacao " + ontoParceiro.getNome());
								System.out.println("id citacao " + ontoParceiro.getId());
								System.out.println("abre citacao " + ontoParceiro.getCitacao());
								System.out.println(ontoParceiro.getNome());
								System.out.println("---");
								System.out.println("&");
								ontoParceiro.setNome(nome);
								ontoParceiro.setId(idLattes);
							} else {
								if (listcitacao.contains(ontoParceiro.getCitacao())) {
									System.out.println("-----------ProjetoPesquisa-----------------");
									System.out.println("nome analisado " + nome);
									System.out.println("lista autor analisado " + pe.getNomeCompleto());
									System.out.println("nome citacao " + ontoParceiro.getNome());
									System.out.println("id citacao " + ontoParceiro.getId());
									System.out.println("abre citacao " + ontoParceiro.getCitacao());
									System.out.println(ontoParceiro.getCitacao());
									System.out.println("@");
									System.out.println("&");
									ontoParceiro.setNome(nome);
									ontoParceiro.setId(idLattes);
								}
							}
						}
					}
				}
			});

		}
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
		listaPessoa.sort(Comparator.comparing(u -> u.getIdLattes()));
		int j = 0;
		for (int i = 0; i < listaPessoa.size(); i++) {
			String idlattes = listaPessoa.get(i).getIdLattes();
			if (!idlattes.contentEquals("")) {
				while (i + 1 < listaPessoa.size()) {
					j = i + 1;
					if (idlattes.contentEquals(listaPessoa.get(j).getIdLattes())) {
						listaPessoa.get(i).Copiar(listaPessoa.get(j));
						listaPessoa.get(i).cont();
						listaPessoa.remove(j);
					} else {
						break;
					}
				}
			}
		}
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		j = 0;
		for (int i = 0; i < listaPessoa.size(); i++) {
			String nome = listaPessoa.get(i).getNomeCompleto();
			while (i + 1 < listaPessoa.size()) {
				j = i + 1;
				if (nome.contentEquals(listaPessoa.get(j).getNomeCompleto())) {
					listaPessoa.get(i).Copiar(listaPessoa.get(j));
					// listaPessoa.get(i).cont();
					listaPessoa.remove(j);
				} else {
					break;
				}
			}
		}

	}

}
