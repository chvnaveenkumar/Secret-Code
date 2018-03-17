package com.example.s530742.chandaluri_program05;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Naveen Kumar Chandaluri on 2/26/2018.
 */

class CodeWord {

    private ArrayList<String> code,guess,symbols;
    private int count=0,onSymbol,attempts;
    private String ascii_symbol ;
    private Boolean status;
    private int ascii_number =48;
    boolean s = true;
    public CodeWord(int count)
    {
        symbols = new ArrayList<>();
        code = new ArrayList<>();
        guess = new ArrayList<>();
        onSymbol =0;
        attempts=0;
        status = false;
        ascii_number = 48;
        Random r = new Random();
        for(int i=0;i<6;i++)
        {
            symbols.add(Character.toString((char)(ascii_number + r.nextInt(10))));
            if(ascii_number  == 48) // Numbers
                ascii_number = 65;
            else if(ascii_number == 65) //Letters
                ascii_number = 97;
            else if(ascii_number == 97) {
                if(s == true) {
                    symbols.add("\u265A");
                    s = false;
                }
                else
                symbols.add("\u280A");
                i++;
                ascii_number = 48;
            }
        }
        for(int i=0;i<count;i++) {
            code.add(symbols.get(r.nextInt(6)));
        }
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    public ArrayList<String> getGuess() {
        return guess;
    }

    public void setGuess(ArrayList<String> guess) {
        this.guess = guess;
    }

    public ArrayList<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(ArrayList<String> symbols) {
        this.symbols = symbols;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOnSymbol() {
        return onSymbol;
    }

    public void setOnSymbol(int onSymbol) {
        this.onSymbol = onSymbol;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getAscii_number() {
        return ascii_number;
    }

    public void setAscii_number(int ascii_number) {
        this.ascii_number = ascii_number;
    }
}
