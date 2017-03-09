//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestInstructor {
    private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;


    public TestInstructor() {
    }

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();
    }
    @Test
    public void testAddHW(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci" );
        Assert.assertTrue(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }
    @Test
    public void testAddHWClassDoesNotExist(){
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }

    @Test
    public void testAddHW_WrongClassName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test1", 2017, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }

    @Test
    public void testAddHW_WrongInstructorName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor2", "Test", 2017, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }

    @Test
    public void testAddHW_WrongDatePast(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2016, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2016, "hw1"));
    }
    @Test
    public void testAddHW_WrongDateFuture(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2018, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2018, "hw1"));
    }

    @Test
    public void testAddHW_NoClassName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "", 2017, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }

    @Test
    public void testAddHW_NoInstructorName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("", "Test", 2017, "hw1", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }

    @Test
    public void testAddHW_NoHwName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "", "compsci" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, ""));
    }
    @Test
    public void testAddHW_NoHwDescription(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "" );
        Assert.assertFalse(this.instructor.homeworkExists("Test", 2017, "hw1"));
    }

    @Test
    public void testAddGrade() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test", 2017, "hw1", "jamie" ,100 );
        Assert.assertEquals(100, this.instructor.getGrade("Test",2017, "hw1", "jamie").intValue());
    }
    @Test
    public void testAddGradeZero() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test", 2017, "hw1", "jamie" ,0 );
        Assert.assertEquals(0, this.instructor.getGrade("Test",2017, "hw1", "jamie").intValue());
    }

    @Test
    public void testAddGradeNegative() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test", 2017, "hw1", "jamie" ,-100 );
        Assert.assertNotEquals(-100, this.instructor.getGrade("Test",2017, "hw1", "jamie").intValue());
    }

    @Test
    public void testAddGradeWrongInstructor() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor1","Test", 2017, "hw1", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test",2017, "hw1", "jamie").intValue());
    }
    @Test
    public void testAddGradeWrongClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test1", 2017, "hw1", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test1",2017, "hw1", "jamie").intValue());
    }
    @Test
    public void testAddGradeWrongHW() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test", 2017, "hw12", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test",2017, "hw12", "jamie").intValue());
    }
    @Test
    public void testAddGradeNoStudent() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test", 2017, "hw1", "" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test",2017, "hw1", "").intValue());
    }

    @Test
    public void testAddGradeNoHW() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "", "compsci");
        this.instructor.assignGrade("instructor","Test", 2017, "", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test",2017, "", "jamie").intValue());
    }

    @Test
    public void testAddGradeWrongDate() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","Test", 2016, "hw1", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test",2016, "hw1", "jamie").intValue());
    }


    @Test
    public void testAddGradeNoInstructor() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("","Test", 2017, "hw1", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("Test",2017, "hw1", "jamie").intValue());
    }

    @Test
    public void testAddGradeNoClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.instructor.assignGrade("instructor","", 2017, "hw1", "jamie" ,100 );
        Assert.assertNotEquals(100, this.instructor.getGrade("",2017, "hw1", "jamie").intValue());
    }

}
