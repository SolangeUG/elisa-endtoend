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

    })

    /**
     * Service for shared data (selected products)
     * between the ProductsController and the OrdersController
     */
    .factory('sharedService', function() {
        var sharedData = {};

        var selectedProducts = [];
        var orderLines = [];
        var customer = {};
        var orderTotalPrice = 0;

        // selected products
        sharedData.addProduct = function(product) {
            selectedProducts.push(product);
        };

        sharedData.getProducts = function() {
            return selectedProducts;
        };

        // ordered products
        sharedData.addOrderLine = function(orderline) {
            orderLines.push(orderline);
        };

        sharedData.getOrderedProducts = function() {
            return orderLines;
        };

        // order total price
        sharedData.setTotalPrice = function(price) {
            orderTotalPrice = price;
        };

        sharedData.getTotalPrice = function() {
            return orderTotalPrice;
        };

        // customer
        sharedData.setCustomer = function(orderCustomer) {
            customer = orderCustomer;
        };

        sharedData.getCustomer = function() {
            return customer;
        };

        // reset
        sharedData.reset = function() {
            selectedProducts = [];
            orderLines = [];
            customer = {};
            orderTotalPrice = 0;
        };

        return sharedData;

    });