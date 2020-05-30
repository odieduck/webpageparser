package haiyang.webpage.parser;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class Price {
    double price;
    Date date;
    String domain;
}
