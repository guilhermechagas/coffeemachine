package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import java.util.Collections;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine {
	private ComponentsFactory factory;	
	private int value;
	private Coin coin;
	private ArrayList<Coin> coins;
	private Drink drink;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info(Messages.INSERT_COINS);
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
			Collections.reverse(this.coins);
			//Coin.reverse();
			this.factory.getDisplay().warn(Messages.CANCEL);
			for(Coin c : this.coins) 
				this.factory.getCashBox().release(c);
		}		
		this.factory.getDisplay().info(Messages.INSERT_COINS);		
	}

	public void select(Drink drink) {
		this.factory.getCupDispenser().contains(1);
		this.factory.getWaterDispenser().contains(2);		
		if(!this.factory.getCoffeePowderDispenser().contains(1)) {
			this.factory.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER);
			this.factory.getCashBox().release(Coin.quarter);
			this.factory.getCashBox().release(Coin.dime);
			this.factory.getDisplay().info(Messages.INSERT_COINS);
		}else{	
			if(drink == this.drink.BLACK_SUGAR) 
				this.factory.getSugarDispenser().contains(100);	
			this.factory.getDisplay().info(Messages.MIXING);
			this.factory.getCoffeePowderDispenser().release(100);
			this.factory.getWaterDispenser().release(3);
			if(drink == this.drink.BLACK_SUGAR) 
				this.factory.getSugarDispenser().release(100);	
			this.factory.getDisplay().info(Messages.RELEASING);
			this.factory.getCupDispenser().release(1);
			this.factory.getDrinkDispenser().release(1);
			this.factory.getDisplay().info(Messages.TAKE_DRINK);
			this.factory.getDisplay().info(Messages.INSERT_COINS);
			this.coins.clear();
		}
	}
}













