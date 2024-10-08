package projekat;

import java.util.Random;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class ErdosRenyiModel {

	private UndirectedSparseGraph<Integer, String> graph;

	public ErdosRenyiModel(int n, double p) {
		graph = new UndirectedSparseGraph<>();

		for (int i = 0; i < n; i++) {
			graph.addVertex(i);
		}

		Random r = new Random();

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (r.nextDouble() <= p) {
					graph.addEdge(i + " " + j, i, j);
				}
			}
		}
	}
	
	public UndirectedSparseGraph<Integer, String> getGraph() {
		return graph;
	}
	
}
