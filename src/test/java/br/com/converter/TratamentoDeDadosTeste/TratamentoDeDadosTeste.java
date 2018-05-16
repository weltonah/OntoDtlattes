package br.com.converter.TratamentoDeDadosTeste;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import br.com.converter.TratamentoDeDados;
import br.com.modelo.OntoClass;
import br.com.modelo.OntoParceiro;
import br.com.modelo.OntoPessoa;

public class TratamentoDeDadosTeste {

	private TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();


	@Test
	public void ExpansaoMenbrosBanca() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("nome 1", "", "", "");
		ArrayList<OntoParceiro> list = new ArrayList<>();
		list.add(new OntoParceiro("nome 2", ""));
		OntoClass bancaAux = new OntoClass("Banca 1", "", list);
		pessoa.AddListOntoBanca(bancaAux);
		list = new ArrayList<>();
		list.add(new OntoParceiro("nome 3", ""));
		bancaAux = new OntoClass("Banca 2", "", list);
		pessoa.AddListOntoBanca(bancaAux);
		listaPessoa.add(pessoa);
		this.tratamentoDeDados.ExpansaoMembros(listaPessoa);
		assertEquals(3, listaPessoa.size());
		assertEquals(0, listaPessoa.get(0).getListOntoBanca().get(0).getListAutores().size());
		assertEquals(0, listaPessoa.get(0).getListOntoBanca().get(1).getListAutores().size());
		assertEquals("nome 2", listaPessoa.get(1).getNomeCompleto());
		assertEquals("Banca 1", listaPessoa.get(1).getListOntoBanca().get(0).getTitulo());
		assertEquals("nome 3", listaPessoa.get(2).getNomeCompleto());
		assertEquals("Banca 2", listaPessoa.get(2).getListOntoBanca().get(0).getTitulo());
	}

	@Test
	public void ExpansaoMenbrosFormacao() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("nome 1", "", "", "");
		ArrayList<OntoParceiro> list = new ArrayList<>();
		list.add(new OntoParceiro("nome 2", ""));
		OntoClass bancaAux = new OntoClass("formacao 1", "", list);
		pessoa.AddListOntoFormacao(bancaAux);
		list = new ArrayList<>();
		list.add(new OntoParceiro("nome 3", ""));
		bancaAux = new OntoClass("formacao 2", "", list);
		pessoa.AddListOntoFormacao(bancaAux);
		listaPessoa.add(pessoa);
		this.tratamentoDeDados.ExpansaoMembros(listaPessoa);
		assertEquals(3, listaPessoa.size());
		assertEquals(0, listaPessoa.get(0).getListOntoFormacao().get(0).getListAutores().size());
		assertEquals(0, listaPessoa.get(0).getListOntoFormacao().get(1).getListAutores().size());
		assertEquals("nome 2", listaPessoa.get(1).getNomeCompleto());
		assertEquals("formacao 1", listaPessoa.get(1).getListOntoFormacao().get(0).getTitulo());
		assertEquals("nome 3", listaPessoa.get(2).getNomeCompleto());
		assertEquals("formacao 2", listaPessoa.get(2).getListOntoFormacao().get(0).getTitulo());
	}

	@Test
	public void ExpansaoMenbrosProjetoPesquisa() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("nome 1", "", "", "");
		ArrayList<OntoParceiro> list = new ArrayList<>();
		list.add(new OntoParceiro("nome 2", ""));
		OntoClass bancaAux = new OntoClass("pesquisa 1", "", list);
		pessoa.AddListOntoProjetoPesquisa(bancaAux);
		list = new ArrayList<>();
		list.add(new OntoParceiro("nome 3", ""));
		bancaAux = new OntoClass("pesquisa 2", "", list);
		pessoa.AddListOntoProjetoPesquisa(bancaAux);
		listaPessoa.add(pessoa);
		this.tratamentoDeDados.ExpansaoMembros(listaPessoa);
		assertEquals(3, listaPessoa.size());
		assertEquals(0, listaPessoa.get(0).getListOntoProjetoPesquisa().get(0).getListAutores().size());
		assertEquals(0, listaPessoa.get(0).getListOntoProjetoPesquisa().get(1).getListAutores().size());
		assertEquals("nome 2", listaPessoa.get(1).getNomeCompleto());
		assertEquals("pesquisa 1", listaPessoa.get(1).getListOntoProjetoPesquisa().get(0).getTitulo());
		assertEquals("nome 3", listaPessoa.get(2).getNomeCompleto());
		assertEquals("pesquisa 2", listaPessoa.get(2).getListOntoProjetoPesquisa().get(0).getTitulo());
	}

	@Test
	public void corrigirStringTeste() throws Exception {
		String aux = "ola mundo";
		String aux2 = "ólâ mùñdó";
		String aux3 = "o+l*&$#@!a m/un]d'o";
		String aux4 = "o,la\n\t\r mundo";
		String aux5 = "OlA mUnDo";
		String aux6 = "Ola         mundo";
		assertEquals("ola_mundo", this.tratamentoDeDados.corrigirString(aux));
		assertEquals("ola_mundo", this.tratamentoDeDados.corrigirString(aux2));
		assertEquals("ola_mundo", this.tratamentoDeDados.corrigirString(aux3));
		assertEquals("ola_mundo", this.tratamentoDeDados.corrigirString(aux4));
		assertEquals("ola_mundo", this.tratamentoDeDados.corrigirString(aux5));
		assertEquals("ola_mundo", this.tratamentoDeDados.corrigirString(aux6));
	}

	@Test
	public void BaterNomeComIdLattes() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("joaquim", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "887", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "8", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "8855", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "", "", "");
		listaPessoa.add(pessoa);
		assertEquals(6, listaPessoa.size());
		this.tratamentoDeDados.BaterNomeComIdLattes(listaPessoa);
		assertEquals(5, listaPessoa.size());
	}

	@Test
	public void BaterNomeComNome() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("joaquim", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "887", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("pedro", "8", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "8855", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquimm", "", "", "");
		listaPessoa.add(pessoa);
		assertEquals(7, listaPessoa.size());
		this.tratamentoDeDados.BaterNomeComNome(listaPessoa);
		assertEquals(3, listaPessoa.size());
		assertEquals("885", listaPessoa.get(0).getIdLattes());
	}


	@Test
	public void teste() throws Exception {
		String aux = "http://www.datalattes.com/ontologies/datalattes.owl#0846763436344325";
		System.out.println(aux.substring(aux.indexOf("#") + 1));

	}

	@Test
	public void BaterListCitacaoComNome() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("joaquim", "", "", "j. c. alberto;j. cal. alberto");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("j. cal. alberto", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("nome 1", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("pedro", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("j. cal. alberto", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("j. c. alberto", "85", "", "j. c. alberto;j. cal. alberto; j. b. cal.");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("nome 2", "", "", "");
		listaPessoa.add(pessoa);
		assertEquals(7, listaPessoa.size());
		this.tratamentoDeDados.BaterListCitacaoComNome(listaPessoa);
		assertEquals(4, listaPessoa.size());
		assertEquals("joaquim", listaPessoa.get(0).getNomeCompleto());
		assertEquals("j. c. alberto;j. cal. alberto; j. b. cal.", listaPessoa.get(0).getCitacao());
	}


	@Test
	public void BaterNomeContidoEmOutro() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("joaquim ", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim barbosa", "88787", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("tiago", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("pedro", "8", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("samara", "8855", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim barbosa ferreira", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquimm", "", "", "");
		listaPessoa.add(pessoa);
		assertEquals(7, listaPessoa.size());
		this.tratamentoDeDados.BaterNomeContidoEmOutro(listaPessoa);
		assertEquals(5, listaPessoa.size());
		assertEquals("joaquim barbosa ferreira", listaPessoa.get(0).getNomeCompleto());
		assertEquals("88787", listaPessoa.get(0).getIdLattes());
	}


	@Test
	public void BaterCitacaoPorCitacao() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("joaquim", "", "", "j. c. alberto;j. cal. alberto");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquem", "857", "", "j. cal. alberto");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("nome 1", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("pedro", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("samara", "", "", "j. cal. alberto");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim barbosa", "", "", "j. c. alberto;j. cal. alberto;j. b. cal.");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("nome 2", "", "", "j. b. cal.");
		listaPessoa.add(pessoa);
		assertEquals(7, listaPessoa.size());
		this.tratamentoDeDados.BaterCitacaoPorCitacao(listaPessoa);
		assertEquals(3, listaPessoa.size());
		assertEquals("joaquim barbosa", listaPessoa.get(0).getNomeCompleto());
		assertEquals("857", listaPessoa.get(0).getIdLattes());
		assertEquals("j. c. alberto;j. cal. alberto;j. b. cal.", listaPessoa.get(0).getCitacao());
	}

	@Test
	public void BaterNomeComNomeAlgoritmoNGram() throws Exception {
		this.tratamentoDeDados = new TratamentoDeDados();
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		OntoPessoa pessoa = new OntoPessoa("joaquim ", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim barbosa ferrrera", "88787", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("tiago augusto", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("thiago augusto", "885", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("pedro", "8", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("samara", "8855", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquim barbosa ferreira", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquimm", "", "", "");
		listaPessoa.add(pessoa);
		pessoa = new OntoPessoa("joaquaimm b", "", "", "");
		listaPessoa.add(pessoa);
		assertEquals(9, listaPessoa.size());
		this.tratamentoDeDados.BaterNomeComNomeAlgoritmoNGram(listaPessoa);
		assertEquals(6, listaPessoa.size());
	}

	public static ArrayList<String> ListaDeArquivos(int tam) {
		// Tam max 44
		ArrayList<String> Namexml = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		Namexml.add("Alessandreiacurriculo.xml");
		Namexml.add("Alexcurriculo.xml");
		Namexml.add("AndreLuizcurriculo.xml");
		Namexml.add("BernardoMartinscurriculo.xml");
		Namexml.add("Carloscurriculo.xml");
		Namexml.add("Cirocurriculo.xml");
		Namexml.add("Edelbertocurriculo.xml");
		Namexml.add("EdmarOliveiracurriculo.xml");
		Namexml.add("EduardoBarrelecurriculo.xml");
		Namexml.add("EduardoPaganicurriculo.xml");
		Namexml.add("FabricioMartinscurriculo.xml");
		Namexml.add("Fernandacurriculo.xml");
		Namexml.add("Gleiphcurriculo.xml");
		Namexml.add("Hedercurriculo.xml");
		Namexml.add("Heliocurriculo.xml");
		Namexml.add("IgorKnopcurriculo.xml");
		Namexml.add("Itamarcurriculo.xml");
		Namexml.add("IuryHigorcurriculo.xml");
		Namexml.add("Jairocurriculo.xml");
		Namexml.add("JoseMariacurriculo.xml");
		Namexml.add("LeonardoVieiracurriculo.xml");
		Namexml.add("Liamaracurriculo.xml");
		Namexml.add("Lorenzacurriculo.xml");
		Namexml.add("LucianaBrugiolocurriculo.xml");
		Namexml.add("LucianaCamposcurriculo.xml");
		Namexml.add("LucianoJerezcurriculo.xml");
		Namexml.add("LuizFelipecurriculo.xml");
		Namexml.add("MarceloBernardescurriculo.xml");
		Namexml.add("MarceloCaniatocurriculo.xml");
		Namexml.add("MarceloLoboscocurriculo.xml");
		Namexml.add("MarceloMorenocurriculo.xml");
		Namexml.add("MarcoAntoniocurriculo.xml");
		Namexml.add("MarcosPassinicurriculo.xml");
		Namexml.add("MarioAntoniocurriculo.xml");
		Namexml.add("PriscilaCaprilescurriculo.xml");
		Namexml.add("RafaelAlvescurriculo.xml");
		Namexml.add("RaulFonsecacurriculo.xml");
		Namexml.add("ReginaBragacurriculo.xml");
		Namexml.add("RodrigoLuiscurriculo.xml");
		Namexml.add("RodrigoWebercurriculo.xml");
		Namexml.add("SauloMoraescurriculo.xml");
		Namexml.add("Steniocurriculo.xml");
		Namexml.add("Vâniacurriculo.xml");
		Namexml.add("VictorStroelecurriculo.xml");
		Namexml.add("Wagnercurriculo.xml");

		for (int i = 0; i < tam; i++)
			result.add(Namexml.get(i));
		return result;
	}
}
