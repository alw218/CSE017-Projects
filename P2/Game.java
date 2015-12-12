/*
CSE 17
Amber Wallace
alw218
Program #2       DEADLINE: March 3, 2015
Program Description: Game.java
*/

/**import date objects*/
import java.text.SimpleDateFormat;
import java.util.Date;

/**Game The description of a specific game.*/
public class Game{
  //instance variables
  private String homeTeam;
  private String visitorTeam;
  private int homeScore=0;
  private int visitorScore=0;
  private Date date;

  /**Initialize fields using parameter values. dateString is the date the game was played in mm/dd/yy format.*/
  public Game(String homeTeam, String visitorTeam, String dateString) throws Exception{
    this.homeTeam = homeTeam;
    this.visitorTeam = visitorTeam;
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
    this.date = df.parse(dateString); //fix date thing
  }

  /**Getter for home team.*/
  public String getHomeTeam(){
    return this.homeTeam;
  }
  
  /**Getter for visiting team*/
  public String getVisitorTeam(){
    return this.visitorTeam;
  }
  
  /**Getter for the home team’s score.*/
  public int getHomeScore(){
    return this.homeScore;
  }
  
  /**Getter for the visiting team's score*/
  public int getVisitorScore(){
    return this.visitorScore;
  }
  
  /**Returns the date as a string in mm/dd/yy format.*/
  public String getDateString(){
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
    return df.format(this.date);
  }
  
  /**adds points to homeScore*/
  public void addToHomeScore(int points){
    this.homeScore += points;
  }
  
  /**adds points to visitor score*/
  public void addToVisitorScore(int points){
    this.visitorScore += points;
  }
}