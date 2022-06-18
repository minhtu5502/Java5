var app = angular.module("myApp", ['ngRoute', 'ngAnimate']);
app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when("/exams", { templateUrl: "html/exams.html", controller: "indexCtrl" })
        .when("/about", { templateUrl: "html/about.html" })
        .when("/contact", { templateUrl: "html/contact.html" })
        .when("/feedback", { templateUrl: "html/feedback.html", controller: "feedbackCtrl" })
        .when("/FAQ", { templateUrl: "html/FAQ.html" })
        .when("/login", { templateUrl: "html/login.html", controller: "loginCtrl" })
        .when("/register", { templateUrl: "html/register.html", controller: "registerCtrl" })
        .when("/forgotpassword", { templateUrl: "html/forgotpassword.html", controller: "forgotpasswordCtrl" })
        .when("/updateaccount", { templateUrl: "html/updateaccount.html", controller: "updateaccountCtrl" })
        .when("/changepassword", { templateUrl: "html/changepassword.html", controller: "changepasswordCtrl" })
        .when("/viewtest/:id", { templateUrl: "html/viewtest.html", controller: "viewtestCtrl" })
        .when("/test/:id", { templateUrl: "html/test.html", controller: "testCtrl" })
        .when("/manageAccount", { templateUrl: "html/manageAccount.html", controller: "manageAccount"})
        .when("/manageSubject", { templateUrl: "html/mangageSubjects.html", controller: "manageSubject" })
        .when("/createQuetions/:id", { templateUrl: "html/createQuetions.html", controller: "createQuetions" })
        .otherwise({ redirectTo: "/exams" });
});