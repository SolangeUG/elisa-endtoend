'use strict';

/**
 * AdminApp angular module definition and configuration
 */
angular.module('AdminApp', [])
    .controller('AdminController', function($scope, $http) {
        $scope.orders = [];
        $scope.service = "http://localhost:8080/api/orders";
        $scope.error = {
            value: false,
            message: ""
        };

        /**
         * Load all orders from backend
         */
        $scope.getOrders = function() {
            $http.get($scope.service)
                .then(
                    function(response) {
                        $scope.error.value = false;
                        $scope.orders = response.data;
                    },
                    function(error) {
                        $scope.error.value = true;
                        $scope.error.message = error.data;
                    }
                );
        };
    });
