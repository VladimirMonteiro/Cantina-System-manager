package com.outercode.Cantina.EB.utils;

import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;
import com.outercode.Cantina.EB.entities.Product;

public class InitProductConstants {

    public static final Product PRODUCT = new Product(1L, "Pastel", 4.50);
    private static final ResponseProductDTO RESPONSE_PRODUCT_DTO = new ResponseProductDTO(1L, "Pastel", 4.50);
}
