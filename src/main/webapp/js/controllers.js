/**
 * ShopApp controller definitions
 */
angular.module('ShopApp.controllers', [])

    /**
     * ProductsController handles products related operations
     */
    .controller('ProductsController', function($scope, appService, sharedService) {
        $scope.prices = [];
        $scope.products = [];

        $scope.selected = {
            counter: 0,
            value: false
        };
        $scope.error = {
            value: false,
            message: ""
        };

        /**
         * Load list of products
         */
        $scope.getProducts = function() {
            // load list of prices
            appService.getPrices()
                .then(
                    function(response) {
                        $scope.error.value = false;
                        $scope.prices = response.data;

                        // load list of products
                        appService.getProducts()
                            .then(
                                // products successfully loaded
                                function(response) {
                                    $scope.error.value = false;
                                    $scope.products = response.data;

                                    // associate products and prices
                                    $scope.products.forEach(function(product) {
                                        $scope.prices.forEach(function(price) {
                                            if (product.priceId === price.id) {
                                                product.price = price;
                                            }
                                        });
                                        product.selected = false;
                                        product.quantity = 1;
                                    });
                                },
                                // in case of an error
                                function(error) {
                                    $scope.error.value = true;
                                    $scope.error.message = error.data;
                                }
                            );
                    },
                    // in case of an error
                    function(error) {
                        $scope.error.value = true;
                        $scope.error.message = error.data;
                    }
                );
        };

        /**
         * When a product is selected/unselected from the list
         */
        $scope.updateSelection = function(product) {
            if (product.selected) {
                $scope.selected.counter += 1;
            } else {
                $scope.selected.counter -= 1;
            }

            if ($scope.selected.counter > 0) {
                $scope.selected.value = true;
            } else {
                $scope.selected.value = false;
            }
        };

        /**
         * Broadcast selected products for order
         */
         $scope.publishSelectedProducts = function() {
            $scope.products.forEach(function(product) {
                if (product.selected) {
                    sharedService.addProduct(product);
                }
            })
         }

    })

    /**
     * OrdersController handles orders related operations
     */
    .controller('OrdersController', function($scope, appService, sharedService) {
        $scope.order = {};
        $scope.customer = {};
        $scope.orderLines = [];
        $scope.totalPrice = 0;

        $scope.error = {
            value: false,
            message: ""
        };

        // return selected products as orderlines
        $scope.getOrderLines = function() {
            var products = sharedService.getProducts();

            if (products.length === 0) {
                $scope.error.message = "No products selected!"
                $scope.error.value = true;
                return;
            }

            products.forEach(function(product) {
                var orderLine = {};

                console.log(product.id)

                orderLine.productId = product.id;
                orderLine.productName = product.name;
                orderLine.quantity = product.quantity;
                orderLine.unitPrice = product.price.recurringPrice;
                orderLine.price = product.price.recurringPrice * product.quantity;
                $scope.orderLines.push(orderLine);

                // update order total price
                $scope.totalPrice += product.price.recurringPrice * product.quantity;
            });
        };

        // post customer order
        $scope.postOrder = function() {
            //TODO: delete price property from orderLines before creating an order

            $scope.reset();
        };

        // reset/cleanup customer form and orderLines
        $scope.reset = function() {
            $scope.customer = {};
            $scope.orderLines = [];
            $scope.totalPrice = 0;
            $scope.error = {
                value: false,
                message: ""
            };
        };

    });