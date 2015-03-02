/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author SARJIT
 */
public class FunnyString {
    public static void main(String args[]){
        int T;
        String str[],ans[];
        Scanner scr = new Scanner(System.in);
        T = scr.nextInt();
        str = new String[T];
        ans = new String[T];
        for(int i = 0; i < T; i++)
        {
            str[i] = scr.next();
        }
        for(int i =0; i < T; i++)
        {
            String temp = str[i];
            char arr[] = new char[temp.length()];
            arr = temp.toCharArray();
            arr = reverse(arr,0,arr.length-1);
            temp = new String(arr);
            ans[i] = "abc";
            System.out.println("String : " + str[i]);
            for(int j = 1; j < temp.length(); j++){
                System.out.println("(temp.charAt(" + j + ") - temp.charAt(" + (j-1) + ")" + (temp.charAt(j) - temp.charAt(j-1)));
                System.out.println("( str[i].charAt( " + (j) + ")  - str[i].charAt(" + (j-1) + " ) " + ( str[i].charAt(j) - str[i].charAt(j-1) ) );
                if( Math.abs(temp.charAt(j) - temp.charAt(j-1) ) != Math.abs( str[i].charAt(j) - str[i].charAt(j-1) ) ){
                    ans[i] = "Not Funny";
                }
                System.out.println("\n");
            }
            if(ans[i].equals("abc"))
            {
                ans[i] = "Funny";
            }
        }
        for(int i = 0 ; i < T; i++)
        {
            System.out.println(ans[i]);
        }
    }
    
    public static char[] reverse(char[] arr,int start, int end){
        char temp;
        while(start < end)
        {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            end--;
            start++;
        }
        return arr;
    }
}
