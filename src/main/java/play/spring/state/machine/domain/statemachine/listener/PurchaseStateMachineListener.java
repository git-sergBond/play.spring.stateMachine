package play.spring.state.machine.domain.statemachine.listener;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;

public class PurchaseStateMachineListener implements org.springframework.statemachine.listener.StateMachineListener<play.spring.state.machine.domain.statemachine.state.PurchaseState, play.spring.state.machine.domain.statemachine.event.PurchaseEvent> {
    @Override
    public void stateChanged(State<PurchaseState, PurchaseEvent> state, State<PurchaseState, PurchaseEvent> state1) {
        if(state != null) {
            if (state.getId() != null) {
                System.out.println(String.join(" ", "Переход из статуса", state.getId().toString(), "в статус", state1.getId().toString()));
            }
        }

    }

    @Override
    public void stateEntered(State<PurchaseState, PurchaseEvent> state) {

    }

    @Override
    public void stateExited(State<PurchaseState, PurchaseEvent> state) {

    }

    @Override
    public void eventNotAccepted(Message<PurchaseEvent> message) {
        System.out.println("eventNotAccepted: " + message);
    }

    @Override
    public void transition(Transition<PurchaseState, PurchaseEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<PurchaseState, PurchaseEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<PurchaseState, PurchaseEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<PurchaseState, PurchaseEvent> stateMachine) {
        System.out.println("MachineStarted!");
    }

    @Override
    public void stateMachineStopped(StateMachine<PurchaseState, PurchaseEvent> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<PurchaseState, PurchaseEvent> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<PurchaseState, PurchaseEvent> stateContext) {

    }
}
