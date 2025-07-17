package com.outercode.Cantina.EB.utils;

import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.entities.enums.OrderStatus;

import java.util.Date;
import java.util.List;

import static com.outercode.Cantina.EB.utils.InitClientConstants.CLIENT;
import static com.outercode.Cantina.EB.utils.InitProductConstants.PRODUCT;

public class InitOrderConstants {

    public static final Order ORDER = new Order(1L, new Date(), 4.5,OrderStatus.PAID, CLIENT);
}
