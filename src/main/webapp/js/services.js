/**
 * ShopApp service definition and configuration
 */
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
        }

        shopAPI.getProducts = function() {
            return $http({
                method: 'GET',
                url: baseUrl + "/products"
            });
        }

        shopAPI.getOrders = function() {
            return $http({
                method: 'GET',
                url: baseUrl + "/orders"
            });
        }

        shopAPI.createOrder = function() {
            return $http({
                method: 'POST',
                url: baseUrl + "/orders"
            });
        }

        return shopAPI;

    })

    /**
     * Service for shared data (selected products)
     * between the ProductsController and the OrdersController
     */
    .factory('sharedService', function() {
        var sharedData = {};
        var selectedProducts = [];

        sharedData.addProduct = function(product) {
            selectedProducts.push(product);
        }

        sharedData.getProducts = function() {
            return selectedProducts;
        }

        return sharedData;

    });