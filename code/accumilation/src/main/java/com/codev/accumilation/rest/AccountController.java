package com.codev.accumilation.rest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codev.accumilation.model.Account;
import com.codev.accumilation.model.Card;
import com.codev.accumilation.model.Cycle;
import com.codev.accumilation.rest.AccountStatusResponse.AccountResponseData;
import com.codev.accumilation.rest.AccountStatusResponse.AccountResponseData.CardResponseData;
import com.codev.accumilation.srvcs.Accounts;
import com.codev.accumilation.srvcs.Cards;
import com.codev.accumilation.srvcs.Cycles;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	Accounts accounts;


	@Autowired
	Cards cards;
	
	@Autowired
	Cycles cycles;
	
	
	@PostMapping

	public AccountStatusResponse getAccountStatus(@RequestBody AccountStatusRequest accountStatusRequest) {

		// accountStatusRequest.
		String bank = accountStatusRequest.getAccountDetails().getBANK();

		String snif = accountStatusRequest.getAccountDetails().getSNIF();

		String accnum = accountStatusRequest.getAccountDetails().getMCH();

		long accid = accounts.getAcoountId(Integer.parseInt(bank), Integer.parseInt(snif), Integer.parseInt(accnum));

		Account acc = accounts.getAccountById(accid);

		AccountStatusResponse accountStatusResponse=new AccountStatusResponse();
		
		
		
		accountStatusResponse.setGenericHeader(new GenericHedaer());	
		
		
		
		
		AccountResponseData accountResponseData=new AccountResponseData();
		
		accountResponseData.setBANK(acc.getBANK());
		
		accountResponseData.setSNIF(acc.getSNIF());
		
		accountResponseData.setMCH(acc.getMCH());
		
		//accountResponseData.se
		accountStatusResponse.setAccount(accountResponseData);
		
		
		//AtomicReference<BigDecimal> totalAccontPoints=new AtomicReference<BigDecimal>(BigDecimal.ZERO);
		
		
		
		acc.getCardids().forEach(cid -> {

			CardResponseData cardResponseData=new CardResponseData(); 
			
			accountResponseData.getCards().add(cardResponseData);
			
			Card card=cards.getCardById(cid);
			
			card.getCardLevel();
			
			cardResponseData.setMIS_KARTIS_ASHRAI_X(card.getMispar_ashrai_x_sfarot());

			Cycle current_cycle=cycles.getCycleById(card.getCurrentCycleId());
			
			System.out.println("current_cycle"+current_cycle);
			
			cardResponseData.setEND_CYCLE_DATE(current_cycle.getEndDate());
			
			cardResponseData.setSTATUS_NAME(card.getCardLevel().name());
			
			cardResponseData.setPOTENTIONAL_POINTS_CYCLE(current_cycle.getNekudot().toString());
			
			//totalAccontPoints.set(totalAccontPoints.get().add(current_cycle.getNekudot()));
		//	accountStatusResponse.getAccount().getCards().add(cardResponseData);
			
			
		}

		);
		
		accountStatusResponse.getAccount().setTotalAccountPoints(BigDecimal.ZERO);

		return accountStatusResponse;
	}

}
