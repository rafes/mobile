package com.example.sabina.hillcipher;

import android.util.Log;

import java.util.HashMap;

import java.lang.Math;

public class Encryptor {
    private HashMap<String,Integer> characterCodes=new HashMap();

    Encryptor(){
        characterCodes.put("_",0);
        characterCodes.put("a",1);characterCodes.put("b",2);characterCodes.put("c",3);characterCodes.put("d",4);characterCodes.put("e",5);
        characterCodes.put("f",6);
        characterCodes.put("g",7);characterCodes.put("h",8);characterCodes.put("i",9);characterCodes.put("j",10);
        characterCodes.put("k",11);characterCodes.put("l",12);characterCodes.put("m",13);characterCodes.put("n",14);
        characterCodes.put("o",15);characterCodes.put("p",16);characterCodes.put("q",17);
        characterCodes.put("r",18);characterCodes.put("s",19);characterCodes.put("t",20);characterCodes.put("u",21);
        characterCodes.put("v",22);characterCodes.put("w",23);characterCodes.put("x",24);characterCodes.put("y",25);
        characterCodes.put("z",26);
    }

    public int[][] getMatrix(String text)
    {

        int[][] matrix;
        int length=text.length();

        if(length%2==0)
        {
            matrix=new int[length/2][2];
        }
        else{
            matrix=new int[length/2 +1][2];
        }
        int index=0;
        for(int i=0;i<length/2;i++) {
            for(int j=0;j<2;j++)
            {
                if(index<length){
                    matrix[i][j]=characterCodes.get(Character.toString(text.charAt(index)));
                    index++;
                }
                else
                {
                    matrix[i][j]=0;
                }
            }

        }
        return matrix;
    }

    public String getTextFromMatrix(int[][]matrix)
    {
        String text="";
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<2;j++)
            {
                text+=getKey(matrix[i][j]);
            }
        }
        return text;
    }

    private String getKey(Integer value){
        for(String key : characterCodes.keySet()){
            if(characterCodes.get(key).equals(value)){
                return key;
            }
        }
        return null;
    }

    boolean isValid(String key)
    {
        for(int i=0;i<key.length();i++) {
            if(!characterCodes.containsKey(Character.toString(key.charAt(i)))) return false;

        }
        return true;
    }

    boolean keyIsValid(String key)
    {
        if(isValid(key))
        {
            int[][] matrix=getMatrix(key);
            int determinant=matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
            boolean divisor=determinant%27!=0;
            divisor=divisor&&(27%determinant!=0);
            if(determinant!=0 && divisor &&key.length()==4)
            {
                return true;
            }
        }
        return false;
    }

    String invalidCharacters(String text)
    {
        String characters="";
        for(int i=0;i<text.length();i++) {
            if (!characterCodes.containsKey(Character.toString(text.charAt(i))))
            {characters+=Character.toString(text.charAt(i))+" ";}
        }
        return characters;
    }

    int[][] simplifyMatrix(int[][]m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] > 27) {
                    m[i][j]=m[i][j]%27;
                }
                else if(m[i][j]<0)
                {
                    m[i][j] = m[i][j]%27;
                    while(m[i][j]<0)
                    {
                        m[i][j]+=27;
                    }
                }
            }
        }
        return m;
    }

    public  int[][] multiplicator(int[][] A, int[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        int[][] C = new int[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0;
            }
        }

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                for (int k = 0; k < aColumns; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
    String printMatrix(int[][]matrix)
    {
        String prt="";
        prt+="{"+Integer.toString(matrix[0][0]);
        prt+=","+Integer.toString(matrix[0][1])+"}";
        prt+="{"+Integer.toString(matrix[1][0]);
        prt+=","+Integer.toString(matrix[1][1])+"}";
        return  prt;
    }

    String calculateEncryption(String plainTxt, String encryptKey)
    {
        int[][] matrixA=getMatrix(plainTxt);
        int[][] matrixB=getMatrix(encryptKey);
        int[][] result=multiplicator(matrixA,matrixB);
        int[][] finalR=simplifyMatrix(result);
        return getTextFromMatrix(finalR);

    }

    int[][] calculateKeyInverse(int[][]key)
    {
        //int[][] trans={{key[1][1],-key[0][1]},{-key[1][0],key[0][0]}};
        int determinant=key[0][0]*key[1][1]-key[0][1]*key[1][0];
        int det=0;
        for(int i=0;i<27;i++)
        {
            if((determinant*i)%27==1){
                det=i;
            }
        }
        int[][] trans=new int[2][2];
        trans[0][0]=(key[1][1]*det)%27;
        trans[0][1]=(key[0][1]*(-det))%27;
        trans[1][0]=(key[1][0]*(-det))%27;
        trans[1][1]=(key[0][0]*det)%27;
        return trans;
    }

    String calculateDescryption(String encryptedText, String decryptionKey)
    {
        int[][] matrixA=getMatrix(encryptedText);
        int[][] matrixB=getMatrix(decryptionKey);
        int[][] inverseKey=calculateKeyInverse(matrixB);
        int[][] result=multiplicator(matrixA,inverseKey);
        //return result;
        return getTextFromMatrix(simplifyMatrix(result));
    }

}
