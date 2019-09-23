package it.eng.unipa.filesharing.telegram;

import it.eng.unipa.filesharing.service.TokenUtenteService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class NotificationBot extends TelegramLongPollingBot {

    @Autowired
    TokenUtenteService tokenUtenteService ;

    public static Logger LOGGER = LoggerFactory.getLogger(NotificationBot.class);


    public  synchronized void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();

        if (command.startsWith("/token")){

            String token = command.substring("/token".length(),command.length());
            token = token.replaceAll(" ","");
            message.setText("Il tuo ChatId : "+ update.getMessage().getChatId());
                  //ha trovato il token nella repository, aggiungiamo il chat id alla tabella
                String chatid = Long.toString(update.getMessage().getChatId());
                String email = tokenUtenteService.verifyToken(token,chatid);
                if(email != null){
                    message.setText("Da adesso hai accesso alle notifiche per l'utenza: "+email);
                }else{
                    message.setText("Token inserito non valido");
                }



        }



        else if (command.equals("/start")){
            message.setText("Ciao, inserisci il token come richiesto in descrizione");

        }

        else{
            message.setText("Mi dispiace, non posso aiutarti, controlla la lista dei comandi abilitati");
        }

        message.setChatId(update.getMessage().getChatId());

        try{
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }

    }

    public void pushMessage(String messaggio, List<String> emails){

        List<String> chatIds = tokenUtenteService.getChatsId(emails);
        for(String chatId : chatIds) {
            SendMessage response = new SendMessage();
            response.setText(messaggio);
            response.setChatId(chatId);
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
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

    @PostConstruct
    public void start(){
        LOGGER.info("Sono il bot username:{} token:{}",getBotUsername(),getBotToken());
    }


}
