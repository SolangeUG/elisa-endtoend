'use strict';

angular.module('ShopApp.services', [])
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