package Airlines;
import java.util.*;

class Node										//class to define the passengers attributes
{
	String name;
    int age;
	Node next;
	
	public Node(String n,int a,String pnr)
	{
		this.name=n;
		this.age=a;
		next=null;
	}
}

class Seats											//class for ticket reservation and cancellation
{
	private Node front,rear;
	int k=0,no=5,a,adults,flag=0;
	String n,pnr;
	public String Z;
	Scanner s=new Scanner(System.in);
	
	public Seats()									//default constructor of seats class
	{
		this.front=null;
		this.rear=null;	
	}
	
	void book(int arr[][],int sid,int did,Vertex City[])			//function to book seats in the flight
	{ 
		 if((no-k)==0)
		 {
			 System.out.println("AVAILABLE SEATS : "+(no-k));
			 int fn=Seats.retno(sid,did,City);
  			 System.out.println("SEAT OCCUPIED"+"\t\t\t"+"SEAT VACANT");
  			 System.out.println("   | A01 |   "+"\t\t\t"+"  | A01 |  ");
  			 System.out.println("   | ==  |   "+"\t\t\t"+"  |     |  ");
  			 System.out.println();
  			 System.out.println("| A01  A02  A03 \t  A04  A05  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| B06  B07  B08 \t  B09  B10  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| C11  C12  C13 \t  C14  C15  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| D16  D17  D18 \t  D19  D20  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| E21  E22  E23 \t  E24  E25  |");
 			 System.out.print("| ");
 			
 			 for(int j=0;j<5;j++)
 			 {
 				 if(City[sid].arr[fn][j]==1 && j==2)
 					 System.out.print("==  \t  ");
 				 else if(City[sid].arr[fn][j]==1)
 					 System.out.print("==   ");
 				 else if(City[sid].arr[fn][j]==0 && j==2)
 					 System.out.print("    \t  ");
 				 else
 					 System.out.print("     ");
 			 }
 			
 			 System.out.println("|");
 			 System.out.println();
			 System.out.println("-----SORRY!! THE FLIGHT IS FULL-----");
			 adults=0;
			 return;
		 }
		 else 
		 {
			 int fn=Seats.retno(sid,did,City);
  			 System.out.println("SEAT OCCUPIED"+"\t\t\t"+"SEAT VACANT");
  			 System.out.println("   | A01 |   "+"\t\t\t"+"  | A01 |  ");
  			 System.out.println("   | ==  |   "+"\t\t\t"+"  |     |  ");
  			 System.out.println();
  			 System.out.println("| A01  A02  A03 \t  A04  A05  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| B06  B07  B08 \t  B09  B10  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| C11  C12  C13 \t  C14  C15  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| D16  D17  D18 \t  D19  D20  |");
 			 System.out.println("| ==   ==   ==  \t  ==   ==   |");
 			 System.out.println("| E21  E22  E23 \t  E24  E25  |");
 			 System.out.print("| ");
 			
 			 for(int j=0;j<5;j++)
 			 {
 				 if(City[sid].arr[fn][j]==1 && j==2)
 					 System.out.print("==  \t  ");
 				 else if(City[sid].arr[fn][j]==1)
 					 System.out.print("==   ");
 				 else if(City[sid].arr[fn][j]==0 && j==2)
 					 System.out.print("    \t  ");
 				 else
 					 System.out.print("     ");
 			 }
 			
 			 System.out.println("|");
 			 System.out.println();
  		
 			 System.out.println("AVAILABLE SEATS : "+(no-k));
 			 System.out.println("\nENTER THE DETAILS : ");
 			 System.out.print("NUMBER OF PASSENGERS : "); 
			 adults=s.nextInt();
			 
			 if(adults > (no-k))
			 {
				 System.out.println("-----OOPS!! THERE ARE ONLY "+(no-k)+" SEATS AVAILABLE-----");
				 adults=0;
			 }
			 else
			 {			
				 int z=5-(no-k);
				 int x=0;
				 z=z+adults;
	 			 while(x!=z)
	 				City[sid].arr[fn][x++]=1;
	 			
				for(int a1=0;a1<adults;a1++)
				{
					System.out.println("\nDETAILS OF PASSENGER "+(a1+1));
					System.out.print("NAME : ");
					n=s.next();
					System.out.print("AGE : ");
					a=s.nextInt();
					System.out.print("PNR NO (Format of pnr is : sid.name) : ");
					pnr=s.next();
					Node temp=new Node(n,a,pnr);
					temp.next=null;
					
					if(isempty()==1)
					{
						front=temp;
						rear=temp;
						k++;
					}
					else
					{
						rear.next=temp;
						rear=temp;
						k++;
					}
				}
			}
		 }
	}
	
