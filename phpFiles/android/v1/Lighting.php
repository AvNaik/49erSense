<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	if (isset ($_POST['Email']) and isset($_POST['Floor']) and isset($_POST['Location']) and isset($_POST['Intensity'])) {

		$db = new DbOperation ();
		$result = $db->LightingUpdate($_POST['Email'], $_POST['Floor'], $_POST['Location'], $_POST['Intensity']);
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


