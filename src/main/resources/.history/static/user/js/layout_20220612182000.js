var app = angular.module("myApp", ['ngRoute', 'ngAnimate']);
app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/exams", { templateUrl: "html/exams.html", controller: "indexCtrl" })
        .when("/about", { templateUrl: "html/about.html" })
        .when("/contact", { templateUrl: "html/contact.html" })
        .when("/feedback", { templateUrl: "html/feedback.html", controller: "feedbackCtrl" })
        .when("/FAQ", { templateUrl: "html/FAQ.html" })
        .when("/login", { templateUrl: "html/login.html", controller: "loginCtrl" })
        .otherwise({ redirectTo: "/exams" });
});