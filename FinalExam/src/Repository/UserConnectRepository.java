package Repository;

import constant.Constant;
import Object.Admin;
import Object.Employee;

import java.io.PrintStream;
import java.sql.*;

public class UserConnectRepository {

    public Connection createConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);

        }catch (SQLException e) {
            System.out.println(e);;
        }
    return con;
    }


    public void getAllData(){
        try{
            Connection con = createConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            while (resultSet.next())
                out.println(resultSet.getInt(1)+ " "+ resultSet.getString(2)+" "
                        +resultSet.getString(3)+" "+resultSet.getString(4)+" "
                        +resultSet.getString(5)+" "+resultSet.getString(6)+" "
                        +resultSet.getString(7)+" "+resultSet.getString(8));
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //cau2
    public void getAllDatabyProjectID(int projectId){
        try{
            Connection con = createConnection();
            String sql = "SELECT * FROM user WHERE role IN ('EMPLOYEE','MANAGER') AND project_id = " + projectId;
            Statement statement = con.createStatement();
            ResultSet resultData = statement.executeQuery(sql);
            int count = 0;

            while (resultData.next()){
                int id = resultData.getInt(1);
                String password = resultData.getString(2);
                String fullName = resultData.getString(3);
                String email = resultData.getString(4);
                int expInYear = resultData.getInt(5);
                String proSkill = resultData.getString(6);
                int projectID = resultData.getInt(7);
                String role = resultData.getString(8);

                String output = "User #%d: %s - %s - %s - %d -%s - %d -%s";
                System.out.println(String.format(output, id, password, fullName, email, expInYear, proSkill, projectID, role));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



        //cau 3
    public void login(Admin admin) {
        try{
            Connection connection = createConnection();
            String sql = "SELECT * FROM user WHERE email = '" + admin.getEmail() + "' AND password = '" + admin.getPassword() +"'";
            System.out.println("SQL = "+ sql);
            Statement statement = connection.createStatement();
            ResultSet resultLogin = statement.executeQuery(sql);

            int count = 0;
            while(resultLogin.next()){
                int id = resultLogin.getInt(1);
                String password = resultLogin.getString(2);
                String fullName = resultLogin.getString(3);
                String email = resultLogin.getString(4);
                int expInYear = resultLogin.getInt(5);
                String proSkill = resultLogin.getString(6);
                int projectId = resultLogin.getInt(7);
                String role = resultLogin.getString(8);

                String output ="User #%d: %s - %s - %d - %s -%s - %d -%s";
                System.out.println(String.format(output,id,password,fullName,projectId,email,expInYear,projectId,role));
            }
            connection.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }


        //cau4



    }

    public void createEmployee(Employee employee) {
        try{
            Connection con = createConnection();
            String sql = "INSERT INTO user (password, fullName, email, exp_in_year, pro_skill, project_id, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, Constant.DEFAULT_PASSWORD);
            statement.setString(2, employee.getFullName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, Constant.DEFAULT_VALUE);
            statement.setString(5, Constant.DEFAULT_VALUE);
            statement.setString(6, Constant.DEFAULT_VALUE);
            statement.setString(7, Constant.EMPLOYEE);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new Employee was inserted successfully!");
            } else {
                System.out.println("A new Employee was inserted fail!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
