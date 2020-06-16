package db.uz.telegramwebusers.Service;


import db.uz.telegramwebusers.Controller.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Servicebot extends Bot {
    public void buttonContact(Long chat_id){
        /**Created by SanjarKha
         * 14.02.2020
         * buttonContact method
         * */
        SendMessage sendMessage=new SendMessage();
        ReplyKeyboardMarkup keyboard=new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(true);
        keyboard.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows=new ArrayList<>();
        KeyboardRow row =new KeyboardRow();
        KeyboardButton button =new KeyboardButton("Мой номер ");

        button.setRequestContact(true);
        row.add(button);
        keyboardRows.add(row);
        keyboard.setKeyboard(keyboardRows);
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chat_id.toString());
        sendMessage.setText("Пожалуйста, введите свой номер... ");
        sendMessage.setReplyMarkup(keyboard);
        try{
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
    public void button_status(Long chat_id){
        SendMessage sendMessage=new SendMessage();

        ReplyKeyboardMarkup keyboard=new ReplyKeyboardMarkup();
        keyboard.setOneTimeKeyboard(true);
        keyboard.setResizeKeyboard(true);
        List<KeyboardRow> keyboardRows=new ArrayList<>();
        KeyboardRow row =new KeyboardRow();
        KeyboardButton button =new KeyboardButton("Мой Статус");
        row.add(button);
        keyboardRows.add(row);
        keyboard.setKeyboard(keyboardRows);
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chat_id.toString());
        sendMessage.setText("Ваш статус");
        sendMessage.setReplyMarkup(keyboard);
        try{
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
    public void post_validatin (Long chatid) {
        SendPhoto sendPhoto = new SendPhoto()
                .setChatId(chatid)
                .setPhoto("AgACAgIAAxkBAAIICV5KXlltJOl3CP_8p31FgHy_lS97AALlrjEb_qxRSjQS9CTA0x2tqHBcDwAEAQADAgADeAADKLYFAAEYBA")
                .setCaption("\nПоздравляю, вы зарегистрированы.\n Ждите одобрения администратора\n");
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void branch(Long chatid) {
        /**Created By Ilyos
         * method branch
         * 14.02.2020
         * */
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Главный").setCallbackData("09051"));
        rowInline.add(new InlineKeyboardButton().setText("Практический").setCallbackData("00981"));
        rowInline.add(new InlineKeyboardButton().setText("Яккасарай").setCallbackData("01069"));

        rowInline2.add(new InlineKeyboardButton().setText("Чилонзор").setCallbackData("01046"));
        rowInline2.add(new InlineKeyboardButton().setText("Алмазар").setCallbackData("01121"));
        rowInline2.add(new InlineKeyboardButton().setText("Учтепина").setCallbackData("01086"));

        rowInline3.add(new InlineKeyboardButton().setText("Мирзо Улугбек").setCallbackData("01072"));
        rowInline3.add(new InlineKeyboardButton().setText("Юнусабад").setCallbackData("01122"));
        rowsInline.add(rowInline);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        SendMessage sendMess = new SendMessage()
                .setChatId(chatid)
                .setText("Пожалуйста  выберите  филиал");
        sendMess.setReplyMarkup(inlineKeyboardMarkup).disableNotification();
        try {
            execute(sendMess);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void department(Long chatid) {
        /**
         * Created by Ilyos
         * 14.02.2020
         * */
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline5 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline6 = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Управляющий").setCallbackData("111"));
        rowInline.add(new InlineKeyboardButton().setText("Главный бухгалтер").setCallbackData("112"));
        rowInline.add(new InlineKeyboardButton().setText("Оператор").setCallbackData("113"));
        rowsInline.add(rowInline);

        rowInline2.add(new InlineKeyboardButton().setText("Денежное обращение").setCallbackData("114"));
        rowInline2.add(new InlineKeyboardButton().setText("Кредитование").setCallbackData("115"));
        rowInline2.add(new InlineKeyboardButton().setText("Валюта").setCallbackData("116"));
        rowsInline.add(rowInline2);

        rowInline3.add(new InlineKeyboardButton().setText("Конвертация").setCallbackData("117"));
        rowInline3.add(new InlineKeyboardButton().setText("Внутренний контроль").setCallbackData("118"));
        rowInline3.add(new InlineKeyboardButton().setText("Кадр").setCallbackData("119"));
        rowsInline.add(rowInline3);

        rowInline4.add(new InlineKeyboardButton().setText("Касса").setCallbackData("120"));
        rowInline4.add(new InlineKeyboardButton().setText("Пластик").setCallbackData("121"));
        rowInline4.add(new InlineKeyboardButton().setText("Розничный").setCallbackData("122"));
        rowsInline.add(rowInline4);

        rowInline5.add(new InlineKeyboardButton().setText("Общий отдел").setCallbackData("123"));
        rowInline5.add(new InlineKeyboardButton().setText("Последний контроль").setCallbackData("124"));
        rowInline5.add(new InlineKeyboardButton().setText("Юрист").setCallbackData("125"));
        rowsInline.add(rowInline5);

        rowInline6.add(new InlineKeyboardButton().setText("Мониторинг").setCallbackData("126"));
        rowInline6.add(new InlineKeyboardButton().setText("Коллектор").setCallbackData("127"));
        rowInline6.add(new InlineKeyboardButton().setText("АТБ").setCallbackData("128"));

        rowsInline.add(rowInline6);
        inlineKeyboardMarkup.setKeyboard(rowsInline);

        SendMessage sendMess = new SendMessage()
                .setChatId(chatid)
                .setText("Пожалуйста, выберите раздел");
        sendMess.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(sendMess);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    public void post_activestatus (Long chatid) {
        SendPhoto sendPhoto = new SendPhoto()
                .setChatId(chatid)
                .setPhoto("AgACAgIAAxkBAAIICV5KXlltJOl3CP_8p31FgHy_lS97AALlrjEb_qxRSjQS9CTA0x2tqHBcDwAEAQADAgADeAADKLYFAAEYBA")
                .setCaption("\nПоздравляю, вы зарегистрированы.\n Ждите одо1111111брения администратора\n");
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }





}
