/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv1.grafy3;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author arenaq
 */
public class Test {
    private static final String line = "aaaaaaaaaaaaaaaaaaaaaa bbbbbbbbbbbbbbbbbbbbbbbb";
    private static final int RUNS = 100000000;// 000000;
    static int[] pow = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    
    public static void main(String[] args) throws IOException {
        //testIndexOf2();
        testSubStringFor();
        testSubStringWhile();
        testSubStringWhile2();
        testIncrementChar();
        testStringBuilder();
        testStringTokenizer();
    }

    public static final void testIndexOf2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {

            int x = 0;
            int y = 0;

            int len = line.length();
            int index = len - 1;
            while (index > 0 && line.charAt(index) != ' ') {
                char c = line.charAt(index);
                switch (c) {
                    case '+':
                        break;
                    case '-':
                        y *= -1;
                        break;
                    default:
                        y += (c - '0') * pow[len - index - 1];
                        break;
                }

                index--;
            }

            len = index;
            index--;
            while (index >= 0) {
                char c = line.charAt(index);
                switch (c) {
                    case '+':
                        break;
                    case '-':
                        x *= -1;
                        break;
                    default:
                        x += (c - '0') * pow[len - index - 1];
                        break;
                }

                index--;
            }
        }
        System.out.println("IndexOf2: " + (System.currentTimeMillis() - start) + "ms");
    }

    public static final void testSubStringFor() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            int index = 0;
            for (index = 0; index < line.length(); index++) {
                if (line.charAt(index) == ' ') break;
            }
            
            String x = line.substring(0, index);
            String y = line.substring(index + 1);
        }
        System.out.println("SubStringFor: " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static final void testSubStringWhile() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            int index = 0;
            while (line.charAt(index) != ' ') index++;
            
            String x = line.substring(0, index);
            String y = line.substring(index + 1);
        }
        System.out.println("SubStringWhile: " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static final void testSubStringWhile2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            int index = 0;
            while (line.charAt(index) != ' ') {
                index++;
            }
            
            String x = line.substring(0, index);
            String y = line.substring(index + 1);
        }
        System.out.println("SubStringWhile2: " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static final void testIncrementChar() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            String x = "";
            
            int index = 0;
            for (index = 0; index < line.length(); index++) {
                char c = line.charAt(index);
                if (c == ' ') break;
                x += c;
            }
            
            String y = line.substring(index + 1);
        }
        System.out.println("IncrementChar: " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static final void testStringBuilder() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            StringBuilder sb = new StringBuilder();
            
            int index = 0;
            for (index = 0; index < line.length(); index++) {
                char c = line.charAt(index);
                if (c == ' ') break;
                sb.append(c);
            }
            
            String x = sb.toString();
            String y = line.substring(index + 1);
        }
        System.out.println("StringBuilder: " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static final void testStringTokenizer() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            StringTokenizer st = new StringTokenizer(line, " ");
            String x = st.nextToken();
            String y = st.nextToken();
        }
        System.out.println("StringTokenizer: " + (System.currentTimeMillis() - start) + "ms");
    }
    
}
