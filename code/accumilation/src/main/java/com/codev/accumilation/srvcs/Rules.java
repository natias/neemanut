package com.codev.accumilation.srvcs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.codev.accumilation.model.CardLevel;
import com.codev.accumilation.model.Tnua;

public class Rules {

	List<Integer> kodAnaftzover = new ArrayList<Integer>();

	BigDecimal pointsLowerThreshold;

	BigDecimal pointsMaxThreshold;

	
	//BigDecimal 
	
	
	BigDecimal mekadem;

	public void Rules() {
		kodAnaftzover.add(1);

		pointsLowerThreshold = new BigDecimal(4000);

		pointsMaxThreshold = new BigDecimal(25000);

		mekadem = new BigDecimal(0.95);

	}

	public List<Integer> kodAnafZover() {
		return kodAnaftzover;
	}

	public BigDecimal getPointsLowerThreshold() {
		return pointsLowerThreshold;
	}

	public BigDecimal getPointsMaxThreshold() {
		return pointsMaxThreshold;
	}

	private BigDecimal getMekadem() {
		return mekadem;
	}

	
	public BigDecimal getYahasHamaraForTnua(Tnua tnua,CardLevel cl) {

		return getYahasHamaraBeforeMekadem(tnua.getKodMatbeaIsoMekori().intValue()!=376,cl).multiply(getMekadem());
	}
	
	

	private BigDecimal getYahasHamaraBeforeMekadem(boolean isChul,CardLevel cl) {
		switch (cl) {
		case P0:
			
			return isChul? BigDecimal.valueOf(100):BigDecimal.valueOf(200);

		default:
			return BigDecimal.valueOf(1);
		// break;
		}
	}

	public CardLevel levelFor(BigDecimal totalSchum) {
		
		
		
		
		return null;
	}

}
