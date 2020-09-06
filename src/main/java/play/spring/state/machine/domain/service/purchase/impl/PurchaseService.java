package play.spring.state.machine.domain.service.purchase.impl;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import play.spring.state.machine.domain.service.purchase.PurchaseServiceInterface;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    private final StateMachineFactory stateMachineFactory;
    private final StateMachinePersister stateMachinePersister;

    public PurchaseService(
            //TODO why i have error at this line ? Cuold not autowire No beans statemachine factory
            StateMachineFactory<PurchaseState, PurchaseEvent> stateMachineFactory,
            StateMachinePersister<PurchaseState, PurchaseEvent, String> stateMachinePersister
    ) {
        this.stateMachineFactory = stateMachineFactory;
        this.stateMachinePersister = stateMachinePersister;
    }

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
}
