package com.dht.pojo;

import com.dht.pojo.Product;
import com.dht.pojo.Receipt;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-11T23:09:58")
@StaticMetamodel(ReceiptDetail.class)
public class ReceiptDetail_ { 

    public static volatile SingularAttribute<ReceiptDetail, Integer> quantity;
    public static volatile SingularAttribute<ReceiptDetail, Product> productId;
    public static volatile SingularAttribute<ReceiptDetail, Long> price;
    public static volatile SingularAttribute<ReceiptDetail, Integer> id;
    public static volatile SingularAttribute<ReceiptDetail, Receipt> receiptId;

}