import java.util.*;
public class MainProgram
{
    int bval[][]= new int[4][4]; String mess; int score;
    MainProgram()//done
    {
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                bval[i][j]=1;
        random(2); random(2);
        score = 0;
    }
    
    void random(int a)//This method assigns a to any random box which is empty  //done
    {  
        int z=0;//These for loop check the number of empty boxes
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                z=(bval[i][j]==1)? z+1:z;
        z=1+(int)(Math.random()*z);//z is assigned with a random value from 1 to z
        //a is assigned to z th empty box
        for(int i=0, k=0; i<4; i++)
            for(int j=0; j<4; j++)
                if(bval[i][j]==1)
                {
                    k++;
                    if(k==z)
                    {
                        bval[i][j]=a;
                    }
                }
    }
    
    void print()//done
    {
        System.out.println("\f\n\t\t\t2048\n\t\tScore:" + score);
        for(int i=0; i<4; i++)
        {
            System.out.print("\t|");
            for(int j=0; j<4; j++)
                System.out.print((bval[i][j]!=1)? "\t"+bval[i][j]+"\t|": "\t\t|");
            //Print the number only if its not equal to 1
           
            System.out.println("\n");//Only 4 boxes in one row
        }  
        System.out.println(mess);
    }

    void fmove4or6(int c)//done
    {  
        boolean b=false;
        if(move4or6(c)!=0)
            b=true;
        if(add4or6(c)!=0)
            b=true;
        move4or6(c);
        if(b)
            random((Math.random()<=.7)? 2:4);
        else
            mess="Move Invalid!!";
    }

    int add4or6(int a)//This is a method to add all the identical number into a large single number
    {
        int r=0;
        for(int i=0; i>=0 && i<4; i++)
            for(int j=0; j<3; j++)
            {    
                if(a==4 && bval[i][j]==bval[i][j+1] && bval[i][j]!=1)
                {
                    bval[i][j]*=2;      bval[i][j+1]=1; score += bval[i][j]; r++;
                }
                if(a==6 && bval[i][3-j]==bval[i][2-j] && bval[i][3-j]!=1)
                {
                    bval[i][3-j]*=2;   bval[i][2-j]=1;  score+=bval[i][3-j];  r++;
                }
            }
        return r;
    }
    
    int move4or6(int code)
    {
        int r=0;
        for(int i=0; i<4;i++)
            for(int k=0; k<3; k++)
                if(code==4)
                {
                    for(int j=0; j!=3; j++)                
                    {    if(bval[i][j]==1)
                         {
                             swap(i,j,bval,i,j+ 1);
                             if(bval[i][j]!=bval[i][j+1])  
                                r++;
                         }
                    }
                }
                else
                {
                    for(int j=3; j!=0; j--)
                    {    if(bval[i][j]==1)
                         {
                             swap(i,j,bval,i,j-1);  
                             if(bval[i][j]!= bval[i][j-1])
                                r++;      
                         }
                    }
                }
        return r;
    }
   
    //This one swaps ar[a][b] with ar[c][d]
    void swap(int a, int b, int ar[][], int c, int d)
    {
        ar[a][b]=ar[a][b]+ar[c][d];
        ar[c][d]=ar[a][b]-ar[c][d];
        ar[a][b]=ar[a][b]-ar[c][d];//a=a+b; b=a-b; a=a-b //SWAP TECHNIQUE            
    }
   
    int add2or8(int a)
    {
        int r=0;
        for(int j=0; j<4; j++)
            for(int i=0; i<3; i++)
            {
                if(a==8 && bval[i][j]==bval[i+1][j]   &&  bval[i][j]!=1)
                {
                    bval[i][j]*=2;    bval[i+1][j]=1; score+=bval[i][j]; r++;
                }
                if(a==2 && bval[3-i][j]==bval[2-i][j] &&  bval[2-i][j]!=1)
                {
                    bval[3-i][j]*=2;   bval[2-i][j]=1; score+= bval[i][3-j]; r++;
                }
            }
        return r;
    }
    
    int move2or8(int code)
    {
        int r=0;
        for(int j=0; j<4;j++)
            for(int k=0; k<3; k++)
                if(code==8)
                {
                    for(int i=0; i!=3; i++)                
                    {    if(bval[i][j]==1)
                         {
                             swap(i,j ,bval,i+ 1,j);
                             if(bval[i][j]!=bval[i+1][j] )
                                r++;
                         }
                    }
                }
                else
                {
                    for(int i=3; i!=0; i--)
                    {    if(bval[i][j]==1 && bval[i-1][j]!=1)
                         {   swap(i,j,bval,i-1,j);    
                             if(bval[i][j]!=bval[i-1][j])
                                r++;
                         }
                    }              
                }
        return r;
    }
   
    void fmove2or8(int c)
    {
        boolean b=false;
        if(move2or8(c)!=0)
            b=true;
        if(add2or8(c)!=0)
            b=true;
        move2or8(c);
        if(b)
            random((Math.random()<=.7)? 2:4);
        else
            mess="Move Invalid!!";
    }
    
    boolean isGameOver() {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            if (bval[i][j] == 1) return false;
            if (j < 3 && bval[i][j] == bval[i][j + 1]) return false;

            if (i < 3 && bval[i][j] == bval[i + 1][j]) return false;
        }
    }
    return true; // No moves left
}
    public static void main(String[] args)
    {
        MainProgram ob=new MainProgram(); ob.print(); 
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter choice");
        while(true)
        {
            if(ob.isGameOver())
                ob.mess="GAME OVER";
            System.out.println("\f");
            ob.print(); ob.mess="";
            switch(sc.nextInt())
            {
                case 2: ob.fmove2or8(2); break;
                case 4: ob.fmove4or6(4); break;
                case 6: ob.fmove4or6(6); break;
                case 8: ob.fmove2or8(8); break;
                default: System.out.println("Re-enter"); continue;
            }
            if(ob.isGameOver())
            {    
                ob.mess="GAME OVER";
                ob.print();
                System.exit(0);
            }
        }
    }
}


