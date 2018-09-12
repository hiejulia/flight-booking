package com.project.flightbooking.hateoasconfig;


import it.valeriovaudi.emarket.endpoint.restfull.ShipmentRestFullEndPoint;
import it.valeriovaudi.emarket.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static it.valeriovaudi.emarket.hateoas.AbstractHateoasFactoryConstants.PURCHASE_ORDER_LINK_KEY;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@Component
public class ShipmentHateoasFactory {

    @Autowired
    private PurchaseOrderHateoasFactory purchaseOrderHateoasFactory;

    public Resource<Shipment> toResource(String orderNumber, Shipment shipment){
        Resource<Shipment> resource = new Resource<>(shipment);

        resource.add(getShipmentSelfLink(orderNumber));
        resource.add(purchaseOrderHateoasFactory.gertPurchaseOrderSelfLink(orderNumber).withRel(PURCHASE_ORDER_LINK_KEY));

        return resource;
    }

    public Link getShipmentSelfLink(String orderNumber){
        return linkTo(ControllerLinkBuilder.methodOn(ShipmentRestFullEndPoint.class)
                .getShipmentDataPuchaseOrder(orderNumber))
                .withSelfRel();
    }
}