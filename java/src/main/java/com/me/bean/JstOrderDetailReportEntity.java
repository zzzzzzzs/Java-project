package com.me.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zs
 * @date 2021/9/9.
 * 数据是从excel读取来的
 */
@Data
public class JstOrderDetailReportEntity {
    @ExcelProperty("内部订单号")
    private String internalOrderNumber;
    @ExcelProperty("标记多标签")
    private String tagMultipleLabels;
    @ExcelProperty("售后单号")
    private String afterSalesOrderNo;
    @ExcelProperty("订单类型")
    private String orderType;
    @ExcelProperty("线上订单号")
    private String onlineOrderNo;
    @ExcelProperty("订单状态")
    private String orderStatus;
    @ExcelProperty("发货仓")
    private String deliveryWarehouse;
    @ExcelProperty("分销商")
    private String distributor;
    @ExcelProperty("店铺")
    private String shop;
    @ExcelProperty("买家账号")
    private String buyerAccount;
    @ExcelProperty("买家留言")
    private String buyerMessage;
    @ExcelProperty("卖家备注")
    private String sellerComments;
    @ExcelProperty("订单日期")
    private String orderDate;
    @ExcelProperty("发货日期")
    private String theDateOfIssuance;
    @ExcelProperty("付款日期")
    private String paymentDate;
    @ExcelProperty("确认收货日期")
    private String confirmReceiptDate;
    @ExcelProperty("供销支付时间")
    private String supplyAndMarketingPaymentTime;
    @ExcelProperty("业务员")
    private String salesman;
    @ExcelProperty("收件人姓名")
    private String recipientName;
    @ExcelProperty("省")
    private String province;
    @ExcelProperty("市")
    private String city;
    @ExcelProperty("区县")
    private String districtAndCounty;
    @ExcelProperty("快递公司")
    private String courierServicesCompany;
    @ExcelProperty("快递单号")
    private String courierNumber;
    @ExcelProperty("售后登记日期")
    private String afterSalesRegistrationDate;
    @ExcelProperty("售后确认日期")
    private String afterSalesConfirmationDate;
    @ExcelProperty("售后分类")
    private String afterSalesClassification;
    @ExcelProperty("售后进仓日期")
    private String afterSalesWarehousingDate;
    @ExcelProperty("问题类型")
    private String problemType;
    @ExcelProperty("售后进仓单号")
    private String afterSalesWarehouseEntryNo;
    @ExcelProperty("商品编码")
    private String commodityCode;
    @ExcelProperty("款式编码")
    private String styleCode;
    @ExcelProperty("原始线上订单号")
    private String originalOnlineOrderNumber;
    @ExcelProperty("虚拟分类")
    private String virtualClassification;
    @ExcelProperty("产品分类")
    private String productClassification;
    @ExcelProperty("品牌")
    private String brand;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("颜色规格")
    private String colorSpecification;
    @ExcelProperty("线上颜色规格")
    private String onlineColorSpecification;
    @ExcelProperty("供应商")
    private String supplier;
    @ExcelProperty("供应商款号")
    private String supplierItemNo;
    @ExcelProperty("供应商商品编码")
    private String supplierCommodityCode;
    @ExcelProperty("店铺商品编码")
    private String storeCommodityCode;
    @ExcelProperty("售价")
    private String price;
    @ExcelProperty("销售数量")
    private String salesVolumes;
    @ExcelProperty("赠品数量")
    private String numberOfGifts;
    @ExcelProperty("价格为零的商品数量")
    private String quantityOfGoodsWithZeroPrice;
    @ExcelProperty("实发数量")
    private String issuedQuantity;
    @ExcelProperty("实发金额")
    private String paidInAmount;
    @ExcelProperty("销售金额")
    private String salesAmount;
    @ExcelProperty("已付金额")
    private String amountPaid;
    @ExcelProperty("应付金额")
    private String amountPayable;
    @ExcelProperty("基本售价")
    private String basicSellingPrice;
    @ExcelProperty("退货数量")
    private String returnQuantity;
    @ExcelProperty("实退数量")
    private String actualReturnedQuantity;
    @ExcelProperty("退货金额")
    private String returnAmount;
    @ExcelProperty("运费收入")
    private String freightIncome;
    @ExcelProperty("运费收入分摊")
    private String freightIncomeAllocation;
    @ExcelProperty("运费支出")
    private String freightExpenses;
    @ExcelProperty("实退金额")
    private String actualRefundedAmount;
    @ExcelProperty("运费支出分摊")
    private String freightExpenseAllocation;
    @ExcelProperty("优惠金额")
    private String preferentialAmount;
    @ExcelProperty("订单重量")
    private String orderWeight;
    @ExcelProperty("商店站点")
    private String storeSite;
    @ExcelProperty("订单来源")
    private String orderSource;
    @ExcelProperty("便签")
    private String note;
    @ExcelProperty("其它价格1")
    private String otherPrice1;
    @ExcelProperty("其它价格2")
    private String otherPrices2;
    @ExcelProperty("其它价格3")
    private String otherPrices3;
    @ExcelProperty("其它价格4")
    private String otherPrices4;
    @ExcelProperty("其它价格5")
    private String otherPrice5;
    @ExcelProperty("其它属性1")
    private String otherAttributes1;
    @ExcelProperty("其它属性2")
    private String otherAttributes2;
    @ExcelProperty("其它属性3")
    private String otherAttributes3;
    @ExcelProperty("其它属性4")
    private String otherAttributes4;
    @ExcelProperty("其它属性5")
    private String otherAttributes5;
    @ExcelProperty("店铺编号")
    private String shopNumber;
    @ExcelProperty("线上商品名")
    private String onlineTradeName;
    @ExcelProperty("组合装商品编码")
    private String combinedCommodityCode;
    @ExcelProperty("预计发货日期")
    private String estimatedDeliveryDate;
    @ExcelProperty("市场吊牌价")
    private String marketTagPrice;
    @ExcelProperty("国标码")
    private String nationalStandardCode;
    @ExcelProperty("市场吊牌金额")
    private String marketTagAmount;
    @ExcelProperty("订单支付时间")
    private String orderPaymentTime;
}
