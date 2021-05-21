package com.me.builder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LombokPurchase {
    public String shipNo;
    public String menuId;
    public String menuName;
    public Double price;
}
