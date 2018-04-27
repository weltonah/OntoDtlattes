package br.com.modelo;

/**
 * 
 */
public class AreaConhecimento {

	private String areaConhecimento;

	private String subAreaConhecimento;

	private String nomeEspecialidade;

	public AreaConhecimento(String areaConhecimento, String subAreaConhecimento, String nomeEspecialidade) {
		super();
		this.areaConhecimento = areaConhecimento;
		this.subAreaConhecimento = subAreaConhecimento;
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public String getAreaConhecimento() {
		return this.areaConhecimento;
	}

	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String getSubAreaConhecimento() {
		return this.subAreaConhecimento;
	}

	public void setSubAreaConhecimento(String subAreaConhecimento) {
		this.subAreaConhecimento = subAreaConhecimento;
	}

	public String getNomeEspecialidade() {
		return this.nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	@Override
	public String toString() {
		return "AreaConhecimento [areaConhecimento=" + this.areaConhecimento + ", subAreaConhecimento="
				+ this.subAreaConhecimento + ", nomeEspecialidade=" + this.nomeEspecialidade + "]";
	}

}