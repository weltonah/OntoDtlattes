package br.com.converter;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.semanticweb.owlapi.model.OWLOntology;

import br.com.modelo.OntoClass;
import br.com.modelo.OntoParceiro;
import br.com.modelo.OntoPessoa;
import br.com.modelo.TrabalhoEventoXml;
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
			ontoPessoa.setFlagLattes(true);
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

	public static ArrayList<TriplaOwl> listaBancaDesnecessario(OWLOntology ontology) {
		ArrayList<TriplaOwl> listDelete = new ArrayList<>();
		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Banca"))
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

	public void BaterNomeComIdLattes(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getIdLattes()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			String idlattes = listaPessoa.get(i).getIdLattes();
			if (!idlattes.contentEquals("")) {
				for (int j = i + 1; j < listaPessoa.size(); j++) {
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
			for (int j = i + 1; j < listaPessoa.size(); j++) {
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
			for (int j = i + 1; j < listaPessoa.size(); j++) {
				if (i != j) {
					OntoPessoa segundoNome = listaPessoa.get(j);
					if (i == listaPessoa.size())
						break;
					if (nome.charAt(0) == listaPessoa.get(j).getNomeCompleto().charAt(0)) {
						if (ngram.distance(nome, segundoNome.getNomeCompleto()) < 0.25) {
							listaPessoa.get(i).Copiar(segundoNome);
							listaPessoa.get(i).cont();
							listaPessoa.remove(j);
							j--;
						}
					}
				}
			}
		}

	}

	public void BaterCitacaoPorCitacao(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> u.getNomeCompleto()));
		for (int i = 0; i < listaPessoa.size(); i++) {
			ArrayList<String> listcitacaoPivo = listaPessoa.get(i).getCitacaoList();
			for (int j = i + 1; j < listaPessoa.size(); j++) {
				if (!(i == j)) {
					for (int k = 0; k < listaPessoa.get(j).getCitacaoList().size(); k++) {
						String unidadelist = listaPessoa.get(j).getCitacaoList().get(k);
						if (listcitacaoPivo.contains(unidadelist) && (!unidadelist.contentEquals(""))) {
							// if (listaPessoa.get(j).getNomeCompleto().length() >
							// listaPessoa.get(i).getNomeCompleto()
							// .length()) {
							// listaPessoa.get(i).setNomeCompleto(listaPessoa.get(j).getNomeCompleto());
							// }
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
				for (int j = i + 1; j < listaPessoa.size(); j++) {
					if (i != j) {
						for (int p = 0; p < listcitacaoPivo.size(); p++) {
							if (i == listaPessoa.size())
								break;
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
		listaPessoa.sort(Comparator.comparing(u -> ((OntoPessoa) u).getNomeCompleto()).reversed());
		for (int i = 0; i < listaPessoa.size(); i++) {
			String nome = listaPessoa.get(i).getNomeCompleto();
			for (int j = i + 1; j < listaPessoa.size(); j++) {
				if (!(i == j)) {
					if (nome.contains(listaPessoa.get(j).getNomeCompleto())) {
						listaPessoa.get(i).Copiar(listaPessoa.get(j));
						listaPessoa.get(i).cont();
						listaPessoa.remove(j);
						j--;
					}
				}
			}
		}
	}

	public void tratarEventos(ArrayList<OntoPessoa> listaPessoa) {
		listaPessoa.sort(Comparator.comparing(u -> ((OntoPessoa) u).getListOntoTrabalhoEvento().size()).reversed());

		int totalcont = 0;
		int cont = 99;
		while (cont != 0) {
			long tempoInicio = System.currentTimeMillis();
			cont = 0;
			for (int i = 0; i < listaPessoa.size(); i++) {
				OntoPessoa ontoPessoa = listaPessoa.get(i);
				if (ontoPessoa.getListOntoTrabalhoEvento().size() == 0)
					break;
				for (int j = i + 1; j < listaPessoa.size(); j++) {
					OntoPessoa ontoPessoa2 = listaPessoa.get(j);
					if (ontoPessoa2.getListOntoTrabalhoEvento().size() == 0)
						break;

					if ((j % 10 == 0))
						System.out.println(i + " " + j);

					for (int k = 0; k < ontoPessoa.getListOntoEvento().size(); k++) {
						OntoClass evento = ontoPessoa.getListOntoEvento().get(k);

						if ((ontoPessoa2.getListOntoEvento().size()
								+ ontoPessoa2.getListOntoTrabalhoEvento().size()) != 0) {
							for (int t = 0; t < ontoPessoa2.getListOntoEvento().size(); t++) {
								OntoClass evento2 = ontoPessoa2.getListOntoEvento().get(t);
								if (testeEvento(evento.getTitulo(), evento2.getTitulo())) {
									// System.out.println(
									// "combinado: " + evento.getTitulo() + " --> " + evento2.getTitulo());
									cont++;
									if (evento.getTitulo().length() < evento2.getTitulo().length())
										evento2.setTitulo(evento.getTitulo());
									else {
										if (evento.getTitulo().length() == evento2.getTitulo().length()) {
											if (evento.getTitulo().hashCode() < evento2.getTitulo().hashCode())
												evento2.setTitulo(evento.getTitulo());
											else
												evento.setTitulo(evento2.getTitulo());
										} else
											evento.setTitulo(evento2.getTitulo());
									}
								}
							}

							for (int t = 0; t < ontoPessoa2.getListOntoTrabalhoEvento().size(); t++) {
								TrabalhoEventoXml evento2 = ontoPessoa2.getListOntoTrabalhoEvento().get(t);
								if (testeEvento(evento.getTitulo(), evento2.getEvento().getTitulo())) {
									// System.out.println("combinado: " + evento.getTitulo() + " --> "
									// + evento2.getEvento().getTitulo());
									cont++;
									if (evento.getTitulo().length() < evento2.getEvento().getTitulo().length())
										evento2.getEvento().setTitulo(evento.getTitulo());
									else {
										if (evento.getTitulo().length() == evento2.getEvento().getTitulo().length()) {
											if (evento.getTitulo().hashCode() < evento2.getEvento().getTitulo()
													.hashCode())
												evento2.getEvento().setTitulo(evento.getTitulo());
											else
												evento.setTitulo(evento2.getEvento().getTitulo());
										} else
											evento.setTitulo(evento2.getEvento().getTitulo());
									}
								}
							}
						}
					}
					for (int k = 0; k < ontoPessoa.getListOntoTrabalhoEvento().size(); k++) {
						TrabalhoEventoXml evento = ontoPessoa.getListOntoTrabalhoEvento().get(k);

						if ((ontoPessoa2.getListOntoEvento().size()
								+ ontoPessoa2.getListOntoTrabalhoEvento().size()) != 0) {
							for (int t = 0; t < ontoPessoa2.getListOntoEvento().size(); t++) {
								OntoClass evento2 = ontoPessoa2.getListOntoEvento().get(t);
								if (testeEvento(evento.getEvento().getTitulo(), evento2.getTitulo())) {

									// System.out.println("combinado: " + evento.getEvento().getTitulo() + " --> "
									// + evento2.getTitulo());
									cont++;
									if (evento.getEvento().getTitulo().length() < evento2.getTitulo().length())
										evento2.setTitulo(evento.getEvento().getTitulo());
									else {
										if (evento.getEvento().getTitulo().length() == evento2.getTitulo().length()) {
											if (evento.getEvento().getTitulo().hashCode() < evento2.getTitulo()
													.hashCode())
												evento2.setTitulo(evento.getEvento().getTitulo());
											else
												evento.getEvento().setTitulo(evento2.getTitulo());
										} else
											evento.getEvento().setTitulo(evento2.getTitulo());
									}
								}
							}
							for (int t = 0; t < ontoPessoa2.getListOntoTrabalhoEvento().size(); t++) {
								TrabalhoEventoXml evento2 = ontoPessoa2.getListOntoTrabalhoEvento().get(t);
								if (testeEvento(evento.getEvento().getTitulo(), evento2.getEvento().getTitulo())) {
									// System.out.println("combinado: " + evento.getEvento().getTitulo() + " --> "
									// + evento2.getEvento().getTitulo());
									cont++;
									if (evento.getEvento().getTitulo().length() < evento2.getEvento().getTitulo()
											.length())
										evento2.getEvento().setTitulo(evento.getEvento().getTitulo());
									else {
										if (evento.getEvento().getTitulo().length() == evento2.getEvento().getTitulo()
												.length()) {
											if (evento.getEvento().getTitulo().hashCode() < evento2.getEvento()
													.getTitulo().hashCode())
												evento2.getEvento().setTitulo(evento.getEvento().getTitulo());
											else
												evento.getEvento().setTitulo(evento2.getEvento().getTitulo());
										} else
											evento.getEvento().setTitulo(evento2.getEvento().getTitulo());
									}
								}
							}
						}
					}
					// if (cont2 < cont)
					// System.out.println("deu alguma coisa " + (cont - cont2));
				}
			}
			System.out.println("numero de evento que foram combinadas " + cont);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
		}

		for (int i = 0; i < listaPessoa.size(); i++) {
			totalcont = totalcont + listaPessoa.get(i).getListOntoEvento().size();
		}
		System.out.println("numero total de evento " + totalcont);
	}

	public void tratarBancaInterna(ArrayList<OntoPessoa> listaPessoa) {
		NGram ngram = new NGram(4);
		int totalcont = 0;
		int cont = 0;

		for (int i = 0; i < listaPessoa.size(); i++) {
			OntoPessoa ontoPessoa = listaPessoa.get(i);
			System.out.println(i + " -" + ontoPessoa.getNomeCompleto() + " -- " + ontoPessoa.getListOntoBanca().size());
			for (int k = 0; k < ontoPessoa.getListOntoBanca().size(); k++) {
				OntoClass banca = ontoPessoa.getListOntoBanca().get(k);

				for (int t = k + 1; t < ontoPessoa.getListOntoBanca().size(); t++) {
					OntoClass banca2 = ontoPessoa.getListOntoBanca().get(t);

					if (banca.getAno() + 1 == banca2.getAno() || banca.getAno() - 1 == banca2.getAno()
							|| banca.getAno() + 2 == banca2.getAno() || banca.getAno() - 2 == banca2.getAno()
							|| banca.getAno() == banca2.getAno()) {
						double aux = ngram.distance(banca.getTitulo(), banca2.getTitulo());
						if ((t % 100 == 0) && (k % 50 == 0))
							System.out.println(k + " " + t);
						if (aux > 0 && aux < 0.25) {
							cont++;
							if (banca.getTitulo().length() < banca2.getTitulo().length()) {
								banca2.setTitulo(banca.getTitulo());
								ontoPessoa.getListOntoBanca().remove(k);
								k = -1;
								break;
							} else {
								if (banca.getTitulo().length() == banca2.getTitulo().length()) {
									if (banca.getTitulo().hashCode() < banca2.getTitulo().hashCode()) {
										banca2.setTitulo(banca.getTitulo());
										ontoPessoa.getListOntoBanca().remove(k);
										k = -1;
										break;
									} else {
										banca.setTitulo(banca2.getTitulo());
										ontoPessoa.getListOntoBanca().remove(t);
										t--;
									}
								} else {
									banca.setTitulo(banca2.getTitulo());
									ontoPessoa.getListOntoBanca().remove(t);
									t--;
								}
							}
						}
					}
				}
			}
			// System.out.println(i + " -" + ontoPessoa.getNomeCompleto() + " -- " +
			// ontoPessoa.getListOntoBanca().size());
		}
		System.out.println("bancas combinadas interiormente " + cont);
		cont = 99;
		System.out.println("fim");

		for (int i = 0; i < listaPessoa.size(); i++) {
			totalcont = totalcont + listaPessoa.get(i).getListOntoBanca().size();
		}
		System.out.println("numero total de banca " + totalcont);
	}

	public void tratarBancaExterna(ArrayList<OntoPessoa> listaPessoa) {
		NGram ngram = new NGram(4);
		int totalcont = 0;
		int cont = 99;

		while (cont != 0) {
			long tempoInicio = System.currentTimeMillis();
			cont = 0;
			for (int i = 0; i < listaPessoa.size(); i++) {
				OntoPessoa ontoPessoa = listaPessoa.get(i);

				for (int j = i + 1; j < listaPessoa.size(); j++) {
					OntoPessoa ontoPessoa2 = listaPessoa.get(j);

					if ((j % 10 == 0) && (i % 1 == 0))
						System.out.println(i + " " + j);

					for (int k = 0; k < ontoPessoa.getListOntoBanca().size(); k++) {
						OntoClass banca = ontoPessoa.getListOntoBanca().get(k);

						for (int t = 0; t < ontoPessoa2.getListOntoBanca().size(); t++) {
							OntoClass banca2 = ontoPessoa2.getListOntoBanca().get(t);
							if (banca.getAno() + 1 == banca2.getAno() || banca.getAno() - 1 == banca2.getAno()
									|| banca.getAno() + 2 == banca2.getAno() || banca.getAno() - 2 == banca2.getAno()
									|| banca.getAno() == banca2.getAno()) {
								double aux = ngram.distance(banca.getTitulo(), banca2.getTitulo());
								if (aux > 0 && aux < 0.20) {
									// if (banca.getAno() != banca2.getAno()) {
									// System.out.println(aux);
									// System.out.println(banca.getTitulo());
									// System.out.println(banca.getAno());
									// System.out.println(banca2.getAno());
									// System.out.println(banca2.getTitulo());
									// }

									cont++;
									if (banca.getTitulo().length() < banca2.getTitulo().length())
										banca2.setTitulo(banca.getTitulo());
									else {
										if (banca.getTitulo().length() == banca2.getTitulo().length()) {
											if (banca.getTitulo().hashCode() < banca2.getTitulo().hashCode())
												banca2.setTitulo(banca.getTitulo());
											else
												banca.setTitulo(banca2.getTitulo());
										} else
											banca.setTitulo(banca2.getTitulo());
									}
									// System.out.println(ontoPessoa.getListOntoEvento().get(k).getTitulo());
								}
							}
						}
					}
				}
			}
			System.out.println("numero de banca que foram combinadas " + cont);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println("Tempo Total: " + (System.currentTimeMillis() - tempoInicio));
		}

	}

	public void eliminarIndividuosDesnecessarios(ArrayList<OntoPessoa> listaPessoa) {

		for (int i = 0; i < listaPessoa.size(); i++) {
			OntoPessoa ontoPessoa = listaPessoa.get(i);
			int valortotal = ontoPessoa.getListOntoBanca().size() + ontoPessoa.getListOntoEvento().size()
					+ ontoPessoa.getListOntoProjetoPesquisa().size() + ontoPessoa.getListOntoAreaAtuacao().size()
					+ ontoPessoa.getListOntoFormacao().size() + ontoPessoa.getListOntoOrientacao().size()
					+ ontoPessoa.getListOntoTrabalhoEvento().size();
			if (valortotal < 2) {
				listaPessoa.remove(i);
				i--;
			}
		}
	}

	public Boolean testeEvento(String nome1, String nome2) {
		NGram ngram = new NGram(4);
		if (nome1.length() * 1.4 <= nome2.length() || nome1.length() >= nome2.length() * 1.4) {
			return false;
		} else {
			if (nome1 == nome2) {
				return false;
			} else {
				double aux = ngram.distance(nome1, nome2);
				if (aux > 0 && aux < 0.25) {
					return true;
				}
			}
		}
		return false;
	}

	public Map<String, String> CriarMap(ArrayList<OntoPessoa> listaPessoa) {
		Map<String, String> MapNome = new HashMap<String, String>();
		for (int i = 0; i < listaPessoa.size(); i++) {
			OntoPessoa ontoPessoa = listaPessoa.get(i);
			if (ontoPessoa.getIdLattes().length() > 1) {
				MapNome.put(ontoPessoa.getIdLattes(), ontoPessoa.getNomeCompleto());
			}
		}
		return MapNome;
	}

	public void tratarEventoExterna(ArrayList<OntoPessoa> listaPessoa) {
		int cont = 0;
		for (int i = 0; i < listaPessoa.size(); i++) {
			OntoPessoa ontoPessoa = listaPessoa.get(i);
			for (int k = 0; k < ontoPessoa.getListOntoEvento().size(); k++) {
				OntoClass evento = ontoPessoa.getListOntoEvento().get(k);
				for (int t = k + 1; t < ontoPessoa.getListOntoEvento().size(); t++) {
					OntoClass evento2 = ontoPessoa.getListOntoEvento().get(t);
					if (testeEvento(evento.getTitulo(), evento2.getTitulo())) {
						cont++;
						if (evento.getTitulo().length() < evento2.getTitulo().length()) {
							// evento2.setTitulo(evento.getTitulo());
							ontoPessoa.getListOntoEvento().remove(t);
							t--;
						} else {
							if (evento.getTitulo().length() == evento2.getTitulo().length()) {
								if (evento.getTitulo().hashCode() < evento2.getTitulo().hashCode()) {
									// evento2.setTitulo(evento.getTitulo());
									ontoPessoa.getListOntoEvento().remove(t);
									t--;
								} else {
									evento.setTitulo(evento2.getTitulo());
									ontoPessoa.getListOntoEvento().remove(t);
									t--;
								}
							} else {
								evento.setTitulo(evento2.getTitulo());
								ontoPessoa.getListOntoEvento().remove(t);
								t--;
							}
						}
					}
				}
			}

			for (int k = 0; k < ontoPessoa.getListOntoTrabalhoEvento().size(); k++) {
				TrabalhoEventoXml evento = ontoPessoa.getListOntoTrabalhoEvento().get(k);
				for (int t = k + 1; t < ontoPessoa.getListOntoTrabalhoEvento().size(); t++) {
					TrabalhoEventoXml evento2 = ontoPessoa.getListOntoTrabalhoEvento().get(t);
					if (testeEvento(evento.getEvento().getTitulo(), evento2.getEvento().getTitulo())) {
						cont++;
						if (evento.getEvento().getTitulo().length() < evento2.getEvento().getTitulo().length())
							evento2.getEvento().setTitulo(evento.getEvento().getTitulo());
						else {
							if (evento.getEvento().getTitulo().length() == evento2.getEvento().getTitulo().length()) {
								if (evento.getEvento().getTitulo().hashCode() < evento2.getEvento().getTitulo()
										.hashCode())
									evento2.getEvento().setTitulo(evento.getEvento().getTitulo());
								else
									evento.getEvento().setTitulo(evento2.getEvento().getTitulo());
							} else
								evento.getEvento().setTitulo(evento2.getEvento().getTitulo());
						}
					}
				}
			}
			for (int k = 0; k < ontoPessoa.getListOntoEvento().size(); k++) {
				OntoClass evento = ontoPessoa.getListOntoEvento().get(k);
				for (int t = k; t < ontoPessoa.getListOntoTrabalhoEvento().size(); t++) {
					TrabalhoEventoXml evento2 = ontoPessoa.getListOntoTrabalhoEvento().get(t);
					if (testeEvento(evento.getTitulo(), evento2.getEvento().getTitulo())) {
						// System.out.println("combinado: " + evento.getTitulo() + " --> "
						// + evento2.getEvento().getTitulo());
						cont++;
						if (evento.getTitulo().length() < evento2.getEvento().getTitulo().length())
							evento2.getEvento().setTitulo(evento.getTitulo());
						else {
							if (evento.getTitulo().length() == evento2.getEvento().getTitulo().length()) {
								if (evento.getTitulo().hashCode() < evento2.getEvento().getTitulo().hashCode())
									evento2.getEvento().setTitulo(evento.getTitulo());
								else
									evento.setTitulo(evento2.getEvento().getTitulo());
							} else
								evento.setTitulo(evento2.getEvento().getTitulo());
						}
					}
				}
			}
		}
		System.out.println("rerrr" + cont);
	}

}
