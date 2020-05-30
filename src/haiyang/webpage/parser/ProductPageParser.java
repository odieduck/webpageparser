package haiyang.webpage.parser;

import java.net.URI;
import java.util.Optional;

public interface ProductPageParser {
    Optional<Product> parse(URI uri);
}
