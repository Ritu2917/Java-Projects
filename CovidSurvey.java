/* 
 * PROBLEM STATEMENT :
 * A COVID-19 help-care implementation which includes self-assessment test , finding nearest hospital 
 * to your resident , knowing in which zone your residential area lies , an online delivery system to
 * deliver your daily essentials and to know the count of COVID cases in India.
 * 
 *  DATA STRUCTURES USED :
 *  1) User Verification - Hashing
 *  2) Finding nearest hospital - BST
 *  3) Count of COVID cases - File Organization
 *  4) Supply of daily essentials - Array 
 */


import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Vector;

class node
{
    long pin;
    String email;
    node next;
    node left,right;
	String place,hospital;
	double dist;
	node(String p,String h, double d)
	{
		left=right=next=null;
		place = p;
		hospital =h;
		dist =d;
	}    
    node(String email, long pin) 
    {
        this.email=email;
        this.pin = pin;
        this.next = null;
    }
}

class hospitals
{
	
	int x;
	Scanner s=new Scanner(System.in);
	node root = null;
	String zone[] = {"ORANGE", "GREEN", "RED", "ORANGE","GREEN", "GREEN", "GREEN", "RED", "RED", "ORANGE", "GREEN"};
	String place[] = {"Sinhgad Road","Kothrud","Bhavani Peth","Sadashiv Peth","Bavdhan","Baner","Aundh","Pimpri","Sangamwadi","Bibwewadi","SB road"};
	String hosp[] = {"Sasson   ","Naidu    ", "AIMS     ", "KEM      ", "Wadia    ", "Deoyani  ", "Tarachand", "Deenanath", "YCM      ", "Poona    ", "Jupiter  ", "Ruby Hall"};
	double dist[][] = {{12.6, 12.3, 15.5, 12.4, 13.3, 6.6, 11.7, 4.7, 31.8, 5.7, 18.7, 13.4 }, 
			           {8.8, 8.5, 10.2, 9.7, 9.4, 2.4, 7.8, 3.9, 19.1, 4.8, 12.8, 9.6 }, 
			           {1.9,2.9,10.2,1.1,3.2,9.3,1.2,6.8,15,5,12.6,3.4}, 
			           {5.6, 4.7, 8.9, 5.4, 6.3, 5, 4.7 ,2.5, 16.8, 0.6, 11.5, 6.4}, 
			           {13.9,13.4,11,14.6,14.3,8.9,14.1,11.5,21.5,13.2,8.3,14.5}, 
			           {10.3,9.8,3.5,11,10.7,16.7,10.4,10.5,13.3,10.6,1.2,10.9}, 
			           {9, 8.5, 1, 9.7, 9.4, 10.9, 9.2, 9.3, 11.2, 9.3, 3.5, 9.6}, 
			           {14.1, 13.4, 10.9, 14.8, 14.3, 18.6, 14.6, 16.8, 1.9, 16, 14.4, 14.5 }, 
			           {3, 1.8, 9.9, 3.5, 1, 9.7, 4.2, 7.8, 14.9, 5.8, 12.6, 0.5}, 
			           {7.9, 8.9, 13.7, 7, 9.1, 9.8, 7.1, 7.5, 15.3, 13.7, 2.9, 9.3}, 
			           {6.2, 5.7, 4.9, 6.8, 6.6, 5.9, 6.3, 4.2, 13.8, 4.3, 7.5, 6.8} };
	node temp;
	void hosp()
	{
	System.out.println("List of hospitals treating Corona Virus in Pune : ");
	System.out.println();
	System.out.println("-------------------------------------------------");
	System.out.println("|     Hospitals\t\t|\tArea \t\t|");
	System.out.println("-------------------------------------------------");
	System.out.println("| Sasson Hospital\t|   Station Road\t| \n| Naidu Hospital\t|   RTO     \t\t|\n| AIMS Hospital  \t|   Aundh\t\t| \n| KEM Hospital  \t|   Rasta Peth\t\t|\n| Wadia Hospital\t|   Sangamwadi\t\t|\n| Deoyani Hospital\t|   Dhanukar Colony\t|\n| Tarachand Hospital\t|   Tilak Road\t\t|\n| Deenanath Hospital\t|   Erandwane\t\t|\n| YCM Hospital  \t|   Pimpri\t\t|\n| Poona Hospital\t|   Sadashiv Peth\t|\n| Jupiter Hospital\t|   Baner\t\t|\n| Ruby Hall     \t|   Sangamwadi\t\t|");
	System.out.println("-------------------------------------------------");
	System.out.println();
	System.out.println();
	System.out.println("Enter your residential area");
	for(int i=0;i<place.length;i++)
	{
		System.out.println((i+1) + ".  " + place[i]);
	}
    x=s.nextInt();	
    int i=x-1,k=x-1,j;
    for(j=0;j<hosp.length;j++) 
	{
    temp=new node(place[i],hosp[j],dist[k][j]);
	if(root==null)
	{
		root=temp;
	}
	else
	{
		node ptr,parent;
		ptr=root;
		parent =null;
		while(true)
		{
			parent =ptr;
	         if(dist[k][j]<parent.dist)
	         {
	           ptr = ptr.left;                
	            if(ptr == null) 
	            {
	               parent.left= temp;
	               break;
	            }
	         }
	         else
	         {
	          ptr=ptr.right;
	            if(ptr==null)
	            {
	               parent.right = temp;
	               break;	
		}
	         }
		}
	}
	}   
    System.out.println(place[x-1] +" : " + zone[x-1] +" ZONE");
    System.out.println();
	display(root);
	System.out.println();
	System.out.println();
	min();
	root=null;
	}
	void min()
	{
		node ptr=root;
		while(ptr.left!=null)
		{
			ptr=ptr.left;
		}
		System.out.println("Nearest hospital : "+ ptr.hospital );
		System.out.println(ptr.place  + " -----> " + ptr.hospital + " = " + ptr.dist + "km");
		System.out.println();
	}
	void display(node localRoot)
	{
	if(localRoot!=null)	
	{
		display(localRoot.left);
		System.out.print(localRoot.place+" ----->  ");
		System.out.print(localRoot.hospital+" Hospital");
		System.out.println(" : "+ localRoot.dist + "km");
		display(localRoot.right);
	}
   }
}


