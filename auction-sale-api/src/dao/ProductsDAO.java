package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.PolyBayDatabase;
import models.Product;

public class ProductsDAO {
    private PolyBayDatabase database;

    public ProductsDAO() {
        try {
            this.database = new PolyBayDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    public ArrayList<Product> findAll(){
        try {
            PreparedStatement myPreparedStatement = this.database.prepareStatement("SELECT * FROM product;");

            ResultSet results = myPreparedStatement.executeQuery();

            ArrayList<Product> products = new ArrayList<Product>();

            while(results.next())
            {
                final int id = results.getInt("id");
                final String name = results.getString("name");
                final String owner = results.getString("owner");
                final float bid = results.getFloat("bid");

                Product product = new Product(id, name, owner, bid);
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }

    public Product bid(int id) throws SQLException{
        PreparedStatement myPreparedStatement = this.database.prepareStatement("UPDATE product SET bid = bid + ? WHERE id = ?;");
        myPreparedStatement.setFloat(1, 50);
        myPreparedStatement.setInt(2, id);

        myPreparedStatement.executeUpdate();

        return findById(id);
    }

    public Product findById(int id) throws SQLException{
        PreparedStatement myPreparedStatement = this.database.prepareStatement("SELECT * FROM product WHERE id = ?;");
        myPreparedStatement.setInt(1, id);

        ResultSet results = myPreparedStatement.executeQuery();

        if(results.next())
        {
            final int productId = results.getInt("id");
            final String name = results.getString("name");
            final String owner = results.getString("owner");
            final float bid = results.getFloat("bid");

            return new Product(productId, name, owner, bid);
        }

        return null;
    }
}
