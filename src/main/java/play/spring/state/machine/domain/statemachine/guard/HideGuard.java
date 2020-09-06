package play.spring.state.machine.domain.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;

public class HideGuard implements Guard<PurchaseState, PurchaseEvent> {
    @Override
    public boolean evaluate(StateContext<PurchaseState, PurchaseEvent> stateContext) {
        return true;
    }
}
