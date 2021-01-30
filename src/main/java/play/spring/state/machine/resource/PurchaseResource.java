package play.spring.state.machine.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import play.spring.state.machine.service.PurchaseServiceInterface;

@RestController
@AllArgsConstructor
public class PurchaseResource {

    private final PurchaseServiceInterface purchaseService;

    @RequestMapping(path = "/reserve")
    public boolean reserve (final String userId, final String productId) {
        return purchaseService.reserved(userId, productId);
    }

    @RequestMapping(path = "/cancel")
    public boolean cancelReserve (final String userId) {
        return purchaseService.cancelReserve(userId);
    }

    @RequestMapping(path = "/buy")
    public boolean buyReserve (final String userId) {
        return purchaseService.buy(userId);
    }
}
