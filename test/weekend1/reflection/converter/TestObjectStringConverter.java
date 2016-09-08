package weekend1.reflection.converter;

import org.junit.*;

public class TestObjectStringConverter {

    String initialString = "type=weekend1.reflection.converter.User" +
            "\nid=1" +
            "\nname=Bogdan" +
            "\nphoneNumber=+380933509790" +
            "\nage=25";

    private User user;
    private ObjectStringConverter converter;

    @Before
    public void setUp() throws Exception
    {
        user = new User(1, "Bogdan", "+380933509790", 25);
        converter = new ObjectStringConverterImpl();
    }

    @Test
    public void objectToStringConvertTest()
    {
        String string = converter.objectToStringConvert(user);
        Assert.assertTrue(string.equals(initialString));
    }

    @Test
    public void stringToObjectConvertTest()
    {
        Object object = converter.stringToObjectConvert(initialString);
        Assert.assertTrue(object.equals(user));
    }


}
