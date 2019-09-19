package it.eng.unipa.filesharing.container;

import com.google.common.primitives.UnsignedInteger;
import it.eng.unipa.filesharing.service.TokenUtenteImpl;
import it.eng.unipa.filesharing.service.TokenUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.security.SecureRandom;

@Component
public class NotificationBot extends TelegramLongPollingBot {

    @Autowired
    TokenUtenteService tokenUtenteService ;


    public  synchronized void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();

        if (command.length() == 8 ){//se inserisce nella chat un messaggio di 8 caratteri
            //controlla che sia un token (valido)
            //il token viene generato in AddToken




        }

        else if (command.startsWith("/inseriscitoken")){

            String token = command.substring("/inseriscitoken".length(),command.length());
            token = token.replaceAll(" ","");
            message.setText("Il tuo ChatId : "+ update.getMessage().getChatId());
            boolean verifica = tokenUtenteService.verifyToken(update.getMessage().getText());

            if(verifica == true ){
                //ha trovato il token nella repository, aggiungiamo il chat id alla tabella
                String chatid = Long.toString(update.getMessage().getChatId());
                tokenUtenteService.insertChatID(chatid,update.getMessage().getText());
                message.setText("Da adesso hai accesso alle notifiche");

            }


        }

        else if (command.equals("/mostraidchat")){


            message.setText("Il tuo ChatId : "+ update.getMessage().getChatId());

        }

        else if (command.equals("/start")){
            message.setText("Ciao, inserisci usa il token come richiesto il Descrizione");

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
