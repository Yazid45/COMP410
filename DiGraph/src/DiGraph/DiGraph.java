package DiGraph;

import java.util.HashMap; 
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DiGraph implements DiGraphInterface {

	// in here go all your data and methods for the graph

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	HashMap<Long, Vertex> nodes = new HashMap<Long, Vertex>();
	HashMap<Long, Edge> edges = new HashMap<Long, Edge>();

	@Override
	public boolean addNode(long idNum, String label) {
		if (idNum < 0 | label == null)
			return false;
		if (nodes.containsKey(idNum) | nodes.containsValue(new Vertex(label, idNum)))
			return false;
		nodes.put(idNum, new Vertex(label, idNum));
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (idNum < 0 | sLabel == null | dLabel == null)
			return false;
		if (edges.containsKey(idNum))
			return false;
		if (!nodes.containsValue(new Vertex(dLabel, idNum)) | !nodes.containsValue(new Vertex(sLabel, idNum)))
			return false;
		Vertex start = null;
		Vertex dest = null;
		for (long k : nodes.keySet()) {
			if (start == null | dest == null) {
				if (nodes.get(k).equals(sLabel)) {
					start = nodes.get(k);
				}
				if (nodes.get(k).equals(dLabel))
					dest = nodes.get(k);
			} else {
				break;
			}
		}
		Edge e = new Edge(idNum, start, dest, weight, eLabel);
		if (start.addEdge(e)) {
			edges.put(idNum, e);
			return true;
		}
		return false;
	}

	@Override
	public boolean delNode(String label) {
		for (long k : nodes.keySet()) {
			if (nodes.get(k).equals(label)) {
				Vertex v = nodes.remove(k);
				for (Edge e : v.out_edges) {
					edges.remove(e.id);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		for (long k : nodes.keySet()) {
			if (nodes.get(k).equals(sLabel)) {
				Vertex v = nodes.get(k);
				for (Edge e : v.out_edges) {
					if (e.d.equals(dLabel)) {
						v.out_edges.remove(e);
						edges.remove(e.getID());
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return nodes.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return edges.size();
	}

	public void print() {
		for (long key : nodes.keySet()) {
			Vertex v = nodes.get(key);
			System.out.print(v);
			for (Edge e : v.out_edges)
				System.out.print(e);
			System.out.println();
		}
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		HashMap<Long, Vertex> nodes_c = (HashMap<Long, Vertex>) nodes.clone();
		PriorityQueue<Edge> prQ = new PriorityQueue<Edge>();
		Vertex v = null;
		for (Vertex i : nodes_c.values())
			if (i.equals(label)) {
				v = i;
				break;
			}
		ShortestPathInfo[] shtpth = new ShortestPathInfo[(int) numNodes()];

		int counter = 0;
		if (v != null) {
			counter++;
			shtpth[0] = new ShortestPathInfo(label, 0);
			nodes_c.remove(v.id);
			for (Edge e : v.out_edges) {
				prQ.add(e.clone(0));
			}
		}
		while (!prQ.isEmpty()) {
			Edge e = prQ.remove();
			v = e.d;
			if (!nodes_c.containsKey(v.id))
				continue;
			nodes_c.remove(v.id);
			shtpth[counter] = new ShortestPathInfo(v.s, e.w);
			counter++;
			for (Edge ed : v.out_edges) {
				prQ.add(ed.clone(e.w));
			}
		}

		for (Vertex i : nodes_c.values()) {
			shtpth[counter] = new ShortestPathInfo(i.s, -1);
			counter++;
		}

		return shtpth;
	}

	// rest of your code to implement the various operations
}

class Vertex {
	public String s;
	long id;
	LinkedList<Edge> out_edges = new LinkedList<Edge>();

	public Vertex(String sLabel, long idNum) {
		s = sLabel;
		id = idNum;
	}

	public String getSrc() {
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj.getClass() == String.class)
			return s.equals(obj);
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Vertex v = (Vertex) obj;
		return s.equals(v.s);
	}

	public boolean addEdge(Edge e) {
		if (out_edges.contains(e))
			return false;
		out_edges.add(e);
		return true;

	}

	@Override
	public String toString() {
		return "(" + id + ")" + s + "\n";
		// TODO Auto-generated method stub
	}

}

class Edge implements Comparable<Edge> {
	public Vertex s;
	public long id;
	public Vertex d;
	public long w;
	public String e;

	public Edge(long idNum, Vertex start, Vertex dest, long weight, String eLabel) {
		s = start;
		id = idNum;
		d = dest;
		w = weight;
		e = eLabel;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Edge o = (Edge) obj;
		return d.equals(o.d) & s.equals(o.s);
	}

	public long getID() {
		return id;
	}

	public Vertex getDest() {
		return d;
	}

	public Vertex getStart() {
		return s;
	}

	public long getWeight() {
		return w;
	}

	public String getElbl() {
		return e;
	}

	public String toString() {
		String s = "(" + id + ")" + "   --";
		if (e != null)
			s = s + e + "--";
		s = s + w + "--> " + d.s + "\n";
		return s;
	}

	public Edge clone(long exw) {
		return new Edge(id, s, d, w + exw, e);
	}

	@Override
	public int compareTo(Edge o) {
		int c = 0;
		if (w > o.w)
			c++;
		if (w < o.w)
			c--;
		return c;
	}
}
