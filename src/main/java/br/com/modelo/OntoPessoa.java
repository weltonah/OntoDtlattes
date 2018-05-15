package br.com.modelo;

import java.util.ArrayList;

public class OntoPessoa {
	private String NomeCompleto;
	private String Citacao;
	private String IdLattes;
	private String Data;
	private int cont = 0;
	private ArrayList<AreaConhecimento> ListOntoAreaAtuacao;
	private ArrayList<TrabalhoEvento> ListOntoTrabalhoEvento;
	private ArrayList<OntoClass> ListOntoEvento;
	private ArrayList<OntoClass> ListOntoFormacao;
	private ArrayList<OntoClass> ListOntoOrientacao;
	private ArrayList<OntoClass> ListOntoProducao;
	private ArrayList<OntoClass> ListOntoProjetoPesquisa;
	private ArrayList<OntoClass> ListOntoBanca;

	public OntoPessoa(String nomeCompleto, String idLattes, String data, String citacao) {
		super();
		this.NomeCompleto = nomeCompleto;
		this.IdLattes = idLattes;
		this.Data = data;
		this.Citacao = citacao;
		this.ListOntoAreaAtuacao = new ArrayList<>();
		this.ListOntoTrabalhoEvento = new ArrayList<>();
		this.ListOntoEvento = new ArrayList<>();
		this.ListOntoFormacao = new ArrayList<>();
		this.ListOntoProducao = new ArrayList<>();
		this.ListOntoProjetoPesquisa = new ArrayList<>();
		this.ListOntoBanca = new ArrayList<>();
		this.ListOntoOrientacao = new ArrayList<>();
	}

	public void Copiar(OntoPessoa pessoa) {
		if (this.getCitacaoList().size() < pessoa.getCitacaoList().size()) {
			if (this.IdLattes.length() <= pessoa.getIdLattes().length())
			this.Citacao = pessoa.getCitacao();
		}
		this.cont = this.cont + pessoa.getCont();

		this.IdLattes = (this.IdLattes.contentEquals("") || this.IdLattes == null) ? pessoa.getIdLattes()
				: this.IdLattes;
		this.ListOntoAreaAtuacao.addAll(pessoa.getListOntoAreaAtuacao());
		this.ListOntoTrabalhoEvento.addAll(pessoa.getListOntoTrabalhoEvento());
		this.ListOntoEvento.addAll(pessoa.getListOntoEvento());
		this.ListOntoFormacao.addAll(pessoa.getListOntoFormacao());
		this.ListOntoOrientacao.addAll(pessoa.getListOntoOrientacao());
		this.ListOntoProducao.addAll(pessoa.getListOntoProducao());
		this.ListOntoProjetoPesquisa.addAll(pessoa.getListOntoProjetoPesquisa());
		this.ListOntoBanca.addAll(pessoa.getListOntoBanca());
	}

	public void cont() {
		this.cont++;
	}

	public String getNomeCompleto() {
		return this.NomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.NomeCompleto = nomeCompleto;
	}

	public String getIdLattes() {
		return this.IdLattes;
	}

	public void setIdLattes(String idLattes) {
		this.IdLattes = idLattes;
	}

	public String getData() {
		return this.Data;
	}

	public void setData(String data) {
		this.Data = data;
	}

	public ArrayList<AreaConhecimento> getListOntoAreaAtuacao() {
		return this.ListOntoAreaAtuacao;
	}

	public void setListOntoAreaAtuacao(ArrayList<AreaConhecimento> listOntoAreaAtuacao) {
		this.ListOntoAreaAtuacao = listOntoAreaAtuacao;
	}

	public ArrayList<OntoClass> getListOntoEvento() {
		return this.ListOntoEvento;
	}

	public void setListOntoEvento(ArrayList<OntoClass> listOntoEvento) {
		this.ListOntoEvento = listOntoEvento;
	}

	public ArrayList<OntoClass> getListOntoFormacao() {
		return this.ListOntoFormacao;
	}

	public void setListOntoFormacao(ArrayList<OntoClass> listOntoFormacao) {
		this.ListOntoFormacao = listOntoFormacao;
	}

	public void AddListOntoFormacao(OntoClass aux) {
		this.ListOntoFormacao.add(aux);
	}

	public ArrayList<OntoClass> getListOntoProducao() {
		return this.ListOntoProducao;
	}

	public void setListOntoProducao(ArrayList<OntoClass> listOntoProducao) {
		this.ListOntoProducao = listOntoProducao;
	}

	public ArrayList<OntoClass> getListOntoProjetoPesquisa() {
		return this.ListOntoProjetoPesquisa;
	}

	public void setListOntoProjetoPesquisa(ArrayList<OntoClass> listOntoProjetoPesquisa) {
		this.ListOntoProjetoPesquisa = listOntoProjetoPesquisa;
	}

	public void AddListOntoProjetoPesquisa(OntoClass listOntoProjetoPesquisa) {
		this.ListOntoProjetoPesquisa.add(listOntoProjetoPesquisa);
	}

	public ArrayList<OntoClass> getListOntoBanca() {
		return this.ListOntoBanca;
	}

	public void setListOntoBanca(ArrayList<OntoClass> listOntoBanca) {
		this.ListOntoBanca = listOntoBanca;
	}

	public void AddListOntoBanca(OntoClass aux) {
		this.ListOntoBanca.add(aux);
	}

	@Override
	public String toString() {
		return "OntoPessoa [NomeCompleto=" + this.NomeCompleto + "\n, IdLattes=" + this.IdLattes + "\n, Data="
				+ this.Data + "\n, ListOntoAreaAtuacao=" + this.ListOntoAreaAtuacao.toString() + "\n, ListOntoEvento="
				+ this.ListOntoEvento.toString() + "\n, ListOntoFormacao=" + this.ListOntoFormacao.toString()
				+ "\n, ListOntoProducao=" + this.ListOntoProducao.toString() + "\n, ListOntoProjetoPesquisa="
				+ this.ListOntoProjetoPesquisa.toString() + "]";
	}

	public ArrayList<OntoClass> getListOntoOrgEvento() {
		return this.ListOntoEvento;
	}

	public void setListOntoOrgEvento(ArrayList<OntoClass> listOntoOrgEvento) {
		this.ListOntoEvento.addAll(listOntoOrgEvento);
	}

	public ArrayList<TrabalhoEvento> getListOntoTrabalhoEvento() {
		return this.ListOntoTrabalhoEvento;
	}

	public void setListOntoTrabalhoEvento(ArrayList<TrabalhoEvento> listOntoTrabalhoEvento) {
		this.ListOntoTrabalhoEvento = listOntoTrabalhoEvento;
	}

	public String getCitacao() {
		return this.Citacao;
	}

	public ArrayList<String> getCitacaoList() {
		String[] textoSeparado = this.Citacao.split(";");
		ArrayList<String> lista = new ArrayList<>();
		for (int i = 0; i < textoSeparado.length; i++)
			lista.add(textoSeparado[i]);

		return lista;
	}

	public void setCitacao(String citacao) {
		this.Citacao = citacao;
	}

	public ArrayList<OntoClass> getListOntoOrientacao() {
		return this.ListOntoOrientacao;
	}

	public void setListOntoOrientacao(ArrayList<OntoClass> listOntoOrientacao) {
		this.ListOntoOrientacao = listOntoOrientacao;
	}

	public void AddListOntoOrientacao(OntoClass aux) {
		this.ListOntoOrientacao.add(aux);
	}

	public int getCont() {
		return this.cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

}
