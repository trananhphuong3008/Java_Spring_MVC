package com.dht.pojo;

import com.dht.pojo.ReceiptDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-11T23:09:58")
@StaticMetamodel(Receipt.class)
public class Receipt_ { 

    public static volatile SingularAttribute<Receipt, String> note;
    public static volatile SingularAttribute<Receipt, Date> createdDate;
    public static volatile SingularAttribute<Receipt, Integer> id;
    public static volatile ListAttribute<Receipt, ReceiptDetail> receiptDetailList;

}