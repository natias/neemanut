package com.codev.accumilation.load;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.codev.accumilation.model.Tnua;

public class TnuaFac {

	static Tnua fromResultSet(ResultSet rs) throws SQLException {

		Tnua tnua = new Tnua();

		tnua.setNumerator(rs.getInt("NUMERATOR"));
		tnua.setBank(rs.getBigDecimal("BANK"));
		tnua.setSnif(rs.getBigDecimal("SNIF"));
		tnua.setMch(rs.getBigDecimal("MCH"));
		tnua.setMisKartisAshrai6(rs.getString("MIS_KARTIS_ASHRAI_6"));
		tnua.setKodChevratAshrai(rs.getBigDecimal("KOD_CHEVRAT_ASHRAI"));

		tnua.setKodAnaf(rs.getBigDecimal("KOD_ANAF"));

		tnua.setSchumChiyuv(rs.getBigDecimal("SCHUM_CHIYUV"));
		tnua.setMatbeaChiyuv(rs.getBigDecimal("MATBEA_CHIYUV"));

		tnua.setTrChiyuv(rs.getTimestamp("TR_CHIYUV").toLocalDateTime().toLocalDate());

		tnua.setMisTashlumNochechi(rs.getBigDecimal("MIS_TASHLUM_NOCHECHI"));

		tnua.setShemAnaf(rs.getString("SHEM_ANAF"));

		tnua.setKodMatbeaIsoMekori(rs.getBigDecimal("KOD_MATBEA_ISO_MEKORI"));

		tnua.setSchumAmlaTaarif(rs.getBigDecimal("SCHUM_AMLA_TAARIF"));
		tnua.setAchuzHanachaAmla(rs.getBigDecimal("ACHUZ_HANACHA_AMLA"));

		tnua.setSchumHanachaAmla(rs.getBigDecimal("SCHUM_HANACHA_AMLA"));

		tnua.setGkKartisMch(rs.getBigDecimal("GK_KARTIS_MCH"));
		tnua.setMisKartisAshrai(rs.getBigDecimal("MIS_KARTIS_ASHRAI"));

		tnua.setSchumTnuaMekori(rs.getBigDecimal("SCHUM_TNUA_MEKORI"));

		
		return tnua;
	}
}
