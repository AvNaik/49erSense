<?php

require_once '../includes/DbOperations.php';
$response = array ();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	if (isset($_POST['Email']) and isset($_POST['Location'])){

		$db = new DbOperation ();
	 
		if ($db->getLockStatus($_POST['Email'], $_POST['Location'])){
			$user = $db->getLockStatus($_POST['Email'], $_POST['Location']);
			$response['error'] = false;
			$response['Id'] = $user ['Id'];
			$response['Email'] = $user ['Email'];
			$response['Location'] = $user ['Location'];
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


