package com.outercode.Cantina.EB.utils;

import com.outercode.Cantina.EB.dto.product.CreateProductDTO;
import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;
import com.outercode.Cantina.EB.entities.Product;

public class InitProductConstants {

    public static final Product PRODUCT = new Product(1L, "Pastel", 4.50);
    private static final ResponseProductDTO RESPONSE_PRODUCT_DTO = new ResponseProductDTO(1L, "Pastel", 4.50);
    public static final CreateProductDTO CREATE_PRODUCT_DTO = new CreateProductDTO("Pastel", 4.50);
    public static final Product INVALID_PRODUCT = new Product(null, "", null);
    public static final CreateProductDTO INVALID_CREATE_PRODUCT_DTO = new CreateProductDTO("", null);
}
