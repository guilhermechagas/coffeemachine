package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

public class MyCoffeeMachine implements CoffeeMachine {
	private ComponentsFactory factory;	
	private int value;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		this.factory = factory;
		this.factory.getDisplay().info("Insert coins and select a drink!");
	}

	public void insertCoin(Coin dime) {
		this.value += dime.getValue();	
		this.factory.getDisplay().info("Total: US$ " + this.value / 100 + "." + this.value % 100);
	}
}
