package com.codev.accumilation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codev.accumilation.load.Loader;
import com.codev.accumilation.srvcs.Accounts;
import com.codev.accumilation.srvcs.Cards;

@RestController
@RequestMapping("/ctrl")
public class TiffulController {

	@Autowired
	Loader loader;

	@Autowired

	Cards cards;

	@Autowired

	Accounts accounts;

	@GetMapping("/load-tnuout")
	public void loadTnuout(String partition) {
		if(null==partition)
			partition="";
		
		loader.loadTnuot(partition);

	}

//	@GetMapping("/load-heshbonot")
//	public void loadHeshbonot(String partition)
//	{
//		
//		loader.loadHeshbonot(partition);
//		
//	}

	@GetMapping("/load-kartisim")
	public void loadKartisim(String partition) {
		if(null==partition)
			partition="";
		
		loader.loadKartisim(partition);

	}

	@GetMapping("/dump-kartisim")
	public void dumpKartisim() {
		cards.dumpCards();
		;

	}

	@GetMapping("/dump-accounts")
	public void dumpHeshbonot() {
		accounts.dumpAccounts();
		;

	}

}
