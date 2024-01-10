/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author nasif
 */
@Stateful
public class CartBean implements CartBeanRemote {

    List<String> shoppingCart;

    public CartBean() {
        shoppingCart = new ArrayList<String>();
    }

    @Override
    public void addItem(String item) {
        shoppingCart.add(item);
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> placeOrder() {
        return shoppingCart;
    }

    @Override
    public void clearCart() {
        shoppingCart.clear();
    }

    @Remove
    public void remove() {
        shoppingCart = null;
    }

}
