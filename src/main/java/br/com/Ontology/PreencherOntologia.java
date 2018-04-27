package br.com.Ontology;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.xpath.XPathExpressionException;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import br.com.converter.ConverterFile;
import br.com.converter.TratamentoDeDados;
import br.com.modelo.OntoPessoa;

@Service
public class PreencherOntologia {
	@Autowired
	OntologyDAO ontologyDAO;
	String NomeCurriculo;
	Document xmlfile;
	public TratamentoDeDados tratamentoDeDados;

	public void inserirFile(File xmlfile) {
		this.xmlfile = ConverterFile.ConverterFileToDocument(xmlfile);
		this.tratamentoDeDados = new TratamentoDeDados();
		try {
			inserirDadosGerais();
		} catch (XPathExpressionException | OWLOntologyStorageException | FileNotFoundException
				| OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void inserirDadosGerais()
			throws XPathExpressionException, OWLOntologyStorageException, FileNotFoundException,
			OWLOntologyCreationException {
		BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(this.xmlfile);
		OntoPessoa pessoa = new OntoPessoa(
				this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
				this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
				this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()),
				this.tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCitacao()));
		preencherXMLtoOnto.buscarXML(pessoa);
		// System.out.println(pessoa.toString());
		this.ontologyDAO.preencherOntoPessoa(pessoa);

	}

}
