/*
CSE 17
Amber Wallace
alw218
Program #1       DEADLINE: February 12, 2015
Program Description: TixMatrix
*/

import java.util.Scanner; //import scanner

/**create class for tixMatrix*/
public class TixMatrix{
  
  //data fields
  private Seat[] seats; //instance field called seats that is an array of type Seat
  private int numSold=0; //instance field called numSold that tracks the number of seats sold for the show so far
  private int[] sectionNums;
  private int seatsPerRow;
  private int rowsPerSect;
  
   /**constructor for TixMatrix that takes array of section numbers, number of rows per section, and number of seats per row*/
  public TixMatrix(int[] sectionNums, int rowsPerSect, int seatsPerRow){
    
    //create new seat array for seat objects for tixMatrix
    int seatIndex=0; //keep track of each seat in tixMatrix
    this.sectionNums=sectionNums;
    this.seatsPerRow=seatsPerRow;
    this.rowsPerSect=rowsPerSect;
    seats=new Seat[sectionNums.length*rowsPerSect*seatsPerRow]; //make the seat array appropriate size
    
    //loop through sections, rows, and seats to create each seat with unique info
    for(int sectionCount=1; sectionCount<=sectionNums.length; sectionCount++){ //loop sections
      for(int rowCount=1; rowCount<=rowsPerSect; rowCount++){ //loop rows
        for(int seatCount=1; seatCount<=seatsPerRow; seatCount++){ //loop seats
          
          //create new seat within array with appropriate info
          this.seats[seatIndex]=new Seat(sectionNums[sectionCount-1], (char)('@'+rowCount), seatCount);
          seatIndex++; //increment index
          
        }
      }
    }
  }
    
  /**no-arg constructor that creates object with four sections (101,102,201,202), 10 rows per section, and 15 seats per row*/
  public TixMatrix(){
    this(new int[]{101,102,201,202}, 10, 15);
  }
  
  
  /**method sellSeat that takes a Seat parameter and returns a boolean*/
  public boolean sellSeat(Seat seat){
    if(seat.isSold()){ //if not sold
      return false; //return false
    }
    else{ //if sold
      seat.sellSeat(); //sell seat
      this.numSold++; //increment the numSold
      return true; //return true
    }
  }
  
  /**An overloaded sellSeat method that takes formal parameters for section, row, and number*/
  public boolean sellSeat(int section, char row, int number){
    
    //locate specified seat in seats array
    
    int numRow=((row)-'a'); //store row as number  
    //find index of section number
    int sectionIndex=0;
    for(int i=0; i<this.sectionNums.length; i++){
      if(section==this.sectionNums[i]){
        sectionIndex=i;
      }
    }
    
    //get index of seat according to tixMatrix seat array
    int seatIndex=sectionIndex*this.rowsPerSect*this.seatsPerRow+numRow*this.seatsPerRow+number-1;
    
    //sell seat as described in previous method
    boolean seatStatus=this.sellSeat(this.seats[seatIndex]);
    if(seatStatus){
      System.out.println("Sold!");
    }
    else{
      System.out.println("Sorry, that seat is taken.");
    }
    return seatStatus;
  }
    
