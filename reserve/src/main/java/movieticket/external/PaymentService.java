
package movieticket.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="pay", url="${api.pay.url}")
public interface PaymentService {

    //@RequestMapping(method = RequestMethod.POST, path = "/payments", produces = "application/json", consumes="application/json")
    @PostMapping(value= "/payments", consumes="application/json", produces = "application/json")
    public void pay(@RequestBody Payment payment);
    
}