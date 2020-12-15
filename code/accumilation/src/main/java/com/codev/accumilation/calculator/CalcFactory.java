package com.codev.accumilation.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.codev.accumilation.srvcs.Cycles;

@Service
public class CalcFactory {

	private @Autowired AutowireCapableBeanFactory beanFactory;

	private @Autowired Cycles cycles;
	
	public Calculator createCalculator() {

		Calculator calculator = new Calculator(new CalculationContext(cycles));

		beanFactory.autowireBean(calculator);

		return calculator;
	}

}
