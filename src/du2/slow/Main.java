package du2.slow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Petr Kuška
 */
public class Main {
    static int N; // number of nodes (vertices)
    static int M; // number of edges
    static Vertex[] vertices; /* int[][] can by used instead */
    static Tuple bestEdge;
    static int bestEdgeCost = Integer.MIN_VALUE;
    static Tuple actualEdge;
    
    static void dejCenu(Vertex vychozi, Vertex aktualni, int aktualniCena) {
        // projdeme vsechny sousedni hrany z te aktualni
        for (Vertex n : aktualni.neighbors) {
            // nesnazime se nahodou pouzit prevracenou hranu?
            if (n.id == actualEdge.b && aktualni.id == actualEdge.a) continue;
            // nejsme uz nahodou na vychozi hrane? (v cili?)
            if (n.id != vychozi.id) {
                // nejsme, tak pridame vahu aktualniho uzlu a jdeme dal
                dejCenu(vychozi, n, aktualniCena + n.weigth);
            } else {
                // jsme, tak porovname, jestli tato komponenta neni lepsi nez nase dosud nejlepsi a pokud je, tak si ji ulozime
                if (aktualniCena + n.weigth > bestEdgeCost) {
                    bestEdge = actualEdge;
                    bestEdgeCost = aktualniCena + n.weigth;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        vertices = new Vertex[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            vertices[i] = new Vertex(i, Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            vertices[v1].add(vertices[v2]);
        }
        
        /**
         * prochazim vsechny vrcholy,
         * pro kazdou hranu vrcholu predpokladam jeji otoceni tak, ze si ulozim vrchol do ktereho smeruje jako vychozi
         * dale pokracuju ostatními hranami do dalších vrcholů a z nich do dalších a dalších do té doby, dokud nenarazím na výchozí vrchol
         * v tento moment jsem našel komponentu - ukladam si pro danou otocenou hranu cenu nalezene komponenty tak, ze pokud je vyssi, nez ulozena, tak ji prepisu
         * (komponent pro otocenou hranu muze byt i vice)
         * takto projedu vsechny moznosti a na konci najdu hranu s nejvyssi cenou komponenty - tu vypisu
         * 
        */
        
        for (int i = 0; i < vertices.length; i++) {
            for (Vertex vrchol : vertices[i].neighbors) {
                actualEdge = new Tuple(vertices[i].id, vrchol.id);
                dejCenu(vrchol, vertices[i], vertices[i].weigth);
            }
        }
        
        System.out.println(bestEdge.a+" "+bestEdge.b+" "+bestEdgeCost);
    }
    
}
