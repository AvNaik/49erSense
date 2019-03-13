<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	if (isset ($_POST['Email']) and isset($_POST['Floor']) and isset($_POST['Current']) and isset($_POST['Control'])) {

		$db = new DbOperation ();
		$result = $db->TemperatureUpdate($_POST['Email'], $_POST['Floor'], $_POST['Current'], $_POST['Control']);
		$response['error'] = false;
		$response['message'] = "Action Implemented";
 
	} else {
		$response['error'] = true;
		$response['message'] = "Requried Fields are missing";
		}
} else {
	$response ['error'] = true;
	$response ['message'] = "Invalid Request";
	}

echo json_encode ($response);


