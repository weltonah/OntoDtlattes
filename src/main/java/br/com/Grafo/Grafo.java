package br.com.Grafo;

import java.util.ArrayList;
import java.util.Comparator;

public class Grafo {

	private ArrayList<Pessoa> listParticipante;
	private ArrayList<Banca> listParticipouBanca;
	private ArrayList<Evento> listParticipouEvento;
	private ArrayList<AreaAtuacao> listParticipouAreaAtuacao;
	private ArrayList<AreaConhecimento> listParticipouAreaConhecimento;
	private ArrayList<SubArea> listParticipouSubArea;
	private ArrayList<Especialidade> listParticipouEspecialidade;
	// private ArrayList<Orientacao> listParticipouOrientacao;

	public Grafo() {
		this.listParticipante = new ArrayList<>();
		this.listParticipouBanca = new ArrayList<>();
		this.listParticipouEvento = new ArrayList<>();
		this.listParticipouAreaAtuacao = new ArrayList<>();
		this.listParticipouAreaConhecimento = new ArrayList<>();
		this.listParticipouSubArea = new ArrayList<>();
		this.listParticipouEspecialidade = new ArrayList<>();
	}

	public ArrayList<String[]> InferirBanca() {
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (Banca banca : this.listParticipouBanca) {
			for (int i = 0; i < banca.getListParticipante().size(); i++) {
				for (int j = 0; j < banca.getListParticipante().size(); j++) {
					if (i != j) {
						String[] resultado = new String[2];
						resultado[0] = banca.getListParticipante().get(i).getNome();
						resultado[1] = banca.getListParticipante().get(j).getNome();
						list.add(resultado);

						// String[] resultado2 = new String[2];
						// resultado2[1] = banca.getListParticipante().get(i).getNome();
						// resultado2[0] = banca.getListParticipante().get(j).getNome();
						// list.add(resultado2);
					}
				}
			}
		}
		list.sort(Comparator.comparing(u -> u[0]));
		return list;
	}

	public ArrayList<String[]> InferirEvento() {
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (Evento evento : this.listParticipouEvento) {
			for (int i = 0; i < evento.getListParticipante().size(); i++) {
				for (int j = 0; j < evento.getListParticipante().size(); j++) {
					if (i != j) {
						String[] resultado = new String[2];
						resultado[0] = evento.getListParticipante().get(i).getNome();
						resultado[1] = evento.getListParticipante().get(j).getNome();
						list.add(resultado);
						// String[] resultado2 = new String[2];
						// resultado2[1] = banca.getListParticipante().get(i).getNome();
						// resultado2[0] = banca.getListParticipante().get(j).getNome();
						// list.add(resultado2);
					}
				}
			}
		}
		list.sort(Comparator.comparing(u -> u[0]));
		return list;
	}

	public ArrayList<String[]> InferirAreaAtuacao() {
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (AreaAtuacao areaatucao : this.listParticipouAreaAtuacao) {
			for (int i = 0; i < areaatucao.getListParticipante().size(); i++) {
				for (int j = 0; j < areaatucao.getListParticipante().size(); j++) {
					if (i != j) {
						String[] resultado = new String[2];
						resultado[0] = areaatucao.getListParticipante().get(i).getNome();
						resultado[1] = areaatucao.getListParticipante().get(j).getNome();
						list.add(resultado);
						// String[] resultado2 = new String[2];
						// resultado2[1] = banca.getListParticipante().get(i).getNome();
						// resultado2[0] = banca.getListParticipante().get(j).getNome();
						// list.add(resultado2);
					}
				}
			}
		}
		list.sort(Comparator.comparing(u -> u[0]));
		return list;
	}

	public ArrayList<String[]> InferirAreaConhecimento() {
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (AreaConhecimento areaConhecimento : this.listParticipouAreaConhecimento) {
			for (int i = 0; i < areaConhecimento.getListParticipante().size(); i++) {
				for (int j = 0; j < areaConhecimento.getListParticipante().size(); j++) {
					if (i != j) {
						String[] resultado = new String[2];
						resultado[0] = areaConhecimento.getListParticipante().get(i).getNome();
						resultado[1] = areaConhecimento.getListParticipante().get(j).getNome();
						list.add(resultado);
						// String[] resultado2 = new String[2];
						// resultado2[1] = banca.getListParticipante().get(i).getNome();
						// resultado2[0] = banca.getListParticipante().get(j).getNome();
						// list.add(resultado2);
					}
				}
			}
		}
		list.sort(Comparator.comparing(u -> u[0]));
		return list;
	}

