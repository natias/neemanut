package com.codev.accumilation.rest;

import lombok.Data;

@Data
public class AccountStatusRequest {

	@Data
	public class AccountDetails {
		String BANK;
		String SNIF;
		String MCH;
	}

	GenericHedaer genericHeader;

	AccountDetails accountDetails;

}
