package com.codev.accumilation.srvcs;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codev.accumilation.model.Cycle;

@Service
public class Cycles {

	Long counter = 0l;

	Map<Long, Cycle> storage=new HashMap<Long, Cycle>();

	private synchronized long storeCycle(Cycle cycle)

	{
		long id=counter++;
		
		storage.put(id, cycle);

		return id;
		
	}
	
	
	public synchronized Cycle getCycleById(Long id)

	{
			return storage.get(id);
		
	}
	
	
	public long createEmptyCycle(long card_id,LocalDate endDate)
	{
		
		Cycle cycle=new Cycle();
		
		cycle.setCardId(card_id);
		
		cycle.setEndDate(endDate);
		
		return storeCycle(cycle);
		
		
		//throw new RuntimeException("not implemented");
	}
	
	
	
}
