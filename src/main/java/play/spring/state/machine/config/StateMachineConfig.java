package play.spring.state.machine.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import play.spring.state.machine.statemachine.action.transition.BuyAction;
import play.spring.state.machine.statemachine.action.transition.CancelAction;
import play.spring.state.machine.statemachine.action.transition.ErrorAction;
import play.spring.state.machine.statemachine.action.transition.ReservedAction;
import play.spring.state.machine.statemachine.action.exit.ExitAction;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.guard.HideGuard;
import play.spring.state.machine.statemachine.listener.PurchaseStateMachineListener;
import play.spring.state.machine.statemachine.persist.PurchaseStateMachinePersister;
import play.spring.state.machine.statemachine.state.PurchaseState;
import static play.spring.state.machine.statemachine.state.PurchaseState.*;
import static play.spring.state.machine.statemachine.event.PurchaseEvent.*;

import java.util.EnumSet;

@Configuration
@AllArgsConstructor
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<PurchaseState, PurchaseEvent> {

    private final HideGuard hideGuard;

    private final BuyAction buyAction;

    private final CancelAction cancelAction;

    private final ErrorAction errorAction;

    private final ReservedAction reservedAction;

    private final ExitAction exitAction;

    @Bean
    public StateMachinePersister<PurchaseState, PurchaseEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new PurchaseStateMachinePersister());
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<PurchaseState, PurchaseEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new PurchaseStateMachineListener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<PurchaseState, PurchaseEvent> states) throws Exception {
        states
                .withStates()
                .initial(NEW)
                .end(PURCHASE_COMPLITE)
   //TODO       .stateEntry()
   //TODO       .stateExit()
   //TODO       .state()
                .states(EnumSet.allOf(PurchaseState.class))
                .stateExit(RESERVED, exitAction);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<PurchaseState, PurchaseEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(NEW)
                .target(RESERVED)
                .event(RESERVE)
                .action(reservedAction, errorAction)

                .and()
                .withExternal()
                .source(RESERVED)
                .target(CANCEL_RESERVED)
                .event(RESERVE_DECLINE)
                .action(cancelAction, errorAction)

                .and()
                .withExternal()
                .source(RESERVED)
                .target(PURCHASE_COMPLITE)
                .event(BUY)
                .guard(hideGuard)
                .action(buyAction, errorAction);
    }

}
