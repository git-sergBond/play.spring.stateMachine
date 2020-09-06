package play.spring.state.machine.domain.statemachine.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.util.StringUtils;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;

public class ReservedAction implements Action<PurchaseState, PurchaseEvent> {

    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> stateContext) {
        final String productId = stateContext.getExtendedState().get("PRODUCT_ID", String.class);
        System.out.println(String.join(" ", "Товар с номером", productId , "зарезервирован."));
    }
}
