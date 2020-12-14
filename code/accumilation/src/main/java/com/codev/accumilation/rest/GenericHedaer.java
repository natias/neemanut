package com.codev.accumilation.rest;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data

public class GenericHedaer {
	
	String clientId;
	LocalDateTime timeStamp;
	String requestId;

}
