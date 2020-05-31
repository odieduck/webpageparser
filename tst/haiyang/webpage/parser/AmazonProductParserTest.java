package haiyang.webpage.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.Optional;

import static haiyang.webpage.parser.AmazonProductParser.FT_SELECT_ASIN;
import static haiyang.webpage.parser.AmazonProductParser.PRICEBLOCK_OURPRICE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AmazonProductParserTest {
    private Document echoDotDocument;

    @Before
    public void setup() {
        echoDotDocument = mock(Document.class);
        when(echoDotDocument.title()).thenReturn("echo");
        when(echoDotDocument.location()).thenReturn("https://www.amazon.com/dp/echoasin");
        Element asinElement = mock(Element.class);
        when(asinElement.val()).thenReturn("B07N8RPRF7");
        when(echoDotDocument.getElementById(FT_SELECT_ASIN)).thenReturn(asinElement);
        Element priceElement = mock(Element.class);
        when(priceElement.text()).thenReturn("$12.22");
        when(echoDotDocument.getElementById(PRICEBLOCK_OURPRICE)).thenReturn(priceElement);
    }

    @Test
    public void itShouldParseProductDocument() {
        ProductPageParser parser = new AmazonProductParser(Locale.US);
        Optional<Product> parsed = parser.parse(echoDotDocument);
        assertTrue(parsed.isPresent());
        Product product = parsed.get();
        assertEquals("B07N8RPRF7", product.getId());
        assertEquals("www.amazon.com", product.getDomain());
        assertEquals(12.22, product.getPrice(), 1E-6);
        assertEquals("https://www.amazon.com/dp/echoasin", product.getUri());
        assertNotNull(product.getDate());
    }
}
