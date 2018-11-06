package projeto.interdisciplinar;

// A Java program for Bellman-Ford's single source shortest path 
// algorithm. 
import java.util.*; 
import java.lang.*; 
import java.io.*; 

// A class to represent a connected, directed and weighted graph 
class Graph 
{ 
	// A class to represent a weighted edge in graph 
	class Edge { //Aresta
		int src, //Vertice de origem
                    dest, // Vertice de Destino
                    weight; // peso da aresta
                
		Edge() { 
			src = dest = weight = 0; 
		} 
	}; 

	int V, E; 
	Edge edge[]; 

	// Creates a graph with V vertices and E edges 
	Graph(int v, int e) 
	{ 
		V = v; 
		E = e; 
		edge = new Edge[e]; 
		for (int i=0; i<e; ++i) 
			edge[i] = new Edge(); 
	} 

	// A principal função que encontra distâncias mais curtas de src
        // para todos os outros vértices usando o algoritmo de Bellman-Ford. o
        // função também detecta ciclo de peso negativo 
	void BellmanFord(Graph graph, int src) 
	{
		int V = graph.V, E = graph.E; 
		int dist[] = new int[V];
                int matriz [][] = new int[V][V];

		// Step 1: Initialize distances from src to all other 
		// vertices as INFINITE
                
                System.out.println("Matriz de distância");
                for (int i = 0; i<V; i++) {
                    for (int j = 0; j<V; j++) {
                        matriz [i] [j] = 0;
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.print("\n");
                }
                
                for (int i=0; i<V; ++i) 
			dist[i] = Integer.MAX_VALUE; 
		dist[src] = 0; 

		// Step 2: Relax all edges |V| - 1 times. A simple 
		// shortest path from src to any other vertex can 
		// have at-most |V| - 1 edges 
		for (int i=1; i<V; ++i){ 
			for (int j=0; j<E; ++j){ 
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest; 
				int weight = graph.edge[j].weight; 
				if (dist[u]!=Integer.MAX_VALUE && dist[u]+weight<dist[v])
					dist[v]=dist[u]+weight; 
			} 
		} 

		// Step 3: check for negative-weight cycles. The above 
		// step guarantees shortest distances if graph doesn't 
		// contain negative weight cycle. If we get a shorter 
		// path, then there is a cycle. 
		for (int j=0; j<E; ++j) 
		{ 
			int u = graph.edge[j].src; 
			int v = graph.edge[j].dest; 
			int weight = graph.edge[j].weight; 
			if (dist[u] != Integer.MAX_VALUE && 
				dist[u]+weight < dist[v]) 
			System.out.println("Graph contains negative weight cycle"); 
		} 
		printArr(dist, V); 
	} 

	// A utility function used to print the solution 
	void printArr(int dist[], int V) 
	{ 
		System.out.println("Vertex Distance from Source"); 
		for (int i=0; i<V; ++i) 
			System.out.println(i + "\t\t" + dist[i]); 
	} 

	// Driver method to test above function 
	public static void main(String[] args) 
	{ 
		int V = 5; // Number of vertices in graph 
		int E = 8; // Number of edges in graph -> arestas

		Graph graph = new Graph(V, E); 

		graph.edge[0].src = 0; 
		graph.edge[0].dest = 1; 
		graph.edge[0].weight = -1; 

		graph.edge[1].src = 0; 
		graph.edge[1].dest = 2; 
		graph.edge[1].weight = 4; 

		graph.edge[2].src = 1; 
		graph.edge[2].dest = 2; 
		graph.edge[2].weight = 3; 

		graph.edge[3].src = 1; 
		graph.edge[3].dest = 3; 
		graph.edge[3].weight = 2; 

		graph.edge[4].src = 1; 
		graph.edge[4].dest = 4; 
		graph.edge[4].weight = 2; 

		graph.edge[5].src = 3; 
		graph.edge[5].dest = 2; 
		graph.edge[5].weight = 5; 

		graph.edge[6].src = 3; 
		graph.edge[6].dest = 1; 
		graph.edge[6].weight = 1; 

		graph.edge[7].src = 4; 
		graph.edge[7].dest = 3; 
		graph.edge[7].weight = -3; 

		graph.BellmanFord(graph, 0); 
	} 
} 

