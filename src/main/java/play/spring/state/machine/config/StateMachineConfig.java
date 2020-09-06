package play.spring.state.machine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import play.spring.state.machine.domain.statemachine.action.CancelAction;
import play.spring.state.machine.domain.statemachine.action.ErrorAction;
import play.spring.state.machine.domain.statemachine.action.ReservedAction;
import play.spring.state.machine.domain.statemachine.action.buyAction;
import play.spring.state.machine.domain.statemachine.event.PurchaseEvent;
import play.spring.state.machine.domain.statemachine.guard.HideGuard;
import play.spring.state.machine.domain.statemachine.listener.PurchaseStateMachineListener;
import play.spring.state.machine.domain.statemachine.persist.PurchaseStateMachinePersister;
import play.spring.state.machine.domain.statemachine.state.PurchaseState;
import static play.spring.state.machine.domain.statemachine.state.PurchaseState.*;
import static play.spring.state.machine.domain.statemachine.event.PurchaseEvent.*;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<PurchaseState, PurchaseEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<PurchaseState, PurchaseEvent> states) throws Exception {
        states
                .withStates()
                .initial(NEW)
                .end(PURCHASE_COMPLITE)
   //TODO       .stateEntry()
   //TODO       .stateExit()
   //TODO       .state()
                .states(EnumSet.allOf(PurchaseState.class));
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<PurchaseState, PurchaseEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new PurchaseStateMachineListener());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<PurchaseState, PurchaseEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(NEW)
                .target(RESERVED)
                .event(RESERVE)
                .action(reservedAction(), errorAction())

                .and()
                .withExternal()
                .source(RESERVED)
                .target(CANCEL_RESERVED)
                .event(RESERVE_DECLINE)
                .action(cancelAction(), errorAction())

                .and()
                .withExternal()
                .source(RESERVED)
                .target(PURCHASE_COMPLITE)
                .event(BUY)
                .guard(hideGuard())
                .action(buyAction(), errorAction());
    }

    @Bean
    public Guard<PurchaseState, PurchaseEvent> hideGuard() {
        return new HideGuard();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> buyAction() {
        return new buyAction();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> cancelAction() {
        return new CancelAction();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public Action<PurchaseState, PurchaseEvent> reservedAction() {
        return new ReservedAction();
    }

    @Bean
    public StateMachinePersister<PurchaseState, PurchaseEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new PurchaseStateMachinePersister());
    }

}
