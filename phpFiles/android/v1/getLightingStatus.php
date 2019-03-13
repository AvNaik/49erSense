<?php

require_once '../includes/DbOperations.php';
$response = array ();


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	if (isset($_POST['Email']) and isset($_POST['Floor']) and isset($_POST['Location'])){

		$db = new DbOperation ();
	 
		if ($db->getLightingStatus($_POST['Email'], $_POST['Floor'], $_POST['Location'])){
			$user = $db->getLightingStatus($_POST['Email'], $_POST['Floor'], $_POST['Location']);
			$response['error'] = false;
			$response['Id'] = $user ['Id'];
			$response['Email'] = $user ['Email'];
			$response['Floor'] = $user ["Floor"];
			$response['Location'] = $user ['Location'];
			$response['Intensity'] = $user ['Intensity'];
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


