/*
CSE 17
Amber Wallace
alw218
Program #2 DEADLINE: March 3, 2015
Program Description: Football Box Scores
*/ 

/**import File and Scanner*/
import java.io.File;
import java.util.Scanner;

/**create class FootballGame- The description of a game of football. It is a subclass of Game.*/
public class FootballGame extends Game{
  //instance variables
  private String homeShort; //Short version of home team’s name
  private String visitorShort; //Short version of visiting team’s name
  private FootballScore[] scores; //Array of scoring plays
  
  /**Initialize fields using parameter values. dateString is the date game was played in mm/dd/yy format. Will calculate
   the inherited homeScore and visitorScore using the scores array.*/
  public FootballGame(String homeTeam, String homeShort, String visitorTeam, String visitorShort, String dateString,
                      FootballScore[] scores) throws Exception{
    
    //call super class constructor
    super(homeTeam, visitorTeam, dateString);
    
    this.homeShort = homeShort;
    this.visitorShort = visitorShort;
    this.scores = scores;
  }
  
  /**adds the points for each scoring play to calculate the final score for each team and set the inherited homeScore and visitorScore.*/
  private void sumScores(){ 
    //sum home scores
    int[] homeScores = this.getHomeScoreByQuarter();
    
    for(int i=0; i<homeScores.length; i++){
      this.addToHomeScore(homeScores[i]);
    }
    
    //sum visitor scores
    int[] visitorScores = this.getVisitorScoreByQuarter();
    
    for(int i=0; i<visitorScores.length; i++){
      this.addToVisitorScore(visitorScores[i]);
    }
  }
  
  /**Returns an array of the total points scored by the home team in each quarter. 
   This is calculated from scores using the team, quarter, and points of each Score object.*/
  public int[] getHomeScoreByQuarter(){
    //create temp array to store quarter scores and initialize all quarter scores to 0
    int[] temp = {0,0,0,0};
    
    //cycle through for each quarter
    for(int quarter=1; quarter<=4; quarter++){
      //cycle through all scoring plays
      for(int i=0; i<this.scores.length; i++){
        if(this.scores[i].getQuarter()==quarter && this.scores[i].getTeam().toLowerCase().equals(this.homeShort.toLowerCase())){
          //if score is by the hometown in the correct quarter, add points to point array for that quarter
          temp[quarter-1]+=this.scores[i].getPoints();
        }
      }
    }
    
    return temp;
  }
  
  /**Returns an array of the total points scored by the visitor team in each quarter. 
   This is calculated from scores using the team, quarter, and points of each Score object.*/
  public int[] getVisitorScoreByQuarter(){
    //create temp array to store quarter scores and initialize all quarter scores to 0
    int[] temp = {0,0,0,0};
    
    //cycle through for each quarter
    for(int quarter=1; quarter<=4; quarter++){
      //cycle through all scoring plays
      for(int i=0; i<this.scores.length; i++){
        if(this.scores[i].getQuarter()==quarter && this.scores[i].getTeam().toLowerCase().equals(this.visitorShort.toLowerCase())){
          //if score is by the visitors in the correct quarter, add points to point array for that quarter
          temp[quarter-1]+=this.scores[i].getPoints();
        }
      }
    }
    
    return temp;
  }
  
