package com.codev.accumilation.load;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.codev.accumilation.model.Tnua;

@Service
public class Loader {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public void load(String sql) {

		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet resultSet) throws SQLException {

				while (resultSet.next()) {
					
					Tnua tnua=TnuaFac.fromResultSet(resultSet);
					
	                //kafkaTemplate.in
	                
	            }
			}

		});
	}

}
