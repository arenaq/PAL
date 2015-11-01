package du1;
// kruskal

import java.util.*;

public class Kruskal2 {

  // edges: [cost,from,to]
    public static int[][] mstKruskal(int[][] edges, int n) {

        UnionFind uf = new UnionFind(n);

        Arrays.sort(edges, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] < e2[0]) {
                    return -1;
                }
                if (e1[0] > e2[0]) {
                    return 1;
                }
                return 0;
            }
        });

        int[][] mst = new int[n - 1][];
        int offset = 0;

        for (int[] edge : edges) {

            int root1 = uf.find(edge[1]);
            int root2 = uf.find(edge[2]);

            if (root1 != root2) {
                mst[offset++] = edge;
                if (offset == n - 1) {
                    break;
                }
                uf.union(root1, root2);
            }
        }
        return mst;
    }

    public static void main(String[] args) {

        int[][] graph = new int[][]{new int[]{28, 0, 1},
        new int[]{14, 1, 2},
        new int[]{11, 2, 3},
        new int[]{4, 4, 5},
        new int[]{20, 5, 6},
        new int[]{16, 6, 7},
        new int[]{15, 8, 9},
        new int[]{6, 9, 10},
        new int[]{23, 10, 11},
        new int[]{21, 12, 13},
        new int[]{12, 13, 14},
        new int[]{13, 14, 15},
        new int[]{3, 0, 4},
        new int[]{27, 4, 8},
        new int[]{9, 8, 12},
        new int[]{26, 1, 5},
        new int[]{5, 5, 9},
        new int[]{29, 9, 13},
        new int[]{24, 2, 6},
        new int[]{2, 6, 10},
        new int[]{25, 10, 14},
        new int[]{7, 3, 7},
        new int[]{10, 7, 11},
        new int[]{8, 11, 15}
        };

        System.out.println("graph = " + Arrays.deepToString(graph));

        int[][] mst = mstKruskal(graph, 16);

        System.out.println("mst = " + Arrays.deepToString(mst));

    }

}
