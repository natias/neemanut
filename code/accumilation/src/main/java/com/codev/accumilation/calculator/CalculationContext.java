package com.codev.accumilation.calculator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.codev.accumilation.model.Cycle;

public class CalculationContext {
	
	
	public void addLine() {
		
	}
	
	
	
	Map<Long, Cycle> openCycles=new HashMap<Long, Cycle>();
	
	public Cycle gocCycle(long cardId,LocalDate endDate) {
		
		Cycle r=openCycles.get(cardId);
		
		if(null==r)
		{
			r=new Cycle();
			
			r.setCardId(cardId);
			
			r.setEndDate(endDate);
			
			openCycles.put(cardId, r);
		}
			
		return r;
	}

	
	public Map<Long, Cycle> getOpenCycles() {
		return openCycles;
	}
	
	
	public void commit() {

	}


}
