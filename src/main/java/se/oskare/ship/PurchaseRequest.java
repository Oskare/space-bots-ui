package se.oskare.ship;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PurchaseRequest {
    public ShipsToBuy shipsToBuy = new ShipsToBuy();

    public static class ShipsToBuy {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public Integer miner;
    }
}
