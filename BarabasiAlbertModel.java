package projekat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class BarabasiAlbertModel {

private UndirectedSparseGraph<Integer, String> graph;
	
	public BarabasiAlbertModel(int n, int m0, double p, int m) {
		
		graph = new ErdosRenyiModel(m0, p).getGraph();
//		System.out.println(graph.getVertexCount());
		
		List<Integer> degs = new ArrayList<>();
		for(int i = 0; i < m0; i++) {
			for(int j = 0; j < graph.degree(i); j++) {
				degs.add(i);
			}
		}
		
		Random r = new Random();
		
		for(int i = m0; i < n; i++) {
			
			graph.addVertex(i);
			
			for(int j = 0; j < m; j++) {
				int old = degs.get((int) (r.nextDouble() * degs.size()));
				graph.addEdge(i + " " + old, i, old);
				degs.add(old);
			}
			
			for(int j = 0; j < m; j++) {
				degs.add(i);
			}
		}
		
	}
	
	public UndirectedSparseGraph<Integer, String> getGraph() {
		return graph;
	}
	
}
