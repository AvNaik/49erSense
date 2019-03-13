<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	if (isset($_POST['Email']) and isset($_POST['Password'])){

		$db = new DbOperation ();
	 
		if ($db->userLogin($_POST['Email'], $_POST['Password'])){
			$user = $db->getUser($_POST['Email']);
			$response['error'] = false;
			$response['Id'] = $user ['Id'];
			$response['Name']=$user['Name'];
			$response['Email'] = $user ['Email'];
			$response['Password'] = $user ['Password'];
			$response['Role'] = $user ['Role'];
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
