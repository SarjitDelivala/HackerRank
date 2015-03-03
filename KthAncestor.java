/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hackerrank;

import java.util.Scanner;

/**
 *
 * @author SARJIT
 */

class Node
{
    private int data;
    private Node child;
    private Node right;
    private Node left;
    private Node parent;
    
    Node(int d)
    {
        data = d;
        child = left = parent = right = null;
    }
    
    public void setChild(Node c)
    {
        child = c;
    }
    
    public Node getChild()
    {
        return child;
    }
    
    public void setData(int d)
    {
        data = d;
    }
    
    public int getData()
    {
        return data;
    }
    
    public void setParent(Node p)
    {
        parent = p;
    }
    
    public Node getParent()
    {
        return parent;
    }
    
    public void setRight(Node r,Node p)
    {
        right = r;
        parent = p;
    }
    
    public Node getRight()
    {
        return right;
    }
    
    public void setLeft(Node l)
    {
        left = l;
    }
    
    public Node getLeft()
    {
        return left;
    }
}

class Tree
{
    private Node root;
    
    Tree(int data)
    {
        root = new Node(data);
        root.setParent(new Node(0));
    }
    
    public void addChild(int parent,int child)
    {
        Node pNode = searchNode(parent,root);
        Node newNode = new Node(child);
        Node p = pNode;
        //System.out.println("Node : " + child);
  //      System.out.println("Parent found : " + p.getData());
        newNode.setParent(p);
        if(pNode.getChild() == null)
            pNode.setChild(newNode);
        else
        {
            pNode = pNode.getChild();
            while(pNode.getRight() != null)
            {
                pNode = pNode.getRight();
            }
            newNode.setLeft(pNode);
            pNode.setRight(newNode, p);
        }
        
        //System.out.println("Node added under : " + p.getData());
    }
    
    public Node searchNode(int parent,Node pNode)
    {
        Node temp = null;
        
        //System.out.println("Searching : " + parent + "\t Node data : " + pNode.getData());
        if(pNode.getData() == parent){
            return pNode;
        }
            
        
        if(pNode.getRight() == null && pNode.getChild() == null)
        {
            return pNode;
        }
        
        if(pNode.getChild() != null){
            temp = searchNode(parent, pNode.getChild());
        }
        
        if(temp != null){
            if(temp.getData() == parent )
            return temp;
        }
        
        if(pNode.getRight() != null){
            temp = searchNode(parent, pNode.getRight());
        }
        
        
            return temp;
        
    }
    
    public Node removeNode(int node,Node pNode)
    {
        Node temp = null;
        if(pNode == null)
        {
            return null;
        }
        
        if(pNode.getData() == node){
            
            /*
            If we are removing the first child then link of parent to child should be set to the second child.
            */
            
            if(pNode.getParent().getChild() == pNode && pNode.getRight() != null)
            {
                pNode.getParent().setChild(pNode.getRight());
            }
            /*
            If we are removing a child who is not the last but somewhere in between some nodes
            */
            else if(pNode.getRight() != null && pNode.getParent().getChild() != pNode)
            {
                pNode.getRight().setLeft(pNode.getLeft());
                pNode.getLeft().setRight(pNode.getRight(), pNode.getParent());
            }
            else if(pNode.getParent().getChild() == pNode && pNode.getRight() == null)
            {
                pNode.getParent().setChild(null);
            }
            else
            {
                pNode.getLeft().setRight(null, pNode.getParent());
            }
            pNode = null;
            return pNode;
        }
        if(pNode.getChild() != null)
            temp = removeNode(node,pNode.getChild());
        
        
        
        if(pNode.getRight() != null)
            temp = removeNode(node,pNode.getRight());
        
        return temp;
        
    }
    
