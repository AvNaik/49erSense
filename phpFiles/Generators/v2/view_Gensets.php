<?php

require_once '../includes/DbOperations.php';
$response = array ();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	if (isset($_POST['Name'])){

		$db = new DbOperation ();
	 
		if ($db->GensetStatus($_POST['Name'])){
			$user = $db->GensetStatus($_POST['Name']);
			$response['error'] = false;
			$response['Id'] = $user ['Id'];
			$response['Name']=$user['Name'];
			$response['Status'] = $user ['Status'];
			$response['Voltage'] = $user ['Voltage'];
			$response['Current'] = $user ['Current'];
		} 		
	} else {
		$response['error'] = true;
		$response['message'] = "Requried Fields are missing";
	}
}

echo json_encode($response);

