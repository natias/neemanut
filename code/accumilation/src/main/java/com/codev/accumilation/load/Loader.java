package com.codev.accumilation.load;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.codev.accumilation.calculator.CalcFactory;
import com.codev.accumilation.calculator.Calculator;
import com.codev.accumilation.model.Account;
import com.codev.accumilation.model.Card;
import com.codev.accumilation.model.Tnua;
import com.codev.accumilation.srvcs.Accounts;
import com.codev.accumilation.srvcs.Cards;

@Service
public class Loader {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	CalcFactory calcFactory;

	@Autowired
	Accounts accounts;
	
	@Autowired
	Cards cards;

	public void loadKartisim(String partition) {

		String sql = "select distinct bank,snif,mch,MIS_KARTIS_ASHRAI,MIS_KARTIS_ASHRAI_6 " + "from STR_KARTIS where MIS_KARTIS_ASHRAI like '%"
				+ partition + "'";

		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet resultSet) throws SQLException {

				while (resultSet.next()) {

					Long acid=accounts.gocAcoountId(resultSet.getInt("bank"), resultSet.getInt("snif"), resultSet.getInt("mch"));
					
					Account account=accounts.getAccountById(acid);
					
					Long ccid=cards.gocCardId(resultSet.getLong("MIS_KARTIS_ASHRAI"));
					
					Card card=cards.getCardById(ccid);
					
					account.cardids.add(ccid);
					
					card.setAccId(acid);
					
					card.setMispar_ashrai_x_sfarot(resultSet.getString("MIS_KARTIS_ASHRAI_6"));
					

				}
			}

		});

	}

	public void loadTnuot(String partition) {

		String sql = "select * from STR_KARTIS where MIS_KARTIS_ASHRAI like '%" + partition + "'";

		Calculator calculator = calcFactory.createCalculator();

		calculator.start();

		jdbcTemplate.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet resultSet) throws SQLException {

				while (resultSet.next()) {
					
					System.out.println("line");

					Tnua tnua = TnuaFac.fromResultSet(resultSet);

					calculator.putTnua(tnua);

				}
			}

		});

		calculator.end();
	}


}
