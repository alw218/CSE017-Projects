/*
CSE 17
Amber Wallace
alw218
Program #5 DEADLINE: April 23, 2015
Program Description: Digital Music Library with Advanced Sorting
*/ 

//import Comparator
import java.util.Comparator;

/**create class for Song*/
public class Song{
  //fields
  private String title; //name of song
  private String artist; //name of artist
  private String album; //name of album
  private int time; //length of song in seconds
  private int year; //year the song was released
  
  /**comparator sorts based on title*/
  public class SongTitleComparator implements Comparator<Song>{
    public Comparator<Song> SongTitleComparator(){
      return new SongTitleComparator();
    }
    
    /**compare based on title*/
    public int compare(Song s1, Song s2){
      //compare based on title
      return s1.getTitle().compareTo(s2.getTitle());
    }
  }
  
  /**comparator sorts based on Artist*/
  public class SongArtistComparator implements Comparator<Song>{
    public Comparator<Song> SongArtistComparator(){
      //A constant instance with a compare() method that orders by artist (ascending).
      return new SongArtistComparator();
    }
    
    /**compare based on artist*/
    public int compare(Song s1, Song s2){
      //compare based on Artist
      return s1.getArtist().compareTo(s2.getArtist());
    }
  }
  
  /**comparator sorts based on year*/
  public class SongYearComparator implements Comparator<Song>{
    public Comparator<Song> SongYearComparator(){
      //A constant instance with a compare() method that orders by year (ascending).
      return new SongYearComparator();
    }
    
    /**compare by year*/
    public int compare(Song s1, Song s2){
      //compare based on title
      if (s1.getYear() == s2.getYear()){
        return 0;
      }
      else if(s1.getYear() > s2.getYear()){
        return 1;
      }
      else{
        return -1;
      }
    }
  }
  
  /**Initialize a new song. Time is specified as a number of seconds.*/
  public Song(String title, String artist, String album, int year, int time){
    //Initialize a new song. Time is specified as a number of seconds.
    this.title = title;
    this.artist = artist;
    this.album = album;
    this.year = year;
    this.time = time;
  }
  
  /**Initialize a new song. Time is specified as mm:ss*/
  public Song(String title, String artist, String album, int year, String timeString){
    //initialize new song with time in seconds
    this(title, artist, album, year, timeStringToSecs(timeString));
  }
  
  /**getter for title*/
  public String getTitle(){
    return this.title;
  }
  
  /**getter for artist*/
  public String getArtist(){
    return this.artist;
  }
  
  /**getter for album*/
  public String getAlbum(){
    return this.album;
  }
  
  /**getter for year*/
  public int getYear(){
    return this.year;
  }
  
  /**gettter for time*/
  public int getTime(){
    return this.time;
  }
  
  /**getter for time as formated string*/
  public String getTimeAsFormattedString(){
    //return time as string in mm:ss format
    int sec = this.time % 60;
    int min = (this.time - sec)/60;
    
    return String.format("%2d:%02d", min, sec);
  }
  
  /**method prints song info in a line*/
  public void printSongRow(){
    System.out.printf("%-25s\t%-25s\t%-30s\t%d\t%s\n", this.getTitle(), this.getArtist(), this.getAlbum(), this.getYear(), this.getTimeAsFormattedString());
  }
  
  /**to string method*/
  public String toString(){
    return "\""+this.getTitle()+"\" by "+this.getArtist()+" ("+this.getYear()+")";
  }
  
  /**Given a string of the form “mm:ss” returns an integer representing the time as a total number of seconds.*/
  public static int timeStringToSecs(String timeString){
    //store timeString in stringBuilder
    StringBuilder string = new StringBuilder(timeString);
    
    //use parseInt and substring to separate out mins and secs and convert to int form
    int secs = Integer.parseInt(string.substring(timeString.length()-2));
    int mins = Integer.parseInt(string.substring(0, timeString.length()-3));
    
    //convert min into seconds and add additional seconds then return
    return mins * 60 + secs;
  }
}


