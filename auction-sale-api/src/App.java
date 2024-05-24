import controllers.ProductsController;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer();
        webServer.listen(8080);
        webServer.getRouter().get(
            "/products",
            (WebServerContext context) -> { ProductsController.findAll(context); }
        );

        webServer.getRouter().post(
            "/bid/:productId",
            (WebServerContext context) -> { ProductsController.bid(context); }
        );
    }
}
