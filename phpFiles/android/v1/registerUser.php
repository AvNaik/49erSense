<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	if (isset ($_POST['Name']) and isset($_POST['Email']) and isset ($_POST['Password']) and isset ($_POST['Role'])){
		//Operate the data further//
		$db = new DbOperation ();

		$result = $db->createUser($_POST['Name'], $_POST['Email'], $_POST['Password'], $_POST['Role']);
		if ($result == 1){
			$response ['error'] = false; 
			$response ['message'] = "User Registered";
		} elseif ($result == 2){
			$response ['error'] = true; 
			$response ['message'] = "Some Error Occured. Please try again.";
		} elseif ($result == 0){
			$response ['error'] = true; 
			$response ['message'] = "User Already Exists.";
		}

		
	} else {
		$response['error'] = true;
		$response['message'] = "Requried Fields are missing";
		} 
	
} else {
	$response ['error'] = true;
	$response ['message'] = "Invalid Request";
	}

echo json_encode ($response);
 
