package br.com.DAO;

import org.w3c.dom.Document;

import br.com.Ontology.OntologyDAO;
import br.com.converter.TratamentoDeDados;

public class PreencherOntologia {

	OntologyDAO ontologyDAO;
	String NomeCurriculo;
	Document xmlfile;
	public TratamentoDeDados tratamentoDeDados;

	// public void inserirFile(File xmlfile) {
	// this.xmlfile = ConverterFile.ConverterFileToDocument(xmlfile);
	// this.tratamentoDeDados = new TratamentoDeDados();
	// try {
	// inserirDadosGerais();
	// } catch (XPathExpressionException | OWLOntologyStorageException |
	// FileNotFoundException
	// | OWLOntologyCreationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// private void inserirDadosGerais()
	// throws XPathExpressionException, OWLOntologyStorageException,
	// FileNotFoundException,
	// OWLOntologyCreationException {
	// BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(this.xmlfile);
	// OntoPessoa pessoa = new OntoPessoa(
	// this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
	// this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
	// this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()),
	// this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCitacao()));
	// preencherXMLtoOnto.buscarXML(pessoa);
	// // System.out.println(pessoa.toString());
	// this.ontologyDAO.preencherOntoPessoa(pessoa);
	//
	// }

}
