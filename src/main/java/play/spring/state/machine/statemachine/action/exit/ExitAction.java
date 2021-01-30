package play.spring.state.machine.statemachine.action.exit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;
import play.spring.state.machine.util.logger.StateMachineLogger;

/**
 * Этот экшен отрабатывает после того так был произведен перреход из одного состояния в другое
 */
@Component
@Slf4j
public class ExitAction implements Action<PurchaseState, PurchaseEvent> {
    @Override
    public void execute(StateContext<PurchaseState, PurchaseEvent> stateContext) {
        StateMachineLogger.getCurrentInfo("ExitAction", stateContext);
    }
}