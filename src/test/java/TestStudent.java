

import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestStudent {
    private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;

    public TestStudent() {
    }




    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.instructor = new Instructor();
    }
    @Test
    public void TestEnrollClass(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2017));
    }

    @Test
    public void TestEnrollClassFull(){
        this.admin.createClass("Test", 2017, "Instructor", 1);
        this.student.registerForClass("jamie","Test",2017);
        this.student.registerForClass("jamie1","Test",2017);
        Assert.assertFalse(this.student.isRegisteredFor("jamie1","Test",2017));
    }

    @Test
    public void TestEnrollOldClass(){
        this.admin.createClass("Test", 2016, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2016);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","Test",2016));
    }

    @Test
    public void TestEnrollWrongDate(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2016);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","Test",2017)&&this.student.isRegisteredFor("jamie","Test",2016));
    }

    @Test
    public void TestEnrollClassWrongClass(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test1",2017);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","Test1",2017));
    }
    @Test
    public void TestEnrollNoCapacityClass(){
        this.admin.createClass("Test", 2017, "Instructor", 0);
        this.student.registerForClass("jamie","Test",2017);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","Test",2017));
    }
    @Test
    public void TestEnrollNegativeCapacityClass(){
        this.admin.createClass("Test", 2017, "Instructor", -15);
        this.student.registerForClass("jamie","Test",2017);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","Test",2017));
    }
    @Test
    public void TestEnrollClassNoStudent(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("","Test",2017);
        Assert.assertFalse(this.student.isRegisteredFor("","Test",2017));
    }
    @Test
    public void TestEnrollClassNoClassName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","",2017);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","",2017));
    }
    @Test
    public void TestDropClass(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.student.dropClass("jamie", "Test", 2017);
        Assert.assertFalse(this.student.isRegisteredFor("jamie","Test",2017));
    }

    @Test
    public void TestDropClassWongClass(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.student.dropClass("jamie", "Test1", 2017);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2017));
    }

    @Test
    public void TestDropClassWongStudent(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.student.dropClass("jamie1", "Test", 2017);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2017));
    }
    @Test
    public void TestDropClassNoStudent(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.student.dropClass("", "Test", 2017);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2017));
    }
    @Test
    public void TestDropClassNoClassName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.student.dropClass("jamie", "", 2017);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2017));
    }
    @Test
    public void TestDropClassWrongDate(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.student.dropClass("", "Test", 2018);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2017));
    }
    @Test
    public void TestDropClassFinishedClass(){
        this.admin.createClass("Test", 2016, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2016);
        this.student.dropClass("", "Test", 2016);
        Assert.assertTrue(this.student.isRegisteredFor("jamie","Test",2016));
    }

    @Test
    public void TestSubmitHW(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","Test",2017);
        Assert.assertTrue(this.student.hasSubmitted("jamie","hw1","Test",2017));
    }

    @Test
    public void TestSubmitHW_NotRegistered(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
    //    this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2017));
    }

    @Test
    public void TestSubmitHW_NoHW(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        //this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2017));
    }

    @Test
    public void TestSubmitHW_NotTaughtCurrent(){
        this.admin.createClass("Test", 2016, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2016);
        this.instructor.addHomework("instructor", "Test", 2016, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2017));
    }

    @Test
    public void TestSubmitHW_NotSubmitCurrent(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","Test",2016);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2016));
    }
    @Test
    public void TestSubmitHW_EmptyAnswer(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2017));
    }

    @Test
    public void TestSubmitHW_EmptyHW(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","","blah","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","","Test",2017));
    }

    @Test
    public void TestSubmitHW_EmptyName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("","hw1","blah","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("","hw1","Test",2017));
    }

    @Test
    public void TestSubmitHW_EmptyClassName(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","",2017));
    }
    @Test
    public void TestSubmitHW_FUTURE(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.submitHomework("jamie","hw1","blah","Test",2018);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2018));
    }
    @Test
    public void TestSubmitHW_AfterDropClass(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.student.registerForClass("jamie","Test",2017);
        this.instructor.addHomework("instructor", "Test", 2017, "hw1", "compsci");
        this.student.dropClass("jamie","Test",2017);
        this.student.submitHomework("jamie","hw1","blah","Test",2017);
        Assert.assertFalse(this.student.hasSubmitted("jamie","hw1","Test",2017));
    }
}
