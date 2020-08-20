App.controller('paymentListCtrl', ['$scope', '$http', function paymentListController($scope, $http) {

	$scope.payments = [];

	$scope.listPayments = function() {

		$http.get("/payment/list").then(function success(response) {

			$scope.payments = response.data;

		}, function error(response) {

			alert(response.data);

		});


	}

	$scope.delete = function(id) {

		$http.delete("/payment/delete/" + id).then(function() {
			$scope.listPayments();
		});

	}

	$scope.listPayments();

}])