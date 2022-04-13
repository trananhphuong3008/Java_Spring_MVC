/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.repository.implement;

import com.dht.pojo.Cart;
import com.dht.repository.ReceiptRepository;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.dht.pojo.Receipt;
import com.dht.pojo.ReceiptDetail;
import com.dht.repository.ProductRepository;
import com.dht.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private ProductRepository productRepository;
    //@Autowired
    //private UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<Integer, Cart> cart) {
        try {
            Session s = this.sessionFactory.getObject().getCurrentSession();
            Receipt r = new Receipt();
            
            //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //r.setUser(this.userRepository.getUserByUsername(authentication.getName()));
            s.save(r);

            if (cart != null) {
                for (Cart c : cart.values()) {
                    ReceiptDetail d = new ReceiptDetail();
                    d.setReceiptId(r);
                    d.setProductId(this.productRepository.getProductById(c.getId()));
                    d.setQuantity(c.getQuantity());
                    d.setPrice(c.getPrice());

                    s.save(d);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;

    }

}
