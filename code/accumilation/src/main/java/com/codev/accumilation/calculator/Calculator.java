package com.codev.accumilation.calculator;

import java.lang.ref.Reference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.codev.accumilation.load.TnuaFac;
import com.codev.accumilation.model.Account;
import com.codev.accumilation.model.Card;
import com.codev.accumilation.model.CardLevel;
import com.codev.accumilation.model.Cycle;
import com.codev.accumilation.model.Tnua;
import com.codev.accumilation.srvcs.Accounts;
import com.codev.accumilation.srvcs.Cards;
import com.codev.accumilation.srvcs.Cycles;
import com.codev.accumilation.srvcs.Rules;

public class Calculator {

	@Autowired
	Cards cards;

	@Autowired
	Accounts accounts;

	@Autowired
	Cycles cycles;

	
	@Autowired
	Rules rules;

	final
	CalculationContext context; 

	
	public Calculator(CalculationContext contextXX) {
		// TODO Auto-generated constructor stub
		this.context=contextXX;
	}
	/*
	 * @PostConstruct void init() { context= new CalculationContext(cycles); }
	 */
	
	public void putTnua(Tnua tnua) {

//DB
//		long accid = accounts.gocAcoountId(tnua.getBank().intValue(), tnua.getSnif().intValue(),
//				tnua.getMch().intValue());
//
//		Account acc = accounts.getAccountById(accid);

//DB		
		long cardId = cards.gocCardId(tnua.getMisKartisAshrai().longValue());

		Card card = cards.getCardById(cardId);

		long cycle_id = 
				context.
				gocCycle(cardId,
						tnua.getTrChiyuv());

		// tnua.getSchumChiyuv()

		cycles.getCycleById(cycle_id).addTnua(tnua);
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

		context.getOpenCycles().forEach((cardNum, cid) -> {

			Cycle cycle=cycles.getCycleById(cid);
			
			List<Tnua> tnuot = cycle.getTnuot();

			CardLevel cardLevel = cards.getCardById(cardNum).getCardLevel();

			AtomicReference<BigDecimal> accumTotalForNokud = new AtomicReference(BigDecimal.ZERO);

			// Reference<BigDecimal> accumTotalForNokud=
			// BigDecimal accumTotalForNokud = new BigDecimal(0);

			AtomicReference<BigDecimal> accumTotal = new AtomicReference(BigDecimal.ZERO);
			// BigDecimal = new BigDecimal(0);

			List<Tnua> tnuotForNikud = new ArrayList();

			System.out.println("tnuot.size "+tnuot.size());

			
			tnuot.forEach(tnua -> {

				if (isMezaka(tnua)) {
					
					System.out.println("is mezaka");

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

			System.out.println("tnuotForNikud.size "+tnuotForNikud.size());
			
			AtomicReference<BigDecimal> sumNekudot = new AtomicReference(BigDecimal.ZERO);
			;

			tnuotForNikud.forEach(tnuaForNikud -> {

				BigDecimal tnuaAmt = isTashlumim(tnuaForNikud) ? tnuaForNikud.getSchumTnuaMekori()
						: tnuaForNikud.getSchumChiyuv();
				
//				System.out.println(tnuaAmt);

				BigDecimal pointsForTnua = tnuaAmt.divide(rules.getYahasHamaraForTnua(tnuaForNikud, cardLevel),2,RoundingMode.HALF_UP);

				cycle.addTnuaNikud(tnuaForNikud, pointsForTnua);

				sumNekudot.set(sumNekudot.get().add(pointsForTnua));

			});

			cycle.setTotal(accumTotal.get());

			
	//		System.out.println(sumNekudot.get());
			cycle.setNekudot(sumNekudot.get());
			
			
			
//			long cycid=cycles.storeCycle(cycle);
//			
//			
			long cardid=cycle.getCardId();
//			
			
			
			Card card=cards.getCardById(cardid);
			
			
			
			card.getDailyCycles().add(cid);
			
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

		
		return rules.isMezaka(tnua.getKodAnaf());

	}

}
