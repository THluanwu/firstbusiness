package com.neuedu.controller;
import com.neuedu.exception.MyException;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user/product/")
public class ProductController {

    @Autowired
    IProductService productService;


    @RequestMapping(value = "find")
    public String findall(HttpSession session) {
        List<Product> productList = productService.findall();
        List<Category> categoryList=productService.findId();
        session.setAttribute("productlist", productList);
        session.setAttribute("categoryList",categoryList);
        return "productlist";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer productId, HttpServletRequest request) {
        Product product = productService.findProductById(productId);
        request.setAttribute("product", product);
        return "productupdate";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(Product product, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        String newFileName="";
//        if (uploadFile!=null){
//            String uuid= UUID.randomUUID().toString();
//            String fileName=uploadFile.getOriginalFilename();
//            String fileextendname=fileName.substring(fileName.lastIndexOf("."));
//            newFileName=uuid+fileextendname;
//
//            File file=new File("E:\\bmpicture");
//            if (!file.exists()){
//                file.mkdir();
//            }
//            File newFile=new File(file,newFileName);
//            try {
//                uploadFile.transferTo(newFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        product.setSubImages(newFileName);

        int count = productService.updateProduct(product);
        if (count > 0) {
            return "redirect:/user/product/find";
        }
        return "productupdate";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer productId) {
        int count = productService.deleteProduct(productId);
        return "redirect:/user/product/find";
    }
    @RequestMapping(value = "insert",method = RequestMethod.GET)
    public String insert(){
        return "productinsert";
    }
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public String insert(HttpServletRequest request, HttpServletResponse response, Product product,
                         @RequestParam(value = "subimages")MultipartFile[] uploadFiles) throws UnsupportedEncodingException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String newFileName="";
        String mainImageName="";
        String finalFileName="";
        if (uploadFiles!=null) {
            for (MultipartFile uploadFile : uploadFiles) {
                String uuid = UUID.randomUUID().toString();
                String fileName = uploadFile.getOriginalFilename();
                String fileextendname = fileName.substring(fileName.lastIndexOf("."));
                newFileName = uuid + fileextendname;

                File file = new File("E:\\bmpicture");
                if (!file.exists()) {
                    file.mkdir();
                }
                File newFile = new File(file, newFileName);
                try {
                    uploadFile.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(mainImageName.equals("")){
                    mainImageName=newFileName;
                }
                finalFileName+=newFileName;

            }
        }

        product.setMainImage(mainImageName);
        product.setSubImages(finalFileName);
        int count=productService.addProduct(product);
        if (count>0){
            return "redirect:/user/product/find";
        }
        return "productinsert";

    }
}
