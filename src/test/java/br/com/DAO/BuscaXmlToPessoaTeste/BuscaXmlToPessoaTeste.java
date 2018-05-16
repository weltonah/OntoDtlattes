package br.com.DAO.BuscaXmlToPessoaTeste;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import org.w3c.dom.Document;

import br.com.DAO.BuscarXmlToPessoa;
import br.com.converter.ConverterFile;
import br.com.converter.TratamentoDeDados;

public class BuscaXmlToPessoaTeste {

	private TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
	@Test
	public void ArtigoCompletoAceito() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());

		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals("Alessandreia Marta de Oliveira", preencherXMLtoOnto.NomeCompleto());
		assertEquals("7907503278098258", preencherXMLtoOnto.IDLattes());
		assertEquals("21/05/2017 ", preencherXMLtoOnto.UltimaAtualizacao());
		assertEquals("OLIVEIRA, Alessandreia Marta", preencherXMLtoOnto.NomeCitacao());
	}

	@Test
	public void AreaAtuacao() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());

		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(this.tratamentoDeDados.corrigirString("CIENCIAS_EXATAS_E_DA_TERRA"),
				preencherXMLtoOnto.listOntoAreaAtuacao().get(0).getGrandeArea());
		assertEquals(this.tratamentoDeDados.corrigirString("Ciência da Computação"),
				preencherXMLtoOnto.listOntoAreaAtuacao().get(0).getAreaConhecimento());
		assertEquals(this.tratamentoDeDados.corrigirString("Metodologia e Técnicas da Computação"),
				preencherXMLtoOnto.listOntoAreaAtuacao().get(0).getSubAreaConhecimento());
		assertEquals(this.tratamentoDeDados.corrigirString("Banco de Dados"),
				preencherXMLtoOnto.listOntoAreaAtuacao().get(0).getNomeEspecialidade());
	}



	@Test
	public void TrabalhoEvento() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());

		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(
				this.tratamentoDeDados.corrigirString(
						"Persistência em Software Orientado a Objetos Abordagens Utilizando Frameworks OpenSource"),
				preencherXMLtoOnto.listOntoTrabalhoEvento().get(0).getTituloTrabalho());
		assertEquals(this.tratamentoDeDados.corrigirString("FESTSOL"),
				preencherXMLtoOnto.listOntoTrabalhoEvento().get(0).getEvento().getTitulo());
		assertEquals(2005,
				preencherXMLtoOnto.listOntoTrabalhoEvento().get(0).getEvento().getAno());
		assertEquals(10, preencherXMLtoOnto.listOntoTrabalhoEvento().size());
	}

	@Test
	public void Eventos() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());
		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(
				this.tratamentoDeDados.corrigirString(
						"VII Seminário de Inciação Científica"),
				preencherXMLtoOnto.listOntoEvento().get(0).getTitulo());
		assertEquals(2005,
				preencherXMLtoOnto.listOntoEvento().get(0).getAno());
		assertEquals(12, preencherXMLtoOnto.listOntoEvento().size());
	}

	@Test
	public void OrgEvento() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());
		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(this.tratamentoDeDados.corrigirString("I Maratona de Programação da FMG"),
				preencherXMLtoOnto.listOrganizacaoEvento().get(0).getTitulo());
		assertEquals(2004, preencherXMLtoOnto.listOrganizacaoEvento().get(0).getAno());
		assertEquals(11, preencherXMLtoOnto.listOrganizacaoEvento().size());
	}


	@Test
	public void formacao() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());
		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(
				this.tratamentoDeDados.corrigirString("GOS: SERVIÇOS DE ONTOLOGIA NA INTEGRAÇÃO DE BASES DE DADOS"),
				preencherXMLtoOnto.listOntoFormacao().get(0).getTitulo());
		assertEquals(this.tratamentoDeDados.corrigirString("Marta Lima de Queirós Mattoso"),
				preencherXMLtoOnto.listOntoFormacao().get(0).getListAutores().get(0).getNome());
		assertEquals(2, preencherXMLtoOnto.listOntoFormacao().size());
	}

	@Test
	public void ProjetoPesquisa() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());
		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(this.tratamentoDeDados.corrigirString("EVOLUÇÃO SEMÂNTICA DE VARIANTES DE DOCUMENTOS XML"),
				preencherXMLtoOnto.listOntoProjetoPesquisa().get(0).getTitulo());
		assertEquals(0, preencherXMLtoOnto.listOntoProjetoPesquisa().get(0).getAno());
		assertEquals(1, preencherXMLtoOnto.listOntoProjetoPesquisa().size());
	}

	@Test
	public void Banca() throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File owlfile = new File(classLoader.getResource("static/testFile/" + "Alessandreiacurriculo.xml").getFile());
		Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
		assertEquals(this.tratamentoDeDados.corrigirString("Um Aporte de Web Mining para Web Semântica"),
				preencherXMLtoOnto.listOntoBanca().get(0).getTitulo());
		assertEquals(this.tratamentoDeDados.corrigirString("Tarcísio de Souza Lima"),
				preencherXMLtoOnto.listOntoBanca().get(0).getListAutores().get(0).getNome());
		assertEquals(90, preencherXMLtoOnto.listOntoBanca().size());
	}
}
