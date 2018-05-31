angular.module('productOrderingApp', [])

    .controller('ProductsController', function($http) {
        var productCtrl = this;

        productCtrl.baseUrl = "http://localhost:8080/api";
        productCtrl.prices = [];
        productCtrl.products = [];

        /**
         * Load list of products
         */
        productCtrl.getProducts = function() {
            const pricesPath = productCtrl.baseUrl + "/prices";
            const productsPath = productCtrl.baseUrl + "/products";

            // load list of prices
            $http.get(pricesPath)
                .then(function(response) {
                    productCtrl.prices = response.data;

                    // load list of products
                    $http.get(productsPath)
                        .then(function(response) {
                            productCtrl.products = response.data;

                            //TODO : add price property to each product
                            productCtrl.products.forEach(function(product) {
                                productCtrl.prices.forEach(function(price) {
                                    if (product.priceId === price.id) {
                                        product.price = price;
                                    };
                                });
                                product.selected = false;
                            });
                        }
                    );
                }
            );
        };
  });