package ru.job4j.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBUsage {
    public static void main(String[] args) throws Exception {
        Lion lion = new Lion(true, (byte) 20, "breed", new Sound(), new String[]{"First", "Second"});
        JAXBContext context = JAXBContext.newInstance(Lion.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(lion, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Lion result = (Lion) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }
}
