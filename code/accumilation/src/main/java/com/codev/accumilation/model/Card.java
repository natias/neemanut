package com.codev.accumilation.model;

import java.time.LocalDate;
import java.util.List;

import com.codev.accumilation.srvcs.Cycles;
import com.codev.accumilation.srvcs.Rules;

public class Card {

	long id;
	
	long accId;
	
	List<Long> cardNumHistory;
	
	boolean participating;
	
	
	Cycles cycles;
//	CardLevel cardLevel;
	
	Rules rules;
	
	public CardLevel getCardLevel() {
		
		if(historicalCycleIds.isEmpty())
		{
			return CardLevel.P0;
		}
		else
		{
		
			return rules.levelFor(cycles.getCycleById(historicalCycleIds.get(historicalCycleIds.size()-1)).getTotalSchum())
			;
			
		}
	}
	
	List<Long> historicalCycleIds;
	
	Long currentCycleId;

	
	
	
	
}
