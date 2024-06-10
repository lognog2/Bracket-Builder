package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final int total = 16;
        Bracket bracket = new Bracket();
        for (int i = 1; i <= total; i++) {
            bracket.add(i);
        }
        bracket.orderBracket();
        System.out.print(bracket.play(false) + " wins!");
    }
}

class Bracket {

    private List<Integer> bracket;

    public Bracket() {
        bracket = new ArrayList<>();
    }
    public void add(int num) {bracket.add(num);}
    public void remove(int num) {bracket.remove(num);}

    public Integer getInt(int index) {
        Integer val = bracket.get(index);
        return val;
    }

    public void orderBracket() {
        int bracketSize = bracket.size();
        int orderedSize = nextFactorOfTwo(bracketSize);
        List<Integer> orderedBracket = new ArrayList<>(orderedSize);

        orderedBracket.add(bracket.remove(0));
        for (int n = 2; n <= orderedSize; n *= 2) {
            for (int i = 0; i < n/2; i++) {
                Integer thisInt = bracket.isEmpty() ? ((n/2)+1+i)*-1 : bracket.remove(0);
                Integer pairing = (n+1) - Math.abs(thisInt);
                orderedBracket.add(1 + orderedBracket.indexOf(pairing), thisInt);
            }  
        }
        bracket = orderedBracket;
    }

    public int play(boolean debug) {
        while (bracket.size() > 1) {
            print();
            for (int i = 0; i < bracket.size(); i++) {
                Integer int1 = getInt(i);
                Integer int2 = getInt(i+1);
                if (debug) System.out.print(int1 + " vs " + int2 + ": ");
                if (int1 <= 0) {
                    bracket.remove(int1);
                    if (debug) System.out.println(int2 + " on bye"); 
                } else if (int2 <= 0) {
                    bracket.remove(int2);
                    if (debug) System.out.println(int1 + " on bye"); 
                } else {
                    //chooses a random number between 1 and both numbers combined and eliminates which int it's in the range of
                    int score = 1 + (int)(Math.random() * (int1 + int2));
                    if (debug) System.out.print(score + ", ");
                    if (score <= int1) {
                        bracket.remove(int1);
                        if (debug) System.out.println(int2 + " wins");
                    } else {
                        bracket.remove(int2);
                        if (debug) System.out.println(int1 + " wins");
                    }
                }
                
            }
        }
        return bracket.get(0);
    }

    public void print() {
        for (int i = 0; i < bracket.size(); i++) {
            System.out.print(getInt(i));
            if (i % 2 == 0) System.out.print("/"); 
            else System.out.print(" ");
        }
        System.out.println();
    }

    private int nextFactorOfTwo(int num) {
        if (num < 1) return 0;
        int factor = 2;
        while (num > factor) {
            factor *= 2;
        }
        return factor;
    }
}