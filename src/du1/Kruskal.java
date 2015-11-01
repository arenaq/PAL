package du1;

import java.io.*;
import java.util.*;

public class Kruskal {
    //private final int MAX_NODES = 2000;
    private HashSet nodes[];               // Array of connected components
    private TreeSet allEdges;              // Priority queue of Edge objects
    private Vector allNewEdges;            // Edges in Minimal-Spanning Tree

    private int N; // number of nodes (buildings)
    private int M; // number of edges (direct connections)
    private int C1; // lower price limit
    private int C2; // upper price limit
    
    private Edge leastEdge;
    private int minSpanTreeSum;
    private TreeSet remainingEdges;
    private Vector altEdges;

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Kruskal k = new Kruskal(in.readLine());
        k.readInGraphData();
        
        k.performKruskal();
        k.removeLeastEdge();
        k.performLastStep();
        
        //k.printFinalEdges();
    }

    Kruskal(String line) {
        parseFirstLine(line);
        nodes = new HashSet[N];      // Create array for components
        allEdges = new TreeSet(new Edge());  // Create empty priority queue
        allNewEdges = new Vector(N); // Create vector for MST edges
        remainingEdges = new TreeSet(new Edge());
        leastEdge = new Edge(0, 0, Integer.MAX_VALUE);
        altEdges = new Vector(N);
        minSpanTreeSum = 0;
    }

    static final int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    private void parseFirstLine(String line) {
        int len = line.length();
        int index = len - 1;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            C2 += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            C1 += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            M += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index >= 0) {
            char c = line.charAt(index);
            N += (c - '0') * pow[len - index - 1];
            index--;
        }
    }

    private Edge readEdge(String line) {
        int A = 0;
        int B = 0;
        int C = 0;

        int len = line.length();
        int index = len - 1;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            C += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index > 0 && line.charAt(index) != ' ') {
            char c = line.charAt(index);
            B += (c - '0') * pow[len - index - 1];
            index--;
        }

        len = index;
        index--;
        while (index >= 0) {
            char c = line.charAt(index);
            A += (c - '0') * pow[len - index - 1];
            index--;
        }
        return new Edge(A, B, C);
    }

    private void readInGraphData() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < M; i++) {
            Edge edge = readEdge(in.readLine());
            if (edge.cost < leastEdge.cost) leastEdge = edge;

            allEdges.add(edge);  // Update priority queue
            if (nodes[edge.from] == null) {
                // Create set of connect components [singleton] for this node
                nodes[edge.from] = new HashSet(2 * N);
                nodes[edge.from].add(new Integer(edge.from));
            }

            if (nodes[edge.to] == null) {
                // Create set of connect components [singleton] for this node
                nodes[edge.to] = new HashSet(2 * N);
                nodes[edge.to].add(new Integer(edge.to));
            }
        }
    }

    private void performKruskal() {
        int size = allEdges.size();
        for (int i = 0; i < size; i++) {
            Edge curEdge = (Edge) allEdges.first();
            if (allEdges.remove(curEdge)) {
                // successful removal from priority queue: allEdges

                if (nodesAreInDifferentSets(curEdge.from, curEdge.to)) {
                    // System.out.println("Nodes are in different sets ...");
                    HashSet src, dst;
                    int dstHashSetIndex;

                    if (nodes[curEdge.from].size() > nodes[curEdge.to].size()) {
                        // have to transfer all nodes including curEdge.to
                        src = nodes[curEdge.to];
                        dst = nodes[dstHashSetIndex = curEdge.from];
                    } else {
                        // have to transfer all nodes including curEdge.from
                        src = nodes[curEdge.from];
                        dst = nodes[dstHashSetIndex = curEdge.to];
                    }

                    Object srcArray[] = src.toArray();
                    int transferSize = srcArray.length;
                    for (int j = 0; j < transferSize; j++) {
                        // move each node from set: src into set: dst
                        // and update appropriate index in array: nodes
                        if (src.remove(srcArray[j])) {
                            dst.add(srcArray[j]);
                            nodes[((Integer) srcArray[j]).intValue()] = nodes[dstHashSetIndex];
                        } else {
                            // This is a serious problem
                            System.out.println("Something wrong: set union");
                            System.exit(1);
                        }
                    }

                    allNewEdges.add(curEdge);
                    minSpanTreeSum += curEdge.cost;
                    // add new edge to MST edge vector
                } else {
                    // System.out.println("Nodes are in the same set ... nothing to do here");
                    remainingEdges.add(curEdge);
                }

            } else {
                // This is a serious problem
                System.out.println("TreeSet should have contained this element!!");
                System.exit(1);
            }
        }
        System.out.println(minSpanTreeSum);
    }
    
    private void performLastStep() {
        System.out.println("RemaningSize: "+remainingEdges.size());
        int size = remainingEdges.size();
        for (int i = 0; i < size; i++) {
            Edge curEdge = (Edge) remainingEdges.first();
            if (remainingEdges.remove(curEdge)) {
                // successful removal from priority queue: allEdges

                if (nodesAreInDifferentSets(curEdge.from, curEdge.to)) {
                    // System.out.println("Nodes are in different sets ...");
                    HashSet src, dst;
                    int dstHashSetIndex;

                    if (nodes[curEdge.from].size() > nodes[curEdge.to].size()) {
                        // have to transfer all nodes including curEdge.to
                        src = nodes[curEdge.to];
                        dst = nodes[dstHashSetIndex = curEdge.from];
                    } else {
                        // have to transfer all nodes including curEdge.from
                        src = nodes[curEdge.from];
                        dst = nodes[dstHashSetIndex = curEdge.to];
                    }

                    Object srcArray[] = src.toArray();
                    int transferSize = srcArray.length;
                    for (int j = 0; j < transferSize; j++) {
                        // move each node from set: src into set: dst
                        // and update appropriate index in array: nodes
                        if (src.remove(srcArray[j])) {
                            dst.add(srcArray[j]);
                            nodes[((Integer) srcArray[j]).intValue()] = nodes[dstHashSetIndex];
                        } else {
                            // This is a serious problem
                            System.out.println("Something wrong: set union");
                            System.exit(1);
                        }
                    }

                    //System.out.println(curEdge.cost + " > " + C1 + " && " + curEdge.cost + " < " + C2);
                    if (curEdge.cost > C1 && curEdge.cost < C2) //altEdges.add(curEdge);
                        System.out.println(curEdge.from + " " + curEdge.to + " " + curEdge.cost);
                    // add new edge to MST edge vector
                } else {
                    // System.out.println("Nodes are in the same set ... nothing to do here");
                }

            } else {
                // This is a serious problem
                System.out.println("TreeSet should have contained this element!!");
                System.exit(1);
            }
        }
    }

    private boolean nodesAreInDifferentSets(int a, int b) {
        // returns true if graph nodes (a,b) are in different
        // connected components, ie the set for 'a' is different
        // from that for 'b'
        return (!nodes[a].equals(nodes[b]));
    }
    
    private void removeLeastEdge() {
        allNewEdges.remove(leastEdge);
    }

    private void printFinalEdges() {
        System.out.println(minSpanTreeSum);
        while (!altEdges.isEmpty()) {
            Edge e = (Edge) altEdges.firstElement();
            System.out.println(e.from + " " + e.to + " " + e.cost);
            altEdges.remove(e);
        }
    }

    class Edge implements Comparator {

        // Inner class for representing edge+end-points
        public int from, to, cost;

        public Edge() {
            // Default constructor for TreeSet creation
        }

        public Edge(int f, int t, int c) {
            // Inner class constructor
            from = f;
            to = t;
            cost = c;
        }

        public int compare(Object o1, Object o2) {
            // Used for comparisions during add/remove operations
            int cost1 = ((Edge) o1).cost;
            int cost2 = ((Edge) o2).cost;
            int from1 = ((Edge) o1).from;
            int from2 = ((Edge) o2).from;
            int to1 = ((Edge) o1).to;
            int to2 = ((Edge) o2).to;

            if (cost1 < cost2) {
                return (-1);
            } else if (cost1 == cost2 && from1 == from2 && to1 == to2) {
                return (0);
            } else if (cost1 == cost2) {
                return (-1);
            } else if (cost1 > cost2) {
                return (1);
            } else {
                return (0);
            }
        }

        public boolean equals(Object obj) {
            // Used for comparisions during add/remove operations
            Edge e = (Edge) obj;
            return (cost == e.cost && from == e.from && to == e.to);
        }
    }

}
