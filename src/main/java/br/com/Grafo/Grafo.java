package br.com.Grafo;

import java.util.ArrayList;
import java.util.Comparator;

public class Grafo {

	private ArrayList<Pessoa> listParticipante;
	private ArrayList<Banca> listParticipouBanca;
	private ArrayList<Evento> listParticipouEvento;

	public Grafo() {
		this.listParticipante = new ArrayList<>();
		this.listParticipouBanca = new ArrayList<>();
		this.listParticipouEvento = new ArrayList<>();
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

		// for (String[] strings : result) {
		// System.out.println(strings[0] + " ---> " + strings[1] + " valor: " +
		// strings[2]);
		// }

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

	public void listParticipouEvento() {
		this.listParticipouEvento.forEach(u -> {
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

}
