package com.codev.accumilation.srvcs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codev.accumilation.model.Account;
import com.codev.accumilation.model.Card;


@Service

public class Cards {

	
	// Object dbLock=new Object()

	Long counter = 0l;

	Map<String, Long> storage = new HashMap<>();

	Map<Long, String> inversestorage = new HashMap<>();

	Map<Long, Card> cards= new HashMap<>();

	private String tokey(Long c) {
		return "" + c;
	}

	synchronized public long gocCardId( long externalCardNum) {

		String key = tokey(externalCardNum);

		Long id = storage.get(key);

		if (id == null) {
			id = counter++;

			storage.put(key, id);
			inversestorage.put(id, key);
			cards.put(id, new Card(id));

		}

		return id;

	}

	synchronized public void changeCardDetails(long id, long externalCardNum) {

		String v = inversestorage.get(id);

		if (v != null) {
			String key = tokey(externalCardNum);

			inversestorage.put(id, key);

			storage.remove(v);

			storage.put(key, id);

		}

	}

	synchronized public Card getCardById(long id) {

		return cards.get(id);
	}

	
	public void  dumpCards() {

		System.out.println(cards);;
	}

	

}
