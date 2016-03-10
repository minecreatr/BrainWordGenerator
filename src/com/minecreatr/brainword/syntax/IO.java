package com.minecreatr.brainword.syntax;

import java.io.IOException;

public class IO {

    public void print(byte c){
        System.out.println(c);
    }

    public byte readChar(){
        try {
            return (byte) System.in.read();
        }
        catch (IOException exception) {
            return (char) 0;
        }
    }

}
