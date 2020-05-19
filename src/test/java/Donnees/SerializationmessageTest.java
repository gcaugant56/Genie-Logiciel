package Donnees;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class SerializationmessageTest {


    @Test
    public void DeserializationTest() throws IOException {

        Assertions.assertNotNull(Serializationmessage.Deserialization("Json.json"));
        Assertions.assertNull(Serializationmessage.Deserialization("json.test"));
    }

    @Test
    public void SerializationTest()throws IOException {

        Assertions.assertTrue(Serializationmessage.Serialization(Serializationmessage.Deserialization("Json.json"), "Testu.json"));
        Assertions.assertFalse(Serializationmessage.Serialization(Serializationmessage.Deserialization("Test.json"), "Test1.json"));


    }

}
