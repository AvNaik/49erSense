package sqlDatabase_Read;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class read_Database {

	private static final String url = "jdbc:mysql://172.20.10.4:3306/";
	private static final String user = "root";
	private static final String password = "my-new-password";
	public static final String queryFloor = "Main Floor"; 

	static String SecurityStatus = "";
	static String EMailId = "avanaik92@gmail.com";
	static String SecurityStatus1 = "";
	static String ApplicanceFloor = "";
	static String ApplicanceFloor1 = "";
	static String ThermostatMode = "";
	static String FanMode = "";
	static String ThermostatMode1 = "";
	static String FanMode1 = "";
	static String ControlTemperature = "";
	static String ControlTemperature1 = "";
	static String LightLocation = "";
	static String LightLocation1 = "";
	static String LivingRoomIntensity = "";
	static String LivingRoomIntensity1 = "";
	static String KitchenIntensity = "";
	static String KitchenIntensity1 = "";
	static String BathroomIntensity = "";
	static String BathroomIntensity1="";
	static String DoorLocation = "";
	static String FrontDoorStatus = "";
	static String FrontDoorStatus1 = "";
	static String RearDoorStatus = "";
	static String RearDoorStatus1 = "";
	static String Car1garageDoor = "";
	static String Car1garageDoor1 = "";
	static String Car2garageDoor = "";
	static String Car2garageDoor1 = "";
	static String MotionDetectorStatus = "";
	static String MotionDetectorStatus1 = "";
	
	static int flag = 0;

	public static void main(String[] args) {

		while (true)
		{
			try {
				Connection connect = null;
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance ();
				connect = DriverManager.getConnection(url, user, password);
				Statement stt = connect.createStatement ();

				stt.execute("CREATE DATABASE IF NOT EXISTS Login");
				stt.execute("USE Login");

				ResultSet res = stt.executeQuery("SELECT * FROM Security_System WHERE Email = '" + EMailId + "' ");

				while (res.next())
				{
					SecurityStatus = res.getString("Status");
				}
				if (SecurityStatus.equals(SecurityStatus1))
				{
					//do nothing
				} else
				{
					System.out.println("Status is:" + SecurityStatus);
				}


				Statement Thermostatstt = connect.createStatement ();
				Thermostatstt.execute("CREATE DATABASE IF NOT EXISTS Login");
				Thermostatstt.execute("USE Login");
				ResultSet Thermostatres = Thermostatstt.executeQuery("SELECT * FROM Thermostat WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor'");

				while (Thermostatres.next())
				{
					ApplicanceFloor = Thermostatres.getString("Floor");
					ThermostatMode = Thermostatres.getString("Mode");
					FanMode = Thermostatres.getString("Fan");

					if (ThermostatMode.equals(ThermostatMode1))
					{
					} else
					{
						System.out.println("Thermostat Mode is:" + ThermostatMode);
					}

					if (FanMode.equals(FanMode1))
					{
						//do nothing
					} else
					{
						System.out.println("Fan Mode :" + FanMode);
					}
				}
				
				Statement Temperaturestt = connect.createStatement ();
				Temperaturestt.execute("CREATE DATABASE IF NOT EXISTS Login");
				Temperaturestt.execute("USE Login");
				ResultSet Temperatureres = Temperaturestt.executeQuery("SELECT * FROM Temperature WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor'");

				while (Temperatureres.next())
				{
					ApplicanceFloor = Temperatureres.getString("Floor");
					ControlTemperature = Temperatureres.getString("Control");

					if (ControlTemperature.equals(ControlTemperature1))
					{
					} else
					{
						System.out.println("Control Temperature has been set to: " + ControlTemperature);
					}
				}

				Statement Lightingstt = connect.createStatement ();
				Lightingstt.execute("CREATE DATABASE IF NOT EXISTS Login");
				Lightingstt.execute("USE Login");
				ResultSet Lightingres = Lightingstt.executeQuery("SELECT * FROM Lighting WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor' AND Location = 'LIVING ROOM'");

				while (Lightingres.next())
				{
					ApplicanceFloor = Lightingres.getString("Floor");
					LightLocation = Lightingres.getString("Location");
					LivingRoomIntensity = Lightingres.getString("Intensity");
					
					if (LivingRoomIntensity.equals(LivingRoomIntensity1))
					{
					} else
					{					
							System.out.println("Light in " + LightLocation + " Changed to  " + LivingRoomIntensity);
					}
				}
					
					Statement Lightingstt2 = connect.createStatement ();
					Lightingstt2.execute("CREATE DATABASE IF NOT EXISTS Login");
					Lightingstt2.execute("USE Login");
					ResultSet Lightingres2 = Lightingstt2.executeQuery("SELECT * FROM Lighting WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor' AND Location = 'KITCHEN'");

					while (Lightingres2.next())
					{
						ApplicanceFloor = Lightingres2.getString("Floor");
						LightLocation = Lightingres2.getString("Location");
						KitchenIntensity = Lightingres2.getString("Intensity");
						
						if (KitchenIntensity.equals(KitchenIntensity1))
						{
						} else
						{					
								System.out.println("Light in " + LightLocation + " Changed to  " + KitchenIntensity);
						}
					}
					
					Statement Lightingstt4 = connect.createStatement ();
					Lightingstt4.execute("CREATE DATABASE IF NOT EXISTS Login");
					Lightingstt4.execute("USE Login");
					ResultSet Lightingres4 = Lightingstt4.executeQuery("SELECT * FROM Lighting WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor' AND Location = 'BATHROOM'");

					while (Lightingres4.next())
					{
						ApplicanceFloor = Lightingres4.getString("Floor");
						LightLocation = Lightingres4.getString("Location");
						BathroomIntensity = Lightingres4.getString("Intensity");
						
						if (BathroomIntensity.equals(BathroomIntensity1))
						{
						} else
						{					
								System.out.println("Light in " + LightLocation + " Changed to  " + BathroomIntensity);
						}
					}

					Statement FrontDoorstt = connect.createStatement ();
					FrontDoorstt.execute("CREATE DATABASE IF NOT EXISTS Login");
					FrontDoorstt.execute("USE Login");
					ResultSet FrontDoorres = FrontDoorstt.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = 'FRONT DOOR'");

					while (FrontDoorres.next())
					{
						DoorLocation = FrontDoorres.getString("Location");
						FrontDoorStatus = FrontDoorres.getString("Status");
												
						if (FrontDoorStatus.equals(FrontDoorStatus1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + FrontDoorStatus);
						}
					}
					
					Statement RearDoorstt = connect.createStatement ();
					RearDoorstt.execute("CREATE DATABASE IF NOT EXISTS Login");
					RearDoorstt.execute("USE Login");
					ResultSet RearDoorres = RearDoorstt.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = 'REAR DOOR'");

					while (RearDoorres.next())
					{
						DoorLocation = RearDoorres.getString("Location");
						RearDoorStatus = RearDoorres.getString("Status");
												
						if (RearDoorStatus.equals(RearDoorStatus1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + RearDoorStatus);
						}
					}

					Statement Car1Doorstt = connect.createStatement ();
					Car1Doorstt.execute("CREATE DATABASE IF NOT EXISTS Login");
					Car1Doorstt.execute("USE Login");
					ResultSet Car1Doorres = Car1Doorstt.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = '1 CAR GARAGE DOOR'");

					while (Car1Doorres.next())
					{
						DoorLocation = Car1Doorres.getString("Location");
						Car1garageDoor = Car1Doorres.getString("Status");
												
						if (Car1garageDoor.equals(Car1garageDoor1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + Car1garageDoor);
						}
					}
					
					Statement Car2GarageDoorstt = connect.createStatement ();
					Car2GarageDoorstt.execute("CREATE DATABASE IF NOT EXISTS Login");
					Car2GarageDoorstt.execute("USE Login");
					ResultSet Car2GarageDoorres = Car2GarageDoorstt.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = '2 Car Garage DOOR'");

					while (Car2GarageDoorres.next())
					{
						DoorLocation = Car2GarageDoorres.getString("Location");
						Car2garageDoor = Car2GarageDoorres.getString("Status");
												
						if (Car2garageDoor.equals(Car2garageDoor1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + Car2garageDoor);
						}
					}

					
					Statement MotionDetectorstt = connect.createStatement ();
					MotionDetectorstt.execute("CREATE DATABASE IF NOT EXISTS Login");
					MotionDetectorstt.execute("USE Login");
					ResultSet MotionDetectorres = MotionDetectorstt.executeQuery("SELECT * FROM Motion_Detector WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor'");

					while (MotionDetectorres.next())
					{
						ApplicanceFloor = MotionDetectorres.getString("Floor");
						MotionDetectorStatus = MotionDetectorres.getString("Status");

						if (MotionDetectorStatus.equals(MotionDetectorStatus1))
						{
						} else
						{
							System.out.println("The Status of Motion Detector for " + ApplicanceFloor + " has been set to: " + MotionDetectorStatus);
						}
					}
					

					TimeUnit.SECONDS.sleep(3);

					Statement stt1 = connect.createStatement ();
					stt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					stt1.execute("USE Login");

					ResultSet res1 = stt1.executeQuery("SELECT * FROM Security_System WHERE Email = 'avanaik92@gmail.com'");

					while (res1.next())
					{
						SecurityStatus1= res1.getString("Status");				}

					if (SecurityStatus.equals(SecurityStatus1))
					{
						//do nothing
					} else
					{
						System.out.println("Status is: " + SecurityStatus1);
					}

					Statement Thermostatstt1 = connect.createStatement ();
					Thermostatstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					Thermostatstt1.execute("USE Login");
					ResultSet Thermostatres1 = Thermostatstt1.executeQuery("SELECT * FROM Thermostat WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor'");

					while (Thermostatres1.next())
					{
						ApplicanceFloor1=Thermostatres1.getString("Floor");
						ThermostatMode1= Thermostatres1.getString("Mode");
						FanMode1= Thermostatres1.getString("Fan");

						if (ThermostatMode.equals(ThermostatMode1))
						{
						} else
						{
							System.out.println("Thermostat Mode is: " + ThermostatMode1);
						}

						if (FanMode.equals(FanMode1))
						{
						} else
						{
							System.out.println("Fan Mode is: " + FanMode1);
						}
					}
					
					Statement Temperaturestt1 = connect.createStatement ();
					Temperaturestt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					Temperaturestt1.execute("USE Login");
					ResultSet Temperatureres1 = Temperaturestt1.executeQuery("SELECT * FROM Temperature WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor'");

					while (Temperatureres1.next())
					{
						ApplicanceFloor = Temperatureres1.getString("Floor");
						ControlTemperature1 = Temperatureres1.getString("Control");

						if (ControlTemperature.equals(ControlTemperature1))
						{
						} else
						{
							System.out.println("Control Temperature has been set to: Avanaik" + ControlTemperature1);
						}
					}

					Statement Lightingstt1 = connect.createStatement ();
					Lightingstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					Lightingstt1.execute("USE Login");
					ResultSet Lightingres1 = Lightingstt1.executeQuery("SELECT * FROM Lighting WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor' AND Location='LIVING ROOM'");
					
					while (Lightingres1.next())
					{
						ApplicanceFloor1=Lightingres1.getString("Floor");
						LightLocation1= Lightingres1.getString("Location");
						LivingRoomIntensity1= Lightingres1.getString("Intensity");

						if (LivingRoomIntensity.equals(LivingRoomIntensity1))
						{
						} else
						{					
								System.out.println("Light in " + LightLocation1 + " Changed to  " + LivingRoomIntensity1);
						}
					}

					
					Statement Lightingstt3 = connect.createStatement ();
					Lightingstt3.execute("CREATE DATABASE IF NOT EXISTS Login");
					Lightingstt3.execute("USE Login");
					ResultSet Lightingres3 = Lightingstt3.executeQuery("SELECT * FROM Lighting WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor' AND Location='KITCHEN'");

					while (Lightingres3.next())
					{
						ApplicanceFloor1=Lightingres3.getString("Floor");
						LightLocation1= Lightingres3.getString("Location");
						KitchenIntensity1= Lightingres3.getString("Intensity");

						if (KitchenIntensity.equals(KitchenIntensity1))
						{
						} else
						{
							System.out.println("Light in " + LightLocation1 + " changed to " + KitchenIntensity1);
						}
					}
					
					Statement Lightingstt5 = connect.createStatement ();
					Lightingstt5.execute("CREATE DATABASE IF NOT EXISTS Login");
					Lightingstt5.execute("USE Login");
					ResultSet Lightingres5 = Lightingstt5.executeQuery("SELECT * FROM Lighting WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor' AND Location='BATHROOM'");

					while (Lightingres5.next())
					{
						ApplicanceFloor1=Lightingres5.getString("Floor");
						LightLocation1= Lightingres5.getString("Location");
						BathroomIntensity1= Lightingres5.getString("Intensity");

						if (BathroomIntensity.equals(BathroomIntensity1))
						{
						} else
						{
							System.out.println("Light in " + LightLocation1 + " changed to " + BathroomIntensity1);
						}
					}
					
					Statement FrontDoorstt1 = connect.createStatement ();
					FrontDoorstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					FrontDoorstt1.execute("USE Login");
					ResultSet FrontDoorres1 = FrontDoorstt1.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = 'FRONT DOOR'");

					while (FrontDoorres1.next())
					{
						DoorLocation = FrontDoorres1.getString("Location");
						FrontDoorStatus1 = FrontDoorres1.getString("Status");
												
						if (FrontDoorStatus.equals(FrontDoorStatus1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + FrontDoorStatus1);
						}
					}
					
					Statement RearDoorstt1 = connect.createStatement ();
					RearDoorstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					RearDoorstt1.execute("USE Login");
					ResultSet RearDoorres1 = RearDoorstt1.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = 'REAR DOOR'");

					while (RearDoorres1.next())
					{
						DoorLocation = RearDoorres1.getString("Location");
						RearDoorStatus1 = RearDoorres1.getString("Status");
												
						if (RearDoorStatus.equals(RearDoorStatus1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + RearDoorStatus1);
						}
					}
					
					Statement Car1Doorstt1 = connect.createStatement ();
					Car1Doorstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					Car1Doorstt1.execute("USE Login");
					ResultSet Car1Doorres1 = Car1Doorstt1.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = '1 CAR GARAGE DOOR'");

					while (Car1Doorres1.next())
					{
						DoorLocation = Car1Doorres1.getString("Location");
						Car1garageDoor1 = Car1Doorres1.getString("Status");
												
						if (Car1garageDoor.equals(Car1garageDoor1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + Car1garageDoor1);
						}
					}
					
					Statement Car2GarageDoorstt1 = connect.createStatement ();
					Car2GarageDoorstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					Car2GarageDoorstt1.execute("USE Login");
					ResultSet Car2GarageDoorres1 = Car2GarageDoorstt1.executeQuery("SELECT * FROM Door_Locks WHERE Email = 'avanaik92@gmail.com' AND Location = '2 Car Garage DOOR'");

					while (Car2GarageDoorres1.next())
					{
						DoorLocation = Car2GarageDoorres1.getString("Location");
						Car2garageDoor1 = Car2GarageDoorres1.getString("Status");
												
						if (Car2garageDoor.equals(Car2garageDoor1))
						{
						} else
						{					
								System.out.println(DoorLocation + " is " + Car2garageDoor1);
						}
					}
					
					Statement MotionDetectorstt1 = connect.createStatement ();
					MotionDetectorstt1.execute("CREATE DATABASE IF NOT EXISTS Login");
					MotionDetectorstt1.execute("USE Login");
					ResultSet MotionDetectorres1 = MotionDetectorstt1.executeQuery("SELECT * FROM Motion_Detector WHERE Email = 'avanaik92@gmail.com' AND Floor = 'Main Floor'");

					while (MotionDetectorres1.next())
					{
						ApplicanceFloor = MotionDetectorres1.getString("Floor");
						MotionDetectorStatus1 = MotionDetectorres1.getString("Status");

						if (MotionDetectorStatus.equals(MotionDetectorStatus1))
						{
						} else
						{
							System.out.println("The Status of Motion Detector for " + ApplicanceFloor + " has been set to: " + MotionDetectorStatus1);
						}
					}

				//res1.close();
				//stt1.close();
				//  prep.close();
				//connect1.close();
			}

			catch(Exception e)
			{
				//e.printStackTrace();
			}					
		}
	}
}


