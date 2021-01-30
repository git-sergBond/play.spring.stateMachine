package play.spring.state.machine.statemachine.guard;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;

@Component
public class HideGuard implements Guard<PurchaseState, PurchaseEvent> {
    @Override
    public boolean evaluate(StateContext<PurchaseState, PurchaseEvent> stateContext) {
        return true;
    }
}
