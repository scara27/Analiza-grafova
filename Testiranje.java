package projekat;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class Testiranje {

	public static void main(String[] args) {
		UndirectedSparseGraph<Integer, String> g1 = new UndirectedSparseGraph<>();
		UndirectedSparseGraph<Integer, String> g2 = new UndirectedSparseGraph<>();
		UndirectedSparseGraph<Integer, String> g3 = new UndirectedSparseGraph<>();

		for (int i = 0; i < 15; i++) {
			g1.addVertex(i);
		}	
		int n = 0;
		g1.addEdge("a" + ++n, 1, 2);
		g1.addEdge("a" + ++n, 1, 3);
		g1.addEdge("a" + ++n, 1, 4);
		g1.addEdge("a" + ++n, 1, 6);
		g1.addEdge("a" + ++n, 1, 14);
		g1.addEdge("a" + ++n, 5, 2);
		g1.addEdge("a" + ++n, 5, 9);
		g1.addEdge("a" + ++n, 5, 14);
		g1.addEdge("a" + ++n, 5, 10);
		g1.addEdge("a" + ++n, 7, 2);
		g1.addEdge("a" + ++n, 7, 1);
		g1.addEdge("a" + ++n, 7, 6);
		
		for (int i = 0; i < 17; i++) {
			g2.addVertex(i);
		}
		n = 0;
		g2.addEdge("b" + ++n, 1, 1);
		g2.addEdge("b" + ++n, 2, 1);
		g2.addEdge("b" + ++n, 3, 1);
		g2.addEdge("b" + ++n, 4, 1);
		g2.addEdge("b" + ++n, 5, 1);
		g2.addEdge("b" + ++n, 6, 1);
		g2.addEdge("b" + ++n, 7, 1);
		g2.addEdge("b" + ++n, 8, 1);
		g2.addEdge("b" + ++n, 9, 1);
		g2.addEdge("b" + ++n, 10, 1);
		g2.addEdge("b" + ++n, 11, 1);
		g2.addEdge("b" + ++n, 12, 1);
		g2.addEdge("b" + ++n, 13, 1);
		g2.addEdge("b" + ++n, 14, 1);
		g2.addEdge("b" + ++n, 15, 1);
		g2.addEdge("b" + ++n, 16, 1);
		
		for (int i = 0; i < 20; i++) {
			g3.addVertex(i);
		}
		n = 0;
		g3.addEdge("c" + ++n, 1, 9);
		g3.addEdge("c" + ++n, 4, 9);
		g3.addEdge("c" + ++n, 2, 8);
		g3.addEdge("c" + ++n, 3, 8);
		g3.addEdge("c" + ++n, 4, 8);
		g3.addEdge("c" + ++n, 20, 5);
		g3.addEdge("c" + ++n, 19, 9);
		g3.addEdge("c" + ++n, 8, 9);
		g3.addEdge("c" + ++n, 17, 9);
		g3.addEdge("c" + ++n, 16, 9);
		g3.addEdge("c" + ++n, 11, 1);
		g3.addEdge("c" + ++n, 12, 3);
		g3.addEdge("c" + ++n, 1, 8);
		g3.addEdge("c" + ++n, 19, 9);
		
		BatageljZaversnik<Integer, String> bz1 = new BatageljZaversnik<Integer, String>(g1);
		BatageljZaversnik<Integer, String> bz2 = new BatageljZaversnik<Integer, String>(g2);
		BatageljZaversnik<Integer, String> bz3 = new BatageljZaversnik<Integer, String>(g3);
		
		System.out.println("Prvi graf...");
		System.out.println(bz1.coreGraph(1).getVertexCount());
		System.out.println(bz1.coreGraph(2).getVertexCount());
		
		System.out.println("Drugi graf...");
		
		System.out.println("Treci graf...");
		
//		UndirectedSparseGraph<Integer, String> g4 = new ErdosRenyiModel(120, 0.01).getGraph();
//		BatageljZaversnik bz4 = new BatageljZaversnik(g4);
//		g11 = bz4.coreGraph(0);
//		System.out.println(g11.getVertexCount());
//		g4 = new BarabasiAlbertModel(100, 50, 0.01, 10).getGraph();
//		bz4 = new BatageljZaversnik(g4);
//		g11 = bz4.coreGraph(0);
//		System.out.println(g11.getVertexCount());
	}

}
