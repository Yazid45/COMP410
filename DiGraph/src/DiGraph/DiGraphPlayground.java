package DiGraph;

public class DiGraphPlayground {

	public static void main(String[] args) {

		testr();
	}

	public static void testr() {
		int size = 5;
		DiGraph d = new DiGraph();
		String rm[] = new String[size + 1];
		for (int i = 0; i < size; i++) {
			char a[] = new char[2];
			a[0] = (char) ((int) 26 * Math.random() + 65);
			a[1] = (char) ((int) 26 * Math.random() + 65);
			rm[i] = new String(a);
			d.addNode(i, rm[i]);
		}
		int id = 0;
		for (int i = 0; i < size*3; i++) {
			int f =(int) (size * Math.random());
			int n = (int) (size * Math.random());
				d.addEdge(id++, rm[f], rm[n], (long) (1+10* Math.random()), null);
			

		}

		d.print();
		System.out.println(d.numNodes() + "____ " + d.numNodes() * d.numNodes());
		System.out.println(d.numEdges());
		for (ShortestPathInfo p : d.shortestPath(rm[0]))
			System.out.println(p);
	}

	public static void exTest() {
		DiGraph d = new DiGraph();
		d.addNode(1, "f");
		d.addNode(3, "s");
		d.addNode(7, "t");
		d.addNode(0, "fo");
		d.addNode(4, "fi");
		d.addNode(6, "si");
		d.addEdge(0, "f", "s", 4, null);
		d.addEdge(1, "f", "si", 2, null);
		d.addEdge(2, "s", "t", 1, null);
		d.addEdge(3, "fo", "fi", 5, null);
		d.addEdge(4, "fi", "si", 8, null);
		System.out.println("numEdges: " + d.numEdges());
		System.out.println("numNodes: " + d.numNodes());
		d.print();
		for (ShortestPathInfo p : d.shortestPath("fi"))
			System.out.println(p);
	}
}