// Java program to print connected components in
// an undirected graph
package com.example.terogmergifx.service;

import java.util.ArrayList;

public class Graph {
    Integer V;
    ArrayList<ArrayList<Integer>> adjListArray;

    public Graph(Integer V) {
        this.V = V;

        adjListArray = new ArrayList<>();


        for (int i = 0; i < V; i++) {
            adjListArray.add(i, new ArrayList<>());
        }
    }

    public void adaugaMuchie(int s, int d) {

        adjListArray.get(s).add(d);


        adjListArray.get(d).add(s);
    }

    public void DFS(Integer v, boolean[] visited) {

        visited[v] = true;
        System.out.print(v + " ");

        for (int x : adjListArray.get(v)) {
            if (!visited[x])
                DFS(x, visited);
        }
    }

   public Integer componenteConexe() {

        Integer nr = 0;
        boolean[] visited = new boolean[V];
        for (int v = 0; v < V; ++v) {
            if (!visited[v]) {

                DFS(v, visited);
                System.out.println();
                nr++;
            }
        }

        return nr;
    }
}
