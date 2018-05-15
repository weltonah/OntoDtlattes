package br.com;

import java.io.File;
import java.util.ArrayList;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;

import br.com.DAO.BuscarXmlToPessoa;
import br.com.Ontology.OntologyDAO;
import br.com.converter.ConverterFile;
import br.com.converter.TratamentoDeDados;
import br.com.modelo.OntoPessoa;

public class MainAnalise {

	public static void main(String[] args) throws Exception {
		executar();
	}

	public static void executar() throws Exception {
		String nomeFile = "Completo.owl";

		OntologyDAO ontoDao = new OntologyDAO(nomeFile);
		TratamentoDeDados tratamentoDeDados = new TratamentoDeDados();
		int tam = 15;
		ArrayList<String> Namexml;
		ontoDao = new OntologyDAO(nomeFile);
		tratamentoDeDados = new TratamentoDeDados();
		Namexml = ListaDeArquivos(tam);
		ArrayList<OntoPessoa> listaPessoa = new ArrayList<>();
		for (String string : Namexml) {
			File owlfile = new ClassPathResource("static/testFile/" + string).getFile();
			// File owlfile = new File(System.getProperty("user.dir") +
			// "src/main/resources/static/testFile/" + string);
			// System.out.println(System.getProperty("user.dir") +
			// "src/main/resources/static/testFile/" + string);
			Document xmlfile = ConverterFile.ConverterFileToDocument(owlfile);
			BuscarXmlToPessoa preencherXMLtoOnto = new BuscarXmlToPessoa(xmlfile);
			OntoPessoa pessoa = new OntoPessoa(tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCompleto()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.IDLattes()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.UltimaAtualizacao()),
					tratamentoDeDados.corrigirString(preencherXMLtoOnto.NomeCitacao()));
			System.out.println(pessoa.getIdLattes() + " " + pessoa.getNomeCompleto() + "&&& "
					+ pessoa.getCitacaoList().toString());
			preencherXMLtoOnto.buscarXML(pessoa);
			// pessoa.getListOntoAreaAtuacao()
			// .forEach(u -> System.out.println(u.getGrandeArea() + " ()()" +
			// u.getAreaConhecimento() + " ()() "
			// + u.getSubAreaConhecimento() + " ()() " + u.getSubAreaConhecimento()));
			listaPessoa.add(pessoa);
		}
		System.out.println("tamanho pessoas antes da expansao " + listaPessoa.size());
		tratamentoDeDados.ExpansaoMembros(listaPessoa);
		System.out.println("tamanho pessoas depois da expansao " + listaPessoa.size());
		tratamentoDeDados.JuncaoMembros(listaPessoa);
		System.out.println("tamanho pessoas depois da juncao de membros " + listaPessoa.size());

		tratamentoDeDados.tratarEventos(listaPessoa);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		ontoDao.preencherOnto(listaPessoa);
		System.out.println("Depois");
		// ontoDao.saveOntologyDAO(new FunctionalSyntaxDocumentFormat());
		System.out.println("Fim");

		// criarArquivoResult(nomeFile);

		// listaPessoa.forEach(u -> System.out.println(u.getNomeCompleto() + " " +
		// u.getIdLattes() + " @@@ "
		// + u.getCitacaoList().toString() + " cont: " + u.getCont()));
		// listaPessoa.stream().filter(p -> p.getCont() >= 0).forEach(u ->
		// System.out.println(u.getNomeCompleto() + " "
		// + u.getIdLattes() + " @@@ " + u.getCitacaoList().toString() + " cont: " +
		// u.getCont()));

		// listaPessoa.forEach(u -> System.out.println(u.getNomeCompleto() + " " +
		// u.getIdLattes() + "\n @@@ "
		// + u.getCitacaoList().toString() + " cont: " + u.getCont() + "\n| " +
		// u.getListOntoAreaAtuacao().size()
		// + "| " + u.getListOntoBanca().size() + "| " + u.getListOntoEvento().size() +
		// "| "
		// + u.getListOntoFormacao().size() + "| " + u.getListOntoOrgEvento().size() +
		// "| "
		// + u.getListOntoOrientacao().size() + "| " + u.getListOntoProducao().size() +
		// "| "
		// + u.getListOntoProjetoPesquisa().size() + "| " +
		// u.getListOntoTrabalhoEvento().size()));
		// listaPessoa.stream().filter(u ->
		// u.getNomeCompleto().contentEquals("2487554612123446"))
		// .forEach(u -> System.out.println(u.getIdLattes() + "" + u.getNomeCompleto() +
		// " " + u.getCont()));

	}

	public static void criarArquivoResult(String nomeArq) throws OWLOntologyCreationException {
		File file = new File(System.getProperty("user.dir") + "/" + nomeArq);
		IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
		OWLDataFactory factory = manager.getOWLDataFactory();
		OWLObjectProperty obj = factory.getOWLObjectProperty(DATALATTESIRI + "#", "relacaoEvento");

		ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get().getIRI()
						.getFragment().contains("Pessoa"))
				.filter(u -> ontology.objectPropertyAssertionAxioms(u).anyMatch(
						i -> i.signature().findFirst().get().getIRI().getFragment().contains("relacaoEvento")))
				.forEach(p -> {
					System.out.println("%%%%%%%$%$%$%$%" + p.getIRI());
					ontology.objectPropertyAssertionAxioms(p).filter(
							w -> w.signature().findFirst().get().getIRI().getFragment().contains("relacaoEvento"))
							.forEach(y -> {
								System.out.println("-------------------------");
								y.signature().forEach(w -> System.out.println(w.getIRI()));
							});
				});
		;
		// ontology.getAxioms(obj).forEach(u ->{
		// u.signature().forEach(t -> System.out.println(t.getIRI()));
		// u
		// System.out.println(u.getAxiomType().getName());
		// });
		// ontology.axioms().forEach(u -> u.signature());
		// ontology.individualsInSignature().filter(u ->
		// u.isOWLNamedIndividual()).forEach(w -> {
		// System.out.println("@@@" + w.getIRI());
		//
		// });

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
