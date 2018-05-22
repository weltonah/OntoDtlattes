package br.com.Grafo;

import java.util.ArrayList;
import java.util.Comparator;

public class Resultado {
	private final int valorBanca = 2;
	private final int valorEvento = 3;
	private final int valorAreaAtuacao = 4;
	private final int valorAreaConhecimento = 5;
	private final int valorSubArea = 6;
	private final int valorEspecialidade = 7;
	private final int valorOrientacao = 8;
	private final int valorProjetoPesquisa = 9;
	private final int valorProjetoEvento = 10;
	private int contBanca;
	private int contEvento;
	private int contAreaAtuacao;
	private int contAreaConhecimento;
	private int contSubArea;
	private int contEspecialidade;
	private int contOrientacao;
	private int contProjetoPesquisa;
	private int contProjetoEvento;
	private ArrayList<Pessoa> listParticipante;

	public Resultado() {
		super();
		this.listParticipante = new ArrayList<>();
	}

	public int getTotal() {
		int result = 0;
		result = +(this.contBanca * this.valorBanca);
		result = +(this.contEvento * this.valorEvento);
		result = +(this.contAreaAtuacao * this.valorAreaAtuacao);
		result = +(this.contAreaConhecimento * this.valorAreaConhecimento);
		result = +(this.contSubArea * this.valorSubArea);
		result = +(this.contEspecialidade * this.valorEspecialidade);
		result = +(this.contOrientacao * this.valorOrientacao);
		result = +(this.contProjetoPesquisa * this.valorProjetoPesquisa);
		result = +(this.contProjetoEvento * this.valorProjetoEvento);
		return result;
	}

	public ArrayList<Pessoa> getListParticipante() {
		return this.listParticipante;
	}

	public void setListParticipante(ArrayList<Pessoa> listParticipante) {
		this.listParticipante = listParticipante;
	}

	public void AddListParticipante(Pessoa listParticipante) {
		this.listParticipante.add(listParticipante);
		this.listParticipante.sort(Comparator.comparing(u -> u.getNome()));

	}

	public int getContBanca() {
		return this.contBanca;
	}

	public void setContBanca(int contBanca) {
		this.contBanca = contBanca;
	}

	public int getContEvento() {
		return this.contEvento;
	}

	public void setContEvento(int contEvento) {
		this.contEvento = contEvento;
	}

	public int getContAreaAtuacao() {
		return this.contAreaAtuacao;
	}

	public void setContAreaAtuacao(int contAreaAtuacao) {
		this.contAreaAtuacao = contAreaAtuacao;
	}

	public int getContAreaConhecimento() {
		return this.contAreaConhecimento;
	}

	public void setContAreaConhecimento(int contAreaConhecimento) {
		this.contAreaConhecimento = contAreaConhecimento;
	}

	public int getContSubArea() {
		return this.contSubArea;
	}

	public void setContSubArea(int contSubArea) {
		this.contSubArea = contSubArea;
	}

	public int getContEspecialidade() {
		return this.contEspecialidade;
	}

	public void setContEspecialidade(int contEspecialidade) {
		this.contEspecialidade = contEspecialidade;
	}

	public int getContOrientacao() {
		return this.contOrientacao;
	}

	public void setContOrientacao(int contOrientacao) {
		this.contOrientacao = contOrientacao;
	}

	public int getContProjetoPesquisa() {
		return this.contProjetoPesquisa;
	}

	public void setContProjetoPesquisa(int contProjetoPesquisa) {
		this.contProjetoPesquisa = contProjetoPesquisa;
	}

	public int getContProjetoEvento() {
		return this.contProjetoEvento;
	}

	public void setContProjetoEvento(int contProjetoEvento) {
		this.contProjetoEvento = contProjetoEvento;
	}
}