class Essential 
{
    Scanner s = new Scanner(System.in);

    void method()
    {
    	System.out.println("----------------Welcome to online shopping mart---------------");
    	System.out.println();
    	System.out.println("We provide your daily essentials . Stay home and we are here for your service!");
        System.out.println("What do you wish to buy?");
        System.out.println();
        char ch = 'y';
        String v1[] = {"GARLIC", "GINGER", "TOMATO", "ONION", "CABBAGE"};
        String f1[] = {"APPLE", "WATERMELON   ", "GRAPES", "BANANA"};
        String g1[] = {"WHEAT", "RICE", "BAJRA"};
        String o1[] = {"SUNFLOWER OIL","GROUNDNUT OIL","SALT","SUGAR","EGGS"};
        
        int v[] = new int[5], f[] = new int[4], g[] = new int[3], o[] = new int[5], bill = 0, option = 0;
        int p1[] = {150, 180, 30, 50, 70};
        int p2[] = {150, 30, 70, 50};
        int p3[] = {50, 60, 70};
        int p4[] = {130, 100, 20, 50, 60};
        if (ch == 'y') 
        {
            do {
            	System.out.println();
                System.out.println("1.VEGETABLES\n2.FRUITS\n3.GRAINS\n4.OILS & OTHERS\n5.BILL");
                option = s.nextInt();
                System.out.println();
                switch (option) 
                {
                    case 1:
                    	System.out.println("-----------V E G E T A B L E S ------------------");
                        System.out.println("1.GARLIC \n2.GINGER  \n3.TOMATO  \n4.ONION  \n5.CABBAGE");
                        System.out.println();
                        System.out.println("ENTER QUANTITIES PER VEGETABLE(in kgs):");
                        System.out.println();
                        for (int i = 0; i < 5; i++) 
                        {
                     	   System.out.println(v1[i] + " : ");
                            v[i] = s.nextInt();
                        }

                        for (int i = 0; i < 5; i++) 
                        {
                            bill = bill + (v[i] * p1[i]);
                        }
                        System.out.println(" ----- CART -------       "); 
                        for (int i = 0; i < 5; i++) 
                        {
                           if(v[i]!=0)
                        	   {  
                        	   if(i==0) {
                        		   System.out.println("| Garlic  : "+v[i]+"kg   |");   
                        	   }
                        	   if(i==1) {
                        		   System.out.println("| Ginger  : "+v[i]+"kg   |");   
                        	   }
                        	   if(i==2) {
                        		   System.out.println("| Tomato  : "+v[i]+"kg   |");   
                        	   }
                        	   if(i==3) {
                        		   System.out.println("| Onion   : "+v[i]+"kg   |");   
                        	   }
                        	   if(i==4) {
                        		   System.out.println("| Cabbage : "+v[i]+"kg   |");   
                        	   }
                        	   }
                        }
                        System.out.println("-------------------");
                        System.out.println();
                        System.out.print(" Cart Price  :");
                        System.out.println(bill+"rs.");
                        System.out.println("-----------------------------");
                        break;
                    case 2:
                    	System.out.println("------------ F R U I T S ----------------");
                        System.out.println("1.APPLE \n2.WATERMELON \n3.GRAPES \n4.BANANA");
                        System.out.println();
                        System.out.println("ENTER QUANTITIES PER FRUIT(in kgs/dozens):");
                        System.out.println();
                        for (int i = 0; i < 4; i++) 
                        {
                      		   System.out.println(f1[i]+" : ");   
                            f[i] = s.nextInt();
                        }

                        for (int i = 0; i < 4; i++) 
                        {
                            bill = bill + (f[i] * p2[i]);
                        }
                        
                        System.out.println(" ------- CART ---------"); 
                        for (int i = 0; i < 4; i++) 
                        {
                           if(f[i]!=0)
                           {
                        	   if(i==0) {
                        		   System.out.println("| Apple       : "+f[i]+"kg    |");   
                        	   }
                        	   if(i==1) {
                        		   System.out.println("| Watermelon  : "+f[i]+"kg    |");   
                        	   }
                        	   if(i==2) {
                        		   System.out.println("| Grapes      : "+f[i]+"kg    |");   
                        	   }
                        	   if(i==3) {
                        		   System.out.println("| Banana      : "+f[i]+"Dozen |");   
                        	   }
                        	   }
                        }
                        System.out.println("------------------------");
                        System.out.println();
                        System.out.print(" Cart Price :");
                        System.out.println(bill+"rs.");
                        System.out.println("-----------------------------");
                        break;
                    case 3:
                    	System.out.println("---------- G R A I N S -----------");
                        System.out.println("1.WHEAT \n2.RICE \n3.BAJRA");
                        System.out.println();
                        System.out.println("ENTER QUANTITIES PER GRAIN(in kgs):");
                        System.out.println();
                        for (int i = 0; i < 3; i++) 
                        {
                       		   System.out.println(g1[i]+" : ");   
                               g[i] = s.nextInt();
                        }

                        for (int i = 0; i < 3; i++) 
                        {
                            bill = bill + (g[i] * p3[i]);
                        }
                        
                        System.out.println(" ------ CART --------");            
                        for (int i = 0; i < 3; i++) 
                        {
                           if(g[i]!=0)
                        	   {
                        	   if(i==0) {
                        		   System.out.println("|    Wheat : "+g[i]+"kg     |");   
                        	   }
                        	   if(i==1) {
                        		   System.out.println("|    Rice  : "+g[i]+"kg     |");   
                        	   }
                        	   if(i==2) {
                        		   System.out.println("|    Bajra : "+g[i]+"kg     |");   
                        	   }                   	   
                        	   }
                        }
                        System.out.println("----------------------");
                        System.out.println();
                        System.out.print(" Cart PRICE :");
                        System.out.println(bill+"rs.");
                        System.out.println("-----------------------------");
                        break;
                    case 4:
                    	System.out.println("-----------O I L S   &   O T H E R S ----------");
                        System.out.println("1.SUNFLOWER OIL  \n2.GROUNDNUT OIL  \n3.SALT  \n4.SUGAR  \n5.EGGS (DOZEN) ");
                        System.out.println();
                        System.out.println("ENTER QUANTITIES PER OIL&OTHER (in kgs/dozens):");
                        System.out.println();

                        for (int i = 0; i < 5; i++) 
                        {
                      		   System.out.println(o1[i]+" : "); 
                            o[i] = s.nextInt();
                        }

                        for (int i = 0; i < 5; i++) {
                            bill = bill + (o[i] * p4[i]);
                        }
                    System.out.println(" ------- CART ---------");  
                        for (int i = 0; i < 5; i++) {
                           if(o[i]!=0)
                        	   {
                        	   if(i==0) {
                        		   System.out.println("| Sunflower oil : "+o[i]+"kg  |");   
                        	   }
                        	   if(i==1) {
                        		   System.out.println("| Groundnut oil : "+o[i]+"kg  |");   
                        	   }
                        	   if(i==2) {
                        		   System.out.println("| Salt          : "+o[i]+"kg  |");   
                        	   }
                        	   if(i==3) {
                        		   System.out.println("| Sugar         : "+o[i]+"kg  |");   
                        	   }
                        	   if(i==4) {
                        		   System.out.println("| Eggs          : "+o[i]+"kg  |");   
                        	   }
                        	   }
                        }
                        System.out.println("------------------------");
                        System.out.println();
                        System.out.print(" Cart price :");
                        System.out.println(bill+"rs.");
                        System.out.println("-----------------------------");
                        
                }
            } while (option != 5);
            
            int c=0;
            System.out.println();
            System.out.println("Summary of your purchase : ");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("|  SR NO.\t|\tITEM\t\t|\tQUANTITY    |\tPRICE   |");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("|        \t|\t    \t\t|\t            |\t        |");
            for(int i=0;i<v1.length;i++)
            {
            	if(v[i]!=0)
            	{
            		System.out.println("|    "+(c+1)+".  \t|   "+v1[i]+"\t\t|\t  "+v[i]+"Kg       |\t "+p1[i]+"\t|");
            		c++;
            	
            	}
            }
            for(int i=0;i<f1.length;i++)
            {
            	if(f[i]!=0)
            	{
            		if(i==3)
            		{
            			System.out.println("|    "+(c+1)+".  \t|   "+f1[i]+"\t\t|\t "+f[i]+"dzn       |\t "+p2[i]+"\t|");
            		}
            		else
            			if(i==1)
            			System.out.println("|    "+(c+1)+".  \t|   "+f1[i]+"\t|\t  "+f[i]+"Kg       |\t "+p2[i]+"\t|");
            		else
            			System.out.println("|    "+(c+1)+".  \t|   "+f1[i]+"\t\t|\t  "+f[i]+"Kg       |\t "+p2[i]+"\t|");
            		c++;
            	
            	}
            }
            for(int i=0;i<g1.length;i++)
            {
            	if(g[i]!=0)
            	{
            		System.out.println("|    "+(c+1)+".  \t|   "+g1[i]+"\t\t|\t  "+g[i]+"Kg       |\t "+p3[i]+"\t|");
            		c++;
            	
            	}
            }
            for(int i=0;i<o1.length;i++)
            {
            	if(o[i]!=0)
            	{
            		if(i==4)
            		{
            			System.out.println("|    "+(c+1)+".  \t|   "+o1[i]+"\t\t|\t "+o[i]+"dzn       |\t "+p4[i]+"\t|");
            		}
            		else
            		if(i==0 || i==1)
            			System.out.println("|    "+(c+1)+".  \t|   "+o1[i]+"\t|\t "+o[i]+"kg        |\t "+p4[i]+"\t|");
            		else
            			System.out.println("|    "+(c+1)+".  \t|   "+o1[i]+"\t\t|\t  "+o[i]+"Kg       |\t "+p4[i]+"\t|");
            		c++;
            	
            	}
            }
            System.out.println("|        \t|\t    \t\t|\t            |\t        |");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println();
            System.out.println("Total Items Purchased : "+c);
            System.out.println("Total bill is : Rs. " + bill );
            System.out.println();
            System.out.println();
            System.out.println("              THANK YOU FOR SHOPPING WITH US!");
            System.out.println();
            System.out.println("             ** YOUR ORDER HAS BEEN PLACED ** ");
            System.out.println();
            System.out.println("                   STAY HOME ,STAY SAFE !");
        }
    }
}
class File
{

