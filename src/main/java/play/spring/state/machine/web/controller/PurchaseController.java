package play.spring.state.machine.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import play.spring.state.machine.domain.service.purchase.PurchaseServiceInterface;

@RestController
//TODO @SuppressWarnings("unused")
public class PurchaseController {

    private final PurchaseServiceInterface purchaseService;

    public PurchaseController(PurchaseServiceInterface purchaseService) {
        this.purchaseService = purchaseService;
    }

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
