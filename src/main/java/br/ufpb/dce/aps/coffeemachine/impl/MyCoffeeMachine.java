package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {
	private ComponentsFactory factory;	
	private int value;
	private Coin coin;
	private ArrayList<Coin> coins;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info("Insert coins and select a drink!");
		this.coins = new ArrayList<Coin>();
	}

	public void insertCoin(Coin dime) throws CoffeeMachineException {
		try {
			this.coins.add(dime);
			this.coin = dime;
			this.value += dime.getValue();	
			this.factory.getDisplay().info("Total: US$ " + this.value / 100 + "." + this.value % 100);
		}catch(NullPointerException e) {
			throw new CoffeeMachineException("Invalid coin: null");
		}
	}

	public void cancel() throws CoffeeMachineException {
		if(this.value == 0) 
			throw new CoffeeMachineException("No coin has been inserted");
		if(this.coins.size() != 0) {
			Coin.reverse();
			this.factory.getDisplay().warn(Messages.CANCEL_MESSAGE);
			for(Coin c : this.coins) {
				for(Coin cc : this.coins) {
					if(c != cc) 
						this.factory.getCashBox().release(cc);
					else
						this.factory.getCashBox().release(c);
				}
			}
		}		
		this.factory.getDisplay().info (Messages.INSERT_COINS_MESSAGE);		
	}
}













