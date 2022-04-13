/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.implement;

import com.dht.pojo.Product;
import com.dht.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
@PropertySource("classpath:info.properties")
public class ProductRepositoryImpl implements ProductRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private Environment env;

    @Override
    public List<Product> getProducts(Map<String, String> params, int page) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root root = q.from(Product.class);
        q.select(root);
        
        List<Predicate> pre = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()){
            Predicate p = b.like(root.get("name").as(String.class),String.format("%%%s%%", kw));
            pre.add(p);
        }
        
        String cateId = params.get("categoryId");
        if(cateId != null && !cateId.isEmpty()){
            Predicate p2 = b.equal(root.get("categoryId"), Integer.parseInt(cateId));
            pre.add(p2);
        }
        
        if(pre.size() > 0)
            q.where(pre.toArray(new Predicate[] {}));
        q.orderBy(b.desc(root.get("id")));
        
        Query query = session.createQuery(q);
        
        
        int pageSize = Integer.parseInt(env.getProperty("info.page_size"));
        
        int start = (page - 1) * pageSize;
        
        query.setMaxResults(pageSize);
        query.setFirstResult(start);
        return query.getResultList();
    }

    @Override
    public int countProduct() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM Product");
        Object re = q.getSingleResult();
        
        return Integer.parseInt(re.toString());
    }

    @Override
    public boolean addOrUppdateProduct(Product p) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(p);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Product getProductById(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Product.class, id);
    }
    
}
