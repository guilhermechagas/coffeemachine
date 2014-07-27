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
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin dime) throws CoffeeMachineException {
		try {
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
		
		this.factory.getDisplay().warn(Messages.CANCEL_MESSAGE);
		this.factory.getCashBox().release(coin);
		this.factory.getDisplay().info (Messages.INSERT_COINS_MESSAGE);		
	}
}













