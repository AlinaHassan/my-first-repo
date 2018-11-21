/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package toa_assignment.pkg1;

import java.util.Scanner;

/**
 *
 * @author Alina
 */


//A program that simulates DfA of language {w:w is an integer atleast 23} over the alphabet {0,1,2,.....,9}.
//Words in this language should not have more than three trailing zeros (i.e 10000 is not valid).


class inputs
{
    char value;
    node nextState;
    inputs()
    {
        
        this.nextState=null;
    }
}
class node  //state
{
    inputs[] in;
    node()
    { }
    node(int n)
    {
        in= new inputs[n];
    }
}

//DFA is a 5-tuple(Q,Σ,δ,q0,F)
//Q set of all possbile states
//Σ is the alphabet
// δ: QxΣ-> Q is the transition function (does mapping)
//q0 is the initial/startstate
//F is the set of accepting/final states
class DFA
{
    node[] possiblestates;//Q
    node startstate;  //q0
    node[] finalstates; //F
    char[] alphabets;//Σ
    node currentstate;// this will be needed while processing string
    DFA()
    {
        alphabets= new char[]{'0','1','2','3','4','5','6','7','8','9'};
        possiblestates=new node[14];
        for(int i=0;i<possiblestates.length;i++)                        //initializing all possiblestates (nodes)
        {
            possiblestates[i]=new node(alphabets.length);
            for(int j=0;j<alphabets.length;j++)//Also, initializing the input i[] array (i.e inside class node) with the alphabets for all possiblestates   
            {
                possiblestates[i].in[j]=new inputs();
                possiblestates[i].in[j].value=alphabets[j];
            }
        }
        startstate=possiblestates[0];
        
        // final states        
        finalstates= new node[6];
        for(int i=0;i<finalstates.length;i++)
            finalstates[i]= new node();
        int []finals= new int[]{2,3,4,5,6,13};
        for(int i=0;i<finalstates.length;i++)
        {
            finalstates[i]=possiblestates[finals[i]];
        }
        addtransitions();
    }
    
    void transitions(int oldstate, int[]newstate)
    {
        //initializing the nextstates for all posssiblestates 
        for(int i=0;i<alphabets.length;i++)
        {
            possiblestates[oldstate].in[i].nextState= possiblestates[newstate[i]];
        }
    }
            
    void addtransitions()//δ: QxΣ-> Q  // mapping
    {
        int []state0= new int[]{0,8,11,1,1,1,1,1,1,1}; //transitions form state0 on all alphabets to the state numbers mentioned in array
        int []state1= new int[]{4,2,2,2,2,2,2,2,2,2};
        int []state2_9_13= new int[]{4,3,3,3,3,3,3,3,3,3};
        int []state3= new int[]{4,13,13,13,13,13,13,13,13,13};
        int []state4_10_12= new int[]{5,3,3,3,3,3,3,3,3,3};
        int []state5= new int[]{6,3,3,3,3,3,3,3,3,3};
        int []state6_7= new int[]{7,3,3,3,3,3,3,3,3,3};
        int []state8= new int[]{10,9,9,9,9,9,9,9,9,9};
        int []state11= new int[]{12,9,9,9,3,3,3,3,3,3};
        
        for(int i=0;i<possiblestates.length;i++)
        {
            if(i==0)
                transitions(i,state0);
            if(i==1)
                transitions(i,state1);
            if(i==2 || i==9 || i==13)
                transitions(i,state2_9_13);
            if(i==3)
                transitions(i,state3);
            if(i==4 || i==10 || i==12)
                transitions(i,state4_10_12);
            if(i==5)
                transitions(i,state5);
            if(i==6 || i==7)
                transitions(i,state6_7);
            if(i==8)
                transitions(i,state8);
            if(i==11)
                transitions(i,state11);
        }
    }

    boolean  process(String s)
        {
            currentstate=startstate;
            int i,j;
            
            for(i=0;i<s.length();i++)
            {
                //searching index of inputs[] array (i.e inside node class) at which the value is equal to s[i]
                //the length of inputs[] array for each state (node) is equal to alphabets length
                for(j=0;j<alphabets.length;j++)
                {
                    if(currentstate.in[j].value==s.charAt(i))
                    break;
                }
                if(j==alphabets.length)// if character s[i] doesnot match with any alphabet
                    return false;
                //the index holds the information of next state on which we'll move after taking input s[i] 
                currentstate=currentstate.in[j].nextState;  // traversing through states
            }
            
            for(i=0;i<finalstates.length;i++)
            {
                if( currentstate == finalstates[i])
                  return true;
            }
            return false;
        }
 }

class TOA_assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DFA d= new DFA();
        String s;
        boolean res;
        Scanner input= new Scanner(System.in);
        System.out.println("Enter a string: ");
        s=input.nextLine();
        res=d.process(s);
        if(res==true)
            System.out.println("\n\nRESULT:\nstring accepted...\n");
        else
            System.out.println("\n\nRESULT:\nstring rejected...\n");
    }
    
}

