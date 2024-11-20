package com.ecom.proj.global;

import java.util.ArrayList;
import java.util.List;

import com.ecom.proj.model.Product;
import com.ecom.proj.model.Role;
import com.ecom.proj.model.User;

public class GlobalData {

    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
    public static List<Role> roles;
    static {
        roles = new ArrayList<Role>();
    }
    public static User user;
    static {
        user = new User();
    }
}
