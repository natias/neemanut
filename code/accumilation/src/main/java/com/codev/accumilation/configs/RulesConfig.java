package com.codev.accumilation.configs;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.codev.accumilation.utils.YamlPropertySourceFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import lombok.Data;

@Component
@ConfigurationProperties
@PropertySource(ignoreResourceNotFound = false, value = "${zvr-rules}", factory = YamlPropertySourceFactory.class)
@Data
public class RulesConfig {

	Map<String, Map<String, String>> yahasiHamara;

	Map<String, List<Integer>> mccs;
	
	
	BigDecimal mekadem_klalii;

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		RulesConfig config = new RulesConfig();
		;

		config.yahasiHamara = new HashMap<String, Map<String, String>>();

		Map<String, String> cl1 = new HashMap();

		cl1.put("mcc_cat_1", "3");

		cl1.put("mcc_cat_2", "4");
		

		Map<String, String> cl2 = new HashMap();

		cl2.put("mcc_cat_1", "3");

		cl2.put("mcc_cat_2", "4");

		
		config.yahasiHamara.put("P0", cl1);
		config.yahasiHamara.put("P1", cl2);
		
		
	
		Map<String, List<Integer>> mccsx = new HashMap<String, List<Integer>>();
		
		
		
		mccsx.put("mcc_cat_1",Arrays.asList(1,2,3));
		
		
		mccsx.put("mcc_cat_2",Arrays.asList(4,5,6));
		
		
		
		config.setMccs(mccsx);
		
		
		
		config.mekadem_klalii=BigDecimal.ONE;
		
		
		
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		
		
		
		mapper.writeValue(System.out, config);

	}

}
