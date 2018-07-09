'use strict';

angular.module('ShopApp.services', [])
    /**
     * Service for our backend API
     */
    .factory('appService', function($http) {
        var baseUrl = "http://localhost:8080/api";
        var shopAPI = {};

        shopAPI.getPrices = function() {
            return $http({
                method: 'GET',
                url: baseUrl + "/prices"
            });
        };

        shopAPI.getProducts = function() {
            return $http({
                method: 'GET',
                url: baseUrl + "/products"
            });
        };

        shopAPI.getOrders = function() {
            return $http({
                method: 'GET',
                url: baseUrl + "/orders"
            });
        };

        shopAPI.createOrder = function(newOrder) {
            return $http({
                method: 'POST',
                url: baseUrl + "/orders",
                data: angular.toJson(newOrder),
                headers: {'Content-Type': 'application/json'}
            });
        };

        return shopAPI;

    });