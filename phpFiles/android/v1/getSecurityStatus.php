<?php

require_once '../includes/DbOperations.php';
$response = array ();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	if (isset($_POST['Email'])){

		$db = new DbOperation ();
	 
		if ($db->getSecurityStatus($_POST['Email'])){
			$user = $db->getSecurityStatus($_POST['Email']);
			$response['error'] = false;
			$response['Id'] = $user ['Id'];
			$response['Email'] = $user ['Email'];
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

