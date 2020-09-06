package play.spring.state.machine.domain.statemachine.action;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;

public class CancelAction implements Action<PurchaseState, PurchaseEvent> {
    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> stateContext) {

    }
}
