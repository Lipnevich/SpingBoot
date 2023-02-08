package ru.bell.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bell.api.User;
import ru.bell.service.UserService;

@Component
public class RabbitMQConsumer {

	@Autowired
	private UserService userService;

	@RabbitListener(queues = "${javainuse.rabbitmq.queue}")
	public void receivedMessage(User user) {
		userService.create(user);
	}
}