package haiyang.webpage.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public interface ProductPageParser {
    static Document fromUri(URI uri) {
        try {
            return Jsoup.connect(uri.toString()).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Optional<Product> parse(Document document);
}
