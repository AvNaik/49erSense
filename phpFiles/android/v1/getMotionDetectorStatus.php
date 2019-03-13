<?php

require_once '../includes/DbOperations.php';
$response = array ();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	if (isset($_POST['Email']) and isset($_POST['Floor'])){

		$db = new DbOperation ();
	 
		if ($db->getMotionDetectorStatus($_POST['Email'], $_POST['Floor'])){
			$user = $db->getMotionDetectorStatus($_POST['Email'], $_POST['Floor']);
			$response['error'] = false;
			$response['Id'] = $user ['Id'];
			$response['Email'] = $user ['Email'];
			$response['Floor'] = $user ["Floor"];
			$response['Status'] = $user ['Status'];
		} else {
				$response['error'] = true;
				$response['message'] = "Invalid Credentials.";
		}		 
	} else {
		$response['error'] = true;
		$response['message'] = "Requried Fields are missing";
	}
}

echo json_encode($response);

