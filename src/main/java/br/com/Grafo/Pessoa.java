package br.com.Grafo;

import java.util.ArrayList;

public class Pessoa {
	private String nome;
	private ArrayList<Banca> listParticipouBanca;
	private ArrayList<Evento> listParticipouEvento;


	public Pessoa(String nome) {
		super();
		this.nome = nome;
		this.listParticipouBanca = new ArrayList<>();
		this.listParticipouEvento = new ArrayList<>();
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

}