  /**getBestAdjacentSeats methods that takes an integer parameter for the number of seats requested, 
   and then returns an array consisting of that number of unsold Seats.*/
  public Seat[] getBestAdjacentSeats(int numSeats){
    int numAdjacentSeats=0;
    //create an array to hold seats to be sold
    Seat[] seatsToSell=new Seat[numSeats];
    
    //find the best n seats that are adjacent using isAdjacentTo method from seat.java
    for(int currentSeat=0; currentSeat<this.seats.length-numSeats; currentSeat++){ //loop through all seats
        numAdjacentSeats=0; //reset count with every new seat
        if(!this.seats[currentSeat].isSold()){ //if current seat is availiable
          seatsToSell[0]=this.seats[currentSeat]; //add to array
          numAdjacentSeats++;
          for(int numFreeSeats=1; numFreeSeats<numSeats; numFreeSeats++){ //loop through seats next to it
          
            //if seats are adjacent and not already sold
            if(this.seats[currentSeat+numFreeSeats-1].isAdjacentTo(this.seats[currentSeat+numFreeSeats]) && !this.seats[currentSeat+numFreeSeats].isSold()){
            
              //add seats to array of seats to sell
              seatsToSell[numFreeSeats]=this.seats[currentSeat+numFreeSeats];
              numAdjacentSeats++;
            }
         
        }
      }
        if(numAdjacentSeats==numSeats){
          break;
        }
    }
    
    if(numAdjacentSeats<numSeats){ //if there are not enough availiable adjacent seats
      Seat[] empty=new Seat[0]; 
      return empty; //return an empty array
    }
    else{
      return seatsToSell; //return array of best availiable adjacent seats
    }
  }
  
  public int getNumSeats(){
    //return the total number of seats for the show
    return this.seats.length;
  }
  
  public int getNumSold(){
    //return the total number of seats sold so far
    return this.numSold;
  }
  
  /**print out every seat that hasn't been sold yet using the form described in getSeatId, one per line*/
  public void printAllUnsoldSeats(){
    for(int currentSeat=0; currentSeat<this.seats.length; currentSeat++){ //loop through all seats
      if(!this.seats[currentSeat].isSold()){
        System.out.println(this.seats[currentSeat].getSeatId());
      }
    }
  }
  
  
  /** Main method*/
  public static void main(String[] args){
    
    /**construct TixMatrix with two sections (101 &102), 4 rows, and 5 seats per row*/
    int[] section={101,102};
    TixMatrix tixMatrix=new TixMatrix(section, 4, 5);
    
    //create new scanner
    Scanner input=new Scanner(System.in);
    
    System.out.println("Welcome to TixMatrix!"); //print welcome message
    
    int action; //create var to store action choice for each round
    
    do{
      
      //give user options
      System.out.println();
      System.out.println("Enter a numeric choice: 1) best available, 2) specific seat, 3) quit");
      
      action=input.nextInt(); //store user choice
      
      if(action==1){
        //best availiable
        /**find the best adjacent seats that satisfy the request, and then mark them as sold.*/
        
        System.out.println("How many adjacent seats?");
        int userNumSeats=input.nextInt();
        
        //create array to store best adjacent seats
        Seat[] bestSeats=tixMatrix.getBestAdjacentSeats(userNumSeats);
        
        if(bestSeats.length!=0){
          for(int i=0; i<bestSeats.length; i++){
            //bestSeats[i].sellSeat();
            tixMatrix.sellSeat(bestSeats[i]);
            /**Print out ids in getSeatId format for seats that have been sold*/
            if(i==0){
              System.out.print(bestSeats[i].getSeatId());
            }
            else{
              System.out.print(", "+bestSeats[i].getSeatId());
            }
          }
          System.out.println();
        }
       
      }
      else if(action==2){ //specific seat
        
        //get user input for specific seat
        System.out.println("Enter section, row, and number, separated by spaces:");
        int userSection=input.nextInt();
        char userRow=input.next().toLowerCase().charAt(0); //store char
        int userSeatNum=input.nextInt();
        
        tixMatrix.sellSeat(userSection, userRow, userSeatNum);
      }
      else if(action!=3){
        //invalid input
        System.out.println();
        System.out.println("Invalid Choice.");
      }
      
    }while(action!=3);
    
    System.out.printf("Sold "+tixMatrix.getNumSold()+" of "+tixMatrix.getNumSeats()+" (%.1f percent)", (double)tixMatrix.getNumSold()/(double)tixMatrix.getNumSeats()*100);//change num sold to percent
    //print out list of all unsold seats
    System.out.println();
    System.out.println("All unsold seats:");
    tixMatrix.printAllUnsoldSeats();
  }
}