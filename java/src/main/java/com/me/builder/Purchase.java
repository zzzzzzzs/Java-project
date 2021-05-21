package com.me.builder;

public class Purchase {
    public final String shipNo;
    public final String menuId;
    public final String menuName;
    public final Double price;

    public static class Builder {
        private final String shipNo;
        private String menuId;
        private String menuName;
        private final Double price = 0.0;

        public Builder(String shipNo) {
            this.shipNo = shipNo;
        }

        public Builder menuId(String val) {
            menuId = val;
            return this;
        }

        public Builder menuName(String val) {
            menuName = val;
            return this;
        }

        public Purchase build() {
            return new Purchase(this);
        }
    }

    private Purchase(Builder builder) {
        shipNo = builder.shipNo;
        menuId = builder.menuId;
        menuName = builder.menuName;
        price = builder.price;
    }
}
