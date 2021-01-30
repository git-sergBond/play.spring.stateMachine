package play.spring.state.machine.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import play.spring.state.machine.service.PurchaseServiceInterface;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;

@Service
@AllArgsConstructor
public class PurchaseService implements PurchaseServiceInterface {

    private final StateMachineFactory<PurchaseState, PurchaseEvent> stateMachineFactory;

    private final StateMachinePersister<PurchaseState, PurchaseEvent, String>  stateMachinePersister;

    @Override
    public boolean reserved(String userId, String productId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.getExtendedState().getVariables().put("PRODUCT_ID", productId);
        stateMachine.sendEvent(PurchaseEvent.RESERVE);
        try {
            stateMachinePersister.persist(stateMachine, userId);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean cancelReserve(String userId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            stateMachinePersister.restore(stateMachine, userId);
            stateMachine.sendEvent(PurchaseEvent.RESERVE_DECLINE);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean buy(String userId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();
        try {
            stateMachinePersister.restore(stateMachine, userId);
            stateMachine.sendEvent(PurchaseEvent.BUY);
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public StateMachine<PurchaseState, PurchaseEvent> getCurrentStateMachine(String userId) {
        final StateMachine<PurchaseState, PurchaseEvent> stateMachine = stateMachineFactory.getStateMachine();

        try {
            stateMachinePersister.restore(stateMachine, userId);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

        return stateMachine;
    }
}
