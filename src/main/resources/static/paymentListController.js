App.controller('paymentListCtrl', ['$scope', '$http', '$ngBootbox', function paymentListController($scope, $http, $ngBootbox) {

	$scope.payments = [];

	$scope.listPayments = function() {

		$http.get("/payment/list").then(function success(response) {

			$scope.payments = response.data;

		}, function error(response) {

			alert(response.data);

		});


	}


	$scope.dialogDelete = function(id) {


		$ngBootbox.confirm('Delete ' + id + '?')
			.then(function() {
				$http.delete("/payment/delete/" + id).then(function() {
					$scope.listPayments();
				}, function(response) {
					alert(response.data);
				});
			},
				function() {
					//Confirm was cancelled, don't delete customer
					console.log('Confirm was cancelled');
					});


	}

	$scope.listPayments();

}])