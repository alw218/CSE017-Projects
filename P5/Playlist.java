/*
CSE 17
Amber Wallace
alw218
Program #5 DEADLINE: April 23, 2015
Program Description: Digital Music Library with Advanced Sorting
*/ 

//import
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**Create playlist class*/
public class Playlist{
  private String name; //The name of the playlist
  private ArrayList<Song> songs; //A list of songs in the playlist
  
  /**constructor for playlist*/
  public Playlist(String name){
    //initialize a new playlist with no songs
    this.songs= new ArrayList<Song>();
    this.name = name;
  }
  
  /**getter method for name*/
  public String getName(){
    return this.name;
  }
  
  /**getter for Song at specific index*/
  public Song getSong(int index){
    if(index <= this.songs.size() && index > 0){
      return this.songs.get(index-1);
    }
    else{
      //if index is invalid return null
      return null;
    }
  }
  
  /**getter for number of songs in playlist*/
  public int getNumSongs(){
    return this.songs.size();
  }
  
  /**add a song to the end of songs*/
  public void addSong(Song song){
    this.songs.add(song);
  }
  
  /**method outputs formated playlist*/
  public void printList(){
    //print name
    System.out.println(this.getName());
    //print all songs in playlist
    for(int i = 0; i<this.songs.size(); i++){
      System.out.printf(""+(i+1)+"\t");
      this.songs.get(i).printSongRow();
    }
  }
  
  /**Invokes a merge sort to resort songs by year in ascending order.*/
  public void sortByYear(){
    ComparatorSorts.<Song>mergeSort(this.songs, new SongYearComparator());
  }
  
  /**Invokes a quick sort to resort songs by artist in ascending order.*/
  public void sortByArtist(){
    ComparatorSorts.<Song>quickSort(this.songs, new SongArtistComparator());
  }
  
  /**Invokes a quick sort to resort songs by title in ascending order.*/
  public void sortByTitle(){
    ComparatorSorts.<Song>quickSort(this.songs, new SongTitleComparator());
  }
  
  public static Playlist readPlayListFromFile(String filename){
    //make scanner for given file
    File file = new File(filename);
    
    try{
      Scanner input = new Scanner(file);
      
      //set delimiter to take in all data till the next tab
      input.useDelimiter("\\t|[\\n\\r\\f]+");
      
      //name of playlist is first
      String name = input.next();
      
      //create new playlist object
      Playlist playlist = new Playlist(name);
      
      //go through all data
      while(input.hasNext()){
        //add song with data in the order: title, artist, album, year, and time in timeString form.
        playlist.addSong(new Song(input.next(), input.next(), input.next(), input.nextInt(), input.next()));
      }
      
      return playlist;
    }
    catch(FileNotFoundException e){
      return null;
    }
    
  }
  
  /**main method reads in, sorts, and prints playlists given in args*/
  public static void main(String[] args){
    //creat arraylist to store all playlists given by args
    ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    
    //loop through arg files and turn into playlists
    for(int i = 0; i<args.length; i++){
      //read in playlist and add to arraylist of playlists
      playlists.add(readPlayListFromFile(args[i]));
      //call printList method
      playlists.get(i).printList();
      System.out.println();
    }
    
    //quick sort the first list by artist and print the list again
    System.out.println("Sorting "+playlists.get(0).getName()+" by artist:");
    playlists.get(0).sortByArtist();
    playlists.get(0).printList();
    System.out.println();
    
    //merge sort the last list by year and print our this sorted list
    System.out.println("Sorting "+playlists.get(playlists.size()-1).getName()+" by year:");
    playlists.get(playlists.size()-1).sortByYear();
    playlists.get(playlists.size()-1).printList();
    System.out.println();
    
    //print out the first and last song of the first playlist by calling the toString method on each
    System.out.println("First and last song in "+playlists.get(0).getName());
    System.out.println(playlists.get(0).getSong(1).toString());
    System.out.println(playlists.get(0).getSong(playlists.get(0).getNumSongs()).toString());
  }
}