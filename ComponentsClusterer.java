package projekat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class ComponentsClusterer<V, E> {
	
	//input
	private UndirectedSparseGraph<V, E> g;
	
	//output
	private List<UndirectedSparseGraph<V, E>> components;
	
	private Set<V> visited = new HashSet<>();
	
	
	public ComponentsClusterer(UndirectedSparseGraph<V, E> g) {
		if(g == null || g.getVertexCount() == 0) {
			throw new IllegalArgumentException("Prazan graf...");
		}
		this.g = g;
		components = new ArrayList<>();
		identifyComponents();
	}
	
	private void identifyComponents() {
		Iterator<V> it = g.getVertices().iterator();
		while(it.hasNext()) {
			V node = it.next();
			if(!visited.contains(node)) {
				identifyComponent(node);
			}
		}
		
		Collections.sort(components, (c1, c2) -> c2.getVertexCount() - c1.getVertexCount());
	}

	private void identifyComponent(V startNode) {
		LinkedList<V> queue = new LinkedList<>();
		queue.add(startNode);
		visited.add(startNode);
		UndirectedSparseGraph<V, E> component = new UndirectedSparseGraph<>();
		component.addVertex(startNode);
		
		while(!queue.isEmpty()) {
			V current = queue.removeFirst();
			Iterator<V> nit = g.getNeighbors(current).iterator();
			while(nit.hasNext()) {
				V neighbor = nit.next();
				if(!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.addLast(neighbor);
					component.addVertex(neighbor);
				}
				
				if(component.findEdge(current, neighbor) == null) {
					E link = g.findEdge(current, neighbor);
					component.addEdge(link, current, neighbor);
				}
			}
		}
		components.add(component);
	}
	
	public List<UndirectedSparseGraph<V, E>> getComponents() {
		return components;
	}

	public UndirectedSparseGraph<V, E> getGiantComponent() {
		return components.get(0);
	}

}
