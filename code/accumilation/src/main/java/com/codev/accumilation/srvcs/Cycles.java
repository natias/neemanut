package com.codev.accumilation.srvcs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codev.accumilation.model.Cycle;

@Service
public class Cycles {

	Long counter = 0l;

	Map<Long, Cycle> storage=new HashMap<Long, Cycle>();

	public synchronized long storeCycle(Cycle cycle)

	{
		long id=counter++;
		
		storage.put(id, cycle);

		return id;
		
	}
	
	
	public synchronized Cycle getCycleById(Long id)

	{
			return storage.get(id);
		
	}
	
	
	
	
}
