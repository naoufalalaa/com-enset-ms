package ma.enset.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class CustomerConfigTestController {
    @Value("23")
    //@Value("${global.params.p1}")
    private String p1;

    @Value("32")
    //@Value("${global.params.p2}")
    private String p2;

    @Value("34")
    //@Value("${customer.params.x}")
    private String x;

    @Value("44")
    //@Value("${customer.params.y}")
    private String y;
    @GetMapping("/params")
    public Map<String, String> params(){
        return Map.of("p1",p1,"p2",p2,"x",x,"y",y);
    }
}
