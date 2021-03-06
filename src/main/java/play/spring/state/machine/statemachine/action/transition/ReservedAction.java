package play.spring.state.machine.statemachine.action.transition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;
import play.spring.state.machine.util.logger.StateMachineLogger;

@Slf4j
@Component
public class ReservedAction implements Action<PurchaseState, PurchaseEvent> {

    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> stateContext) {
        final String productId = stateContext.getExtendedState().get("PRODUCT_ID", String.class);
        log.info("ReservedAction {}.", productId);
        StateMachineLogger.getCurrentInfo("ReservedAction", stateContext);
    }
}
