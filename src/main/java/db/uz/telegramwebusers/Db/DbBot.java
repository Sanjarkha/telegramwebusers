package db.uz.telegramwebusers.Db;

import db.uz.telegramwebusers.Settings;

import java.sql.*;
import java.util.Date;

public class DbBot {
    Settings settings=new Settings();
    private String URL=settings.jdbcurl;
    private String USERNAME=settings.jdbclogin;
    private String PASSWORD=settings.jdbcpassword;
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    public String check_contact;
    public  int check_step;
    public Date status_date=null;
    public String status_firstname=null;
    public String status_lastname=null;
    public String status_phone=null;
    public boolean status_status=false;
    public void conn0ection(){
        try{
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            System.out.println("База данных подключена");
        }catch (SQLException e){
            System.out.println("Ошибка подключения к база данным");
        }
    }
    public void userinsert(Long id){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            stm.execute("INSERT INTO d_users(t_userid,status) values("+id+",false );");
            System.out.println("created is succesfull database d_users");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void userupdate(Long id,String firstname,String lastname,String phonenumber){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            stm.executeUpdate("UPDATE d_users set firstname='"+firstname+"',lastname='"+lastname+"',phonenumber='"+phonenumber+"' where t_userid="+id+";");
            System.out.println("Данные пользователя успешно обновлен");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void usersecondupdate(Long id,String category_name ,int category){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            stm.execute("UPDATE d_users set "+category_name+"="+category+" where t_userid="+id+";");
        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void user_status(Long id ){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            rs = stm.executeQuery("SELECT * FROM d_users WHERE t_userid="+id +"");
            while (rs.next()){
                status_date=rs.getDate("created_date");
                status_firstname=rs.getString("firstname");
                status_lastname=rs.getString("lastname");
                status_phone=rs.getString("phonenumber");
                status_status=rs.getBoolean("status");
            }

        }catch (SQLException e){
            System.out.println("Eror user status");
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void step_select(Long id){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            rs = stm.executeQuery("SELECT menu FROM t_menu WHERE d_user=" + id + "");
            while (rs.next()) {
                check_step = rs.getInt("menu");
            }
            System.out.println("Step_select"+check_contact);
        }catch (SQLException e){
            System.out.println("Eror method step_select");
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void step_create(Long user_id,int check){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            stm.execute("INSERT INTO t_menu(d_user,menu) values(" + user_id + "," + check + ");");
        }catch (SQLException e){
            System.out.println("Eror step_create");
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void step_menu(Long user_id, int menu){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            stm.executeUpdate("UPDATE t_menu set menu="+menu+" where d_user="+user_id+";");
            System.out.println("Succesfull ");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void contact_check(Long user_id){
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            stm=con.createStatement();
            rs = stm.executeQuery("SELECT phonenumber from d_users where t_userid=" +user_id+ ";");
            while (rs.next()) {
                check_contact= rs.getString("phonenumber");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("user_contact_chech:"+check_contact);
    }
}
