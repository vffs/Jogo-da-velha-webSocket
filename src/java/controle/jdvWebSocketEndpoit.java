
package controle;

import java.io.IOException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author valeria
 */
 @ServerEndpoint("/jogoVelha")
public class jdvWebSocketEndpoit {
    
    private static Session SJogadorX;
    private static Session SJogadorO;
    private static JogoVelha tabuleiro;
    
    @OnOpen
    public void onOpen(Session session)throws IOException{
        if(SJogadorX == null){
           SJogadorX = session;
           SJogadorX.getBasicRemote().sendText("{\"type\": 0, \"jogador\": 0}");
        }
        else if(SJogadorO == null){
            tabuleiro = new JogoVelha();
            SJogadorO = session;
            SJogadorO.getBasicRemote().sendText("{\"type\": 0, \"jogador\": 1}");
            enviarMensagem("{\"type\": 1, \"tabuleiro\": " + tabuleiro + ", \"turn\": 0}");
        }
        else{
            session.close();
        }
    }
    
    private void enviarMensagem(String mensagem)throws IOException{
        SJogadorX.getBasicRemote().sendText(mensagem);
        SJogadorO.getBasicRemote().sendText(mensagem);
    }
    
    @OnMessage
    public void messege(Session session, String message) throws JSONException,IOException{
        int linhaDestino = Integer.parseInt(message.split("_")[0]);
        int colunaDestino = Integer.parseInt(message.split("_")[1]);
        
        System.out.println("Linha -> " + linhaDestino);
        System.out.println("Coluna -> " + colunaDestino);
         
        tabuleiro.inserirPeca(session == SJogadorX ? JogoVelha.jogadorX : JogoVelha.jogadorO, linhaDestino, colunaDestino);
        tabuleiro.printTabuleiro(); 
        enviarMensagem("{\"type\": 1, \"tabuleiro\": " + tabuleiro + ", \"turn\": " + tabuleiro.getJogadorVez() + "}");
        int resultado = tabuleiro.verificarVencedor();
        if(resultado == 1){
            enviarMensagem("{\"type\": 2, \"message\": \"Fim de Jogo. O Jogador X ganhou o Jogo.\"}");
            SJogadorX.close();
            SJogadorO.close();
        }
        if (resultado == 2) {
            enviarMensagem("{\"type\": 2, \"message\": \"Fim de Jogo. O Jogador O ganhou o Jogo.\"}");
            SJogadorX.close();
            SJogadorO.close();
        }
    }
}
