package br.com.Grafo;

import java.util.ArrayList;

public class Pessoa {
	private String nome;
	private ArrayList<Banca> listParticipouBanca;
	private ArrayList<Evento> listParticipouEvento;
	private ArrayList<AreaAtuacao> listParticipouAreaAtuacao;
	private ArrayList<AreaConhecimento> listParticipouAreaConhecimento;
	private ArrayList<SubArea> listParticipouSubArea;
	private ArrayList<Especialidade> listParticipouEspecialidade;

	public Pessoa(String nome) {
		super();
		this.nome = nome;
		this.listParticipouBanca = new ArrayList<>();
		this.listParticipouEvento = new ArrayList<>();
		this.listParticipouAreaAtuacao = new ArrayList<>();
		this.listParticipouAreaConhecimento = new ArrayList<>();
		this.listParticipouSubArea = new ArrayList<>();
		this.listParticipouEspecialidade = new ArrayList<>();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Banca> getListParticipouBanca() {
		return this.listParticipouBanca;
	}

	public void setListParticipouBanca(ArrayList<Banca> listParticipouBanca) {
		this.listParticipouBanca = listParticipouBanca;
	}

	public void AddListParticipouBanca(Banca listParticipouBanca) {
		this.listParticipouBanca.add(listParticipouBanca);
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
