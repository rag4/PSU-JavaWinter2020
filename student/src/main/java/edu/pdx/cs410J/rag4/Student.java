package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  private final double gpa;
  private final ArrayList<String> classes;

  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male" or "female", or "other", case insensitive)
   */                                                                               
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);

    if (gpa > 4.0) {
      throw new IllegalArgumentException(gpa + " is too big");
    }
    if (gpa < 0.0) {
      throw new IllegalArgumentException(gpa + " is too small");
    }

    this.gpa = gpa;

    throwDuplicateClassExceptionIfContainsDuplicates(classes);
    this.classes = classes;
  }

  private void throwDuplicateClassExceptionIfContainsDuplicates(ArrayList<String> classes) {
    Set<String> set = new HashSet<>(classes);
    set.addAll(classes);
    if (set.size() != classes.size()) {
      throw new DuplicateClassException();
    }
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public double getGpa() {
    return this.gpa;
  }
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */
  @Override
  public String toString() {
    int numberOfClasses = this.classes.size();
    return getName() + " has a GPA of " + getGpa()  + " and is taking " +
            numberOfClasses + " class" + (numberOfClasses != 1 ? "es" : "") + ": ";
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }

}