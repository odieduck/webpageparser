package haiyang.webpage.parser;

import lombok.Value;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Value
public class AmazonProductParser implements ProductPageParser {
    public static final String FT_SELECT_ASIN = "ftSelectAsin";
    public static final String PRICEBLOCK_OURPRICE = "priceblock_ourprice";
    Locale locale;

    public static void main(String[] args) {
        Optional<Product> echoDot = new AmazonProductParser(Locale.US).parse(ProductPageParser.fromUri(URI.create("https://www.amazon" +
                ".com/Echo-Dot/dp/B07N8RPRF7/ref=sr_1_1?dchild=1")));

        System.out.println(echoDot.get().getId());
        System.out.println(echoDot.get().getPrice());
        System.out.println(echoDot.get().getDomain());
        System.out.println(echoDot.get().getUri());
        System.out.println(echoDot.get().getDate());
    }

    @Override
    public Optional<Product> parse(Document document) {
        try {
            String title = document.title();
            String asin = document.getElementById(FT_SELECT_ASIN).val();
            String priceStr = document.getElementById(PRICEBLOCK_OURPRICE).text();
            double price = NumberFormat.getCurrencyInstance(locale).parse(priceStr).doubleValue();
            URI uri = URI.create(document.location());

            return Optional.of(Product.builder().id(asin).price(price).uri(uri.toString()).domain(uri.getAuthority()).date(new Date()).build());
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }
}
