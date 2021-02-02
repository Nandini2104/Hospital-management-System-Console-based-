package net.codejava;
import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class Test1 {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("***********************************************");
		System.out.println("*******WELCOME TO E-HEALTHCARE HOSPITAL********");
		System.out.println("***********************************************");
		
		try {
			String url="jdbc:mysql://localhost:3306/namdb1";
			String username ="root";
			String password ="NANDINI!@#";
			 Connection conn = DriverManager.getConnection(url, username, password);
					System.out.println("	Tell Me Who you are?\n		1.Patient\n		2.Doctor");
					System.out.print("	Enter your choice: ");
						int n = scan.nextInt();
					if(n==1)
					{
						char ch ='y';
						do 
						{
							System.out.print("Enter Pid: ");
							int pid = scan.nextInt();
							System.out.print("Enter Patient first name: ");
							scan.nextLine();
							String pfname = scan.nextLine();
							System.out.print("Enter Patient Second name: ");
							String psname = scan.nextLine();
							String disease =  getDisease();
							String docname = assignDocName(disease);
							String sql = "INSERT INTO patient(pid,pfname,psname,disease,docname) VALUES (?,?,?,?,?)";
							PreparedStatement stat = conn.prepareStatement(sql);
							stat.setInt(1, pid);
							stat.setString(2, pfname);
							stat.setString(3, psname);
							stat.setString(4, disease);
							stat.setString(5, docname);
							int rows = stat.executeUpdate();
							display(pid,pfname,psname,disease,docname);
							if(rows>0)
								System.out.println("------Patient record inserted------");
							System.out.print("Want to continue(y/n): ");
							ch = scan.next().charAt(0);
							if(ch=='n')
									System.out.println("Thankyou for Visiting!!");
						}while(ch!='n');
					}
					else
					{
						char ch1='n';
						do
						{
							System.out.println("1.Patient List");
							System.out.println("2.Doctor's List");
							System.out.println("3.Update Doctor's List");
							System.out.println("Enter your choice: ");
							int m = scan.nextInt();
							switch(m)
							{
								case 1:
									String sql ="SELECT * FROM Patient";
									Statement stat1 = conn.createStatement();
									ResultSet res = stat1.executeQuery(sql);
									while(res.next())
									{
										String pid = res.getString(1);
										String pfname = res.getString("pfname");
										String psname = res.getString("psname");
										String des = res.getString("disease");
										String doc = res.getString("docName");
										System.out.println(pid+"   | "+pfname+" | "+psname+" | "+des+ " | "+doc+" | \n");
									}	
									break;
								case 2:
									String sql2 ="SELECT * FROM Doctors";
									Statement statement = conn.createStatement();
									ResultSet result = statement.executeQuery(sql2);
									while(result.next())
									{
										String pid = result.getString(1);
										String dname = result.getString("dname");
										String desg = result.getString("designation");
										String time = result.getString(4);
										System.out.println(" "+pid+"   | "+dname+" | "+desg+" | "+time+"\n");
									}	
									break;
								case 3:
									System.out.print("Enter Did: ");
									int Did = scan.nextInt();
									System.out.print("Enter Doctor name: ");
									scan.nextLine();
									String dname = scan.nextLine();
									System.out.print("Enter Doctor's Designation: ");
									String designation = scan.nextLine();
									System.out.print("Enter Doctor's Available Timings: ");
									String timings = scan.nextLine();
									String sql1 = "INSERT INTO doctors(Did,dname,designation,timings) VALUES (?,?,?,?)";
									PreparedStatement stat = conn.prepareStatement(sql1);
									stat.setInt(1, Did);
									stat.setString(2, dname);
									stat.setString(3, designation);
									stat.setString(4,timings);
									int rows = stat.executeUpdate();
									if(rows>0)
										System.out.println("Doctor's record inserted");
									break;
							}	
							System.out.print("Do You Want to Exit(y/n): ");
							ch1 = scan.next().charAt(0);
							if(ch1=='y')
								System.out.println("ThankYou For Coming!!");
						}while(ch1!='y');
					}
			conn.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error!!");
			e.printStackTrace();
		}
	}
	public static String getDisease()
	{
		System.out.println("	| 1.Asthma		| 2.Eczema	 	  | 3.Food allergies	  | 4.Insect sting allergies 	|"
				+ "\n\n	| 5.Heart failure	| 6.Heart attack 	  | 7.High blood pressure | 8.Irregular heartbeat 	|"
				+ "\n\n	| 9.Skin allergies	| 10. Diabetes	 	  | 11.Thyroid        	  | 12.Infertility 		|"
				+ "\n\n	| 13.Bone disorders	| 14.Routine checkups 	  | 15.HIV 		  | 16.AIDS 			|	"
				+ "\n\n	| 17.Abdominal pain	| 18.Ulcers 		  | 19.Diarrhea		  | 20.Jaundice 		|"
				+ "\n\n	| 21.Fevers 		| 22.Lyme disease	  | 23.Neumonia 	  | 24.Tuberculosis 		| \n");
		System.out.print("Choose What Problem you are facing: ");
		int n = scan.nextInt();
		String dis="";
		switch(n)
		{
		case 1: dis="Asthma";
				break;
		case 2: dis="Eczema";
				break;
		case 3: dis="Food allergies";
				break;
		case 4: dis="Insect sting allergies";
				break;
		case 5: dis="Heart failure";
				break;
		case 6:	dis = "Heart attack";
				break;
		case 7:	dis = "High blood pressure";
				break;
		case 8:	dis = "Irregular heartbeat";
				break;
		case 9:	dis = "Skin allergies";
				break;
		case 10:dis = "Diabetes";
				break;
		case 11:dis = "Thyroid";
				break;
		case 12:dis = "Infertility";
				break;
		case 13:dis = "Bone disorders";
				break;
		case 14:dis = "Routine checkups";
				break;
		case 15:dis = "HIV";
				break;
		case 16:dis = "AIDS";
				break;
		case 17:dis = "Abdominal pain";
				break;
		case 18:dis = "Ulcers";
				break;
		case 19:dis = "Diarrhea";
				break;
		case 20:dis = "Jaundice";
				break;
		case 21:dis = "Fevers";
				break;
		case 22:dis = "Lyme disease";
				break;
		case 23:dis = "Neumonia";
				break;
		case 24:dis = " Tuberculosis";
				break;
		}
		return (dis);
	}
	public static String assignDocName(String d)
	{
		ArrayList<String> a = new ArrayList<>();
		a.add("Asthma");a.add("Eczema");a.add("Food allergies");a.add("insect sting allergies");
		a.add("Heart failure");a.add("Heart attack");a.add("High blood pressure");a.add("Irregular heartbeat");
		a.add("Skin allergies");a.add("diabetes");a.add("thyroid");a.add("infertility");
		a.add("bone disorders");a.add("Routine checkups");a.add("Abdominal pain");a.add("Ulcers");
		a.add("Diarrhea");a.add("Jaundice ");a.add("Fevers ");a.add("Lyme disease");a.add("Neumonia");
		a.add("Tuberculosis");a.add("HIV");a.add("AIDS");
		String doc;
		if(d.equals(a.get(0))||d.equals(a.get(1))||d.equals(a.get(2))||d.equals(a.get(3)))
			doc = "Dr. Ashok Kumar";
		else if(d.equals(a.get(4))|| d.equals(a.get(5))||d.equals(a.get(6))||d.equals(a.get(7)))
			doc = "Dr.P.Bala Jayan";
		else if(d.equals(a.get(8)))
			doc = "Dr.Amita Singal";
		else if(d.equals(a.get(9))||d.equals(a.get(10))||d.equals(a.get(11))||d.equals(a.get(12)))
			doc = "Dr.Kyale Rathore";
		else if(d.equals(a.get(13)))
			doc = "Dr.Preeti Singh";
		else if(d.equals(a.get(14))||d.equals(a.get(15))||d.equals(a.get(16))||d.equals(a.get(17)))
			doc = "Dr.Ujwal Mehta";
		else if(d.equals(a.get(18))||d.equals(a.get(19))||d.equals(a.get(20))||d.equals(a.get(21))||d.equals(a.get(22))||d.equals(a.get(23)))
			doc = "Dr.Laxmi Patel";
		else
			doc="";
		return doc;
	}
	public static void display(int id,String name,String name2,String dis,String doc)
	{
		System.out.println("-------------------------------------");
		System.out.println("Patient id: "+id);
		System.out.println("Patient name: "+name+" "+name2);
		System.out.println("Patient problem: "+dis);
		System.out.println("Patient Doctor name: "+doc);
		System.out.println("-------------------------------------");
	}
}