	void file()
	{
		File file = new File();
		FileInputStream fis = null;

		try 
		{
			fis = new FileInputStream("C:\\Users\\aksha\\Desktop\\corona.txt");

			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());

			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
class Hashing
{
     int maxSize,size;
      
     node hashtable[];
 
    public Hashing(int m) 
    {
        maxSize = m;
        hashtable = new node[maxSize];
        for (int i = 0; i < maxSize; i++)
            hashtable[i] = null;
    } 
    
    
    public void insert(String emailid, long password) 
    {
        int loc = (int) (myhash( emailid+password ));
        if (hashtable[loc] == null)
            hashtable[loc] = new node(emailid, password);
        else 
        {
            node ptr = hashtable[loc];
            while (ptr.next != null && !ptr.email.equals(emailid))
                ptr = ptr.next;
            if (ptr.email.equals(emailid))
                ptr.email = emailid;
            else
                ptr.next = new node(emailid, password);
        }
        size++;
    }
 
   /* public void remove(String emailid) 
    {
        int loc = (myhash( emailid ) % maxSize);
        if (hashtable[loc] != null) 
        {
            node q = null;
           node ptr = hashtable[loc];
            while (ptr.next != null && !ptr.email.equals(emailid)) 
            {
                q = ptr;
                ptr = ptr.next;
            }
            if (ptr.email.equals(emailid)) 
            {
                if (q == null)
                    hashtable[loc] = ptr.next;
                else
                    q.next = ptr.next;
                size--;
            }
        }
    }*/

    public boolean search(String emailid,long password )
    {    
    	int flag=0;
    	int loc = (int) (myhash( emailid+password ));
    	node ptr=hashtable[loc];
    	while(ptr!=null)
    	{
    		if(ptr.email.equals(emailid))
    		{
    			flag=1;
    			System.out.println("-------------User Account verified! -------------");
    			break;
    			//System.out.println(ptr.email + " --> "+ptr.pin);
    			
    		}
    		ptr=ptr.next;
    		
    	}
    	if(flag==0)
    	{
    		System.out.println("           User Not Found!");
    		return false;
    	}
    	return true;
    }
    
    private int myhash(String x )
    {
        int val = x.hashCode( );
        val %= maxSize;
        if (val < 0)
            val += maxSize;
        return val;
    } 
}

class self_assessment
{
	Scanner s =new Scanner(System.in);
	void show()
	{
		System.out.println("-----------------------------------------------");
		System.out.println("COVID-19 (Coronavirus) - Self Assessment Tool");
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("Please give correct answers");
		System.out.println("Accurate answers help us - help you better . Be a responsible citizen");
		System.out.println();

		Scanner s=new Scanner(System.in);
		boolean c=false,f=false,d=false,nan=false,diab=false,h=false,l=false,hd=false,nan1=false,t=false,r=false,a=false,nan2=false,l5=false,g5=false,g14=false;
		int x,y,z,p,q;
		boolean case1=false,case2=false,case3=false,case31=false,case32=false,case33=false,case34=false;
		System.out.println("Are you facing any of the following symptons ");
		System.out.println();
		System.out.println("1.COUGH");
		System.out.println("2.FEVER");
		System.out.println("3.DIFFICULTY IN BREATHING");
		System.out.println("4.COUGH AND FEVER");
		System.out.println("5.COUGH AND DIFFICULTY IN BREATHING");
		System.out.println("6.FEVER AND DIFFICULTY IN BREATHING");
		System.out.println("7.ALL OF THE ABOVE");
		System.out.println("8.NONE OF THE ABOVE");
		x=s.nextInt();
		switch(x)
		{
		case 1:
			c=true;
			break;
		case 2:
			f=true;
			break;
		case 3:
			d=true;
			break;
		case 4:
			c=true;
			break;
		case 5:
			c=true;
			break;
		case 6:
			f=true;
			break;
		case 7:
			d=true;
			break;
		case 8:
			nan=true;
			break;
			
		}
		if(c==true || f==true || d==true)
		{
			case1=true;
		}
		else
		{
			case1=false;
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		
	
		System.out.println("Have you ever had any of the following ");
		System.out.println();
		System.out.println("1.DIABETES");
		System.out.println("2.HYPERTENSION");
		System.out.println("3.LUNG DISEASE");
		System.out.println("4.HEART DISEASE");
		System.out.println("5.DIABETES AND HYPERTENSION");
		System.out.println("6.DIABETES AND LUNG DISEASE");
		System.out.println("7.DIABETES AND HEART DISEASE");
		System.out.println("8.HYPERTENSION AND LUNG DISEASE");
		System.out.println("9.HYPERTENSION HEART DISEASE");
		System.out.println("10.LUNG DISEASE AND HEART DISEASE");
		System.out.println("11.ALL OF THE ABOVE");
		System.out.println("12.NONE OF THE ABOVE");
		y=s.nextInt();
		switch(y)
		{
		case 1:
			diab=true;
			break;
		case 2:
			h=true;
			break;
		case 3:
			l=true;
			break;
		case 4:
			hd=true;
		break;
		case 5:
			diab=true;
			break;
		case 6:
			diab=true;
			break;
		case 7:
			diab=true;
			break;
		case 8:
			h=true;
			break;
		case 9:
			h=true;
			break;
		case 10:
			l=true;
			break;
		case 11:
			hd=true;
			break;
		case 12:
			nan1=true;
			break;
			
		}
		if(diab==true || h==true || l==true || hd==true)
		{
			case2=true;
		}
		else
		{
			case2=false;
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		
		System.out.println("Which of the following apply to you ");
		System.out.println();
		System.out.println("1.TRAVELED INTERNATIONALLY IN THE LAST 14 DAYS");
		System.out.println("2.RECENTLY INTERACTED OR CURRENTLY LIVE WITH SOMEONE WHO HAS TESTED POSITIVE FOR COVID-19");
		System.out.println("3.A HEALTH CARE WORKER");
		System.out.println("4.TRAVELED INTERNATIONALLY IN THE LAST 14 DAYS AND RECENTLY INTERACTED WITH COVID-19 POSITIVE PERSON");
		System.out.println("5.TRAVELED INTERNATIONALLY IN THE LAST 14 DAYS AND HEALTHCARE WORKER");
		System.out.println("6.RECENTLY INTERACTED WITH COVID-19 POSITIVE PERSON AND HEALTHCARE WORKER");
		System.out.println("7.ALL OF THE ABOVE");
		System.out.println("8.NONE OF THE ABOVE");
		System.out.println();
		System.out.println();
		System.out.println();
		z=s.nextInt();
		switch(z)
		{
		case 1:
		   t=true;
			break;
		case 2:
		    r=true;
			System.out.println("When did this interaction take place? ");
			System.out.println();
			System.out.println("1.LESS THAN 5 DAYS");
			System.out.println("2.GREATER THAN 5 DAYS");
			System.out.println("3.GREATER THAN 14 DAYS");
			
		    p=s.nextInt();
		    switch(p)
		    {
		    case 1:
		    	l5=true;
		    	break;
		    case 2:
		    	g5=true;
		    	break;
		    case 3:
		    	g14=true;
		    	break;
		    }
		    if(l5==true || g5==true)
		    {
		    	case31=true;
		    }
		    else
		    {
		    	case31=false;
		    }
		    System.out.println();
			System.out.println();
			System.out.println();
			break;
		case 3:
			a=true;
			
			break;
		case 4:
			r=true;
			System.out.println("When did this interaction take place? ");
			System.out.println();
			System.out.println("1.LESS THAN 5 DAYS");
			System.out.println("2.GREATER THAN 5 DAYS");
			System.out.println("3.GREATER THAN 14 DAYS");
			
		    p=s.nextInt();
		    switch(p)
		    {
		    case 1:
		    	l5=true;
		    	break;
		    case 2:
		    	g5=true;
		    	break;
		    case 3:
		    	g14=true;
		    	break;
		    }
		    if(l5==true || g5==true)
		    {
		    	case31=true;
		    }
		    else
		    {
		    	case31=false;
		    }
		    System.out.println();
			System.out.println();
			System.out.println();
			break;
		case 5:
			a=true;
			break;
		case 6:
			r=true;
			System.out.println("When did this interaction take place? ");
			System.out.println();
			System.out.println("1.LESS THAN 5 DAYS");
			System.out.println("2.GREATER THAN 5 DAYS");
			System.out.println("3.GREATER THAN 14 DAYS");
			
		    p=s.nextInt();
		    switch(p)
		    {
		    case 1:
		    	l5=true;
		    	break;
		    case 2:
		    	g5=true;
		    	break;
		    case 3:
		    	g14=true;
		    	break;
		    }
		    if(l5==true || g5==true)
		    {
		    	case31=true;
		    }
		    else
		    {
		    	case31=false;
		    }
		    System.out.println();
			System.out.println();
			System.out.println();
			break;
		case 7:
			r=true;
			System.out.println("When did this interaction take place? ");
			System.out.println();
			System.out.println("1.LESS THAN 5 DAYS");
			System.out.println("2.GREATER THAN 5 DAYS");
			System.out.println("3.GREATER THAN 14 DAYS");
			
		    p=s.nextInt();
		    switch(p)
		    {
		    case 1:
		    	l5=true;
		    	break;
		    case 2:
		    	g5=true;
		    	break;
		    case 3:
		    	g14=true;
		    	break;
		    }
		    if(l5==true || g5==true)
		    {
		    	case31=true;
		    }
		    else
		    {
		    	case31=false;
		    }
		    System.out.println();
			System.out.println();
			System.out.println();
			break;
		
		case 8:
			nan2=true;
			break;
			
		}
		if(nan2==true)
		{
			case3=false;
		}
		else if(t==true)
		{
			case3=true;
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		
		if(case1==true && case2==true && case3==false && case31==false)
		{
			System.out.println("Your infection is LOW.");
		}
		else if(case1==true && case2==false && case3==true)
		{
			System.out.println("Your infection is HIGH.");
		}
		else if(case1==true && case2==false && case31==true)
		{
			System.out.println("Your infection is MODERATE..");
		}
		else if(case1==true && case2==false && case31==false)
		{
			System.out.println("Your infection is LOW.");
		}
	
	   else if(case1==false && case2==true && case3==true)
	   {
		System.out.println("Your infection is MODERATE.Do consider Doctor's opinion via phone or video call.");
	   }
	   else if(case1==false && case2==true && case31==true)
	   {
		System.out.println("While you are unwell,your infection is MODERATE.");
	   }
	   else if(case1==false && case2==true && case31==false)
	   {
		System.out.println("Your infection is LOW.");
	   }
	   else if(case1==true && case2==true && case3==true)
		{
			System.out.println("Your infection is HIGH.");
		}
	   else if(case1==true && case2==true && case31==true)
		{
			System.out.println("Your infection is HIGH.");
		}
	   else if(case1==true && case2==true && case3==true)
		{
			System.out.println("Your infection is MODERATE.");
		}
	   else if(case1==false && case2==false && case3==false)
	   {
		   System.out.println("Your infection is LOW.");
	   }
	   System.out.println("Do consider Doctor's opinion via phone or video call.");
	}
}
public class project 
{
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int maxSize=30;
		self_assessment self = new self_assessment();
		hospitals ho =new hospitals();
		File f =new File();
        Hashing h = new Hashing(maxSize);
        int x;
        int c=0;
        Essential e=new Essential();
        node n[]=new node[maxSize];
        System.out.println();
		System.out.println("\t\t\t ----------------------------------------------- C O V I D - 1 9  H E L P C A R E -----------------------------------------------");
		System.out.println();
		System.out.println("Welcome to Covid-19 helpcare ");
		System.out.println();
		System.out.println("Here are some of the precautions : ");
		System.out.println("1. STAY home\n2. KEEP a safe distance\n3. WASH hands often\n4. COVER your mouth while coughing and sneezing \n5. SICK? Consult a doctor" );
		System.out.println();
        System.out.println();
        System.out.println();
     	System.out.println("========================================================");
     	System.out.println("|                ---------------                       |");
     	System.out.println("|                   L O G I N                          |");
     	System.out.println("|                ---------------                       |");
     	System.out.println("|                                                      |");
        System.out.println("|           1. New User                                |");
        System.out.println("|           2. Existing User                           |");
        System.out.println("|                                                      |");
     	System.out.println("========================================================");
     	 x = s.nextInt();    
     	 do
     	 {
         switch (x)
         {
         case 1 : 
        	 int ch=0;
         System.out.println();
             System.out.print("           Enter Email Id : ");
             String cn =s.next();
             System.out.print("           Enter your pin : ");
             long pn=s.nextLong();
             if(Long.toString(pn).length()<5)
             {
            	 System.out.println("Please enter pin of atleast 5 digits for your security");
            	 System.out.println();
            	 System.out.print("           Enter your pin : ");
                  pn=s.nextLong();
             }
             h.insert(cn, pn ); 
             System.out.println();
             System.out.println("           ------------------");
             System.out.println("            User Registered!");
             System.out.println("           ------------------");
             System.out.println();              
             System.out.println();
             x =2;
             break;     
         case 2:
         	  System.out.println("------------- Registered User Verification --------------");
         	  System.out.println();
               System.out.print("           Enter Email Id : ");
               String cn1 =s.next();
               System.out.print("           Enter your pin : ");
               long pn1=s.nextLong();
               if(Long.toString(pn1).length()<5)
               {
              	 System.out.println("     Pins are of minimum 5 digits \n          TRY AGAIN");
              	 System.out.println();
              	 System.out.print("           Enter your pin : ");
                    pn1=s.nextLong();
               }
               System.out.println();
              boolean find= h.search(cn1,pn1);
               if(find) 
               { 
            	 do
              	 {
              		 System.out.println();
              		 System.out.println();
              	 System.out.println(" ************************ WELCOME **************************");
              	 System.out.println(" *                                                         *");
              	 System.out.println(" * Enter 1. to take a self-assessment test                 *");
              	 System.out.println(" * Enter 2. to find nearest hospital and know your zone    *");
              	 System.out.println(" * Enter 3. to buy home essentials                         *");
              	 System.out.println(" * Enter 4. to see no of corona cases                      *");
              	 System.out.println(" * Enter 0. to exit                                        *");
              	 System.out.println(" ***********************************************************");
              	 c=s.nextInt();
              	 if(c==0)
              		 x=0;
              	 System.out.println();
              	 switch(c)
              	 {
              	 case 1:
              		 self.show();
              		 System.out.println();
              		 break;
              	 case 2 :
              		 ho.hosp();
              		 System.out.println();
              		 break;
              	 case 3:
              		 System.out.println();
              		e.method();
              		System.out.println();
              		 break;
              	 case 4:
              		 System.out.println();
              		 f.file();
              		 System.out.println();
              		 break;
              	 }
              	 }while(c> 0 && c<=4); 
            	 System.out.println("                             T H A N K  Y O U !");
               }
               if(!find)
               {   
            	   System.out.println();
            	   System.out.println();
                   System.out.println();
                   System.out.println("========================================================");
                   System.out.println("|                ---------------                       |");
                   System.out.println("|                   L O G I N                          |");
                   System.out.println("|                ---------------                       |");
                   System.out.println("|                                                      |");
                   System.out.println("|           1. Create another account                  |");
                   System.out.println("|           2. Re-enter details                        |");
                   System.out.println("|                                                      |");
                   System.out.println("========================================================");
                   System.out.println("*Enter 0. to exit");
             	  x=s.nextInt();
               }
         	break;         
         }
         }while(x!=0);
	}
}

/* OUTPUT:


			 ----------------------------------------------- C O V I D - 1 9  H E L P C A R E -----------------------------------------------

Welcome to Covid-19 helpcare 

Here are some of the precautions : 
1. STAY home
2. KEEP a safe distance
3. WASH hands often
4. COVER your mouth while coughing and sneezing 
5. SICK? Consult a doctor



========================================================
|                ---------------                       |
|                   L O G I N                          |
|                ---------------                       |
|                                                      |
|           1. New User                                |
|           2. Existing User                           |
|                                                      |
========================================================
1

           Enter Email Id : akshata.jedhe@gmail.com
           Enter your pin : 9960
Please enter pin of atleast 5 digits for your security

           Enter your pin : 99600

           ------------------
            User Registered!
           ------------------


------------- Registered User Verification --------------

           Enter Email Id : akshata.jedhe@gmail.com
           Enter your pin : 678
     Pins are of minimum 5 digits 
          TRY AGAIN

           Enter your pin : 12345

           User Not Found!



========================================================
|                ---------------                       |
|                   L O G I N                          |
|                ---------------                       |
|                                                      |
|           1. Create another account                  |
|           2. Re-enter details                        |
|                                                      |
========================================================
*Enter 0. to exit
2
------------- Registered User Verification --------------

           Enter Email Id : akshata.jedhe@gmail.com
           Enter your pin : 99600

-------------User Account verified! -------------


 ************************ WELCOME **************************
 *                                                         *
 * Enter 1. to take a self-assessment test                 *
 * Enter 2. to find nearest hospital and know your zone    *
 * Enter 3. to buy home essentials                         *
 * Enter 4. to see no of corona cases                      *
 * Enter 0. to exit                                        *
 ***********************************************************
1

-----------------------------------------------
COVID-19 (Coronavirus) - Self Assessment Tool
-----------------------------------------------


Please give correct answers
Accurate answers help us - help you better . Be a responsible citizen

Are you facing any of the following symptons 

1.COUGH
2.FEVER
3.DIFFICULTY IN BREATHING
4.COUGH AND FEVER
5.COUGH AND DIFFICULTY IN BREATHING
6.FEVER AND DIFFICULTY IN BREATHING
7.ALL OF THE ABOVE
8.NONE OF THE ABOVE
8



Have you ever had any of the following 

1.DIABETES
2.HYPERTENSION
3.LUNG DISEASE
4.HEART DISEASE
5.DIABETES AND HYPERTENSION
6.DIABETES AND LUNG DISEASE
7.DIABETES AND HEART DISEASE
8.HYPERTENSION AND LUNG DISEASE
9.HYPERTENSION HEART DISEASE
10.LUNG DISEASE AND HEART DISEASE
11.ALL OF THE ABOVE
12.NONE OF THE ABOVE
10



Which of the following apply to you 

1.TRAVELED INTERNATIONALLY IN THE LAST 14 DAYS
2.RECENTLY INTERACTED OR CURRENTLY LIVE WITH SOMEONE WHO HAS TESTED POSITIVE FOR COVID-19
3.A HEALTH CARE WORKER
4.TRAVELED INTERNATIONALLY IN THE LAST 14 DAYS AND RECENTLY INTERACTED WITH COVID-19 POSITIVE PERSON
5.TRAVELED INTERNATIONALLY IN THE LAST 14 DAYS AND HEALTHCARE WORKER
6.RECENTLY INTERACTED WITH COVID-19 POSITIVE PERSON AND HEALTHCARE WORKER
7.ALL OF THE ABOVE
8.NONE OF THE ABOVE



4
When did this interaction take place? 

1.LESS THAN 5 DAYS
2.GREATER THAN 5 DAYS
3.GREATER THAN 14 DAYS
2






While you are unwell,your infection is MODERATE.
Do consider Doctor's opinion via phone or video call.



 ************************ WELCOME **************************
 *                                                         *
 * Enter 1. to take a self-assessment test                 *
 * Enter 2. to find nearest hospital and know your zone    *
 * Enter 3. to buy home essentials                         *
 * Enter 4. to see no of corona cases                      *
 * Enter 0. to exit                                        *
 ***********************************************************
2

List of hospitals treating Corona Virus in Pune : 

-------------------------------------------------
|     Hospitals		|	Area 		|
-------------------------------------------------
| Sasson Hospital	|   Station Road	| 
| Naidu Hospital	|   RTO     		|
| AIMS Hospital  	|   Aundh		| 
| KEM Hospital  	|   Rasta Peth		|
| Wadia Hospital	|   Sangamwadi		|
| Deoyani Hospital	|   Dhanukar Colony	|
| Tarachand Hospital	|   Tilak Road		|
| Deenanath Hospital	|   Erandwane		|
| YCM Hospital  	|   Pimpri		|
| Poona Hospital	|   Sadashiv Peth	|
| Jupiter Hospital	|   Baner		|
| Ruby Hall     	|   Sangamwadi		|
-------------------------------------------------


Enter your residential area
1.  Sinhgad Road
2.  Kothrud
3.  Bhavani Peth
4.  Sadashiv Peth
5.  Bavdhan
6.  Baner
7.  Aundh
8.  Pimpri
9.  Sangamwadi
10.  Bibwewadi
11.  SB road
5
Bavdhan : GREEN ZONE

Bavdhan ----->  Jupiter   Hospital : 8.3km
Bavdhan ----->  Deoyani   Hospital : 8.9km
Bavdhan ----->  AIMS      Hospital : 11.0km
Bavdhan ----->  Deenanath Hospital : 11.5km
Bavdhan ----->  Poona     Hospital : 13.2km
Bavdhan ----->  Naidu     Hospital : 13.4km
Bavdhan ----->  Sasson    Hospital : 13.9km
Bavdhan ----->  Tarachand Hospital : 14.1km
Bavdhan ----->  Wadia     Hospital : 14.3km
Bavdhan ----->  Ruby Hall Hospital : 14.5km
Bavdhan ----->  KEM       Hospital : 14.6km
Bavdhan ----->  YCM       Hospital : 21.5km


Nearest hospital : Jupiter  
Bavdhan -----> Jupiter   = 8.3km




 ************************ WELCOME **************************
 *                                                         *
 * Enter 1. to take a self-assessment test                 *
 * Enter 2. to find nearest hospital and know your zone    *
 * Enter 3. to buy home essentials                         *
 * Enter 4. to see no of corona cases                      *
 * Enter 0. to exit                                        *
 ***********************************************************
3


----------------Welcome to online shopping mart---------------

We provide your daily essentials . Stay home and we are here for your service!
What do you wish to buy?


1.VEGETABLES
2.FRUITS
3.GRAINS
4.OILS & OTHERS
5.BILL
1

-----------V E G E T A B L E S ------------------
1.GARLIC 
2.GINGER  
3.TOMATO  
4.ONION  
5.CABBAGE

ENTER QUANTITIES PER VEGETABLE(in kgs):

GARLIC : 
1
GINGER : 
1
TOMATO : 
1
ONION : 
3
CABBAGE : 
0
 ----- CART -------       
| Garlic  : 1kg   |
| Ginger  : 1kg   |
| Tomato  : 1kg   |
| Onion   : 3kg   |
-------------------

 Cart Price  :510rs.
-----------------------------

1.VEGETABLES
2.FRUITS
3.GRAINS
4.OILS & OTHERS
5.BILL
2

------------ F R U I T S ----------------
1.APPLE 
2.WATERMELON 
3.GRAPES 
4.BANANA

ENTER QUANTITIES PER FRUIT(in kgs/dozens):

APPLE : 
1
WATERMELON    : 
1
GRAPES : 
1
BANANA : 
1
 ------- CART ---------
| Apple       : 1kg    |
| Watermelon  : 1kg    |
| Grapes      : 1kg    |
| Banana      : 1Dozen |
------------------------

 Cart Price :810rs.
-----------------------------

1.VEGETABLES
2.FRUITS
3.GRAINS
4.OILS & OTHERS
5.BILL
3

---------- G R A I N S -----------
1.WHEAT 
2.RICE 
3.BAJRA

ENTER QUANTITIES PER GRAIN(in kgs):

WHEAT : 
1
RICE : 
1
BAJRA : 
0
 ------ CART --------
|    Wheat : 1kg     |
|    Rice  : 1kg     |
----------------------

 Cart PRICE :920rs.
-----------------------------

1.VEGETABLES
2.FRUITS
3.GRAINS
4.OILS & OTHERS
5.BILL
4

-----------O I L S   &   O T H E R S ----------
1.SUNFLOWER OIL  
2.GROUNDNUT OIL  
3.SALT  
4.SUGAR  
5.EGGS (DOZEN) 

ENTER QUANTITIES PER OIL&OTHER (in kgs/dozens):

SUNFLOWER OIL : 
2
GROUNDNUT OIL : 
0
SALT : 
2
SUGAR : 
2
EGGS : 
1
 ------- CART ---------
| Sunflower oil : 2kg  |
| Salt          : 2kg  |
| Sugar         : 2kg  |
| Eggs          : 1kg  |
------------------------

 Cart price :1380rs.
-----------------------------

1.VEGETABLES
2.FRUITS
3.GRAINS
4.OILS & OTHERS
5.BILL
5


Summary of your purchase : 

-------------------------------------------------------------------------
|  SR NO.	|	ITEM		|	QUANTITY    |	PRICE   |
-------------------------------------------------------------------------
|        	|	    		|	            |	        |
|    1.  	|   GARLIC		|	  1Kg       |	 150	|
|    2.  	|   GINGER		|	  1Kg       |	 180	|
|    3.  	|   TOMATO		|	  1Kg       |	 30	|
|    4.  	|   ONION		|	  3Kg       |	 50	|
|    5.  	|   APPLE		|	  1Kg       |	 150	|
|    6.  	|   WATERMELON   	|	  1Kg       |	 30	|
|    7.  	|   GRAPES		|	  1Kg       |	 70	|
|    8.  	|   BANANA		|	 1dzn       |	 50	|
|    9.  	|   WHEAT		|	  1Kg       |	 50	|
|    10.  	|   RICE		|	  1Kg       |	 60	|
|    11.  	|   SUNFLOWER OIL	|	 2kg        |	 130	|
|    12.  	|   SALT		|	  2Kg       |	 20	|
|    13.  	|   SUGAR		|	  2Kg       |	 50	|
|    14.  	|   EGGS		|	 1dzn       |	 60	|
|        	|	    		|	            |	        |
-------------------------------------------------------------------------

Total Items Purchased : 14
Total bill is : Rs. 1380


              THANK YOU FOR SHOPPING WITH US!

             ** YOUR ORDER HAS BEEN PLACED ** 

                   STAY HOME ,STAY SAFE !



 ************************ WELCOME **************************
 *                                                         *
 * Enter 1. to take a self-assessment test                 *
 * Enter 2. to find nearest hospital and know your zone    *
 * Enter 3. to buy home essentials                         *
 * Enter 4. to see no of corona cases                      *
 * Enter 0. to exit                                        *
 ***********************************************************
4


Total file size to read (in bytes) : 2957

  ----------------------------------- C O V I D - 19  A N A L Y S I S -----------------------------------

Cases:
India
All
Confirmed : 31,787
Recovered : 7,797
Deaths    : 1,008

-----------------------------------------------------------------
   Location	     Confirmed	      Recovered	       Deaths
-----------------------------------------------------------------
 Maharashtra           8,590              -             369
 
 Gujarat               3,548              -             162

 Delhi                 3,108              -              54

 Madhya Pradesh        2,368              -             113

 Rajasthan             2,262              -              46
 
 Uttar Pradesh         2,043              -              31

 Tamil Nadu            1,937              -              24

 Andhra Pradesh        1,259              -              31

 Telangana             1,004              -              26

 West Bengal             697              -              20
 
 Jammu and 
    Kashmir              546              -               7

 Karnataka               520              -              20

 Kerala                  482              -               4

 Bihar                   346              -               2

 Punjab                  313              -              18

 Haryana                 296              -               3

 Odisha                  118              -               1

 Jharkhand               104              -               3

 Uttarakhand              51              -               -

 Chandigarh               40              -               -

 Himachal Pradesh         40              -               1

 Assam                    38              -               1

 Chhattisgarh             37              -               -

 Andaman and 
 Nicobar Islands          33              -               -

 Ladakh                   22              -               -

 Meghalaya                12              -               1

 Puducherry                8              -               -

 Goa                       7              -               -

 Manipur                   2              -               -
 
 Tripura                   2              -               -

 Arunachal Pradesh         1              -               -

 Mizoram                   1              -               -

 Nagaland                  1              -               -
-----------------------------------------------------------------
  The above information is UPDATED TILL 29/04/2020  18:14 (IST)

 *FOR MORE INFORMATION, VISIT : https://www.worldometers.info/coronavirus/country/india/ or 
  https://www.deccanherald.com/national/coronavirus-live-updates-india-records-highest-deaths-in-a-single-day-toll-stands-at-886-tally-crosses-28000-827545.html

 *FOR UPDATES ABOUT PUNE CITY, VISIT: https://punecoronatracker.in/



 ************************ WELCOME **************************
 *                                                         *
 * Enter 1. to take a self-assessment test                 *
 * Enter 2. to find nearest hospital and know your zone    *
 * Enter 3. to buy home essentials                         *
 * Enter 4. to see no of corona cases                      *
 * Enter 0. to exit                                        *
 ***********************************************************
0

                             T H A N K  Y O U !


*/