	public ArrayList<String[]> InferirSubArea() {
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (SubArea subArea : this.listParticipouSubArea) {
			for (int i = 0; i < subArea.getListParticipante().size(); i++) {
				for (int j = 0; j < subArea.getListParticipante().size(); j++) {
					if (i != j) {
						String[] resultado = new String[2];
						resultado[0] = subArea.getListParticipante().get(i).getNome();
						resultado[1] = subArea.getListParticipante().get(j).getNome();
						list.add(resultado);
						// String[] resultado2 = new String[2];
						// resultado2[1] = banca.getListParticipante().get(i).getNome();
						// resultado2[0] = banca.getListParticipante().get(j).getNome();
						// list.add(resultado2);
					}
				}
			}
		}
		list.sort(Comparator.comparing(u -> u[0]));
		return list;
	}

	public ArrayList<String[]> InferirEspecialidade() {
		ArrayList<String[]> list = new ArrayList<String[]>();

		for (Especialidade especialidade : this.listParticipouEspecialidade) {
			for (int i = 0; i < especialidade.getListParticipante().size(); i++) {
				for (int j = 0; j < especialidade.getListParticipante().size(); j++) {
					if (i != j) {
						String[] resultado = new String[2];
						resultado[0] = especialidade.getListParticipante().get(i).getNome();
						resultado[1] = especialidade.getListParticipante().get(j).getNome();
						list.add(resultado);
						// String[] resultado2 = new String[2];
						// resultado2[1] = banca.getListParticipante().get(i).getNome();
						// resultado2[0] = banca.getListParticipante().get(j).getNome();
						// list.add(resultado2);
					}
				}
			}
		}
		list.sort(Comparator.comparing(u -> u[0]));
		return list;
	}

	public ArrayList<String[]> contRelacoes(ArrayList<String[]> lista) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		ArrayList<Relacao> listrelacao = new ArrayList<Relacao>();

		for (int i = 0; i < lista.size(); i++) {
			String nome = lista.get(i)[0];
			Relacao relacao = new Relacao(nome);
			int j = 0;
			for (j = i; (j < lista.size()) && (lista.get(j)[0].contentEquals(nome)); j++) {
				relacao.AddListParticipante(lista.get(j)[1]);
			}
			listrelacao.add(relacao);
			i = j - 1;
		}

		// listrelacao.forEach(u -> u.getListParticipante().forEach(t ->
		// System.out.println(u.getNome() + " --> " + t)));
		listrelacao.forEach(u -> {
			for (int i = 0; i < u.getListParticipante().size(); i++) {
				String nome = u.getListParticipante().get(i);
				int j = 0;
				int cont = 0;
				String[] res = new String[3];
				for (j = i; (j < u.getListParticipante().size())
						&& (u.getListParticipante().get(j).contentEquals(nome)); j++) {
					cont++;
				}
				i = j - 1;
				res[0] = u.getNome();
				res[1] = u.getListParticipante().get(i);
				res[2] = "" + (cont);
				result.add(res);
			}
		});

		for (String[] strings : result) {
			System.out.println(strings[0] + " ---> " + strings[1] + " valor: " + strings[2]);
		}

