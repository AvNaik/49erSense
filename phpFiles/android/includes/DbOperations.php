<?php

	class DbOperation {
	
		private $con;
		
		function __construct () {
			 require_once dirname (__FILE__).'/DbConnect.php';

			$db = new DbConnect ();

			$this->con = $db->connect ();
		}
		/*CRUD -> C -> CREATE */
		
		public function createUser ($Name, $Email, $Pass, $Role) {
			if ($this->isUserExist($Email)){
				return 0;
			} else {
			$Password = md5($Pass);
			$stmt = $this->con->prepare("INSERT INTO `Login_Data` (`Id`, `Name`, `Email`, `Password`, `Role`) VALUES (NULL, ?, ?, ?, ?);");
			$stmt->bind_param ("ssss", $Name, $Email, $Password, $Role);
			if ($stmt->execute ()){
				$Floor = "Main Floor";
				$Mode = "OFF";
				$Fan =  "OFF";
				$Location = "LIVING ROOM";
				$Intensity = "OFF";
				$Current = "OFF";
				$Control = "OFF";
				$Thermostat = $this->con->prepare("INSERT INTO `Thermostat` (`Id`, `Email`, `Floor`, `Mode`, `Fan`) VALUES (NULL, ?, ?, ?, ?);");
				$Thermostat->bind_param("ssss",$Email,$Floor,$Mode,$Fan);
                $Thermostat->execute();

                $Temperature = $this->con->prepare("INSERT INTO `Temperature` (`Id`, `Email`, `Floor`, `Current`, `Control`) VALUES (NULL, ?, ?, ?, ?);");
				$Temperature->bind_param("ssss",$Email,$Floor,$Current,$Control);
                $Temperature->execute(); 


                $Lighting = $this->con->prepare("INSERT INTO `Lighting` (`Id`, `Email`, `Floor`, `Location`, `Intensity`) VALUES (NULL, ?, ?, ?, ?);");
				$Lighting->bind_param("ssss",$Email,$Floor,$Location,$Intensity);
                $Lighting->execute();

                $Location = "KITCHEN";
                $Lighting = $this->con->prepare("INSERT INTO `Lighting` (`Id`, `Email`, `Floor`, `Location`, `Intensity`) VALUES (NULL, ?, ?, ?, ?);");
				$Lighting->bind_param("ssss",$Email,$Floor,$Location,$Intensity);
                $Lighting->execute();

                $Location = "BATHROOM";
                $Lighting = $this->con->prepare("INSERT INTO `Lighting` (`Id`, `Email`, `Floor`, `Location`, `Intensity`) VALUES (NULL, ?, ?, ?, ?);");
				$Lighting->bind_param("ssss",$Email,$Floor,$Location,$Intensity);
                $Lighting->execute();


                $Floor = "Upstairs";
                $Location = "BEDROOM";
                $Intensity = "OFF";
                $Thermostat = $this->con->prepare("INSERT INTO `Thermostat` (`Id`, `Email`, `Floor`, `Mode`, `Fan`) VALUES (NULL, ?, ?, ?, ?);");
				$Thermostat->bind_param("ssss",$Email,$Floor,$Mode,$Fan);
                $Thermostat->execute();

                $Temperature = $this->con->prepare("INSERT INTO `Temperature` (`Id`, `Email`, `Floor`, `Current`, `Control`) VALUES (NULL, ?, ?, ?, ?);");
				$Temperature->bind_param("ssss",$Email,$Floor,$Current,$Control);
                $Temperature->execute(); 


                $Lighting = $this->con->prepare("INSERT INTO `Lighting` (`Id`, `Email`, `Floor`, `Location`, `Intensity`) VALUES (NULL, ?, ?, ?, ?);");
				$Lighting->bind_param("ssss",$Email,$Floor,$Location,$Intensity);
                $Lighting->execute();

                $Location = "BATHROOM";
                $Lighting = $this->con->prepare("INSERT INTO `Lighting` (`Id`, `Email`, `Floor`, `Location`, `Intensity`) VALUES (NULL, ?, ?, ?, ?);");
				$Lighting->bind_param("ssss",$Email,$Floor,$Location,$Intensity);
                $Lighting->execute();

                $Status = "DISARMED";
                $Security_System = $this->con->prepare("INSERT INTO `Security_System` (`Id`, `Email`, `Status`) VALUES (NULL, ?, ?);");
				$Security_System->bind_param("ss",$Email,$Status);
                $Security_System->execute();

                $Location = "FRONT DOOR";
                $Status = "UNLOCKED";
                $Door_Locks = $this->con->prepare("INSERT INTO `Door_Locks` (`Id`, `Email`, `Location`, `Status`) VALUES (NULL, ?, ?, ?);");
				$Door_Locks->bind_param("sss",$Email, $Location, $Status);
                $Door_Locks->execute();

                $Location = "REAR DOOR";
                $Status = "UNLOCKED";
                $Door_Locks = $this->con->prepare("INSERT INTO `Door_Locks` (`Id`, `Email`, `Location`, `Status`) VALUES (NULL, ?, ?, ?);");
				$Door_Locks->bind_param("sss",$Email, $Location, $Status);
                $Door_Locks->execute();

                $Location = "1 Car Garage DOOR";
                $Status = "UNLOCKED";
                $Door_Locks = $this->con->prepare("INSERT INTO `Door_Locks` (`Id`, `Email`, `Location`, `Status`) VALUES (NULL, ?, ?, ?);");
				$Door_Locks->bind_param("sss",$Email, $Location, $Status);
                $Door_Locks->execute();

                $Location = "2 Car Garage DOOR";
                $Status = "UNLOCKED";
                $Door_Locks = $this->con->prepare("INSERT INTO `Door_Locks` (`Id`, `Email`, `Location`, `Status`) VALUES (NULL, ?, ?, ?);");
				$Door_Locks->bind_param("sss",$Email, $Location, $Status);
                $Door_Locks->execute();

                $Floor = "Main Floor";
                $Status = "INACTIVE";
                $Motion_Detector = $this->con->prepare("INSERT INTO `Motion_Detector` (`Id`, `Email`, `Floor`, `Status`) VALUES (NULL, ?, ?, ?);");
				$Motion_Detector->bind_param("sss",$Email, $Floor, $Status);
                $Motion_Detector->execute();

                $Floor = "Upstairs";
                $Status = "INACTIVE";
                $Motion_Detector = $this->con->prepare("INSERT INTO `Motion_Detector` (`Id`, `Email`, `Floor`, `Status`) VALUES (NULL, ?, ?, ?);");
				$Motion_Detector->bind_param("sss",$Email, $Floor, $Status);
                $Motion_Detector->execute();

				return 1;
			} else {
				return 2;
			}						 
			}	
		}

	//Function for User Login//
		public function userLogin ($Email, $Pass) {
			$Password = md5($Pass);
			if ($stmt = $this->con->prepare ("SELECT Id from `Login_Data` WHERE Email = ? AND Password = ?"))
			{
			$stmt->bind_param("ss", $Email, $Password);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows > 0;
			}
			}

		public function getUser ($Email)
		{
			if ($stmt = $this->con->prepare("SELECT * FROM `Login_Data` WHERE Email = ?"))
			{
				$stmt->bind_param("s", $Email);
				$stmt->execute();
				return $stmt->get_result()->fetch_assoc();
			}
		}


		private function isUserExist ($Email) {
			if ($stmt = $this->con->prepare("SELECT Id FROM `Login_Data` WHERE Email = ?"))
			{	
			$stmt->bind_param("s", $Email);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows > 0;
			}
		}

		public function ThermostatUpdate ($Email, $Floor, $Mode, $Fan) {
			if ($Floor === "Main Floor") {
				$stmt = $this->con->prepare("UPDATE `Thermostat` SET `Email`= ?,`Floor`= ?,`Mode`= ?,`Fan`= ? WHERE `Email`='$Email' AND `Floor`='$Floor'");
			} else if ($Floor === "Upstairs") {
				$stmt = $this->con->prepare("UPDATE `Thermostat` SET `Email`= ?,`Floor`= ?,`Mode`= ?,`Fan`= ? WHERE `Email`='$Email' AND `Floor`='$Floor'");
			}
			$stmt->bind_param("ssss",$Email,$Floor, $Mode, $Fan);
            $stmt->execute();
            $stmt->store_result();
        }

        public function getThermostatStatus ($Email, $Floor)
        {
            if ($stmt2 = $this->con->prepare("SELECT * from `Thermostat` where Email = ? AND Floor = ?"))
            {
            $stmt2->bind_param("ss", $Email, $Floor);
            $stmt2->execute();         
            return $stmt2->get_result()->fetch_assoc();
 			}
 		}

 		public function TemperatureUpdate ($Email, $Floor, $Current, $Control) {
			if ($Floor === "Main Floor") {
				$stmt = $this->con->prepare("UPDATE `Temperature` SET `Email`= ?,`Floor`= ?,`Current`= ?,`Control`= ? WHERE `Email`='$Email' AND `Floor`='$Floor'");
			} else if ($Floor === "Upstairs") {
				$stmt = $this->con->prepare("UPDATE `Temperature` SET `Email`= ?,`Floor`= ?,`Current`= ?,`Control`= ? WHERE `Email`='$Email' AND `Floor`='$Floor'");
			}
			$stmt->bind_param("ssss",$Email,$Floor, $Current, $Control);
            $stmt->execute();
            $stmt->store_result();
        }


		public function LightingUpdate ($Email, $Floor, $Location, $Intensity) {
			if ($Floor === "Main Floor") {
			$stmt = $this->con->prepare("UPDATE `Lighting` SET `Email`= ?,`Floor`= ?,`Location`= ?,`Intensity`= ? WHERE `Email`='$Email' AND `Floor`='$Floor' AND `Location`='$Location'");
			} else if ($Floor === "Upstairs") {
			$stmt = $this->con->prepare("UPDATE `Lighting` SET `Email`= ?,`Floor`= ?,`Location`= ?,`Intensity`= ? WHERE `Email`='$Email' AND `Floor`='$Floor' AND `Location`='$Location'");
			}
			$stmt->bind_param("ssss",$Email, $Floor, $Location, $Intensity);
            $stmt->execute();
            $stmt->store_result();
}
        public function getLightingStatus ($Email, $Floor, $Location)
        {
            if ($stmt2 = $this->con->prepare("SELECT * from `Lighting` where Email = ? AND Floor = ? AND Location = ?"))
            {
            $stmt2->bind_param("sss",$Email, $Floor, $Location);
            $stmt2->execute();
            return $stmt2->get_result()->fetch_assoc();
        }
}
        public function Security_SystemUpdate ($Email, $Status) {
			$stmt = $this->con->prepare("UPDATE `Security_System` SET `Email`= ?,`Status`= ? WHERE `Email`='$Email'");
			$stmt->bind_param("ss",$Email,$Status);
            $stmt->execute();
            $stmt->store_result();
        }

        public function getSecurityStatus ($Email)
        {
          if ($stmt2 = $this->con->prepare("SELECT * from `Security_System` where Email = ?"))
          { 
            $stmt2->bind_param("s",$Email);
            $stmt2->execute();
            return $stmt2->get_result()->fetch_assoc();
        }
		}

		 public function Door_LocksUpdate ($Email, $Location, $Status) {
			$stmt = $this->con->prepare("UPDATE `Door_Locks` SET `Email`= ?, `Location`= ?, `Status`= ? WHERE `Email`='$Email' AND `Location`='$Location'");
			$stmt->bind_param("sss",$Email, $Location, $Status);
            $stmt->execute();
            $stmt->store_result();
        }

        public function getLockStatus ($Email, $Location)
        {
           if($stmt2 = $this->con->prepare("SELECT * from `Door_Locks` where Email = ? AND Location = ?"))
           {
            $stmt2->bind_param("ss",$Email, $Location);
            $stmt2->execute();
            return $stmt2->get_result()->fetch_assoc();
		}
	}

		public function Motion_DetectorUpdate ($Email, $Floor, $Status) {
			$stmt = $this->con->prepare("UPDATE `Motion_Detector` SET `Email`= ?, `Floor`= ?, `Status`= ? WHERE `Email`='$Email' AND `Floor`='$Floor'");
			$stmt->bind_param("sss",$Email, $Floor, $Status);
            $stmt->execute();
            $stmt->store_result();
        }

        public function getMotionDetectorStatus ($Email, $Floor)
        {
            $stmt2 = $this->con->prepare("SELECT * from `Motion_Detector` where Email = ? AND Floor = ?");
            $stmt2->bind_param("ss",$Email, $Floor);
            $stmt2->execute();
            return $stmt2->get_result()->fetch_assoc();

		}


	}
