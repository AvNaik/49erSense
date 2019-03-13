<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	if (isset ($_POST['Email']) and isset($_POST['Location']) and isset($_POST['Status'])) {

		$db = new DbOperation ();
		$result = $db->Door_LocksUpdate($_POST['Email'], $_POST['Location'], $_POST['Status']);
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



