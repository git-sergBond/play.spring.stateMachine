package play.spring.state.machine.statemachine.persist;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;

import java.util.HashMap;

public class PurchaseStateMachinePersister implements StateMachinePersist<PurchaseState, PurchaseEvent, String> {

    private final HashMap<String, StateMachineContext<PurchaseState, PurchaseEvent>> contexts =
            new HashMap<>();

    @Override
    public void write(StateMachineContext<PurchaseState, PurchaseEvent> stateMachineContext, String s) throws Exception {
        contexts.put(s, stateMachineContext);
    }

    @Override
    public StateMachineContext<PurchaseState, PurchaseEvent> read(String s) throws Exception {
        return contexts.get(s);
    }
}
