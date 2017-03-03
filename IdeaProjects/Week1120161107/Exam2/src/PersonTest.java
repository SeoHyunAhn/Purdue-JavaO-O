import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * Test cases for Person (Exam 2 - Version 1).
 */
public class PersonTest {

    Person p;

    double epsilon = 1e-6;

    @Test(timeout = 100)
    public void testPersonConstructorNotNull()
    {
        p = new Person(20, 120.0);

        String message = "Check Person Constructor; result should not be null.";

        assertNotEquals(message, null, p);
    }

    @Test(timeout = 100)
    public void testPersonNumberOfConstructors()
    {
        Constructor<?>[] constructors = Person.class.getConstructors();

        String message = "Check number of constructors in Person; should be just 1.";

        assertEquals(message, 1, constructors.length);
    }

    @Test(timeout = 100)
    public void testPersonPrivateFields()
    {
        Field[] fields = Person.class.getDeclaredFields();

        boolean check = true;

        for(Field field : fields)
        {
            if(Modifier.isPrivate(field.getModifiers()))
                continue;
            else
                check = false;
        }

        String message = "Check Class fields in Person; all should be private.";

        assertTrue(message, check);
    }

    @Test(timeout = 100)
    public void testPersonNonStaticFields()
    {
        Field[] fields = Person.class.getDeclaredFields();

        boolean check = true;

        for(Field field : fields)
        {
            if(Modifier.isStatic(field.getModifiers()))
                check = false;
            else
                continue;
        }

        String message = "Check Class fields in Person; all should be non-static.";

        assertTrue(message, check);
    }

    @Test(timeout = 100)
    public void testPersonNumberOfFields()
    {
        Field[] fields = Person.class.getDeclaredFields();

        String message = "Check number of fields in Person; you should have 2 private non-static fields.";

        assertEquals(message, 2, fields.length);
    }

    @Test(timeout = 100)
    public void testPersonImplementsAgeable()
    {
        p = new Person(28, 120.0);

        String message = "Check that Person implements Ageable interface.";

        assertTrue(message, p instanceof Ageable);
    }

    @Test(timeout = 100)
    public void testPersonImplementsWeighable()
    {
        p = new Person(28, 120.0);

        String message = "Check that Person implements Weighable interface.";

        assertTrue(message, p instanceof Weighable);
    }

    @Test(timeout = 100)
    public void testPersonImplementsBothInterfaces()
    {
        p = new Person(28, 120.0);

        String message = "Check that Person implements both Ageable and Weighable interface.";

        assertTrue(message, p instanceof Weighable && p instanceof Ageable);
    }

    @Test(timeout = 100)
    public void testGetAge()
    {
        p = new Person(20, 120.0);

        String message = "Check get age accessor in Person.";

        assertEquals(message, 20, p.getAge());
    }

    @Test(timeout = 100)
    public void testGetWeight()
    {
        p = new Person(20, 120.0);

        String message = "Check get weight accessor in Person.";

        assertEquals(message, 120.0, p.getWeight(), epsilon);
    }

    @Test(timeout = 100)
    public void testGetWeightMultiplePeople()
    {
        p = new Person(20, 120.0);

        Person a = new Person(42, 140);

        String message = "Check get weight when multiple Person instances.";

        assertEquals(message, 120, p.getWeight(), epsilon);
    }

    @Test(timeout = 100)
    public void testGetAgeMultiplePeople()
    {
        p = new Person(20, 120.0);

        Person a = new Person(42, 140);

        String message = "Check get age when multiple Person instances.";

        assertEquals(message, 20, p.getAge());
    }

    @Test(timeout = 100)
    public void testGetWeightIntDouble()
    {
        p = new Person(20, 120.0);

        Person p2 = new Person(20, 120);

        String message = "Check Person construction when weight is a double.";

        assertEquals(message, 120.0, p.getWeight(), epsilon);

        message = "Check Person construction when weight is an integer.";

        assertEquals(message, 120, p2.getWeight(), epsilon);

        message = "Check that Person with integer weight and Person with double weight are the same.";
        assertEquals(message, p.getWeight(), p2.getWeight(), epsilon);
    }
}
