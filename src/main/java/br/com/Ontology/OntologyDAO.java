package br.com.Ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredInverseObjectPropertiesAxiomGenerator;
import org.semanticweb.owlapi.util.InferredObjectPropertyCharacteristicAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredPropertyAssertionGenerator;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.springframework.stereotype.Service;

import br.com.DAO.ReadFile;
import br.com.converter.TratamentoDeDados;
import br.com.modelo.OntoPessoa;
import br.com.modelo.TriplaOwl;

@Service
public class OntologyDAO {

	private File file;
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private IRI DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");

	public OntologyDAO(String NomeArq) throws OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.file = ReadFile.PegarFile(NomeArq);
		this.ontology = this.manager.loadOntologyFromOntologyDocument(this.file);
	}

	public void saveOntologyDAO(OWLDocumentFormat formato)
			throws OWLOntologyStorageException, FileNotFoundException, OWLOntologyCreationException {
		limparDadosDesnecessario();
		this.manager.saveOntology(this.ontology, formato, new FileOutputStream(this.file));
		diferentIndividual();
		this.manager.saveOntology(this.ontology, formato, new FileOutputStream(this.file));
		Inferir();
	}

	public void diferentIndividual() throws OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.ontology = this.manager.loadOntologyFromOntologyDocument(this.file);
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLDifferentIndividualsAxiom diffInd = factory
				.getOWLDifferentIndividualsAxiom(this.ontology.getIndividualsInSignature());
		this.ontology.add(diffInd);
	}

	public void Inferir() throws OWLOntologyStorageException, FileNotFoundException, OWLOntologyCreationException {
		this.manager = OWLManager.createOWLOntologyManager();
		this.ontology = this.manager.loadOntologyFromOntologyDocument(this.file);
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		// Logger LOG = LoggerFactory.getLogger(ReasonTeste.class);
		// ReasonerProgressMonitor progressMonitor = new
		// LoggingReasonerProgressMonitor(LOG, "Loginference");
		// OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		OWLReasonerFactory rf = new ReasonerFactory();
		// OWLReasoner r = rf.createReasoner(this.ontology, config);
		// OWLReasoner r = rf.createNonBufferingReasoner(this.ontology, config);
		OWLReasoner r = rf.createNonBufferingReasoner(this.ontology);
		// r.precomputeInferences(InferenceType.DATA_PROPERTY_ASSERTIONS);

		// OWLObjectProperty obj = factory.getOWLObjectProperty(this.DATALATTESIRI +
		// "#", "relacaoEvento");
		// this.ontology.individualsInSignature().filter(u ->
		// u.isOWLNamedIndividual()).forEach(w -> {
		// System.out.println("@@@" + w.getIRI());
		// r.objectPropertyValues(w, obj).forEach(y -> {
		// System.out.println(y.getIRI());
		// });
		// });

		List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<>();
		// gens.add(new InferredClassAssertionAxiomGenerator());
		gens.add(new InferredInverseObjectPropertiesAxiomGenerator());
		gens.add(new InferredObjectPropertyCharacteristicAxiomGenerator());
		gens.add(new InferredPropertyAssertionGenerator());
		File ont = new File(System.getProperty("user.dir") + "/testeResultado.owl");
		InferredOntologyGenerator iog = new InferredOntologyGenerator(r, gens);
		
		// axiomGenerators.stream().flatMap(g -> generate(df, g))
		
		
		iog.fillOntology(factory, this.ontology);

		System.out.println("ola");
		this.manager.saveOntology(this.ontology, new FunctionalSyntaxDocumentFormat(),
				new FileOutputStream(this.file));
	}


	public void preencherOntoPessoa(OntoPessoa pessoa)
			throws OWLOntologyStorageException, FileNotFoundException, OWLOntologyCreationException {
		preencherDadosGerais(pessoa);
		preencherProjetoPesquisa(pessoa);
		preencherEvento(pessoa);
		preencherFormacao(pessoa);
		preencherBanca(pessoa);
		preencherTrabalhoEvento(pessoa);
	}

	public void preencherOnto(ArrayList<OntoPessoa> listapessoa)
			throws OWLOntologyStorageException, FileNotFoundException, OWLOntologyCreationException {
		for (OntoPessoa pessoa : listapessoa) {
			preencherDadosGerais(pessoa);
			preencherProjetoPesquisa(pessoa);
			preencherEvento(pessoa);
			preencherFormacao(pessoa);
			preencherBanca(pessoa);
			preencherTrabalhoEvento(pessoa);
		}
	}

	public void preencherDadosGerais(OntoPessoa pessoa) {
		String nome = pessoa.getIdLattes();
		// Add dados gerais
		addIndividual(nome, "Pessoa");
		addAtribNoIndivido(nome, pessoa.getIdLattes(), "IdLattes");
		addAtribNoIndivido(nome, pessoa.getNomeCompleto(), "NomeCompleto");
		addAtribNoIndivido(nome, pessoa.getData(), "DataAtualizacao");
	}

	public void preencherProjetoPesquisa(OntoPessoa pessoa) {
		pessoa.getListOntoProjetoPesquisa().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "TrabalhoEmProjetoPesquisa");
			addRelacaoInd(u.getTitulo(), pessoa.getIdLattes(), "ProjetoTeveParticipante");
			u.getListAutores().forEach(t -> {
				String nome = (t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId();
				addIndividual(nome, "Pessoa");
				addRelacaoInd(nome, u.getTitulo(), "TrabalhoEmProjetoPesquisa");
				addRelacaoInd(u.getTitulo(), nome, "ProjetoTeveParticipante");
			});
		});
	}

	public void preencherEvento(OntoPessoa pessoa) {
		pessoa.getListOntoEvento().forEach(u -> {
			addIndividual(u.getTitulo(), u.getTipo());
			addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "participouEvento");
			addRelacaoInd(u.getTitulo(), pessoa.getIdLattes(), "eventoTemParticipante");
		});
	}


	public void preencherFormacao(OntoPessoa pessoa) {
		pessoa.getListOntoFormacao().forEach(u -> {
			// addIndividual(u.getTitulo(), u.getTipo());
			// addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "eFormado");
			u.getListAutores().forEach(t -> {
				String nome = (t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId();
				addIndividual(nome, "Pessoa");
				addRelacaoInd(nome, pessoa.getIdLattes(), "orientou");
				addRelacaoInd(pessoa.getIdLattes(), nome, "foiOrientadoPor");
			});
		});
	}

	public void preencherBanca(OntoPessoa pessoa) {
		pessoa.getListOntoBanca().forEach(u -> {
			// addIndividual(u.getTitulo(), u.getTipo());
			// addRelacaoInd(pessoa.getIdLattes(), u.getTitulo(), "participouDeBanca");
			// addRelacaoInd(u.getTitulo(), pessoa.getIdLattes(), "bancaTemParticipante");
			u.getListAutores().forEach(t -> {
				String nome = (t.getId() == "" || t.getId().isEmpty() || t.getId() == null) ? t.getNome() : t.getId();
				addIndividual(nome, "Pessoa");
				// addRelacaoInd(nome, u.getTitulo(), "participouDeBanca");

				// addRelacaoInd(u.getTitulo(), nome, "bancaTemParticipante");
				if (!pessoa.getIdLattes().contentEquals(nome)) {
					addRelacaoInd(pessoa.getIdLattes(), nome, "relacaoBanca");
					addRelacaoInd(nome, pessoa.getIdLattes(), "relacaoBanca");
				}

			});
		});
	}

	public void preencherTrabalhoEvento(OntoPessoa pessoa) {
		pessoa.getListOntoTrabalhoEvento().forEach(u -> {
			addIndividual(u.getTituloTrabalho(), "Producao");
			addIndividual(u.getEvento().getTitulo(), "Evento");
			addRelacaoInd(pessoa.getIdLattes(), u.getTituloTrabalho(), "apresentouTrabalhoEvento");
			addRelacaoInd(u.getTituloTrabalho(), pessoa.getIdLattes(), "eventoTeveTrabalhoDe");
			addRelacaoInd(u.getTituloTrabalho(), u.getEvento().getTitulo(), "trabalhoEmEvento");
			addRelacaoInd(u.getEvento().getTitulo(), u.getTituloTrabalho(), "eventoTeveTrabalho");
		});
	}




	public void limparDadosDesnecessario() {
		System.out.println(this.ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
						.getIRI().getFragment().contains("Pessoa"))
				.count());
		for (TriplaOwl triplaOwl : TratamentoDeDados.listaPessoaDesnecessario(this.ontology)) {
			removeIndividual(triplaOwl.getSujeito());
		}
		System.out.println(this.ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
						.getIRI().getFragment().contains("Pessoa"))
				.count());
		// System.out.println(this.ontology.individualsInSignature().filter(u ->
		// u.isOWLNamedIndividual())
		// .filter(u ->
		// this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
		// .getIRI().getFragment().contains("Evento"))
		// .count());
		for (TriplaOwl triplaOwl : TratamentoDeDados.listaEventoDesnecessario(this.ontology)) {
			removeIndividual(triplaOwl.getSujeito());
		}
		// System.out.println(this.ontology.individualsInSignature().filter(u ->
		// u.isOWLNamedIndividual())
		// .filter(u ->
		// this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
		// .getIRI().getFragment().contains("Evento"))
		// .count());
	}

	public void removeIndividual(IRI objeto) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLNamedIndividual nome = factory.getOWLNamedIndividual(objeto);
		OWLEntityRemover remover = new OWLEntityRemover(this.ontology);
		remover.visit(nome);
		this.manager.applyChanges(remover.getChanges());
	}

	public void addIndividual(String Nome, String Tipo) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual nome = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", Nome);
		OWLDifferentIndividualsAxiom diffInd = factory.getOWLDifferentIndividualsAxiom(nome);
		OWLClass personClass = factory.getOWLClass(this.DATALATTESIRI + "#", Tipo);
		OWLClassAssertionAxiom da = factory.getOWLClassAssertionAxiom(personClass, nome);
		this.ontology.add(da);
		this.ontology.add(diffInd);
	}

	public void addAtribNoIndivido(String Nome, String valor, String Tipo) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual individual = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", Nome);
		OWLDataProperty dataProp = factory.getOWLDataProperty(this.DATALATTESIRI + "#", Tipo);
		OWLDataPropertyAssertionAxiom da = factory.getOWLDataPropertyAssertionAxiom(dataProp, individual, valor);
		this.ontology.add(da);
	}

	public void addRelacaoInd(String NomePrimeiro, String NomeSegundo, String Relacao) {
		OWLDataFactory factory = this.manager.getOWLDataFactory();
		OWLIndividual individual = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", NomePrimeiro);
		OWLIndividual individual2 = factory.getOWLNamedIndividual(this.DATALATTESIRI + "#", NomeSegundo);
		OWLObjectProperty obj = factory.getOWLObjectProperty(this.DATALATTESIRI + "#", Relacao);
		OWLObjectPropertyAssertionAxiom da = factory.getOWLObjectPropertyAssertionAxiom(obj, individual, individual2);
		this.ontology.add(da);
	}

}
