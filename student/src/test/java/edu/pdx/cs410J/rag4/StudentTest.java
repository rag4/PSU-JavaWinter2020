package edu.pdx.cs410J.rag4;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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
    var student = createStudentWithGpa(gpa);
    assertThat(student.getGpa(), equalTo(gpa));
  }

  private Student getDave() {
    ArrayList<String> classes = new ArrayList<>();
    classes.add("Algorithms");
    classes.add("Operating Systems");
    classes.add("Java");
    return new Student("Dave", classes, 3.64, "male");
  }

  @Ignore
  @Test
  public void exampleStudentFromAssignment() {
    Student dave = getDave();

    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating\n" +
            "Systems, and Java. He says \"This class is too much work\".\n"));
  }

  @Test
  public void toStringContainsStudentName() {
    assertThat(getDave().toString(), containsString("Dave"));
  }

  @Test
  public void toStringContainsGPA() {
    assertThat(getDave().toString(), containsString(" has a GPA of 3.64 "));
  }

  @Test
  public void toStringContainsAnotherGPA() {
    double gpa = 2.5;
    Student student = createStudentWithGpa(gpa);
    assertThat(student.toString(), containsString(" has a GPA of 2.5 "));
  }

  private Student createStudentWithGpa(double gpa) {
    return new Student("Name", new ArrayList<>(), gpa, "Other");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenGpaIs25Point5AnIllegalArgumentExceptionIsThrown() {
    createStudentWithGpa(25.5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenGpaIsLessThanZeroAnIllegalArgumentExceptionIsThrown() {
    createStudentWithGpa(-25.5);
  }

  @Test
  public void davesToStringContains3Classes() {
    String classes = "and is taking 3 classes";
    assertThat(getDave().toString(), containsString(classes));
  }

  @Test
  public void whenStudentTakes1ClassTheWordClassIsSingular() {
    ArrayList<String> classes = new ArrayList<>();
    classes.add("One Class");
    Student student = new Student("Name", classes, 1.23, "Other");
    assertThat(student.toString(), containsString("and is taking 1 class: "));
  }

  @Test(expected = DuplicateClassException.class)
  public void whenStudentTakesTheSameClassTwiceADuplicateClassExceptionShouldBeThrown() {
    ArrayList<String> classes = new ArrayList<>();
    classes.add("One Favorite Class");
    classes.add("One Favorite Class");
    new Student ("Name", classes, 1.23, "Other");
  }

  @Ignore
  @Test
  public void davesToStringContainsHisClasses() {
    String classes = "and is taking 3 classes: Algorithms, Operating Systems, and Java.";
    assertThat(getDave().toString(), containsString(classes));
  }

}
