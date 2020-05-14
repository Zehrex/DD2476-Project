12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/srcx/test/ru/bgcrm/util/XMLUtilsTest.java
package ru.bgcrm.util;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLUtilsTest {

    @Test
    public void testSerialize() throws Exception {
        Document doc = XMLUtils.newDocument();
        Element el = XMLUtils.newElement(doc, "test");
        el.setAttribute("a", "1");
        XMLUtils.newElement(el, "child");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(100);
        XMLUtils.serialize(doc, bos, StandardCharsets.UTF_8.name(), true);
        String result = new String(bos.toByteArray(), StandardCharsets.UTF_8.name());
        Assert.assertTrue(result.contains("<test a=\"1\">\n"));
    }

}