		return result;
	}

	public void AddBanca(String[] info) {
		Pessoa resultPe = null;
		for (Pessoa pessoa : this.listParticipante) {
			if (pessoa.getNome().contentEquals(info[0])) {
				resultPe = pessoa;
				break;
			}
		}
		Banca resultba = null;
		for (Banca banca : this.listParticipouBanca) {
			if (banca.getTitulo().contentEquals(info[1])) {
				resultba = banca;
				break;
			}
		}
		if (resultPe != null && resultba == null) {
			Banca banca = new Banca(info[1]);
			banca.AddListParticipante(resultPe);
			resultPe.AddListParticipouBanca(banca);
			AddParticipouBanca(banca);
		} else {
			if (resultPe == null && resultba != null) {
				Pessoa pe = new Pessoa(info[0]);
				pe.AddListParticipouBanca(resultba);
				resultba.AddListParticipante(pe);
				AddParticipante(pe);
			} else {
				if (resultPe != null && resultba != null) {
					resultPe.AddListParticipouBanca(resultba);
					resultba.AddListParticipante(resultPe);
				} else {
					Pessoa pe = new Pessoa(info[0]);
					Banca banca = new Banca(info[1]);
					banca.AddListParticipante(pe);
					pe.AddListParticipouBanca(banca);
					AddParticipante(pe);
					AddParticipouBanca(banca);
				}
			}
		}
	}

	public void AddEvento(String[] info) {
		Pessoa resultPe = null;
		for (Pessoa pessoa : this.listParticipante) {
			if (pessoa.getNome().contentEquals(info[0])) {
				resultPe = pessoa;
				break;
			}
		}
		Evento resultEv = null;
		for (Evento evento : this.listParticipouEvento) {
			if (evento.getTitulo().contentEquals(info[1])) {
				resultEv = evento;
				break;
			}
		}
		if (resultPe != null && resultEv == null) {
			Evento evento = new Evento(info[1]);
			evento.AddListParticipante(resultPe);
			resultPe.AddListParticipouEvento(evento);
			AddListParticipouEvento(evento);
		} else {
			if (resultPe == null && resultEv != null) {
				Pessoa pe = new Pessoa(info[0]);
				pe.AddListParticipouEvento(resultEv);
				resultEv.AddListParticipante(pe);
				AddParticipante(pe);
			} else {
				if (resultPe != null && resultEv != null) {
					resultPe.AddListParticipouEvento(resultEv);
					resultEv.AddListParticipante(resultPe);
				} else {
					Pessoa pe = new Pessoa(info[0]);
					Evento evento = new Evento(info[1]);
					evento.AddListParticipante(pe);
					pe.AddListParticipouEvento(evento);
					AddParticipante(pe);
					AddListParticipouEvento(evento);
				}
			}
		}
	}

	public void AddAreaAtuacao(String[] info) {
		Pessoa resultPe = null;

		for (Pessoa pessoa : this.listParticipante) {
			if (pessoa.getNome().contentEquals(info[0])) {
				resultPe = pessoa;
				break;
			}
		}
		AreaAtuacao resultAA = null;
		for (AreaAtuacao areatucao : this.listParticipouAreaAtuacao) {
			if (areatucao.getTitulo().contentEquals(info[1])) {
				resultAA = areatucao;
				break;
			}
		}
		if (resultPe != null && resultAA == null) {
			AreaAtuacao areatucao = new AreaAtuacao(info[1]);
			areatucao.AddListParticipante(resultPe);
			resultPe.AddListParticipouAreaAtuacao(areatucao);
			AddListParticipouAreaAtuacao(areatucao);
		} else {
			if (resultPe == null && resultAA != null) {
				Pessoa pe = new Pessoa(info[0]);
				pe.AddListParticipouAreaAtuacao(resultAA);
				resultAA.AddListParticipante(pe);
				AddParticipante(pe);
			} else {
				if (resultPe != null && resultAA != null) {
					resultPe.AddListParticipouAreaAtuacao(resultAA);
					resultAA.AddListParticipante(resultPe);
				} else {
					Pessoa pe = new Pessoa(info[0]);
					AreaAtuacao areatucao = new AreaAtuacao(info[1]);
					areatucao.AddListParticipante(pe);
					pe.AddListParticipouAreaAtuacao(areatucao);
					AddParticipante(pe);
					AddListParticipouAreaAtuacao(areatucao);
				}
			}
		}
	}

	public void AddAreaConhecimento(String[] info) {
		Pessoa resultPe = null;
		for (Pessoa pessoa : this.listParticipante) {
			if (pessoa.getNome().contentEquals(info[0])) {
				resultPe = pessoa;
				break;
			}
		}
		AreaConhecimento resultAC = null;
		for (AreaConhecimento areaconhecimento : this.listParticipouAreaConhecimento) {
			if (areaconhecimento.getTitulo().contentEquals(info[1])) {
				resultAC = areaconhecimento;
				break;
			}
		}
		if (resultPe != null && resultAC == null) {
			AreaConhecimento areaconhecimento = new AreaConhecimento(info[1]);
			areaconhecimento.AddListParticipante(resultPe);
			resultPe.AddListParticipouAreaConhecimento(areaconhecimento);
			AddListParticipouAreaConhecimento(areaconhecimento);
		} else {
			if (resultPe == null && resultAC != null) {
				Pessoa pe = new Pessoa(info[0]);
				pe.AddListParticipouAreaConhecimento(resultAC);
				resultAC.AddListParticipante(pe);
				AddParticipante(pe);
			} else {
				if (resultPe != null && resultAC != null) {
					resultPe.AddListParticipouAreaConhecimento(resultAC);
					resultAC.AddListParticipante(resultPe);
				} else {
					Pessoa pe = new Pessoa(info[0]);
					AreaConhecimento areaconhecimento = new AreaConhecimento(info[1]);
					areaconhecimento.AddListParticipante(pe);
					pe.AddListParticipouAreaConhecimento(areaconhecimento);
					AddParticipante(pe);
					AddListParticipouAreaConhecimento(areaconhecimento);
				}
			}
		}
	}

	public void AddSubArea(String[] info) {
		Pessoa resultPe = null;
		for (Pessoa pessoa : this.listParticipante) {
			if (pessoa.getNome().contentEquals(info[0])) {
				resultPe = pessoa;
				break;
			}
		}
		SubArea resultSA = null;
		for (SubArea subarea : this.listParticipouSubArea) {
			if (subarea.getTitulo().contentEquals(info[1])) {
				resultSA = subarea;
				break;
			}
		}
		if (resultPe != null && resultSA == null) {
			SubArea subarea = new SubArea(info[1]);
			subarea.AddListParticipante(resultPe);
			resultPe.AddListParticipouSubArea(subarea);
			AddListParticipouSubArea(subarea);
		} else {
			if (resultPe == null && resultSA != null) {
				Pessoa pe = new Pessoa(info[0]);
				pe.AddListParticipouSubArea(resultSA);
				resultSA.AddListParticipante(pe);
				AddParticipante(pe);
			} else {
				if (resultPe != null && resultSA != null) {
					resultPe.AddListParticipouSubArea(resultSA);
					resultSA.AddListParticipante(resultPe);
				} else {
					Pessoa pe = new Pessoa(info[0]);
					SubArea subarea = new SubArea(info[1]);
					subarea.AddListParticipante(pe);
					pe.AddListParticipouSubArea(subarea);
					AddParticipante(pe);
					AddListParticipouSubArea(subarea);
				}
			}
		}
	}

	public void AddEspecialidade(String[] info) {
		Pessoa resultPe = null;
		for (Pessoa pessoa : this.listParticipante) {
			if (pessoa.getNome().contentEquals(info[0])) {
				resultPe = pessoa;
				break;
			}
		}
		Especialidade resultEP = null;
		for (Especialidade especialidade : this.listParticipouEspecialidade) {
			if (especialidade.getTitulo().contentEquals(info[1])) {
				resultEP = especialidade;
				break;
			}
		}
		if (resultPe != null && resultEP == null) {
			Especialidade especialidade = new Especialidade(info[1]);
			especialidade.AddListParticipante(resultPe);
			resultPe.AddListParticipouEspecialidade(especialidade);
			AddListParticipouEspecialidade(especialidade);
		} else {
			if (resultPe == null && resultEP != null) {
				Pessoa pe = new Pessoa(info[0]);
				pe.AddListParticipouEspecialidade(resultEP);
				resultEP.AddListParticipante(pe);
				AddParticipante(pe);
			} else {
				if (resultPe != null && resultEP != null) {
					resultPe.AddListParticipouEspecialidade(resultEP);
					resultEP.AddListParticipante(resultPe);
				} else {
					Pessoa pe = new Pessoa(info[0]);
					Especialidade eveespecialidadento = new Especialidade(info[1]);
					eveespecialidadento.AddListParticipante(pe);
					pe.AddListParticipouEspecialidade(eveespecialidadento);
					AddParticipante(pe);
					AddListParticipouEspecialidade(eveespecialidadento);
				}
			}
		}
	}

	public void imprimirBanca() {
		this.listParticipouBanca.forEach(u -> {
			System.out.println("----------------" + u.getTitulo() + "------------------");
			u.getListParticipante().forEach(p -> System.out.println(p.getNome()));
		});
		// this.listParticipante.forEach(u -> {
		// System.out.println("----------------" + u.getNome() + "------------------");
		// u.getListParticipouBanca().forEach(p -> System.out.println(p.getTitulo()));
		// });

	}

	public void imprimirEvento() {
		this.listParticipouEvento.forEach(u -> {
			System.out.println("----------------" + u.getTitulo() + "------------------");
			u.getListParticipante().forEach(p -> System.out.println(p.getNome()));
		});
		// this.listParticipante.forEach(u -> {
		// System.out.println("----------------" + u.getNome() + "------------------");
		// u.getListParticipouBanca().forEach(p -> System.out.println(p.getTitulo()));
		// });

	}

	public void imprimirArea() {
		System.out.println("@@@@@@@@@@@@@" + "Area Atuacao" + "@@@@@@@@@@@2");

		this.listParticipouAreaAtuacao.forEach(u -> {
			System.out.println("----------------" + u.getTitulo() + "------------------");
			u.getListParticipante().forEach(p -> System.out.println(p.getNome()));
		});
		System.out.println("@@@@@@@@@@@@@" + "Area conhecimentp" + "@@@@@@@@@@@2");
		this.listParticipouAreaConhecimento.forEach(u -> {
			System.out.println("----------------" + u.getTitulo() + "------------------");
			u.getListParticipante().forEach(p -> System.out.println(p.getNome()));
		});
		System.out.println("@@@@@@@@@@@@@" + "SubArea" + "@@@@@@@@@@@2");
		this.listParticipouSubArea.forEach(u -> {
			System.out.println("----------------" + u.getTitulo() + "------------------");
			u.getListParticipante().forEach(p -> System.out.println(p.getNome()));
		});
		System.out.println("@@@@@@@@@@@@@" + "Especialidade" + "@@@@@@@@@@@2");
		this.listParticipouEspecialidade.forEach(u -> {
			System.out.println("----------------" + u.getTitulo() + "------------------");
			u.getListParticipante().forEach(p -> System.out.println(p.getNome()));
		});
		// this.listParticipante.forEach(u -> {
		// System.out.println("----------------" + u.getNome() + "------------------");
		// u.getListParticipouBanca().forEach(p -> System.out.println(p.getTitulo()));
		// });

	}

	public void AddParticipante(Pessoa listParticipante) {
		this.listParticipante.add(listParticipante);
		this.listParticipante.sort(Comparator.comparing(u -> u.getNome()));
	}

	public void AddParticipouBanca(Banca listParticipouBanca) {
		this.listParticipouBanca.add(listParticipouBanca);
	}

	public ArrayList<Pessoa> getListParticipante() {
		return this.listParticipante;
	}

	public void setListParticipante(ArrayList<Pessoa> listParticipante) {
		this.listParticipante = listParticipante;
	}

	public ArrayList<Banca> getListParticipouBanca() {
		return this.listParticipouBanca;
	}

	public void setListParticipouBanca(ArrayList<Banca> listParticipouBanca) {
		this.listParticipouBanca = listParticipouBanca;
	}

	public ArrayList<Evento> getListParticipouEvento() {
		return this.listParticipouEvento;
	}

	public void setListParticipouEvento(ArrayList<Evento> listParticipouEvento) {
		this.listParticipouEvento = listParticipouEvento;
	}

	public void AddListParticipouEvento(Evento listParticipouEvento) {
		this.listParticipouEvento.add(listParticipouEvento);
	}

	public ArrayList<AreaAtuacao> getListParticipouAreaAtuacao() {
		return this.listParticipouAreaAtuacao;
	}

	public void setListParticipouAreaAtuacao(ArrayList<AreaAtuacao> listParticipouAreaAtuacao) {
		this.listParticipouAreaAtuacao = listParticipouAreaAtuacao;
	}

	public void AddListParticipouAreaAtuacao(AreaAtuacao listParticipouAreaAtuacao) {
		this.listParticipouAreaAtuacao.add(listParticipouAreaAtuacao);
	}

	public ArrayList<AreaConhecimento> getListParticipouAreaConhecimento() {
		return this.listParticipouAreaConhecimento;
	}

	public void setListParticipouAreaConhecimento(ArrayList<AreaConhecimento> listParticipouAreaConhecimento) {
		this.listParticipouAreaConhecimento = listParticipouAreaConhecimento;
	}

	public void AddListParticipouAreaConhecimento(AreaConhecimento listParticipouAreaConhecimento) {
		this.listParticipouAreaConhecimento.add(listParticipouAreaConhecimento);
	}

	public ArrayList<SubArea> getListParticipouSubArea() {
		return this.listParticipouSubArea;
	}

	public void setListParticipouSubArea(ArrayList<SubArea> listParticipouSubArea) {
		this.listParticipouSubArea = listParticipouSubArea;
	}

	public void AddListParticipouSubArea(SubArea listParticipouSubArea) {
		this.listParticipouSubArea.add(listParticipouSubArea);
	}

	public ArrayList<Especialidade> getListParticipouEspecialidade() {
		return this.listParticipouEspecialidade;
	}

	public void setListParticipouEspecialidade(ArrayList<Especialidade> listParticipouEspecialidade) {
		this.listParticipouEspecialidade = listParticipouEspecialidade;
	}

	public void AddListParticipouEspecialidade(Especialidade listParticipouEspecialidade) {
		this.listParticipouEspecialidade.add(listParticipouEspecialidade);
	}

}
