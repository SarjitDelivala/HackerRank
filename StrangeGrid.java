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
public class StrangeGrid {
    public static void main(String args[])
    {
        long odd=0,even=0,row,col,first=0;
        Scanner scr = new Scanner(System.in);
        row = scr.nextInt();
        col = scr.nextInt();
        if(row % 2 == 0)
        {
            even = row / 2;
            first = ( ( (even - 1) * 5 ) * 2 ) - 1;
            for(int i = 1;i <= col; i++){
                first +=2;
            }
        }
        else
        {
            odd = (row + 1) / 2;
            first = ( ( (odd - 1) * 5 ) * 2 );
            for(int i = 1;i < col; i++){
                first +=2;
            }
        }
        System.out.println("Even : " + even + "\todd : " + odd + "\tfirst : " + first);
        
        
        System.out.println(first);
    }
}