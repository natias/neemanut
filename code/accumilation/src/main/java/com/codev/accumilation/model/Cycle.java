package com.codev.accumilation.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
 * מחזור חיוב
 */
public class Cycle {

	public class TnuaNikud {

		Tnua t;
		BigDecimal nekuda;
	}

	long id;

	List<Tnua> tnuot=new ArrayList<Tnua>();

	List<TnuaNikud> tnuotAndNikud = new ArrayList<Cycle.TnuaNikud>();

	BigDecimal totalSchum;

	public BigDecimal getTotalSchum() {
		return totalSchum;
	}

	
	BigDecimal nekudot;
	
	public void addTnua(Tnua tnua) {
		// TODO Auto-generated method stub

		tnuot.add(tnua);
	}

	public void addTnuaNikud(Tnua t, BigDecimal nekudot) {
		TnuaNikud tnuaNikud = new TnuaNikud();

		tnuaNikud.nekuda = nekudot;

		tnuaNikud.t = t;

		tnuotAndNikud.add(tnuaNikud);

	}

	public List<Tnua> getTnuot() {
		return tnuot;
	}

	public void setTotal(BigDecimal accumTotal) {
		totalSchum = accumTotal;

	}

	public void setNekudot(BigDecimal sumNekudot) {
		nekudot=sumNekudot;
		
	}
}