  /**Prints a description of the Game.*/
  public void printBoxScore(){
    this.sumScores(); //sum scores across all quarters for both the home and visiting team
    
    //print first line of information: visitor at home on date: visitorScore - homeScore
    System.out.println(this.getVisitorTeam()+" at "+this.getHomeTeam()+" on "+this.getDateString()+": "+this.getVisitorScore()+"-"+this.getHomeScore());
    
    //print scores by quarter for the home team
    int[] homeScoreByQuarter = this.getHomeScoreByQuarter();
    System.out.printf("%-20s\t %3d %3d %3d %3d  - %-3d\n", this.getHomeTeam(), homeScoreByQuarter[0], homeScoreByQuarter[1], homeScoreByQuarter[2], homeScoreByQuarter[3], this.getHomeScore());
    //print scores by quarter for the visitor team
    int[] visitorScoreByQuarter = this.getVisitorScoreByQuarter();
    System.out.printf("%-20s\t %3d %3d %3d %3d  - %-3d\n", this.getVisitorTeam(), visitorScoreByQuarter[0], visitorScoreByQuarter[1], visitorScoreByQuarter[2], visitorScoreByQuarter[3], this.getVisitorScore());
    
    //find out if there was a no score quarter
    boolean didScore=false;
   
    //first quarter
    System.out.println("First Quarter");
    //loop through plays
    for(int i=0; i<this.scores.length; i++){
      if(this.scores[i].getQuarter()==1){
        System.out.println(this.scores[i]);
        didScore=true;
      }
    }
    
    //if no one scored print 'No score'
    if(!didScore){
      System.out.println("No score");
    }
    
    didScore=false; //reset didScore to false for second quarter
    
    //second quarter
    System.out.println("Second Quarter");
    //loop through plays
    for(int i=0; i<this.scores.length; i++){
      if(this.scores[i].getQuarter()==2){
        System.out.println(this.scores[i]);
        didScore=true;
      }
    }
    
    if(!didScore){
      System.out.println("No score");
    }
    
    didScore=false;
    //third quarter
    System.out.println("Third Quarter");
    //loop through plays
    for(int i=0; i<this.scores.length; i++){
      if(this.scores[i].getQuarter()==3){
        System.out.println(this.scores[i]);
        didScore=true;
      }
    }
    
    if(!didScore){
      System.out.println("No score");
    }
    
    didScore=false;
    //fourth quarter
    System.out.println("Fourth Quarter");
    //loop through plays
    for(int i=0; i<this.scores.length; i++){
      if(this.scores[i].getQuarter()==4){
        System.out.println(this.scores[i]);
        didScore=true;
      }
    }
    
    if(!didScore){
      System.out.println("No score");
    }
    
  }
  
  /**Reads the description of one football game from the file filename and returns the corresponding FootballGame object.*/ 
  public static FootballGame readGameFromFile(String filename) throws Exception{ 
    //make scanner for given file
    File file = new File(filename);
    Scanner input = new Scanner(file);
    
    //set delimiter to take in all data till the next tab
    input.useDelimiter("\\t|[\\n\\r\\f]+");
    
    //get and store data based on assumed order of information in textfile
    String dateString = input.next();
    String homeTeam = input.next();
    String homeShort = input.next();
    String visitorTeam = input.next();
    String visitorShort = input.next();
    int numPlays = input.nextInt(); //number of subsequent lines that contain descriptions of scoring plays.
    
    //create footballScore array with size equal to numPlays
    FootballScore[] scores = new FootballScore[numPlays];
    
    //loop through reading the plays
    for(int i=0; i<numPlays; i++){
      int quarter = input.nextInt();
      String time = input.next();
      String team = input.next();
      String type = input.next();
      String play = input.next();
      
      //if type is TD include extra fields
      if(type.toLowerCase().equals("td")){
        String patPlay = input.next();
        int patPts = input.nextInt();
        
        //if type is TD call FootballScore constructor with 7 args
        scores[i] = new FootballScore(team, type, quarter, time, play, patPts, patPlay);
      }
      else{
        //call FootballScore constructor with only 5 args
        scores[i] = new FootballScore(team, type, quarter, time, play);
      }
    }
    
    //create and return new footballGame with data from file
    FootballGame footballGame = new FootballGame(homeTeam, homeShort, visitorTeam, visitorShort, dateString, scores);
    return footballGame;

  }
  
  /**main method: takes single command-line arg, reads data from given arg file, creates object, prints out game info using the printBoxScore().*/
  public static void main(String[] args) throws Exception{
    //get file name from commandline arg
    if(args.length != 1){
      System.out.println("Usage: java FootballGame file");
      System.exit(0);
    }
    
    //read info from selected file
    FootballGame footballGame = readGameFromFile(args[0]);
    footballGame.printBoxScore();
  }
}