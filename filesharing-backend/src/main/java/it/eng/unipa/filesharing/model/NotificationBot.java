package it.eng.unipa.filesharing.container;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.security.SecureRandom;

public class NotificationBot extends TelegramLongPollingBot {
    SecureRandom random = new SecureRandom();
    public  synchronized void onUpdateReceived(Update update) {
        //System.out.println(update.getMessage().getText() +"  " + update.getMessage().getChatId());
        //System.out.println(update.getMessage().getChatId());

        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();

        if (command.equals("/token")){

            long longToken = Math.abs(random.nextLong());
            String random = Long.toString(longToken,16);
            message.setText("Ti sto generando un Token Accesso "+ random );

        }

        else if (command.equals("/mostraidchat")){


            message.setText("Il tuo ChatId : "+ update.getMessage().getChatId());

        }

        else{
            message.setText("Mi Dispice non posso aiutarti, controllare lista comandi abilitati");
        }

        message.setChatId(update.getMessage().getChatId());

        try{
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }



        /*if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(chatId);
            String text = message.getText();
            response.setText(text);
            try {
                execute(response);
                logger.info("Sent message \"{}\" to {}", text, chatId);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
            }
        } */


    //@Override
    public String getBotUsername(){
        return "ProjNotificationBot";
    }

    //@Override
    public String getBotToken(){
        return "808828078:AAG2p1SYuC6Hg9PSl5ReBz8jFYU1RbWku-s";
    }


    public static void main(String[] args){
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new NotificationBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
