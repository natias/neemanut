package com.codev.accumilation.srvcs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codev.accumilation.configs.RulesConfig;
import com.codev.accumilation.model.CardLevel;
import com.codev.accumilation.model.Tnua;

import lombok.Data;

@Service
public class Rules {

//	@Data
//	public static class YahasHamara{
//		
//		CardLevel cl;
//		String mcc;
//		BigDecimal yahasAhamara;
//		
//		}
//	

	@Autowired
	RulesConfig rulesConfig;

	List<Integer> kodAnaftzover = new ArrayList<Integer>();

	BigDecimal pointsLowerThreshold;

	BigDecimal pointsMaxThreshold;

	// BigDecimal

	BigDecimal mekadem;

	Map<CardLevel, Map<String, BigDecimal>> yahasiHamara;

	
	Map<Integer,String> kodeiAnafTomcc; 

	@PostConstruct
	void init() {

		yahasiHamara=new HashMap<CardLevel, Map<String,BigDecimal>>();
		
		rulesConfig.getYahasiHamara().forEach((cl, mcc_cr) -> {

			Map<String, BigDecimal> level_rates_per_mcc=new HashMap();
			yahasiHamara.put(CardLevel.valueOf(cl), level_rates_per_mcc);
			
			mcc_cr.forEach((mcc,cr)->{
				
				
				level_rates_per_mcc.put(mcc, new BigDecimal(cr));
				
			});
			
			
			
			
		});
		
		
		kodeiAnafTomcc=new HashMap();
		
		rulesConfig.getMccs().forEach((mcc,list_of_kodei_anaf) -> {
			
			
			
			list_of_kodei_anaf.forEach(ka -> {
				
				kodeiAnafTomcc.put(ka, mcc);
			});
			
			
			
			
		}); 
		
		
		this.mekadem=rulesConfig.getMekadem_klalii();
				
				
		System.out.println(this.kodeiAnafTomcc);
		System.out.println(this.yahasiHamara);
		System.out.println(this.mekadem);
		
		
		

	}

	public Rules() {
//		kodAnaftzover.add(1);
//
//		pointsLowerThreshold = new BigDecimal(4000);
//
//		pointsMaxThreshold = new BigDecimal(25000);
//
//		mekadem = new BigDecimal(0.95);

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

	public BigDecimal getYahasHamaraForTnua(Tnua tnua, CardLevel cl) {

		return getYahasHamaraBeforeMekadem(
				cl, 
				getMccFromTnua(tnua)).
				multiply(getMekadem());
	}

	private String  getMccFromTnua(Tnua tnua) {
		
		if(tnua.getKodMatbeaIsoMekori().intValue()!=376)
		
		return "tnuatChul";
		
//		if(tnua.getKodAnaf())
		
		
		String r = kodeiAnafTomcc.get(tnua.getKodAnaf());
		
		if(r==null)
			r="DEFAULT";
		return r;
	}

	private BigDecimal getYahasHamaraBeforeMekadem( CardLevel cl,String mcc) {
		
			Map<String, BigDecimal> cardLevel=yahasiHamara.get(cl);
			
			if(null==cardLevel)
				throw new IllegalStateException();
			
			
						
			BigDecimal r=cardLevel.get(mcc);
			
			if(r==null)
			{
				System.out.println(cl +" ");
			}
			return r;
	}

	public CardLevel levelFor(BigDecimal totalSchum) {

		return null;
	}

	public boolean isMezaka(Number kodAnaf) {

		// return kodAnafZover().contains(kodAnaf);
		return kodAnaf.intValue() > 200;
	}

}
