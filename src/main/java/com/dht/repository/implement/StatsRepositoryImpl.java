 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.implement;

import com.dht.pojo.Category;
import com.dht.pojo.Product;
import com.dht.pojo.Receipt;
import com.dht.pojo.ReceiptDetail;
import com.dht.repository.StatsRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> countCates() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rootP = q.from(Product.class);
        Root rootC = q.from(Category.class);
        q.where(b.equal(rootP.get("categoryId"), rootC.get("id")));
        q.multiselect(rootC.get("id"), rootC.get("name"),
                b.count(rootP.get("id")));
        q.groupBy(rootC.get("id"));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> productRevenue(String kw, Date fromDate, Date toDate) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root rootP = q.from(Product.class);
        Root rootR = q.from(Receipt.class);
        Root rootD = q.from(ReceiptDetail.class);

        List<Predicate> pre = new ArrayList<>();
        pre.add(b.equal(rootD.get("productId"), rootP.get("id")));
        pre.add(b.equal(rootD.get("receiptId"), rootR.get("id")));

        if (kw != null && kw.isEmpty()) {
            pre.add(b.like(rootP.get("name"), String.format("%%%s%%", kw)));
        }
        if (fromDate != null) {
            pre.add(b.greaterThanOrEqualTo(rootR.get("createdDate"), fromDate));
        }
        if (toDate != null) {
            pre.add(b.lessThanOrEqualTo(rootR.get("createdDate"), toDate));
        }
        
        q.multiselect(rootP.get("id"), rootP.get("name"), b.sum(b.prod(rootD.get("price"), rootD.get("quantity"))));
        
        q.where(pre.toArray(new Predicate[] {}));
        
        q.groupBy(rootP.get("id"));
        
        Query query = session.createQuery(q);
        return query.getResultList();
    }

}
