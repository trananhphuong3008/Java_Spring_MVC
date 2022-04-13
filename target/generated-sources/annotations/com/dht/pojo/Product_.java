package com.dht.pojo;

import com.dht.pojo.Category;
import com.dht.pojo.Comment;
import com.dht.pojo.ProdTag;
import com.dht.pojo.ReceiptDetail;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-11T23:09:58")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile ListAttribute<Product, Comment> commentList;
    public static volatile SingularAttribute<Product, String> images;
    public static volatile SingularAttribute<Product, Date> createdDate;
    public static volatile ListAttribute<Product, ProdTag> prodTagList;
    public static volatile SingularAttribute<Product, Long> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Boolean> active;
    public static volatile ListAttribute<Product, ReceiptDetail> receiptDetailList;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Category> categoryId;

}