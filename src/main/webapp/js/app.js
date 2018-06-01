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
            templateUrl: "partials/products.html",
            controller: "ProductsController"
        })
        .when('/order', {
            templateUrl: "partials/orders.html",
            controller: "OrdersController"
        })
        .otherwise({
            redirectTo: '/'
        });
    }
]);