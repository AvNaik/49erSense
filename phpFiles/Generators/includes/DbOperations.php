<?php

	class DbOperation {
	
		private $con;
		
		function __construct () {
			 require_once dirname (__FILE__).'/DbConnect.php';

			$db = new DbConnect ();

			$this->con = $db->connect ();
		}
		/*CRUD -> C -> CREATE */
		
		public function ManageGenerator ($Name, $Status, $Voltage, $Current) {

			if ($this->GensetON ($Name, $Status)){
				return 0;
			} else if ($Status === "ON" || $Status === "OFF") {
				$stmt = $this->con->prepare("UPDATE `Manage_Generators` SET `Name`= ?,`Status`= ?,`Voltage`= ?,`Current`= ? WHERE `Name`='$Name'");
				$stmt->bind_param ("ssss", $Name, $Status, $Voltage, $Current);
				$stmt->execute ();
				if ($Status === "ON")
				{
					return 1;
				} else
				{
					return 2;
				}
						 
			}
		}

		private function GensetON ($Name, $Status) {

			if ($Status == "ON")
			{
			$stmt = $this->con->prepare("SELECT Id FROM `Manage_Generators` WHERE Name = ? AND Status = ?");	
			$stmt->bind_param("ss", $Name, $Status);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows > 0;
			}
		}

		public function GensetStatus ($Name) {
			if ($stmt = $this->con->prepare ("SELECT * from `Manage_Generators` WHERE Name = ?"))
			{
			$stmt->bind_param("s", $Name);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
			}
			}
	}