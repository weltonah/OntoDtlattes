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
		this.ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
						.getIRI().getFragment().contains("Banca"))
				.filter(u -> this.ontology.objectPropertyAssertionAxioms(u)
						.anyMatch(i -> i.signature().findFirst().get().getIRI().getFragment()
								.contains("bancaTemParticipante")))
				.forEach(p -> {
					// System.out.println("%%%%%%%$%$%$%$%" + p.getIRI());
					this.ontology.objectPropertyAssertionAxioms(p)
							.filter(w -> w.signature().findFirst().get().getIRI().getFragment()
									.contains("bancaTemParticipante"))
							.forEach(y -> {
								// System.out.println("-------------------------");
								String[] individuos = new String[2];
								y.signature().skip(1).forEach(w -> {
									if (this.ontology.classAssertionAxioms((OWLIndividual) w).findFirst().get()
											.signature().findFirst().get().getIRI().getFragment().contains("Banca")) {
										individuos[1] = w.getIRI().getIRIString()
												.substring(w.getIRI().getIRIString().indexOf("#") + 1);
									} else {
										individuos[0] = w.getIRI().getIRIString()
												.substring(w.getIRI().getIRIString().indexOf("#") + 1);
									}
									// System.out.println(this.ontology.classAssertionAxioms((OWLIndividual)
									// w).findFirst()
									// .get()
									// .signature().findFirst().get().getIRI());
									// System.out.println(w.getIRI().getIRIString()
									// .substring(w.getIRI().getIRIString().indexOf("#") + 1));
								});
								this.grafo.AddBanca(individuos);
							});
				});
		// this.grafo.imprimirBanca();
		ArrayList<String[]> aux = this.grafo.InferirBanca();
		this.grafo.contRelacoes(aux);
	}

	public void preencherEvento() {
		this.ontology.individualsInSignature().filter(u -> u.isOWLNamedIndividual())
				.filter(u -> this.ontology.classAssertionAxioms(u).findFirst().get().signature().findFirst().get()
						.getIRI().getFragment().contains("Evento"))
				.filter(u -> this.ontology.objectPropertyAssertionAxioms(u).anyMatch(
						i -> i.signature().findFirst().get().getIRI().getFragment().contains("eventoTemParticipante")))
				.forEach(p -> {
					System.out.println("%%%%%%%$%$%$%$%" + p.getIRI());
					this.ontology.objectPropertyAssertionAxioms(p).filter(w -> w.signature().findFirst().get().getIRI()
							.getFragment().contains("eventoTemParticipante")).forEach(y -> {
								// System.out.println("-------------------------");
								String[] individuos = new String[2];
								y.signature().skip(1).forEach(w -> {
									if (this.ontology.classAssertionAxioms((OWLIndividual) w).findFirst().get()
											.signature().findFirst().get().getIRI().getFragment().contains("Evento")) {
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
								this.grafo.AddEvento(individuos);
							});
				});
		this.grafo.listParticipouEvento();
		// ArrayList<String[]> aux = this.grafo.InferirBanca();
		// this.grafo.contRelacoes(aux);
	}

}
