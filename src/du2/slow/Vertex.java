/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package du2.slow;

import java.util.ArrayList;

/**
 *
 * @author arenaq
 */
public class Vertex {
    int id;
    int weigth;
    ArrayList<Vertex> neighbors;
    
    Vertex(int id, int weight) {
        this.id = id;
        this.weigth = weight;
        neighbors = new ArrayList<>();
    }
    
    void add(Vertex v) {
        neighbors.add(v);
    }
}
