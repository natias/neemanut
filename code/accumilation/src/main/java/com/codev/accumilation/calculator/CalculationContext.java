package com.codev.accumilation.calculator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.codev.accumilation.model.Cycle;
import com.codev.accumilation.srvcs.Cycles;

public class CalculationContext {

	final Cycles cyclesService;

	public CalculationContext(Cycles cyclesServicexxx) {
		// TODO Auto-generated constructor stub
		this.cyclesService = cyclesServicexxx;

	}

	public void addLine() {

	}

	Map<Long, Long> openCycles = new HashMap<Long, Long>();

	public long gocCycle(long cardId, LocalDate endDate) {

		Long cid = openCycles.get(cardId);

		if (null == cid) {
			// r=new Cycle();
			
			 cid=cyclesService.createEmptyCycle(cardId, endDate);
			
			Cycle r = cyclesService.getCycleById(cid);
			 
			r=cyclesService.getCycleById(cid);

//			r.setCardId(cardId);
//
//			r.setEndDate(endDate);

			openCycles.put(cardId, cid);
		}

		return cid;
	}

	public Map<Long, Long> getOpenCycles() {
		return openCycles;
	}

	public void commit() {

	}

}
