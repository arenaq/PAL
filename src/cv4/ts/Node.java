/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv4.ts;

import java.util.LinkedList;

/**
 *
 * @author arenaq
 */
public class Node {

    public String data;
    public int dist;
    public int inDegree;
    LinkedList<Node> AdjacenctNode = new LinkedList<Node>();

    public void addAdjNode(final Node Child) {
        AdjacenctNode.add(Child);
        Child.inDegree++;
    }

    public Node(String data) {
        super();
        this.data = data;
    }
}
