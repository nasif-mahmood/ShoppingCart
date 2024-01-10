/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author nasif
 */
@Remote
public interface CartBeanRemote {

    void addItem(String item);

    List<String> placeOrder();
    
    void remove();

    void clearCart();
}
