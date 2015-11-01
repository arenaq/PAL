/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 *
 * @author arenaq
 */
public class BackupConnection {

    static int N; // number of nodes (buildings)
    static int M; // number of edges (direct connections)
    static int C1; // lower price limit
    static int C2; // upper price limit

    static Node<Edge> first; // first edge of sorted edges
    static Node<Edge> last; // last edge of sorted edges
    static Node<Edge> lowest; // lowest edge

    static STNode[] spanning_tree = new STNode[2000];
    static int total;

    static int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    
    private static class STNode {
        LinkedList<Integer> vertices;
        
        STNode() {
            vertices = new LinkedList<Integer>();
        }
    }

    private static class Node<E> {

        E value;
        Node prev, next;

        Node(E value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private static class Edge {

        int A, B, C;

        Edge(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }
    }

    private static void parseFirstLine(String line) {
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

    private static final Edge getEdge(String line) {
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
    
    private static final void printEdges() {
        Node<Edge> tmp = first;
        while (tmp != null) {
            System.out.println(tmp.value.A + " " + tmp.value.B + " " + tmp.value.C);
            tmp = tmp.next;
        }
    }
    
    private static void remove(Node n) {
        if (n.prev == null && n.next == null) {
            first = null;
            last = null;
            return;
        }
        
        if (n.prev == null) {
            first = n.next;
            first.prev = null;
            n.next = null;
            return;
        }
        
        if (n.next == null) {
            last = n.prev;
            n.prev = null;
            last.next = null;
            return;
        }
        
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // obtaining N, M, C1 and C2 params
        parseFirstLine(in.readLine());

        // creating sorted list od edges
        last = new Node(getEdge(in.readLine()), null, null);
        first = last;
        lowest = last;
        for (int i = 1; i < M; i++) {
            Edge e = getEdge(in.readLine());
            Node<Edge> n = first;

            while (n != null && n.value.C < e.C) {
                n = n.next;
            }

            // adding to the end
            if (n == null) {
                last.next = new Node(e, last, null);
                last = last.next;
                continue;
            }

            // adding to the beginning
            if (n.prev == null) {
                n.prev = new Node(e, null, n);
                first = n.prev;
                continue;
            }

            // adding in between two nodes
            n.prev = new Node(e, n.prev, n);
            n.prev.prev.next = n.prev;

            // obtaining edge with lowest price
            if (last.value.C < lowest.value.C) {
                lowest = last;
            }
        }
        
        // removing all edges used in original spanning tree
        Node<Edge> n = first;
        while (n != null) {
            Edge e = n.value;
            
            if (spanning_tree[e.A] == null) spanning_tree[e.A] = new STNode();
            spanning_tree[e.A].vertices.addLast(e.B);
            if (spanning_tree[e.B] == null) spanning_tree[e.B] = new STNode();
            spanning_tree[e.B].vertices.addLast(e.A);
            
            // TODO test na zacykleni - začít u vkládaného prvku, pro všechny v daném listu..., tmp boolean[2000] na visited stavy
            // test, zdali jsme se z vycházejícího dostali k tomu samému (asi nějaká rekurze)
            
            // pokud test projde, tak přičteme ohodnocení hrany k celkovému součtu total+=e.C posuneme další hranu na n.next a vymažeme hranu ze seznamu hran - remove(n.prev), a continue
            // pokud ne, tak vymažeme vrcholy v obou seznamech - removeLast()
            n = n.next;
        }
        System.out.println(total);
        // TODO minimalni kostra
        // TODO odstraneni hrany
        // TODO dalsi krok algoritmu
    }
}
