package com.neuedu.service.impl;

import com.neuedu.exception.MyException;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    @Override
    public int addProduct(Product product) throws MyException {
        return 0;
    }

    @Override
    public int deleteProduct(int productId) throws MyException {
        return 0;
    }

    @Override
    public int updateProduct(Product product) throws MyException {
        return 0;
    }

    @Override
    public List<Product> findall() throws MyException {
        return null;
    }
}
