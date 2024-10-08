package projekat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.collections15.map.HashedMap;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class BatageljZaversnik<V, E> {

	private UndirectedSparseGraph<V, E> graph;
	
	// niz integera predstavljen mapom gde je kljuc cvor, a vrednost njegov shell index
	private Map<V, Integer> s;

	public BatageljZaversnik(UndirectedSparseGraph<V, E> graph) {
		if(graph == null || graph.getVertexCount() == 0)
			throw new IllegalArgumentException("Prazan graf...");
		
		this.graph = graph;
		s = new HashedMap<>();
		
		algoritam();
	}
	
	private void algoritam() {
		//maxDeg - maksimalni stepen cvorova
		int maxDeg = 0;
		
		//d0 - niz integera duzine n gde je n jednak broju cvorova, predstavljen mapom
		Map<V, Integer> d0 = new HashedMap<>();
		
		//d1 - niz od praznih skupova cvorova kojih ima maxDeg
		//skupovi su predstavljeni preko liste radi pronalazenja cvorova
		List<ArrayList<V>> d1 = new ArrayList<ArrayList<V>>();
		
		Iterator<V> it = graph.getVertices().iterator();
		while (it.hasNext()) {
			V v = it.next();
			if (graph.degree(v) >= maxDeg)
				maxDeg = graph.degree(v);
		}
		
		for (int i = 0; i <= maxDeg; i++) {
			List<V> nodes = new ArrayList<>();
			d1.add((ArrayList<V>) nodes);
		}
		
		it = graph.getVertices().iterator();
		while (it.hasNext()) {
			V v = it.next();
			int k = graph.degree(v);
			d0.put(v, k);
			ArrayList<V> nodes = d1.get(k);
			nodes.add(v);
		}
		
		for (int k = 0; k < maxDeg; k++) {
			while (d1.get(k).size() != 0) {
				ArrayList<V> nodes = d1.get(k);
				int r = new Random().nextInt(nodes.size());
				V x = nodes.get(r);
				nodes.remove(r);
				s.put(x, k);
				
				it = graph.getVertices().iterator();
				while (it.hasNext()) {
					V v = it.next();
					if (graph.findEdge(v, x) != null) {
						if (d0.get(v) > k) {
							nodes = d1.get(d0.get(v));
							nodes.remove(v);
							int lower = d0.get(v) - 1;
							nodes = d1.get(lower);
							nodes.add(v);
							d0.put(v, lower);
						}
					}
				}
			}
		}
	}
	
	public UndirectedSparseGraph<V, E> coreGraph(int k) {
		UndirectedSparseGraph<V, E> novi = new UndirectedSparseGraph<V, E>();
		
		for(Entry<V, Integer> entry : s.entrySet()){
			V key = entry.getKey();
			int value = entry.getValue();
			if (value >= k) {
				novi.addVertex(key);
			}
		}
		
		Iterator<V> it = novi.getVertices().iterator();
		while (it.hasNext()) {
			V v = it.next();
			Iterator<V> it2 = novi.getVertices().iterator();
			while (it2.hasNext()) {
				V v2 = it2.next();
				E edge = graph.findEdge(v, v2);
				if (edge != null)
					if (novi.findEdge(v, v2) == null)
						novi.addEdge(edge, v, v2);
			}
		}
		
		return novi;
	}

	public int getShellIndex(V v) {
		for(Entry<V, Integer> entry : s.entrySet()){
			if (entry.getKey() == v)
				return entry.getValue();
		}
		return 0;
	}
	
	public UndirectedSparseGraph<V, E> getGraph() {
		return graph;
	}

	public Map<V, Integer> getS() {
		return s;
	}
	
}
