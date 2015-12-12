/*
CSE 17
Amber Wallace
alw218
Program #2       DEADLINE: March 3, 2015
Program Description: Score.java
*/

/**create class Score*/
public class Score{
  //create fields
  private String team; //short name of the team that scored
  private String play; //textual description of the play
  private int points; //number of points scored
  
  /**constructor with 3 args*/
  public Score(String team, String play, int points){ 
    //Initialize fields using parameter values.
    this.team=team;
    this.play=play;
    this.points=points;
  }
  
  /**constructor with 2 args*/
  public Score(String team, String play){
    //Initialize fields using parameter values.
    this.team=team;
    this.play=play;
    this.points=1; // Set points to 1
  }
  
  /**Getter for team.*/
  public String getTeam(){
    return this.team;
  }
    
  /**Getter for play.*/
  public String getPlay(){
    return this.play;
  }
  
  /**Getter for points.*/
  public int getPoints(){
    return this.points;
  }
  
  /**Set method for points.*/
  public void setPoints(int points){
    this.points=points;
  }
}