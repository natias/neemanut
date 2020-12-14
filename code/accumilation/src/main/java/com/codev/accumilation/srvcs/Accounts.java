package com.codev.accumilation.srvcs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codev.accumilation.model.Account;

@Service
public class Accounts {

	// Object dbLock=new Object()

	Long counter = 0l;

	Map<String, Long> storage = new HashMap<>();

	Map<Long, String> inversestorage = new HashMap<>();

	Map<Long, Account> accounts = new HashMap<>();

	private String tokey(int bank, int snif, int acc_num) {
		return "" + bank + "_" + snif + "_" + acc_num;
	}

	synchronized public long gocAcoountId(int bank, int snif, int acc_num) {

		String key = tokey(bank, snif, acc_num);

		Long id = storage.get(key);

		if (id == null) {
			id = counter++;

			storage.put(key, id);
			inversestorage.put(id, key);

			Account acc = new Account(id);

			acc.setBANK("" + bank);
			acc.setSNIF("" + snif);
			acc.setMCH("" + acc_num);
			accounts.put(id, acc);

		}

		return id;

	}

	synchronized public long getAcoountId(int bank, int snif, int acc_num) {

		String key = tokey(bank, snif, acc_num);

		Long id = storage.get(key);

		/*
		 * if (id == null) { id = counter++;
		 * 
		 * storage.put(key, id); inversestorage.put(id, key); accounts.put(id, new
		 * Account(id));
		 * 
		 * }
		 * 
		 */
		return id;

	}

	synchronized public void changeAccountDetails(long id, int bank, int snif, int acc_num) {

		String v = inversestorage.get(id);

		if (v != null) {
			String key = tokey(bank, snif, acc_num);

			inversestorage.put(id, key);

			storage.remove(v);

			storage.put(key, id);

		}

	}

	synchronized public Account getAccountById(long id) {

		return accounts.get(id);
	}

	public void dumpAccounts() {

		System.out.println(accounts);
		;

	}

}
