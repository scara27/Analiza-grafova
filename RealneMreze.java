package projekat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import edu.uci.ics.jung.algorithms.metrics.Metrics;
import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.scoring.EigenvectorCentrality;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class RealneMreze {

	UndirectedSparseGraph<Integer, Integer> grafFb1;
	UndirectedSparseGraph<Integer, Integer> grafFb2;
	UndirectedSparseGraph<Integer, Integer> grafFb3;

	public RealneMreze() throws IOException {
		this.grafFb1 = new UndirectedSparseGraph<>();
		this.grafFb2 = new UndirectedSparseGraph<>();
		this.grafFb3 = new UndirectedSparseGraph<>();
		
		ucitajGrafFb1();
		ucitajGrafFb2();
		ucitajGrafFb3();
	}

	public UndirectedSparseGraph<Integer, Integer> getGrafFb1() {
		return grafFb1;
	}
	
	public UndirectedSparseGraph<Integer, Integer> getGrafFb2() {
		return grafFb2;
	}

	public UndirectedSparseGraph<Integer, Integer> getGrafFb3() {
		return grafFb3;
	}

	private void ucitajGrafFb1() throws IOException {
		for (int i = 0; i < 4_031; i++) {
			grafFb1.addVertex(i);
		}
		
		BufferedReader in = 
		new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("facebook_combined.txt")));
		
		for (int i = 0; i < 88_234; i++) {
			String[] elementi = in.readLine().split(" ");
			int v1 = Integer.parseInt(elementi[0]);
			int v2 = Integer.parseInt(elementi[1]);
			
			grafFb1.addEdge(i, v1, v2);
		}
		in.close();
	}
	
	private void ucitajGrafFb2() throws IOException {
		for (int i = 0; i < 3_892; i++) {
			grafFb2.addVertex(i);
		}
		
		BufferedReader in = 
		new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("tvshow.txt")));
		
		for (int i = 0; i < 17_262; i++) {
			String[] elementi = in.readLine().split(",");
			int v1 = Integer.parseInt(elementi[0].trim());
			int v2 = Integer.parseInt(elementi[1].trim());
			
			grafFb2.addEdge(i, v1, v2);
		}
		in.close();
	}
	
	private void ucitajGrafFb3() throws IOException {
		for (int i = 0; i < 5_908; i++) {
			grafFb3.addVertex(i);
		}
		
		BufferedReader in = 
		new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("politician.txt")));
		
		for (int i = 0; i < 41_729; i++) {
			String[] elementi = in.readLine().split(",");
			int v1 = Integer.parseInt(elementi[0].trim());
			int v2 = Integer.parseInt(elementi[1].trim());
			
			grafFb3.addEdge(i, v1, v2);
		}
		in.close();
	}
	
	private void ispisiStepene(UndirectedSparseGraph<Integer, Integer> g) {
		Iterator<Integer> it = g.getVertices().iterator();
		while (it.hasNext()) {
			int v = (int) it.next();
			System.out.println(g.degree(v));
		}
	}
	
	private void ispisiCloseness(UndirectedSparseGraph<Integer, Integer> g) {
		ClosenessCentrality<Integer, Integer> cc = new ClosenessCentrality<>(g);
		
		Iterator<Integer> it = g.getVertices().iterator();
		while (it.hasNext()) {
			int v = (int) it.next();
			System.out.println(cc.getVertexScore(v));
		}
	}
	
	private void ispisiBetweenness(UndirectedSparseGraph<Integer, Integer> g) {
		BetweennessCentrality<Integer, Integer> bc = new BetweennessCentrality<>(g);
		//System.out.println("ok");
		
		Iterator<Integer> it = g.getVertices().iterator();
		while (it.hasNext()) {
			int v = (int) it.next();
			System.out.println(bc.getVertexScore(v));
		}
	}
	
	private void ispisiEV(UndirectedSparseGraph<Integer, Integer> g) {
		EigenvectorCentrality<Integer, Integer> ec = new EigenvectorCentrality<>(g);
		ec.evaluate();
		
		Iterator<Integer> it = g.getVertices().iterator();
		while (it.hasNext()) {
			int v = (int) it.next();
			System.out.println(ec.getVertexScore(v));
		}
	}
	
	private void ispisiShellIndexe(UndirectedSparseGraph<Integer, Integer> g) {
		BatageljZaversnik<Integer, Integer> bz = new BatageljZaversnik<>(g);
		Map<Integer, Integer> s = bz.getS();
		for(Entry<Integer, Integer> entry : s.entrySet()){
			System.out.println(entry.getValue());
		}
	}
	
	private double gustina(UndirectedSparseGraph<Integer, Integer> g) {
		int n = g.getVertexCount();
		int l = g.getEdgeCount();
		
		if (n <= 1)
			return 0;
		
		double k = 2 * l / n;
		double gustina = k / (n - 1);
		
		return gustina;
	}
	
	private double najvecaKompProcenatCvorova(UndirectedSparseGraph<Integer, Integer> g) {
		ComponentsClusterer<Integer, Integer> cc = new ComponentsClusterer<>(g);
		UndirectedSparseGraph<Integer, Integer> gc = cc.getGiantComponent();
		
		int brc1 = g.getVertexCount();
		int brc2 = gc.getVertexCount();
		
		return brc1 * 100 / brc2;
	}
	
	private double najvecaKompProcenatLinkova(UndirectedSparseGraph<Integer, Integer> g) {
		ComponentsClusterer<Integer, Integer> cc = new ComponentsClusterer<>(g);
		UndirectedSparseGraph<Integer, Integer> gc = cc.getGiantComponent();
		
		int brc1 = g.getEdgeCount();
		int brc2 = gc.getEdgeCount();
		
		return brc1 * 100 / brc2;
	}
	
	private double prosecanKoefKlasterisanja(UndirectedSparseGraph<Integer, Integer> g) {
		Map<Integer, Double> ccMap = Metrics.clusteringCoefficients(g);
		double sum = 0;
		
		for(Entry<Integer, Double> entry : ccMap.entrySet()){
			sum += entry.getValue();
		}
		
		return sum / g.getVertexCount();
	}
	
	private void karakteristike(int k, UndirectedSparseGraph<Integer, Integer> g) {
		BatageljZaversnik<Integer, Integer> bz = new BatageljZaversnik<>(g);
		UndirectedSparseGraph<Integer, Integer> g_core = bz.coreGraph(k);
		
		System.out.println("Broj cvorova: " + g_core.getVertexCount());
		System.out.println("Broj linkova: " + g_core.getEdgeCount());
		System.out.println("Gustina: " + gustina(g_core));
		
		ComponentsClusterer<Integer, Integer> cc = new ComponentsClusterer<>(g_core);
		
		System.out.println("Broj povezanih komponenti: " + cc.getComponents().size());
		String str = String.format("%.3f", prosecanKoefKlasterisanja(g_core));
		System.out.println("Prosecan koeficijent klasterisanja: " + str);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//RealneMreze rm = new RealneMreze();
		
		//UndirectedSparseGraph<Integer, Integer> g1 = rm.getGrafFb1();
		
		//rm.ispisiStepene(g1);
		//rm.ispisiCloseness(g1);
		//rm.ispisiBetweenness(g1);
		//rm.ispisiEV(g1);
		//rm.ispisiShellIndexe(g1);
		
		//UndirectedSparseGraph<Integer, Integer> g2 = rm.getGrafFb2();
		
		//rm.ispisiStepene(g2);
		//rm.ispisiCloseness(g2);
		//rm.ispisiBetweenness(g2);
		//rm.ispisiEV(g2);
		//rm.ispisiShellIndexe(g2);
		
		//UndirectedSparseGraph<Integer, Integer> g3 = rm.getGrafFb3();

		//rm.ispisiStepene(g3);
		//rm.ispisiBetweenness(g3);
		//rm.ispisiCloseness(g3);
		//rm.ispisiEV(g3);
		//rm.ispisiShellIndexe(g3);
		//1, 31, 26, 23, 19, 8
		
		//rm.karakteristike(26, g3);
	}
}