    public void traverse(Node pNode)
    {
        
        if(pNode == null)
        {
            return;
        }
        
        System.out.print(pNode.getData() + " ");
        /*if(pNode.getRight() == null && pNode.getChild() == null)
            return pNode;*/
        
        if(pNode.getChild() != null)
            traverse(pNode.getChild());
        
        if(pNode.getRight() != null)
            traverse(pNode.getRight());
        
    }
    
    public int searchKthAncestor(int k,Node node)
    {
        int cnt = 0;
        if(node == null)    return 0;
        while(k > 0)
        {
            k--;
            if((node == getRoot() && k > 0) || (k < 0))
            {
                return 0;
            }
            node = node.getParent();
            cnt = node.getData();
        }
        return cnt;
    }
    
    public Node getRoot()
    {
        return root;
    }
}

public class KthAncestor {
    public static void main(String args[]) throws Exception
    {
        int T,Q,P,arr[][];
        String str;
        Scanner scr = new Scanner(new File("D:\\Input.txt"));
        T = scr.nextInt();
        Tree t = null;
        for(int i = 0; i < T; i++)
        {
            int index = 0;
            P = scr.nextInt();
            arr = new int[P][2];
            for(int j = 0; j < P; j++)
            {
                arr[j][0] = scr.nextInt();
                arr[j][1] = scr.nextInt();
                if(arr[j][1] == 0)  index = j;
            }
            arr = arrangeValues(arr,index); // Arranging the array
            for(int j = 0; j < P; j++)
            {
                if( arr[j][1] == 0)
                {
                    t = new Tree(arr[j][0]);
                }
                else
                {
                    t.addChild(arr[j][1],arr[j][0]);
                }
            }
            /*System.out.println("\n");
            t.traverse(t.getRoot());
            System.out.println("\n");*/
            //System.out.println("Enter Q : ");
            Q = scr.nextInt();
            for(int j = 1; j <= Q; j++)
            {
                
                str = scr.nextLine();
                if(str.equals("")){
                    j-=1;
                    continue;
                }
                
                //System.out.println("Checking  : " + str);
                String cols[] = str.split(" ");
                //System.out.println(cols.length + "  " + cols[0]);
                switch (cols[0]) {
                    case "0":
                        t.addChild(Integer.parseInt(cols[1]),Integer.parseInt(cols[2]));
                        break;
                    case "1":
                        t.removeNode(Integer.parseInt(cols[1]), t.getRoot());
                        
                        break;
                    default:
                        int k;
                        Node temp = t.searchNode(Integer.parseInt(cols[1]), t.getRoot());
                        if(temp.getData() != Integer.parseInt(cols[1])) k = 0;
                        else                                            k = t.searchKthAncestor(Integer.parseInt(cols[2]),temp);
                        
                        System.out.println(k);
                        break;
                }
                
            }
            //t.traverse(t.getRoot());

        }
        
        
        
    }
    /* Arranging the array 
        Main reason for creating this function is 
        if sometime a individual tree is created and then joined to the actual tree.
    For eg. : 
                    2
                  /   \
                5       3
               /
              7
    
    Now creating 8 and 9 and then joining 8 as child of 3.
    
    */
    public static int[][] arrangeValues(int arr[][], int index){
        
        boolean flag ;
        for(int i = 0; i < arr.length; i++)
        {
            flag = false;
            for(int j = i-1 ; j >= 0; j--)
            {
                if(arr[j][0] == arr[i][1])
                {
                    flag = true;
                    break;
                }
            }
            if(!flag)
            {
                int temp = firstOcc(arr, arr[i][1],i),x,y;
                x = arr[temp][0];
                y = arr[temp][1];
                arr[temp][0] = arr[i][0];
                arr[temp][1] = arr[i][1];
                arr[i][0] = x;
                arr[i][1] = y;
            }
        }
        return arr;
    }
    
    public static int firstOcc(int arr[][],int num,int start)
    {
        for(int i = start; i < arr.length; i++)
        {
            if(arr[i][0] == num)
            {
                return i;
            }
        }
        return 0;
    }
}

