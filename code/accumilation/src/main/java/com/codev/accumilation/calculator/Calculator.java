package com.codev.accumilation.calculator;

import java.lang.ref.Reference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	Cards cards;

	@Autowired
	Accounts accounts;

	@Autowired
	Rules rules;

	CalculationContext context = new CalculationContext();

	public void putTnua(Tnua tnua) {

//DB
//		long accid = accounts.gocAcoountId(tnua.getBank().intValue(), tnua.getSnif().intValue(),
//				tnua.getMch().intValue());
//
//		Account acc = accounts.getAccountById(accid);

//DB		
		long cardId = cards.gocCardId( tnua.getMisKartisAshrai().longValue());

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

			AtomicReference<BigDecimal> accumTotalForNokud = new AtomicReference(BigDecimal.ZERO);

			// Reference<BigDecimal> accumTotalForNokud=
			// BigDecimal accumTotalForNokud = new BigDecimal(0);

			AtomicReference<BigDecimal> accumTotal = new AtomicReference(BigDecimal.ZERO);
			// BigDecimal = new BigDecimal(0);

			List<Tnua> tnuotForNikud = new ArrayList();

			tnuot.forEach(tnua -> {

				if (isMezaka(tnua)) {

					if (isTashlumim(tnua))

					{

						accumTotal.set(accumTotal.get().add(tnua.getSchumChiyuv()));

						if (isFirstTashlum(tnua)) {

							accumTotalForNokud.set(accumTotalForNokud.get().add(tnua.getSchumTnuaMekori()));
							tnuotForNikud.add(tnua);

						}

					}

					else {
						accumTotal.set(accumTotal.get().add(tnua.getSchumChiyuv()));

						accumTotalForNokud.set(accumTotalForNokud.get().add(tnua.getSchumTnuaMekori()));
						tnuotForNikud.add(tnua);

					}


				} else {
					accumTotal.set(accumTotal.get().add(tnua.getSchumChiyuv()));
				}

			});

			AtomicReference<BigDecimal> sumNekudot = new AtomicReference(BigDecimal.ZERO);;

			tnuotForNikud.forEach(tnuaForNikud -> {

				BigDecimal tnuaAmt = isTashlumim(tnuaForNikud) ? tnuaForNikud.getSchumTnuaMekori()
						: tnuaForNikud.getSchumChiyuv();

				BigDecimal pointsForTnua = tnuaAmt.divide(rules.getYahasHamaraForTnua(tnuaForNikud, cardLevel));

				cycle.addTnuaNikud(tnuaForNikud, pointsForTnua);

				sumNekudot.set( sumNekudot.get().add(pointsForTnua));

			});

			cycle.setTotal(accumTotal.get());

			cycle.setNekudot(sumNekudot.get());
			// card.le

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
