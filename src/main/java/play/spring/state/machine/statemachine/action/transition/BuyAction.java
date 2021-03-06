package play.spring.state.machine.statemachine.action.transition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;
import play.spring.state.machine.util.logger.StateMachineLogger;

@Component
@Slf4j
public class BuyAction implements Action<PurchaseState, PurchaseEvent> {
    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> stateContext) {
        StateMachineLogger.getCurrentInfo("BuyAction", stateContext);
    }
}
