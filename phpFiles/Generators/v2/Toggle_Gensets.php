<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	if (isset ($_POST['Name']) and isset($_POST['Status']) and isset($_POST['Voltage']) and isset($_POST['Current'])){
		//Operate the data further//
		$db = new DbOperation ();

		$result = $db->ManageGenerator($_POST['Name'], $_POST['Status'], $_POST['Voltage'], $_POST['Current']);
		if ($result == 1){
			$response ['error'] = false; 
			$response ['message'] = "Genset Started";
		} else if ($result == 0)
		{
			$response ['error'] = true; 
			$response ['message'] = "Genset is already ON";
		} else if ($result == 2)
		{
			$response ['error'] = false; 
			$response ['message'] = "Genset turned OFF";	
		} else {
		$response['error'] = true;
		$response['message'] = "Genset Failed to Start";
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
