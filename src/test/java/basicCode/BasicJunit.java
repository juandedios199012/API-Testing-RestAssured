package basicCode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicJunit {
     @Before
     public void before(){
         System.out.println("My Before");
     }

     @After
     public void after(){
         System.out.println("My After");
     }

    @Test
     public void verifyTheProjectWasCreated(){
         System.out.println("My Test");
     }

    @Test
    public void verifyTheProjectWasCreated1(){
        System.out.println("My Test");
    }



}
