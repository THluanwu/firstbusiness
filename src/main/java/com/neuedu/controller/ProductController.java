package com.neuedu.controller;

import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @RequestMapping("find")
    public String findall(HttpSession session){
        List<Product> productList=productService.findall();
        session.setAttribute("productlist",productList);
        return "productlist";
    }
}
