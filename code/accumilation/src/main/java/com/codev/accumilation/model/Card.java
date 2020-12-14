package com.codev.accumilation.model;

import java.util.ArrayList;
import java.util.List;

import com.codev.accumilation.srvcs.Cycles;
import com.codev.accumilation.srvcs.Rules;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Card {

	final long id;
	
	long accId;
	
	List<Long> cardNumHistory;
	
	boolean participating;
	
	
	String mispar_ashrai_x_sfarot;
	
	Cycles cycles;
//	CardLevel cardLevel;
	
	Rules rules;
	
	public Card(Long id2) {
		id=id2;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}
	
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
	
	List<Long> historicalCycleIds=new ArrayList<Long>();
	
	Long currentCycleId;

	
	
	
	
}
