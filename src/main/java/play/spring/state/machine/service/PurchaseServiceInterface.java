package play.spring.state.machine.service;

import org.springframework.statemachine.StateMachine;
import play.spring.state.machine.statemachine.event.PurchaseEvent;
import play.spring.state.machine.statemachine.state.PurchaseState;

public interface PurchaseServiceInterface {
    /**
     * Резервирование товара перед покупкой, зарезервированный товар может находиться в корзине сколько угодно долго
     *
     * @param userId    id пользователя, так как приложение простое, для того чтоб различать пользователей id будем
     *                  принимать прямо в http-запросе
     * @param productId id продукта, который начинает процедуру покупки
     * @return успешная/не успешная операция, в нашем примере операция может стать не успешной если при попытке восстановить
     * машину их импровизированного репозитория произойдет ошибка.
     */
    boolean reserved(String userId, String productId);

    /**
     * Отмена резервирования товара/удаление из пользовательской корзины
     *
     * @param userId id пользователя, так как приложение простое, для того чтоб различать пользователей id будем
     *               принимать прямо в http-запросе
     * @return успешная/не успешная операция, в нашем примере операция может стать не успешной если при попытке восстановить
     * машину их импровизированного репозитория произойдет ошибка.
     */
    boolean cancelReserve(String userId);

    /**
     * Покупка ранее зарезервированного товара
     *
     * @param userId id пользователя, так как приложение простое, для того чтоб различать пользователей id будем
     *               принимать прямо в http-запросе
     * @return успешная/не успешная операция, в нашем примере операция может стать не успешной если при попытке восстановить
     * машину их импровизированного репозитория произойдет ошибка.
     */
    boolean buy(String userId);


    StateMachine<PurchaseState, PurchaseEvent> getCurrentStateMachine(String userId);
}
