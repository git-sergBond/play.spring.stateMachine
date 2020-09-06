package play.spring.state.machine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;

@SpringBootTest
public class StateMachineTests {

    @Autowired
    StateMachineFactory<PurchaseState, PurchaseEvent> factory;

    @Test
    public void testWhenReservedCancel() throws Exception {
        StateMachine<PurchaseState, PurchaseEvent> machine = factory.getStateMachine();
        StateMachineTestPlan<PurchaseState, PurchaseEvent> plan =
                StateMachineTestPlanBuilder.<PurchaseState, PurchaseEvent>builder()
                        .defaultAwaitTime(2)
                        .stateMachine(machine)
                        .step()
                        .expectState(PurchaseState.NEW)
                        .expectStateChanged(0)
                        .and()
                        .step()
                        .sendEvent(PurchaseEvent.RESERVE)
                        .expectState(PurchaseState.RESERVED)
                        .expectStateChanged(1)
                        .and()
                        .step()
                        .sendEvent(PurchaseEvent.RESERVE_DECLINE)
                        .expectState(PurchaseState.CANCEL_RESERVED)
                        .expectStateChanged(1)
                        .and()
                        .build();
        plan.test();
    }

    @Test
    public void testWhenPurchaiseComplete() throws Exception {
        StateMachine<PurchaseState, PurchaseEvent> machine = factory.getStateMachine();
        StateMachineTestPlan<PurchaseState, PurchaseEvent> plan =
                StateMachineTestPlanBuilder.<PurchaseState, PurchaseEvent>builder()
                .defaultAwaitTime(2)
                .stateMachine(machine)
                .step()
                .expectStates(PurchaseState.NEW)
                .expectStateChanged(0)
                .and()
                .step()
                .sendEvent(PurchaseEvent.RESERVE)
                .expectState(PurchaseState.RESERVED)
                .expectStateChanged(1)
                .and()
                .step()
                .sendEvent(PurchaseEvent.BUY)
                .expectState(PurchaseState.PURCHASE_COMPLITE)
                .expectStateChanged(1)
                .and()
                .build();
        plan.test();
    }
}
