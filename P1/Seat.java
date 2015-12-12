/*
CSE 17
Amber Wallace
alw218
Program #1       DEADLINE: February 12, 2015
Program Description: Seat.java
*/

/**create class for seat*/
public class Seat{
  
  //data fields
  private int section;
  private char row; 
  private int seatNum;
  private boolean sold=false; //initially false, and set to true when the seat is sold
  
  /**constructor that has three formal parameters: the section, row, and number*/
  public Seat(int section, char row, int seatNum){
    this.section=section;
    this.row=row;
    this.seatNum=seatNum;
  }
  
  public int getSection(){
    return this.section;
  }
  
  public char getRow(){
    return this.row;
  }
  
  public int getNumber(){
    return this.seatNum;
  }
  
  public boolean isSold(){
    return this.sold;
  }
  
  /**sellSeat method that will set the sold field to true*/
  public void sellSeat(){
    this.sold=true;
  }
  
  /**getSeatId method that will return a string of the form: “Sect. section row number”*/
  public String getSeatId(){
    String output="Sect. "+this.getSection()+" "+this.getRow()+""+this.getNumber();
    return output;
  }  
  
  /** isAdjacentTo method that takes a Seat formal parameter and returns true if the current seat is next to the actual parameter, and false otherwise*/
  public boolean isAdjacentTo(Seat seat){
    if(this.getSection()==seat.getSection() && this.getRow()==seat.getRow() && (this.getNumber()==seat.getNumber()+1 || this.getNumber()==seat.getNumber()-1)){
      return true;
    }
    else{
      return false;
    }
  }
  
}
  