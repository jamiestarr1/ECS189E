//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import api.IAdmin;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

public class TestAdmin {
    private IAdmin admin;
    private IStudent student;

    public TestAdmin() {
    }

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testMakeClassNew() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        Assert.assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClassNewWithoutName() {
        this.admin.createClass("", 2017, "Instructor", 15);
        Assert.assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClassNewWithoutInstructor() {
        this.admin.createClass("Test", 2017, "", 15);
        Assert.assertFalse(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClassOld() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        Assert.assertFalse(this.admin.classExists("Test", 2016));
    }

    @Test
    public void testChangeCapacityNormalEmpty() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 20);
        Assert.assertEquals(20, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacityNegativeEmpty() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, -20);
        Assert.assertEquals(15, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacityZeroEmpty() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 0);
        Assert.assertEquals(15, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacityHasStudents() {
        this.admin.createClass("Test", 2017, "Instructor", 5);
        this.student.registerForClass("jam1", "Test", 2017);
        this.student.registerForClass("jam2", "Test", 2017);
        this.student.registerForClass("jam3", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 2);

        Assert.assertEquals(5, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacityHasStudentsZero() {
        this.admin.createClass("Test", 2017, "Instructor", 5);
        this.student.registerForClass("jam1", "Test", 2017);
        this.student.registerForClass("jam2", "Test", 2017);
        this.student.registerForClass("jam3", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, 0);

        Assert.assertEquals(5, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testChangeCapacityHasStudentsNegative() {
        this.admin.createClass("Test", 2017, "Instructor", 5);
        this.student.registerForClass("jam1", "Test", 2017);
        this.student.registerForClass("jam2", "Test", 2017);
        this.student.registerForClass("jam3", "Test", 2017);
        this.admin.changeCapacity("Test", 2017, -5);

        Assert.assertEquals(5, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void testMakeClassNotUniqueNameYear() {
        this.admin.createClass("Test", 2017, "Instructor1", 15);
        this.admin.createClass("Test", 2017, "Instructor2", 15);
        Assert.assertNotEquals("Instructor2", this.admin.getClassInstructor("Test", 2017));
    }

    @Test
    public void testThreeClassesInstructor() {
        this.admin.createClass("Test1", 2017, "Instructor", 15);
        this.admin.createClass("Test2", 2017, "Instructor", 15);
        this.admin.createClass("Test3", 2017, "Instructor", 15);
        Assert.assertEquals("Instructor", this.admin.getClassInstructor("Test1", 2017));
        Assert.assertEquals("Instructor", this.admin.getClassInstructor("Test2", 2017));
        Assert.assertNotEquals("Instructor", this.admin.getClassInstructor("Test3", 2017));
    }






}
