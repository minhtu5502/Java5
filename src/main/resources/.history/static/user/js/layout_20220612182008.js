var app = angular.module("myApp", ['ngRoute', 'ngAnimate']);
app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/exams", { templateUrl: "html/exams.html"})
        .otherwise({ redirectTo: "/exams" });
});