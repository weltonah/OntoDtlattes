package br.com.Grafo;

import java.io.File;
import java.util.ArrayList;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class GrafoController {

	private IRI DATALATTESIRI;
	private OWLOntologyManager manager;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private Grafo grafo;

	public GrafoController(String nomeArq) throws OWLOntologyCreationException {
		File file = new File(System.getProperty("user.dir") + "/" + nomeArq);
		this.DATALATTESIRI = IRI.create("http://www.datalattes.com/ontologies/datalattes.owl");
		this.manager = OWLManager.createOWLOntologyManager();
		this.ontology = this.manager.loadOntologyFromOntologyDocument(file);
		this.factory = this.manager.getOWLDataFactory();
		this.grafo = new Grafo();
	}

	public void preencherBanca() {
		preencherGrafo("Banca", "bancaTemParticipante", 0);
		this.grafo.imprimirBanca();
		ArrayList<String[]> aux = this.grafo.InferirBanca();
		this.grafo.contRelacoes(aux);
	}

	public void preencherEvento() {
		preencherGrafo("Evento", "eventoTemParticipante", 1);
		this.grafo.imprimirEvento();
		ArrayList<String[]> aux = this.grafo.InferirEvento();
		this.grafo.contRelacoes(aux);
	}

	public void preencherAreaAtuacao() {
		preencherGrafo("AreaAtuacao", "areaAtuacaoTemPesquisador", 2);
		preencherGrafo("AreaConhecimento", "areaConhecimentoTemPesquisador", 3);
		preencherGrafo("SubArea", "subAreaTemPesquisador", 4);
		preencherGrafo("Especialidade", "especialidadeTemPesquisador", 5);
		this.grafo.imprimirArea();
		System.out.println("Area atuacao");
		ArrayList<String[]> aux = this.grafo.InferirAreaAtuacao();
		this.grafo.contRelacoes(aux);
		System.out.println("area conhecimento");
		aux = this.grafo.InferirAreaConhecimento();
		this.grafo.contRelacoes(aux);
		System.out.println("sub area");
		aux = this.grafo.InferirSubArea();
		this.grafo.contRelacoes(aux);
		System.out.println("especialidade");
		aux = this.grafo.InferirEspecialidade();
		this.grafo.contRelacoes(aux);
	}

	// public void preencherOrientacao() {
	// preencherGrafo("Pessoa", "foiOrientadoPor", 6);
	// this.grafo.imprimirOrientacao();
	//
	// ArrayList<String[]> aux = this.grafo.InferirEvento();
	// this.grafo.contRelacoes(aux);
	// }

	public void preencherGrafo(String Classe, String Relacao, int opcao) {
		ArrayList<String[]> aux = new ArrayList<>();
		this.ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
						.getIRI().getFragment().contains(Classe))
				.filter(u -> this.ontology.objectPropertyAssertionAxioms(u)
						.anyMatch(i -> i.signature().findFirst().get().getIRI().getFragment().contains(Relacao)))
				.forEach(p -> {
					// System.out.println("%%%%%%%$%$%$%$%" + p.getIRI());
					this.ontology.objectPropertyAssertionAxioms(p)
							.filter(w -> w.signature().findFirst().get().getIRI().getFragment().contains(Relacao))
							.forEach(y -> {
								// System.out.println("-------------------------");
								String[] individuos = new String[2];
								y.signature().skip(1).forEach(w -> {
									if (this.ontology.classAssertionAxioms((OWLIndividual) w).findFirst().get()
											.signature().findFirst().get().getIRI().getFragment().contains(Classe)) {
										individuos[1] = w.getIRI().getIRIString()
												.substring(w.getIRI().getIRIString().indexOf("#") + 1);
									} else {
										individuos[0] = w.getIRI().getIRIString()
												.substring(w.getIRI().getIRIString().indexOf("#") + 1);
									}
									// System.out.println(this.ontology.classAssertionAxioms((OWLIndividual)
									// w).findFirst()
									// .get().signature().findFirst().get().getIRI());
									// System.out.println(w.getIRI().getIRIString()
									// .substring(w.getIRI().getIRIString().indexOf("#") + 1));
								});
								aux.add(individuos);

							});
				});
		aux.forEach(u -> {
			if (opcao == 0)
				this.grafo.AddBanca(u);
			if (opcao == 1)
				this.grafo.AddEvento(u);
			if (opcao == 2)
				this.grafo.AddAreaAtuacao(u);
			if (opcao == 3)
				this.grafo.AddAreaConhecimento(u);
			if (opcao == 4)
				this.grafo.AddSubArea(u);
			if (opcao == 5)
				this.grafo.AddEspecialidade(u);
			// if (opcao == 6)
			// this.grafo.AddOrientacao(u);

		});

	}

}