	int returnAdults()
	{
		return adults;
	}
	
	void cancel()													//function to cancel the booked seat
	{   
		 if(isempty()==1)
			 System.out.println("-----SORRY!! THE FLIGHT IS EMPTY-----");
		 else
		 {
			 Node ptr=front;
		 	 Node prev=null;
		 	 int m=0;
		 	 System.out.print("ENTER YOUR NAME TO CANCEL THE BOOKING : ");
			 String nn=s.next();
			 
			 while(ptr!=null)
			 {
	             if(ptr.name.equals(nn))
			     {  
	            	 flag=1;
	            	 if(m==0)
				     {
				    	 front=ptr.next;
				    	 System.out.println("\t\t\t\t*******************************");
				    	 System.out.println();
				    	 System.out.println("\t\t\t\tBOOKING CANCELLED SUCCESSFULLY");
				    	 System.out.println();
				    	 System.out.println("\t\t\t\t*******************************");
				    	 k--;
				     }
				     else
				     {
				    	 prev.next=ptr.next;
				    	 ptr.next=null;
				    	 System.out.println("\t\t\t\t*******************************");
					     System.out.println();
					     System.out.println("\t\t\t\tBOOKING CANCELLED SUCCESSFULLY");
					     System.out.println();
					     System.out.println("\t\t\t\t*******************************");
				    	 k--;
				     }
		          }
				  prev=ptr;
				  ptr=ptr.next;
				  m++;
		      }
			  if(flag==0)
			  {
				  System.out.println("-----NO BOOKING WITH THIS NAME AVAILABLE-----");
				  System.out.println("-----PLEASE ENTER VALID DETAILS-----");
				  return;
			  }
		}
	}
	
	int isempty()
	{
		if(front==null)
			return 1;
		return 0; 
	}

	static int retno(int sid,int did,Vertex City[])				//fuunction to return the sid of the booked flight
	{
		for(int i=0;i<11;i++)
    	{
    		if(City[i].equals(sid) && City[i].equals(did))
    			return i;
    	}
		return 0;
	}
	
	void display()												//function to display the details of ticket
	{
		Node ptr=front;
		while(ptr!=null)
		{
			System.out.print("NAME : "+n);
			System.out.print("AGE : "+a);
			ptr=ptr.next;
		}
	}
}

class Vertex implements Comparable<Vertex>						//class to define vertices of the graph
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance=Double.POSITIVE_INFINITY;
    public Vertex previous;
	public double minTime;
	public int s;
	int arr[][]=new int [11][5];
	
	public Vertex()										//default constructor of vertex class
	{	
		name=" ";
		s=0;
		for(int i=0;i<11;i++)
		{
			for(int j=0;j<5;j++)
				arr[i][j]=0;
		}
	}
	
    public Vertex(String argName,int argS) 				//parameterized constructor of vertex class
    { 
    	name=argName; 
    	s=argS;
    }
    
    public String toString() 							//tostring method returns the name of flight 
    {
    	return name; 
    }
    
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance,other.minDistance);
    }
}

class Edge													//class to define the edges between vertices of the graph
{
    public final Vertex target;
    public final double weight,time;
    
    public Edge(Vertex argTarget,double argWeight,double argtime)			//parameterized constructor of edge class
    { 
    	target=argTarget;
    	weight=argWeight; 
    	time=argtime;
    }
}

