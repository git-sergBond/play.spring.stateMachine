package play.spring.state.machine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import play.spring.state.machine.service.PurchaseServiceInterface;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
public class OrdersApplication implements CommandLineRunner {

	private final PurchaseServiceInterface purchaseService;

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	/**
	 * For tests with stateMachine
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		String userId = "Sergey";
		String productId = "product10";
		purchaseService.reserved(userId, productId);
		purchaseService.buy(userId);
		StateMachine stateMachine = purchaseService.getCurrentStateMachine(userId);
		log.info("stateMachine = {}", stateMachine);
	}
}
