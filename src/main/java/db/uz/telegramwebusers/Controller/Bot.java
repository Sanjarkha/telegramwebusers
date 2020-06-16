package db.uz.telegramwebusers.Controller;


import db.uz.telegramwebusers.Db.DbBot;
import db.uz.telegramwebusers.Service.Servicebot;
import db.uz.telegramwebusers.Settings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.util.Date;

public class Bot extends TelegramLongPollingBot {

    Settings settings=new Settings();
    private String URL=settings.jdbcurl;
    private String USERNAME=settings.jdbclogin;
    private String PASSWORD=settings.jdbcpassword;
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    private String bot_token =settings.bottoken;
    private String bot_username = settings.botname;
    static {
        TelegramBotsApi telegram_api = new TelegramBotsApi();
        try {
            telegram_api.registerBot(new Bot());
            System.out.println("Started telegram_api successful");
        } catch (TelegramApiException e) {
            System.out.println("Eror telegram_api");
        }

    }


    public void sendMsg(Message message, String text){
        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        /**Created by SanjarKha
         * 14.02.2020
         * Telegram method onUpdateRecived**/
        DbBot db=new DbBot();
//        db.connection();

        Servicebot service=new Servicebot();
        Message message=update.getMessage();

        if (update.hasCallbackQuery()) {
            int check_step=0;
            try {
                con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                stm=con.createStatement();
                rs = stm.executeQuery("SELECT menu FROM t_menu WHERE d_user=" + update.getCallbackQuery().getFrom().getId().longValue() + "");
                while (rs.next()) {
                    check_step = rs.getInt("menu");
                }
                System.out.println(check_step);
            }catch (SQLException e){
                System.out.println("Eror method step_select");
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            switch (check_step){
                case 2:
                    String back_data = update.getCallbackQuery().getData();
                    System.out.println(back_data);
                    int result= Integer.parseInt(back_data);
                    db.usersecondupdate(update.getCallbackQuery().getFrom().getId().longValue(),"branch",result);
                    service.button_status(update.getCallbackQuery().getFrom().getId().longValue());
                    db.step_menu(update.getCallbackQuery().getFrom().getId().longValue(), 3);
                    DeleteMessage msg = new DeleteMessage()
                            .setChatId(update.getCallbackQuery().getMessage().getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    try {
                        execute(msg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }service.department(update.getCallbackQuery().getFrom().getId().longValue());
                    break;
                case 3:
                    String back_data2 = update.getCallbackQuery().getData();
                    System.out.println(back_data2);
                    int result2= Integer.parseInt(back_data2);
                    db.usersecondupdate(update.getCallbackQuery().getFrom().getId().longValue(),"departmant",result2);
                    db.step_menu(update.getCallbackQuery().getFrom().getId().longValue(), 4);
                    DeleteMessage msg1 = new DeleteMessage()
                            .setChatId(update.getCallbackQuery().getMessage().getChatId())
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    try {
                        execute(msg1);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    service.post_validatin(update.getCallbackQuery().getFrom().getId().longValue());
            }
            return ;
        }
        db.step_select(message.getChatId());
        if (db.check_step==6){
            sendMsg(message,"eror");
        }else if (db.check_step<5) {
            if (message != null && message.hasText()) {
                switch (message.getText()) {
                    case "/start":
                        long userid=0;
                        try/**step_check*/ {
                            db.rs =db.stm.executeQuery("SELECT d_user FROM t_menu WHERE d_user=" +message.getChatId()+ "");
                            while (db.rs.next()) {
                                userid=db.rs.getLong("d_user");
                            }
                        }catch (SQLException e){
                            System.out.println("Eror method step_check");
                        }
                        if (userid==message.getChatId()){
                            sendMsg(message,"Error return type");
                        }else {
                            System.out.println("Enter to start");
                            db.step_create(message.getChatId(), 1);
                            service.buttonContact(message.getChatId());
                            db.userinsert(message.getChatId());
                        }
                        break;
                    case "Мой Статус":
                        db.user_status(message.getChatId());
                        Date date=db.status_date;
                        String firstname=db.status_firstname;
                        String lastname=db.status_lastname;
                        String phone=db.status_phone;
                        boolean status=db.status_status;
                        sendMsg(message,"********Ваш статус********\nДата регистрации: "+date+"\nИмя: "+firstname+"\nФамилия: "+lastname+"\nНомер Телефонный: "+phone+"\nСтатус активен: "+status);
                        System.out.println(date+"/"+firstname+"/"+lastname+"/"+phone+"/"+status);
                        break;
                }
            }else {

                db.contact_check(message.getChatId());
                String contact = db.check_contact;

                if (message.getContact().getPhoneNumber().equals(contact)) {
                    sendMsg(message, "ваш контактный номер уже добавлен в базу");
                }else {

                    if (message.getChatId()==(long)message.getContact().getUserID()) {
                        db.userupdate(message.getChatId(), message.getContact().getFirstName(), message.getContact().getLastName(), message.getContact().getPhoneNumber());
                        db.step_menu(message.getChatId(), 2);
                    }else {
                        sendMsg(message,"4");
                    }
                }
            }
        }
        System.out.println(message.getText());
        db.step_select(message.getChatId());
        switch (db.check_step){
            case 2:
                service.branch(message.getChatId());
                break;
            case 3:
                service.department(message.getChatId());
                break;
            case 4:
                service.button_status(message.getChatId());
                break;

        }
    }

    @Override
    public String getBotUsername() {
        return bot_username;
    }

    @Override
    public String getBotToken() {
        return bot_token;
    }
}
