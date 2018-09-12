package com.project.flightbooking.service;


import it.valeriovaudi.emarket.model.*;

import java.util.List;


public interface LuggageOrderService {

    PurchaseOrder findPurchaseOrder(String userName, String orderNumber);
    List<PurchaseOrder> findPurchaseOrderList(boolean withOnlyOrderId);
    List<PurchaseOrder> findPurchaseOrderList(String userName, boolean withOnlyOrderId);

    PurchaseOrder createPurchaseOrder();
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);
    void deletePurchaseOrder(String orderNumber);
    PurchaseOrder changeStatus(String orderNumber, PurchaseOrderStatusEnum purchaseOrderStatusEnum);

    PurchaseOrder withCustomer(String orderNumber, String userName, Customer customer);
    PurchaseOrder withCustomerContact(String orderNumber, String userName, CustomerContact customerContact);

    PurchaseOrder withCustomerAndCustomerContact(String orderNumber, String userName, Customer customer, CustomerContact customerContact);

    PurchaseOrder saveGoodsInPurchaseOrder(String orderNumber, String priceListId, String goodsId, int quantity);
    PurchaseOrder removeGoodsInPurchaseOrder(String orderNumber, String priceListId, String goodsId);

    PurchaseOrder withShipment(String orderNumber, Shipment shipment);
    PurchaseOrder withDelivery(String orderNumber, Delivery delivery);
}