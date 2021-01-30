package play.spring.state.machine.util.logger;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;

@Slf4j
@UtilityClass
public class StateMachineLogger {

    public void getCurrentInfo(String sectionName, StateContext<PurchaseState, PurchaseEvent> stateContext) {
        log.info("\n==={}===START \nstate = {}, \nstage = {} \n==={}===END",
                sectionName,
                stateContext.getStateMachine().getState().getId(),
                stateContext.getStage().toString(), sectionName);
    }
}
