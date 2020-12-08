package com.codev.accumilation.model;

import java.math.BigDecimal;
import java.util.List;

/*
 * מחזור חיוב
 */
public class Cycle {

	long id;


	List<Tnua> tnuot;

	
	BigDecimal totalSchum;
	
	public BigDecimal getTotalSchum() {
		return totalSchum;
	}
	public void addTnua(Tnua tnua) {
		// TODO Auto-generated method stub

		tnuot.add(tnua);
	}

	public List<Tnua> getTnuot() {
		return tnuot;
	}

	public void setTotal(BigDecimal accumTotal) {
		totalSchum=accumTotal;
		
	}
}
