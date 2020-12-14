package com.codev.accumilation.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;


@Data
public class Account {
	
	public Account(Long id2) {

		id=id2;
	}

	
	String BANK;
	
	String SNIF;
	
	String MCH;
	
	
	final public long id;
	
	public Set<Long> cardids=new HashSet<Long>();

}
