'use strict';

/**
 * ShopApp angular module definition and configuration
 */
angular.module('ShopApp', [
    'ShopApp.controllers',
    'ShopApp.services',
    'ngRoute'
])
.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: "components/products/products.html",
            controller: "ProductsController"
        })
        .when('/order', {
            templateUrl: "components/orders/orders.html",
            controller: "OrdersController"
        })
        .when('/confirmation', {
            templateUrl: "components/orders/confirmation.html",
            controller: "ConfirmationController"
        })
        .otherwise({
            redirectTo: '/'
        });
    }
]);