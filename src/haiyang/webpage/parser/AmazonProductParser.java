package haiyang.webpage.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Optional;

import static java.lang.Double.parseDouble;

public class AmazonProductParser implements ProductPageParser {

    public static void main(String[] args) {
        Optional<Product> echoDot = new AmazonProductParser().parse(URI.create("https://www.amazon" +
                ".com/Echo-Dot/dp/B07N8RPRF7/ref=sr_1_1?dchild=1"));

        System.out.println(echoDot.get().getId());
    }

    @Override
    public Optional<Product> parse(URI uri) {
        try {
            Document document = Jsoup.connect(uri.toString()).get();
            String title = document.title();
            double price = parseDouble(document.getElementById("priceblock_ourprice").text());
            String asin = document.getElementById("ftSelectAsin").val();

            return Optional.of(Product.builder().id(asin).price(price).uri(uri.toString()).domain(uri.getAuthority()).date(new Date()).build());
        } catch (IOException ignored) {
        }
        return Optional.empty();
    }
}
