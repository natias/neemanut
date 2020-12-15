package com.codev.accumilation.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codev.accumilation.load.Loader;
import com.codev.accumilation.model.Card;
import com.codev.accumilation.model.Cycle;
import com.codev.accumilation.srvcs.Accounts;
import com.codev.accumilation.srvcs.Cards;
import com.codev.accumilation.srvcs.Cycles;
//

@RestController
@RequestMapping("/ctrl")
public class TiffulController {

	@Autowired
	Loader loader;

	@Autowired

	Cards cards;

	@Autowired

	Cycles cycles;

	@Autowired

	Accounts accounts;

	@GetMapping("/load-tnuout")
	public void loadTnuout(String partition) {
		if (null == partition)
			partition = "";

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
		if (null == partition)
			partition = "";

		loader.loadKartisim(partition);

	}

	DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@GetMapping("/close-cycles")
	public void closeCycles(String partition,String refernceDayS) {
		
		
		System.out.println( refernceDayS);
		if(null==partition)
			partition="";
		
		
		
		LocalDate refernceDay=LocalDate.parse(refernceDayS, dateTimeFormatter);
		
		
		cards.getCards(partition).forEach(l ->
		{
			Card c=cards.getCardById(l);
			List<Long> dc=c.getDailyCycles();
			
			boolean cont=true;
			
			int dcs=dc.size()-1;
			
			while(dcs>=0)
			{
				Long cycid=dc.get(dcs);
				Cycle cycle= cycles.getCycleById(dc.get(dcs));
				
				if(cycle.getEndDate().equals(refernceDay))
					{
					
						System.out.println("moving "+cycid+" for card "+c.getMispar_ashrai_x_sfarot());
						c.getMonthlyCycles().add(cycid);
						break;
					}
			
				
				
				dcs--;
			}
			
			if(dcs==0)
			{
				//no matching pending cycle found
			}
			
		});
//		loader.loadKartisim(partition);

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
