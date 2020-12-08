package com.codev.accumilation.calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.codev.accumilation.load.TnuaFac;
import com.codev.accumilation.model.Account;
import com.codev.accumilation.model.Card;
import com.codev.accumilation.model.CardLevel;
import com.codev.accumilation.model.Cycle;
import com.codev.accumilation.model.Tnua;
import com.codev.accumilation.srvcs.Accounts;
import com.codev.accumilation.srvcs.Cards;
import com.codev.accumilation.srvcs.Rules;

public class Calculator {

	Cards cards;

	Accounts accounts;

	CalculationContext context;

	Rules rules;

	public void putTnua(Tnua tnua) {

//DB
		long accid = accounts.gocAcoountId(tnua.getBank().intValue(), tnua.getSnif().intValue(),
				tnua.getMch().intValue());

		Account acc = accounts.getAccountById(accid);

//DB		
		long cardId = cards.gocCardId(accid, tnua.getMisKartisAshrai().longValue());

		Card card = cards.getCardById(cardId);

		Cycle cycle = context.gocCycle(cardId);

		// tnua.getSchumChiyuv()

		cycle.addTnua(tnua);
	}

	boolean isTzover(Tnua tnua) {
		if (rules.kodAnafZover().contains(tnua.getKodAnaf()))

			return true;

		else
			return false;
	}

	public void start() {

	}

	public void end() {

		context.getOpenCycles().forEach((cardNum, cycle) -> {

			List<Tnua> tnuot = cycle.getTnuot();

			CardLevel cardLevel = cards.getCardById(cardNum).getCardLevel();

			BigDecimal accumTotalForNokud = new BigDecimal(0);

			BigDecimal accumTotal = new BigDecimal(0);

			List<Tnua> tnuotForNikud = new ArrayList();

//			List<Tnua> tnuotForNikudPositive = new ArrayList();
//
//			List<Tnua> tnuotForNikudBegative = new ArrayList();

			tnuot.forEach(tnua -> {

				if (isMezaka(tnua)) {

					if (isTashlumim(tnua))

					{

						accumTotal.add(tnua.getSchumChiyuv());
						if (isFirstTashlum(tnua)) {

							accumTotalForNokud.add(tnua.getSchumTnuaMekori());
							tnuotForNikud.add(tnua);

						}

					}

					// is not tashlumim
					else {
						accumTotal.add(tnua.getSchumChiyuv());

						accumTotalForNokud.add(tnua.getSchumChiyuv());
						tnuotForNikud.add(tnua);

					}
					// accumTotalForNokud.add(augend)
				} else {
					accumTotal.add(tnua.getSchumChiyuv());
				}

				// tnuot golmi
			});

			tnuotForNikud.forEach(tnuaForNikud -> {

				BigDecimal tnuaAmt = isTashlumim(tnuaForNikud) ? tnuaForNikud.getSchumTnuaMekori()
						: tnuaForNikud.getSchumChiyuv();

				BigDecimal pointsForTnua=tnuaAmt.divide(rules.getYahasHamaraForTnua(tnuaForNikud, cardLevel));
				
				//TODO store in some table
				
			});
			
			cycle.setTotal(accumTotal);
			
			//cycle.setNekudot(???)
			//card.le

			
		});

	}

	
	
	private boolean isFirstTashlum(Tnua tnua) {
		// TODO Auto-generated method stub
		return tnua.getMisTashlumNochechi().intValue() == 1;
	}

	private boolean isTashlumim(Tnua tnua) {
		// TODO Auto-generated method stub
		return tnua.getMisTashlumim().intValue() > 0;
	}

	boolean isMezaka(Tnua tnua) {

		return rules.kodAnafZover().contains(tnua.getKodAnaf());

	}

}
