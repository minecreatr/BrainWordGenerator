package com.minecreatr.brainword;

import com.minecreatr.brainword.syntax.ExecuteManager;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

public class BrainWord {

    public static void main(String[] args){

        String hello = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>";
        ExecuteManager manager = new ExecuteManager(hello);
        boolean tick = true;
        while (tick){
            tick = manager.tick();
        }


//        String code = encode("Help");
//
//        StringSelection stringSelection = new StringSelection(code);
//        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clpbrd.setContents(stringSelection, null);
//        System.out.println("Encoded into "+code);
    }

    public static String encode(String message){
        int lowest = Integer.MAX_VALUE;

        int highest = 0;

        byte[] bytes = message.getBytes();

        for (byte b : bytes){
            if (b < lowest){
                lowest = b;
            }
            if (b > highest){
                highest = b;
            }
        }

        int mid = lowest + (highest - lowest);

        String code = "";
        code = code + valueChange(mid, message.length());
        for (int i = 0 ; i < message.length() ; i++ ){
            code = code + '>';
            if (bytes[i] == mid){
                continue;
            }
            int dif = bytes[i] - mid;
            char c = '+';
            if (mid < 0 ){
                c = '-';
            }
            for (int j = 0 ; j < dif ; j++ ){
                code = code + c;
            }
        }
        for (int i = 0 ; i < message.length() ; i++ ) {
            code = code + '<';
        }
        for (int i = 0 ; i < message.length() ; i++ ) {
            code = code + ">.";
        }
        return code;

    }

    private static String valueChange(int value, int charLen){
        if (value == 0){
            return "";
        }
        char valueChange = '+';

//        char pointerChange = '>';
//        char pointerBack = '<';

        if (value < 0){
            valueChange = '-';
        }
//        if (targetOffset < 0){
//            pointerChange = '<';
//            pointerBack = '>';
//        }
        value = Math.abs(value);
        //targetOffset = Math.abs(targetOffset);
        String statement = "";
        int[] factors = getSmallestFactors(value);
        //Set value for loop
        for (int i = 0 ; i < factors[0] ; i++ ){
            statement = statement + valueChange;
        }
        statement = statement + '[';

        String valueOffset = "";
        //Change value at pointer
        for (int i = 0 ; i < factors[1] ; i++ ){
            valueOffset = valueOffset + valueChange;
        }

        //Shift pointer inside loop
        for (int i = 0 ; i < charLen ; i++ ){
            statement = statement + '>' + valueOffset;
        }

        //Reset pointer to loop location
        for (int i = 0 ; i < charLen ; i++ ){
            statement = statement + '<';
        }
        //End loop and decrement loop value
        statement = statement + "-]";
        return statement;
    }

    private static int[] getSmallestFactors(int num){
        List<Integer[]> factors = new ArrayList<Integer[]>();

        for (int i = 0 ; i < num ; i++ ){
            if (i == 0){
                continue;
            }
            if ((num % i) == 0){
                factors.add(new Integer[]{i, num/i});
            }
        }
        int lowest = Integer.MAX_VALUE;
        int indexLowest = 0;

        for (int i = 0 ; i < factors.size() ; i++ ){
            Integer[] fac = factors.get(i);
            if ((fac[0]+fac[1])< lowest ){
                lowest = (fac[0]+fac[1]);
                indexLowest = i;
            }
        }
        Integer[] fac = factors.get(indexLowest);
        if (fac[0] >= fac[1]){
            return new int[] {fac[0], fac[1]};
        }
        else {
            return new int[] {fac[1], fac[0]};
        }

    }


}
