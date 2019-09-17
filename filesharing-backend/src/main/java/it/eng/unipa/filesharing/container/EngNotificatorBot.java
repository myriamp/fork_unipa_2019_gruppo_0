package it.eng.unipa.filesharing.container;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.logging.Level;


public class EngNotificatorBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
            System.out.println(update.getMessage().getText());
            System.out.println(update.getMessage().getChatId());
    }

    @Override
    public String getBotUsername() {
        return "EngNotificatorBot";
    }

    @Override
    public String getBotToken() {
        return "813348701:AAEd-snzfUPZbASWRqj7hnKV6dUO-c36xv0";
    }


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new EngNotificatorBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
