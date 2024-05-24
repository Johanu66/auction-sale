import { ProductsService } from "../services/products-service.js";

export class ProductsView{
    static #displayProduct(product){
        let button_element = document.createElement("button");
        button_element.classList.add("button");
        button_element.innerHTML = "Enchérir";
        button_element.addEventListener("click", async ()=>{
            let updated_product = await ProductsService.bid(product.id)
            if(updated_product!=null){
                document.getElementById("price_product_"+product.id).innerHTML = `Prix : ${updated_product.bid} $`;
            }
        });

        let product_element = document.createElement("div");
        product_element.classList.add("product");
        product_element.innerHTML = `<div>Nom : ${product.name}</div>`+
        `<div>Propiétaire : ${product.owner}</div>`+
        `<div id="price_product_${product.id}">Prix : ${product.bid} $</div>`+
        `<div id="container_button_${product.id}"></div>`;

        document.querySelector(".products").appendChild(product_element);
        document.getElementById("container_button_"+product.id).appendChild(button_element)
    }

    static async displayProducts(){
        let products = await ProductsService.findAll();
        for(let product of products){
            this.#displayProduct(product);
        }
    }

    static updateBid(data){
        console.log(data);
        //document.getElementById("price_product_"+product.id).innerHTML = `Prix : ${updated_product.bid} $`;
    }
}