/*
CSE 17
Amber Wallace
alw218
Program #2       DEADLINE: March 3, 2015
Program Description: FootballScore.java
*/

/**FootballScore A football scoring play. It is a subclass of Score.*/

/**create class FootballScore*/
public class FootballScore extends Score{
  
  //add new fields
  private int quarter; //The quarter the score occurred in (>=1, <=4)
  private String time; //The time in the quarter when the play occurred.
  private int patPts; //The number of Òpoints after touchdownÓ scored. Is 0 if the play is not a touchdown.
  private String patPlay; //Textual description of the try for points after touchdown. Is null if the play is not a touchdown.
  private String type; //The type of play. Either ÒTDÓ for touchdown, ÒFGÓ for field goal, or ÒSAÓ for safety..
  
  /**FootballScore constructors*/
  //constructor with 7 args
  public FootballScore(String team, String type, int quarter, String time, String play, int patPts, String patPlay){
    super(team,play);
    this.type=type;
    this.quarter=quarter;
    this.time=time;
    this.patPts=patPts;
    this.patPlay=patPlay;
    
    //calculate the total points of the play
    if(type.toLowerCase().equals("td")){
      this.setPoints(6+patPts); //A touchdown is worth 6 points plus patPts
    }
    else if(type.toLowerCase().equals("fg")){
      this.setPoints(3); //A field goal is worth 3
    }
    else if(type.toLowerCase().equals("sa")){
      this.setPoints(2); //A safety is worth 2
    }
  }
  
  //constructor with 5 args
  public FootballScore(String team, String type, int quarter, String time, String play){
    super(team,play);
    //Initialize fields using parameter values
    this.type=type;
    this.quarter=quarter;
    this.time=time;
    this.patPts=0; //sets patPts to 0
    this.patPlay=null; //sets patPlay to null
    
    //calculate the total points of the play
    if(type.toLowerCase().equals("td")){
      this.setPoints(6+patPts); //A touchdown is worth 6 points plus patPts
    }
    else if(type.toLowerCase().equals("fg")){
      this.setPoints(3); //A field goal is worth 3
    }
    else if(type.toLowerCase().equals("sa")){
      this.setPoints(2); //A safety is worth 2
    }
  }
  
  
  /**Getter for quarter.*/
  public int getQuarter(){
    return this.quarter;
  }
  
  /**Getter for time.*/
  public String getTime(){
    return this.time;
  }
  
  /**Getter for type.*/
  public String getType(){
    return this.type;
  }
  
  /**Returns a string of the form Òteam Ð play timeÓ or Òteam Ð play ( PATplay ) time depending on whether patPlay is null or not.*/
  public String toString(){
    if(this.patPlay==null){
      return this.getTeam()+" - "+this.getPlay()+" "+this.getTime();
    }
    else{
      return this.getTeam()+" - "+this.getPlay()+" ( "+this.patPlay+" ) "+this.getTime();
    }
  }
      
}
  