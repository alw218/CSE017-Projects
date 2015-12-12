/*
CSE 17
Amber Wallace
alw218
Homework #4 DEADLINE: March 17, 2015
Program: CSE Department Personnel
*/ 


//import array list, file, and scanner
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**create class Department*/
public class Department{
  
  //fields
  private ArrayList people; //The people that work for the department.
  
  /**Department Constructor. Initializes people to be empty.*/
  public Department(){
    this.people = new ArrayList();
  }
  
  /**Reads the employees from the file filename and adds them to people.*/
  public void readPeopleFromFile(String filename){
  
    try{
      
      //get file
      File file = new File(filename);

      //create new scanner
      Scanner input = new Scanner(file);
      
      //loop through entire file
      while(input.hasNext()){
        
        //grab type
        String type = input.next(); 
        input.useDelimiter("\\t|[\\n\\r\\f]+"); //break up by tabs
        
        //decide how to act depending on specified type
        if(type.equals("F")){
          
          //get info for faculty member and create new object
          String name = input.next();
          String title = input.next();
          String email = input.next();
          String research = input.next();
          
          Faculty faculty = new Faculty (name, title, email, research);
          
          if(this.people.contains(faculty)){ //check if array list already has instance of specific faculty
            System.out.println("Skipping duplicate for "+email); //do not add because duplicate, and print message
          }
          else{
            this.people.add(faculty); //add faculty to array
          }
          
        }
        else if (type.equals("S")){
          
          //get info, create and store new Employee object
          String name = input.next();
          String title = input.next();
          String email = input.next();
          
          Employee employee = new Employee (name, title, email);
          
          //check if array list already has instance of specific employee
          if(this.people.contains(employee)){
            //do not add because duplicate and print message
            System.out.println("Skipping duplicate for "+email);
          }
          else{
            //add employee to array
            this.people.add(employee);
          }
          
        }
        else{ //not faculty or staff
          
          //print error message and dont include in array
          System.out.println("Skipping "+input.next()+". Wrong type: "+type);
          
        }
        
        //check to see if there is more to the line
        if(input.hasNextLine()){
          input.nextLine(); //jump to end of line
        }
        
      }
      
      //close file scanner is reading from
      input.close();
      
    } 
    catch (FileNotFoundException ex) { //if file name was incorrect
      //print error message
      System.out.println("ERROR: Couldn't read from file "+filename);
    }
  }
  
  /**Prints the employees to standard output using their toString methods*/
  public void printPeople(){
    //loop through all of the arraylist
    for(int i=0; i<this.people.size(); i++){
      //print each objects toString
      System.out.println(this.people.get(i));
    }
  }
  
  /**create main method*/
  public static void main(String[] args){
    
    //if class is run with anything other than a single command-line argument, print error message and terminate.
    if(args.length!=1){
      System.out.println("Usage: java Department filename");
      System.exit(0);
    }
    
    //create a department object
    Department department = new Department();
    
    //print 'reading people from file...' and read file using readPeopleFromFile()
    System.out.println("Reading people from file...");
    department.readPeopleFromFile(args[0]);
    
    //After the file has been read, the method prints ÒCSE People: (number total)Ó
    System.out.println();
    System.out.println("CSE People: ("+department.people.size()+")");
    
    //output the employees using the printPeople method
    department.printPeople();
      
  }
}