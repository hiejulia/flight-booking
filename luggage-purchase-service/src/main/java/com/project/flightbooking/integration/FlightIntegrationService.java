package com.project.flightbooking.integration;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import it.valeriovaudi.emarket.anticorruptation.productcatalog.ProductCatalogAntiCorruptionLayerService;
import it.valeriovaudi.emarket.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by mrflick72 on 30/05/17.
 */

@Slf4j
@Service
public class ProductCatalogIntegrationService extends AbstractIntegrationService {

    @Autowired
    private ProductCatalogAntiCorruptionLayerService productCatalogAntiCorruptionLayerService;

    @Autowired
    private OAuth2RestTemplate productCatalogIntegrationServiceRestTemplate;

    @Value("${external-service.base-uri-schema.goods-in-product-catalog}")
    private String goodsInProductCatalogServiceUriSchema;

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public Goods getGoodsInPriceListData(String priceListId, String goodsId){
        URI uri = UriComponentsBuilder.fromHttpUrl(goodsInProductCatalogServiceUriSchema)
                .buildAndExpand(priceListId, goodsId).toUri();

        ResponseEntity<String> serviceCall =
                productCatalogIntegrationServiceRestTemplate.exchange(newRequestEntity(uri), String.class);

        return productCatalogAntiCorruptionLayerService.newGoods(serviceCall.getBody(),
                serviceCall.getHeaders().getContentType().toString());
    }

    /**
     * fallback
     * */
    public Goods fallback(String priceListId, String goodsId) {
        return null;
    }
}