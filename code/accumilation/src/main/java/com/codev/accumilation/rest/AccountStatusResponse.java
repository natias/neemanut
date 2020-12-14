package com.codev.accumilation.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AccountStatusResponse {

	
	@Data
	public static class AccountResponseData {
		String BANK;
		String SNIF;
		String MCH;
		BigDecimal totalAccountPoints;
		Integer MXMPC;
		Integer MNMPC;
		
	
		@Data
		public static  class CardResponseData {
		
			String MIS_KARTIS_ASHRAI_X;
			String POTENTIONAL_POINTS_CYCLE;
			LocalDate END_CYCLE_DATE;
			String STATUS_NAME;
						
			
		}
		
		
		List<CardResponseData> cards=new ArrayList<AccountStatusResponse.AccountResponseData.CardResponseData>();
		
		
	}
	

	GenericHedaer genericHeader;

	AccountResponseData account;

}
