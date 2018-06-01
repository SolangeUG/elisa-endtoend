'use strict';

angular.module('ShopApp.controllers')
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