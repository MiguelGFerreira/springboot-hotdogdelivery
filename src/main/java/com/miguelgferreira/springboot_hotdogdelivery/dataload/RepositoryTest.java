package com.miguelgferreira.springboot_hotdogdelivery.dataload;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.miguelgferreira.springboot_hotdogdelivery.domain.Client;
import com.miguelgferreira.springboot_hotdogdelivery.domain.Item;
import com.miguelgferreira.springboot_hotdogdelivery.domain.Order;
import com.miguelgferreira.springboot_hotdogdelivery.repository.ClientRepository;

@Component
public class RepositoryTest implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(ApplicationRunner.class.getSimpleName());

	private static final long ID_CLIENT_CHRIS = 11L;
	private static final long ID_CLIENT_DAVID = 22L;

	private static final long ID_ITEM1 = 100L;
	private static final long ID_ITEM2 = 101L;
	private static final long ID_ITEM3 = 102L;

	private static final long ID_ORDER1 = 1000L;
	private static final long ID_ORDER2 = 1001L;
	private static final long ID_ORDER3 = 1002L;

	@Autowired
	private ClientRepository clienteRepository;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		logger.info(">>> Starting data load...");
		var chris = new Client(ID_CLIENT_CHRIS, "Chris Bumstead", "Toronto");
		var david = new Client(ID_CLIENT_DAVID, "David Laid", "Los Angeles");

		var dog1 = new Item(ID_ITEM1, "Traditional Hot Dog", 25d);
		var dog2 = new Item(ID_ITEM2, "Spicy traditional Hot Dog", 27d);
		var dog3 = new Item(ID_ITEM3, "Max Protein Hot Dog", 30d);

		var chrisOrderList1 = new ArrayList<Item>();
		chrisOrderList1.add(dog1);

		var davidOrderList1 = new ArrayList<Item>();
		davidOrderList1.add(dog2);
		davidOrderList1.add(dog3);

		var chrisOrder = new Order(ID_ORDER1, chris, chrisOrderList1, dog1.getPrice());
		chris.newOrder(chrisOrder);

		var davidOrder = new Order(ID_ORDER2, david, davidOrderList1, dog2.getPrice() + dog3.getPrice());
		david.newOrder(davidOrder);

		logger.info(">>> Order 1 - Chris : " + chrisOrder);
		logger.info(">>> Order 2 - David: " + davidOrder);

		clienteRepository.saveAndFlush(david);
		logger.info(">>> Client 2 Loaded: " + david);

		var chrisOrderList2 = new ArrayList<Item>();
		chrisOrderList2.add(dog2);

		var chrisOrder2 = new Order(ID_ORDER3, chris, chrisOrderList2, dog2.getPrice());
		chris.newOrder(chrisOrder2);
		logger.info(">>> Order 2 - Chris : " + chrisOrder2);

		clienteRepository.saveAndFlush(chris);
		logger.info(">>> Client 1 loaded: " + chris);

	}

}
