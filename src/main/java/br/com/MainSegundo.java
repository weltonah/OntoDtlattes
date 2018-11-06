package br.com;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

import br.com.DAO.BuscarXmlToPessoa;
import br.com.Grafo.Grafo;
import br.com.Grafo.GrafoController;
import br.com.converter.ConverterFile;
import br.com.converter.TratamentoDeDados;
import br.com.modelo.OntoPessoa;

public class MainSegundo {
	public static void main(String[] args) throws Exception {
		String nomeFile = "Completo.owl";
		// OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		int tam;
		if (args.length == 0)
			tam = 44;
		else
			tam = Integer.parseInt(args[0]);

		System.out.println(tam);
		ArrayList<String> Namexml;
		// ontoDao = new OntologyDAO(nomeFile);
		tratamentoDeDados = new TratamentoDeDados();

		Namexml = ListaDeArquivos(tam);
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();

			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCitacao()));
			System.out.println(pessoa.getIdLattes() + " " + pessoa.getNomeCompleto() + " &&& "
					+ pessoa.getCitacaoList().toString());
			preencherXMLtoOnto.buscarXML(pessoa);
			listaPessoa.add(pessoa);
		}

		// tratamentoDeDados.tratarBancaExterna(listaPessoa);

		System.out.println("tamanho pessoas antes da expansao " + listaPessoa.size());
		tratamentoDeDados.ExpansaoMembros(listaPessoa);
		System.out.println("tamanho pessoas depois da expansao " + listaPessoa.size());

		int aux;
		do {
			aux = listaPessoa.size();
			tratamentoDeDados.JuncaoMembros(listaPessoa);
			System.out.println("tamanho pessoas depois da juncao de membros " + listaPessoa.size());
		} while (aux != listaPessoa.size());

		System.out.println("antes " + listaPessoa.size());
		tratamentoDeDados.eliminarIndividuosDesnecessarios(listaPessoa);
		System.out.println("depois " + listaPessoa.size());
		int totalcont = 0;
		int exemplo = 0;
		for (int i = 0; i < listaPessoa.size(); i++)
			totalcont = totalcont + listaPessoa.get(i).getListOntoEvento().size();

		System.out.println("evento " + totalcont);
		totalcont = 0;
		for (int i = 0; i < listaPessoa.size(); i++) {
			totalcont = totalcont + listaPessoa.get(i).getListOntoBanca().size();
			// if (listaPessoa.get(i).getListOntoBanca().size() > 50)
			// System.out.println(listaPessoa.get(i).getNomeCompleto());
		}

		Map<String, String> MapNome = tratamentoDeDados.CriarMap(listaPessoa);

		// ontoDao.Inferir(new FunctionalSyntaxDocumentFormat());

		GrafoController graf = new GrafoController(nomeFile);
		graf.inferir();
		Grafo result = graf.BuscarResultado();
		result.imprimirResultado(MapNome);
		System.out.println("Fim");
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
