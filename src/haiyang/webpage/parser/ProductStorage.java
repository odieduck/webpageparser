package haiyang.webpage.parser;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class ProductStorage {
    String id;
    String uri;
    String domain;
    Price lastPrice;
    List<Price> priceHistory;
}