class Airlines														//class to show the routes between the cities 
{
    public static void computePaths(Vertex source)
    {
        source.minDistance=0;
        source.minTime=0;
        PriorityQueue <Vertex> vertexQueue=new PriorityQueue <Vertex>();
        vertexQueue.add(source);

        while(!vertexQueue.isEmpty()) 
        {
            Vertex u=vertexQueue.poll();
            for(Edge e:u.adjacencies)
            {
                Vertex v=e.target;
                double weight=e.weight;
                double time=e.time;
                double distanceThroughU=u.minDistance+weight;
                double timeThrough=u.minTime+time;
                
                if(distanceThroughU < v.minDistance)
                {
                    vertexQueue.remove(v);
                    v.minDistance=distanceThroughU;
                    v.minTime=timeThrough;
                    v.previous=u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)				//function to get shortest path
    {
        List <Vertex> path=new ArrayList <Vertex>();
        for(Vertex vertex=target;vertex!=null;vertex=vertex.previous)
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {  
    	System.out.println("*********************************************************************************************************************************************");
    	System.out.println();
    	System.out.println("\t\t\t\t\t\tWELCOME TO AIR INDIA EXPRESS.");
    	System.out.println("\t\t\t\t\t\t\t\t\t-Simply PriceLess");
    	System.out.println();
    	System.out.println("*********************************************************************************************************************************************");
    	System.out.println();
    	System.out.println("        \t\t\t"+" 24x7 Contact Center - 00914440013001 / 00914424301930 ");
    	System.out.println();
    	System.out.println("*ALERT: Due To Covid-19 Pandemic There Are Only 11 Flights Available In Our Airlines.");
    	System.out.println("        This Step Is Taken Under The Government Guidlines.");
    	System.out.println();
    	System.out.println("*NOTE:- AIR INDIA EXPRESS Requests You To Maintain Social Distancing And To Wash Your Hands Regularly.");
    	System.out.println("        Wear You Facemask Throughout The Journey And Avoid Touching Unnecessarily");
    	System.out.println();
    	Scanner s=new Scanner(System.in);
    	
        Vertex City[]=new Vertex[11];				//array of objects of vertex class to store flight details
        Vertex vv=new Vertex();   
        
        City[0]=new Vertex("Aurangabad",5);
        City[1]=new Vertex("San Francisco",5);
        City[2]=new Vertex("Delhi",5);
        City[3]=new Vertex("Austin",5);
        City[4]=new Vertex("Los Angeles",5);
        City[5]=new Vertex("Jammu",5);
        City[6]=new Vertex("Mumbai",5);
        City[7]=new Vertex("London",5);
        City[8]=new Vertex("Denmark",5);
        City[9]=new Vertex("New York",5);
        City[10]=new Vertex("Paris",5);
  
        City[0].adjacencies=new Edge[] {new Edge(City[6],800,1)};
        City[1].adjacencies=new Edge[] {new Edge(City[2],1100,23)};
        City[2].adjacencies=new Edge[] {new Edge(City[1],1100,23)};
        City[3].adjacencies=new Edge[] {new Edge(City[4],2300.5,3)};
        City[4].adjacencies=new Edge[] {new Edge(City[7],4000,10)};
        City[5].adjacencies=new Edge[] {new Edge(City[4],2500,32)};
        City[6].adjacencies=new Edge[] {new Edge(City[9],800,15)};
        City[7].adjacencies=new Edge[] {new Edge(City[4],4000,10)};
        City[8].adjacencies=new Edge[] {new Edge( City[10],1800,7)};
        City[9].adjacencies=new Edge[] {new Edge(City[8],1500,12)};
        City[10].adjacencies=new Edge[] {new Edge(City[8],1800,5)};
        
        System.out.println();
        System.out.println("\t-----POST LOCKDOWN AVAILABLE FLIGHTS-----");
        System.out.println();
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.print("\t\t");

        System.out.printf("%-20s %-20s%n","CITY NAME : ", "SID : ");
        System.out.println("-------------------------------------------------------------");
        for(int i=0;i<11;i++)
        {
        	System.out.print("\t\t");
        	System.out.printf("%-20s %-20s%n",City[i].name, i);;
        }
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        int n;
     	Seats q=new Seats();
     	do 
     	{
     		System.out.println();
     		System.out.println("--------------------------------");
     		System.out.println("\t1.BOOK TICKET");
     		System.out.println("\t2.CANCEL THE BOOKING");
     		System.out.println("\t3.EXIT");
     		System.out.println("--------------------------------");
     		System.out.print("ENTER YOUR CHOICE : ");
     		n=s.nextInt();
     		switch(n)
     		{
	     		case 1:
	     			System.out.println("");
	     			System.out.print("ENTER THE SOURCE ID(SID) : ");
	                int sid=s.nextInt();
	                System.out.print("ENTER THE DESTINATION ID : ");
	                int did=s.nextInt();
	                computePaths(City[sid]); 
	                List <Vertex> path=getShortestPathTo(City[did]);
	                
	                if(City[did].minDistance==Double.POSITIVE_INFINITY)
	                {
	                	System.out.println("-----SORRY!! NO FLIGHTS AVAILABLE FOR THIS ROUTE-----");
	                	break;
	                }
	                else
	                {
		                System.out.println("-------------------------------------------------------------------");
		                System.out.println();
		                System.out.println("\t\t\t\tAIR INDIA EXPRESS.");
		                System.out.println("\t\t\t\t\t\t-Simply PriceLess");
		                System.out.println("\nPATH : " +path);
		                System.out.println("DISTANCE FROM " + City[sid]+" TO "+City[did]+" IS : "+City[did].minDistance+" KM");
		                System.out.println("TIME REQUIRED : " +City[did].minTime+"HRS");
		                System.out.println("");
	                }
	                q.k=5-City[sid].s;
	     			q.book(vv.arr,sid,did,City);
	     			City[sid].s=City[sid].s-q.adults;
	         		double fair=q.adults*City[did].minDistance*10;
	         		
	         		System.out.println();
	         		System.out.println();
	         		System.out.println("\t\t\t*****************************");
	         		System.out.println("");
	         		System.out.println("\t\t\tTICKET BOOKED SUCCESSFULLY");
	         		System.out.println("\t\t\tTICKET AMOUNT : "+fair);
	                System.out.println("\t\t\t***HAPPY JOURNEY***");
	                System.out.println("");
	                System.out.println("\t\t\t*****************************");
	                System.out.println("");
	                System.out.println("-------------------------------------------------------------------");
	                System.out.println();
	     			break;
	     			
	     		case 2:
	     			System.out.print("\nENTER THE SOURCE ID OF YOUR BOOKED TICKET : ");
	     			int sid1=s.nextInt();
	     			System.out.print("ENTER THE PNR NO (Format of pnr is : SID.name) : ");
	     			String pnr=s.next();
	     		    String z=Integer.toString(sid1);
	     		    
	     		    if(pnr.charAt(0)==z.charAt(0))
	     			{
	     		    	q.cancel();
	     		   		if(q.flag==1)
	     		   			City[sid1].s++;
	     			}
	     		    else
	     		    {
	     		    	System.out.print("ENTER THE NAME OF YOUR BOOKED TICKET : ");
	     		    	String nn=s.next();
	     		    	System.out.println("-----NO BOOKING WITH THIS NAME AVAILABLE-----");
	  				    System.out.println("-----PLEASE ENTER VALID DETAILS-----");
	     		    }
	     			break;	
	     			
	     		case 3:System.exit(0);
	     		
	     		default:System.out.println("ENTERED WRONG CHOICE");
     		}
     	}while(n>0&&n<3);  
     	s.close();
    } 
}

/*
*********************************************************************************************************************************************

WELCOME TO AIR INDIA EXPRESS.
			-Simply PriceLess

*********************************************************************************************************************************************

24x7 Contact Center - 00914440013001 / 00914424301930 

*ALERT: Due To Covid-19 Pandemic There Are Only 11 Flights Available In Our Airlines.
This Step Is Taken Under The Government Guidlines.

*NOTE:- AIR INDIA EXPRESS Requests You To Maintain Social Distancing And To Wash Your Hands Regularly.
Wear You Facemask Throughout The Journey And Avoid Touching Unnecessarily


-----POST LOCKDOWN AVAILABLE FLIGHTS-----


-------------------------------------------------------------
CITY NAME :          SID :               
-------------------------------------------------------------
Aurangabad           0                   
San Francisco        1                   
Delhi                2                   
Austin               3                   
Los Angeles          4                   
Jammu                5                   
Mumbai               6                   
London               7                   
Denmark              8                   
New York             9                   
Paris                10                  
-------------------------------------------------------------


--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 1

ENTER THE SOURCE ID(SID) : 1
ENTER THE DESTINATION ID : 2
-------------------------------------------------------------------

AIR INDIA EXPRESS.
-Simply PriceLess

PATH : [San Francisco, Delhi]
DISTANCE FROM San Francisco TO Delhi IS : 1100.0 KM
TIME REQUIRED : 23.0HRS

SEAT OCCUPIED			SEAT VACANT
| A01 |   			  | A01 |  
| ==  |   			  |     |  

| A01  A02  A03 	  A04  A05  |
| ==   ==   ==  	  ==   ==   |
| B06  B07  B08 	  B09  B10  |
| ==   ==   ==  	  ==   ==   |
| C11  C12  C13 	  C14  C15  |
| ==   ==   ==  	  ==   ==   |
| D16  D17  D18 	  D19  D20  |
| ==   ==   ==  	  ==   ==   |
| E21  E22  E23 	  E24  E25  |
|               	            |

AVAILABLE SEATS : 5

ENTER THE DETAILS : 
NUMBER OF PASSENGERS : 2

DETAILS OF PASSENGER 1
NAME : Anjali
AGE : 19
PNR NO (Format of pnr is : sid.name) : 1.Anjali

DETAILS OF PASSENGER 2
NAME : Tiara
AGE : 20
PNR NO (Format of pnr is : sid.name) : 1.Tiara


*****************************

TICKET BOOKED SUCCESSFULLY
TICKET AMOUNT : 22000.0
***HAPPY JOURNEY***

*****************************

-------------------------------------------------------------------


--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 1

ENTER THE SOURCE ID(SID) : 5
ENTER THE DESTINATION ID : 6
-----SORRY!! NO FLIGHTS AVAILABLE FOR THIS ROUTE-----

--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 1

ENTER THE SOURCE ID(SID) : 5
ENTER THE DESTINATION ID : 4
-------------------------------------------------------------------

AIR INDIA EXPRESS.
-Simply PriceLess

PATH : [Jammu, Los Angeles]
DISTANCE FROM Jammu TO Los Angeles IS : 2500.0 KM
TIME REQUIRED : 32.0HRS

SEAT OCCUPIED			SEAT VACANT
| A01 |   			  | A01 |  
| ==  |   			  |     |  

| A01  A02  A03 	  A04  A05  |
| ==   ==   ==  	  ==   ==   |
| B06  B07  B08 	  B09  B10  |
| ==   ==   ==  	  ==   ==   |
| C11  C12  C13 	  C14  C15  |
| ==   ==   ==  	  ==   ==   |
| D16  D17  D18 	  D19  D20  |
| ==   ==   ==  	  ==   ==   |
| E21  E22  E23 	  E24  E25  |
|               	            |

AVAILABLE SEATS : 5

ENTER THE DETAILS : 
NUMBER OF PASSENGERS : 1

DETAILS OF PASSENGER 1
NAME : Suhas
AGE : 22
PNR NO (Format of pnr is : sid.name) : 5.Suhas


*****************************

TICKET BOOKED SUCCESSFULLY
TICKET AMOUNT : 25000.0
***HAPPY JOURNEY***

*****************************

-------------------------------------------------------------------


--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 2

ENTER THE SOURCE ID OF YOUR BOOKED TICKET : 5
ENTER THE PNR NO (Format of pnr is : SID.name) : 5.Ritu
ENTER YOUR NAME TO CANCEL THE BOOKING : Ritu
-----NO BOOKING WITH THIS NAME AVAILABLE-----
-----PLEASE ENTER VALID DETAILS-----

--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 2

ENTER THE SOURCE ID OF YOUR BOOKED TICKET : 1
ENTER THE PNR NO (Format of pnr is : SID.name) : 1.Anjali
ENTER YOUR NAME TO CANCEL THE BOOKING : Anjali
*******************************

BOOKING CANCELLED SUCCESSFULLY

*******************************

--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 1

ENTER THE SOURCE ID(SID) : 1
ENTER THE DESTINATION ID : 2
-------------------------------------------------------------------

AIR INDIA EXPRESS.
-Simply PriceLess

PATH : [San Francisco, Delhi]
DISTANCE FROM San Francisco TO Delhi IS : 1100.0 KM
TIME REQUIRED : 23.0HRS

SEAT OCCUPIED			SEAT VACANT
| A01 |   			  | A01 |  
| ==  |   			  |     |  

| A01  A02  A03 	  A04  A05  |
| ==   ==   ==  	  ==   ==   |
| B06  B07  B08 	  B09  B10  |
| ==   ==   ==  	  ==   ==   |
| C11  C12  C13 	  C14  C15  |
| ==   ==   ==  	  ==   ==   |
| D16  D17  D18 	  D19  D20  |
| ==   ==   ==  	  ==   ==   |
| E21  E22  E23 	  E24  E25  |
| ==          	            |

AVAILABLE SEATS : 4

ENTER THE DETAILS : 
NUMBER OF PASSENGERS : 1

DETAILS OF PASSENGER 1
NAME : Ketki
AGE : 22
PNR NO (Format of pnr is : sid.name) : 1.Ketki


*****************************

TICKET BOOKED SUCCESSFULLY
TICKET AMOUNT : 11000.0
***HAPPY JOURNEY***

*****************************

-------------------------------------------------------------------


--------------------------------
1.BOOK TICKET
2.CANCEL THE BOOKING
3.EXIT
--------------------------------
ENTER YOUR CHOICE : 3
*/