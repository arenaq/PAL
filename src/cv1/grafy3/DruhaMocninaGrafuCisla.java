package cv1.grafy3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Petr Kuška
 */
public class DruhaMocninaGrafuCisla {

    public static void main(String[] args) throws IOException {
        new First().run();
    }

    private static class First {

        int pocet_hran;
        Node first;
        Node last;
        Node results;

        void run() throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            // ziskani poctu hran z první řádky
            try {
                pocet_hran = Integer.parseInt(in.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // sestrojeni grafu G
            for (int i = 0; i < pocet_hran; i++) {
                Tuple tuple = getTuple(in.readLine());
                if (first == null) {
                    first = new Node(tuple, null, null);
                    last = first;
                    continue;
                }
                // vložení prvku na správné místo v seznamu, aby byl seznam seřazený
                Node n = last; // prvek, za který budeme přidávat
                while (n != null) {
                    if (tuple.a <= n.t.a) {
                        n = n.prev;
                    }
                }
                if (n != null) {
                    Node t = new Node(tuple, n, n.next);
                    n.next = t;
                    if (t.next == null) {
                        last = t;
                    }
                } else { // vkládáme na začátek
                    first.prev = new Node(tuple, null, first);
                    first = first.prev;
                }
            }

            // vypocet vysledku
            last = first;
            while (last != null) {
                Node n = last.next;
                while (n != null) {
                    if (last.t.b == n.t.a) {
                        boolean contained = false;
                        Node r = results;
                        while (r != null) {
                            if (r.t.a == last.t.a && r.t.b == n.t.b) {
                                contained = true;
                                break;
                            }
                            r = r.next;
                        }
                        if (!contained) {
                            if (results != null) {
                                results.prev = new Node(new Tuple(last.t.a, n.t.b), null, results);
                                results = results.prev;
                            } else {
                                results = new Node(new Tuple(last.t.a, n.t.b), null, null);
                            }
                        }
                    }
                    n = n.next;
                }
                last = last.next;
            }

            // vypis vysledku
            StringBuilder sb = new StringBuilder();
            sb.append("-------------\n");
            Node r = results;
            while (r != null) {
                sb.append(r.t.a + " " + r.t.b + "\n");
                r = r.next;
            }
            System.out.println(sb);
        }

        private class Node {

            Tuple t;
            Node prev;
            Node next;

            Node(Tuple t, Node p, Node n) {
                this.t = t;
                this.prev = p;
                this.next = n;
            }
        }

        private class Tuple {

            int a, b;

            Tuple(int a, int b) {
                this.a = a;
                this.b = b;
            }
        }

        /* optimizing input parsing */
        final int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

        public final Tuple getTuple(String line) {
            int x = 0;
            int y = 0;

            int len = line.length();
            int index = len - 1;
            while (index > 0 && line.charAt(index) != ' ') {
                char c = line.charAt(index);
                y += (c - '0') * pow[len - index - 1];
                index--;
            }

            len = index;
            index--;
            while (index >= 0) {
                char c = line.charAt(index);
                x += (c - '0') * pow[len - index - 1];
                index--;
            }
            // ošetření pořadí v rámci hrany
            if (y < x) {
                return new Tuple(y, x);
            } else {
                return new Tuple(x, y);
            }
        }
    }

}
