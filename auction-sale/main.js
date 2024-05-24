import { ProductsView } from "./views/products-view.js"
import { SSEClient } from "./lib/sse-client.js"

window.addEventListener("load", () => {
    run();
    const mySSEClient = new SSEClient("localhost:8080");
    mySSEClient.connect();
    mySSEClient.subscribe("bids", ProductsView.updateBid);
})

async function run(){
    ProductsView.displayProducts();
}