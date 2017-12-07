package com.sellfisher;

public class ProxySellFisher2 implements SellFisher {

    private SellFisher sell;
    
    public ProxySellFisher2(SellFisher sell) {
        this.sell = sell;
    }
    
	@Override
	public int sellFish() {
        System.out.println("the fish price higher");
        return sell.sellFish()+10;
	}

}
