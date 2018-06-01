angular.module('ShopApp.controllers', [])

    .controller('ProductsController', function($scope, $http) {

        $scope.baseUrl = "http://localhost:8080/api";
        $scope.prices = [];
        $scope.products = [];

        /**
         * Load list of products
         */
        $scope.getProducts = function() {
            const pricesPath = productCtrl.baseUrl + "/prices";
            const productsPath = productCtrl.baseUrl + "/products";

            // load list of prices
            $http.get(pricesPath)
                .then(function(response) {
                    $scope.prices = response.data;

                    // load list of products
                    $http.get(productsPath)
                        .then(function(response) {
                            $scope.products = response.data;

                            //TODO : add price property to each product
                            $scope.products.forEach(function(product) {
                                $scope.prices.forEach(function(price) {
                                    if (product.priceId === price.id) {
                                        product.price = price;
                                    };
                                });
                                product.selected = false;
                                product.quantity = 0;
                            });
                        }
                    );
                }
            );
        };


        $scope.
  });