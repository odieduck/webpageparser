package haiyang.webpage.parser;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class Product {
    String id;
    String uri;
    String domain;
    double price;
    Date date;
}
