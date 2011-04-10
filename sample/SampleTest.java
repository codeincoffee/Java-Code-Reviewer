package sample;

import junit.framework.TestCase;

public SampleTest extends TestCase {

    private HelloWorld helloWorld;

    public void setUp() {
        helloWorld = new HelloWorld();
    }
    
    /**
     * Code Review works fine in TDD 
     */
    public void testReview() {
        CodeReview.getInstance("sample").review(Sample.class);            
    }

    public void testSayHello() {
        String helloWorld = "Hello World";
        assertEquals(helloWorld, helloWorld.say(helloWorld));
    }
    
    public void testReviewFailInMethodLength() {
        //here, you create a method which's length is largger than the limit you configured in config.xml
        //so it will fail in code review, testReview() will print some words like this:
        
        //message: Your result in method.Length is: 12 which is out of limit: 7
        //in methodTooLong:line:9
    }

}