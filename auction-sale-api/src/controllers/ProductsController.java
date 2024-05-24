package controllers;

import dao.ProductsDAO;
import models.Product;
import webserver.WebServerContext;

public class ProductsController {
    static public ProductsDAO productsDAO = new ProductsDAO();
    static public void findAll(WebServerContext context) {
        context.getResponse().json(productsDAO.findAll());
    }

    static public void bid(WebServerContext context) {
        int productId = Integer.parseInt(context.getRequest().getParam("productId"));
        try {
            Product product = productsDAO.bid(productId);
            context.getSSE().emit("bids", "Message from server");
            context.getResponse().json(product);
        } catch (Exception e) {
            context.getResponse().serverError("Error placing bid");
        }
    }
}
