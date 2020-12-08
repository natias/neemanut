package com.codev.accumilation.calculator;

import java.util.Map;

import com.codev.accumilation.model.Cycle;

public class CalculationContext {
	
	
	public void addLine() {
		
	}
	
	
	
	Map<Long, Cycle> openCycles;
	
	public Cycle gocCycle(long cardId) {
		
		Cycle r=openCycles.get(cardId);
		
		if(null==r)
		{
			r=new Cycle();
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
