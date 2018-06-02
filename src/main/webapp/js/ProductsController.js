'use strict';

angular.module('ShopApp.controllers')
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
            //(re)init shared "state"
            sharedService.reset();

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

    });