package edu.pdx.cs410J.rag4;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  public void studentHasExpectedGPA() {
    double gpa = 3.25;
    var student = new Student("Name", new ArrayList<>(), gpa, "Other");
    assertThat(student.getGpa(), equalTo(gpa));
  }

  @Ignore
  @Test
  public void exampleStudentFromAssignment() {
    //Dave male 3.64 Algorithms "Operating Systems" Java
    ArrayList<String> classes = new ArrayList<>();
    classes.add("Algorithms");
    classes.add("Operating Systems");
    classes.add("Java");
    var dave = new Student("Dave", classes, 3.64, "male");

    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating\n" +
            "Systems, and Java. He says \"This class is too much work\".\n"));
  }



}
