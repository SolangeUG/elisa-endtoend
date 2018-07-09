'use strict';

angular.module('ShopApp.controllers')
    /**
     * OrdersController handles orders related operations
     */
    .controller('OrdersController', function($scope, $location, appService, sharedService) {
        $scope.order = {};
        $scope.customer = {};
        $scope.orderLines = [];
        $scope.totalPrice = 0;

        $scope.error = {
            value: false,
            message: ""
        };

        // Get selected products as orderlines
        $scope.getOrderLines = function() {
            var products = sharedService.getProducts();

            // in case tno product has previously been selected
            if (products.length === 0) {
                $scope.error.message = "No products selected!";
                $scope.error.value = true;
                return;
            }

            products.forEach(function(product) {
                var orderLine = {};

                orderLine.productId = product.id;
                orderLine.productName = product.name;
                orderLine.quantity = product.quantity;
                orderLine.unitPrice = product.price.recurringPrice;
                orderLine.price = product.price.recurringPrice * product.quantity;
                $scope.orderLines.push(orderLine);

                // publish each orderline
                var newOrderLine = {
                    productName: product.name,
                    quantity: product.quantity,
                    unitPrice: product.price.recurringPrice,
                    price: orderLine.price
                };
                sharedService.addOrderLine(newOrderLine);

                // update order total price
                $scope.totalPrice += product.price.recurringPrice * product.quantity;
            });

            // publish order total price
            sharedService.setTotalPrice($scope.totalPrice);
            // publish customer
            sharedService.setCustomer($scope.customer);
        };

        // Post customer order
        $scope.postOrder = function() {
            $scope.orderLines.forEach(function(orderLine) {
                // delete unitPrice and price properties from orderLines before creating an order
                delete orderLine.unitPrice;
                delete orderLine.price;
            });

            // prepare order object
            $scope.order = {
                customer: $scope.customer,
                orderLines: $scope.orderLines
            };

            // send order to backend
            appService.createOrder($scope.order)
                .then(
                    function() {
                        $location.path("/confirmation");
                    },
                    function(error) {
                        $scope.error.value = true;
                        $scope.error.message = error.data;
                        $scope.reset();
                    }
                );
        };

        // Reset/cleanup customer form and orderLines
        $scope.reset = function() {
            $scope.customer = {};
            $scope.orderLines = [];
            $scope.totalPrice = 0;
            $scope.error = {
                value: false,
                message: ""
            };
            sharedService.reset();
        };

    });