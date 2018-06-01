'use strict';

angular.module('ShopApp.controllers')
    /**
     * ConfirmationController handles order confirmation view
     */
    .controller('ConfirmationController', function($scope, sharedService) {
        $scope.totalPrice = 0;
        $scope.customer = {};
        $scope.orderedProducts = []
        $scope.confirmation = {
            value: true
        };

        // Get ordered products
        $scope.getOrderedProducts = function() {
            $scope.orderedProducts = sharedService.getOrderedProducts();
            $scope.totalPrice = sharedService.getTotalPrice();
            $scope.customer = sharedService.getCustomer();
        }
    })
