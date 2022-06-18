var app = angular.module("myApp", ['ngRoute', 'ngAnimate']);
app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/exams", { templateUrl: "html/exams.html", controller: "indexCtrl" })
        .otherwise({ redirectTo: "/exams" });
});