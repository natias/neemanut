package com.codev.accumilation.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

@Service
public class CalcFactory {

	private @Autowired AutowireCapableBeanFactory beanFactory;

	public Calculator createCalculator() {

		Calculator calculator = new Calculator();

		beanFactory.autowireBean(calculator);

		return calculator;
	}

}
