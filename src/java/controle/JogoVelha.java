package controle;

import java.util.Arrays;

/**
 *
 * @author valeria
 */

public class JogoVelha {
    
    private int jogadorVez = 0;
    private final int [][] tabuleiro = {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
     };
    
    public static int jogadorX = 1;
    public static int jogadorO = 2;
   
    public int getJogadorVez(){
        return jogadorVez;
    }
    
    private boolean jogadorEhX(int x, int y){
        return tabuleiro[x][y] == jogadorX;
    }
    
    public int verificarVencedor(){
        int [][] vencedor = new int[3][3];
        int temp = 0;        
        if((tabuleiro[0][0] == tabuleiro[0][1] && tabuleiro[0][0] == tabuleiro[0][2]) || (tabuleiro[0][0] == tabuleiro[1][0] && tabuleiro[0][0] == tabuleiro[2][0]) && tabuleiro[0][0] != 0){
           System.out.println("if  verifica vencedor 0");

            vencedor[0][0] = tabuleiro[0][0];
            System.out.println(vencedor[0][0]);
            temp = vencedor[0][0];
        }
        else if((tabuleiro[1][1] == tabuleiro[1][0] && tabuleiro[1][1] == tabuleiro[1][2]) || (tabuleiro[1][1] == tabuleiro[0][1] && tabuleiro[1][1] == tabuleiro[2][1]) && tabuleiro[1][1] != 0){
            System.out.println("if  verifica vencedor 1");
            vencedor[1][1] = tabuleiro[1][1];
            System.out.println(vencedor[1][1]);
            temp = vencedor[1][1];
        }
        else if((tabuleiro[2][2] == tabuleiro[2][0] && tabuleiro[2][2] == tabuleiro[2][1]) || (tabuleiro[2][2] == tabuleiro[0][2] && tabuleiro[2][2] == tabuleiro[1][2]) && tabuleiro[2][2] != 0){
           System.out.println("if  verifica vencedor 2");
            vencedor[2][2] = tabuleiro[2][2];
            System.out.println(vencedor[2][2]);
            temp = vencedor[2][2];
        }else if((tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[2][2] == tabuleiro[0][0]) || (tabuleiro[2][0] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[1][1]) && tabuleiro[1][1] != 0){
           System.out.println("if  verifica vencedor 2");
            vencedor[1][1] = tabuleiro[1][1];
            System.out.println(vencedor[1][1]);
            temp = vencedor[1][1];
        }
        return temp;
    }
    
    public boolean inserirPeca(int jogador,int l, int c){
      if((jogador == jogadorO && (jogador == jogadorX || jogadorVez == 0)) || (jogador == jogadorX && (jogador == jogadorO || jogadorVez == 1))){
            System.out.println("Um");
            return false;
        }
        
        /* a celula está vazia?*/
        if (tabuleiro[l][c] != 0) {
            System.out.println("dois");
            return false;
        }
        
        boolean ok ;
        
        /* se for jogador X */
        if(jogador == jogadorX){
            tabuleiro[l][c] = 1;
            ok = true;
        } /*se for jogador O */
        else{
            tabuleiro[l][c] = 2;
            ok = true;
        }
       
        if(ok){
            jogadorVez = (jogadorVez + 1) % 2 ;
        }
        
        return ok;
    }
    
    public void printTabuleiro() {
        for (int[] linha : tabuleiro) {
            System.out.println(Arrays.toString(linha));
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(tabuleiro);
    }
    
    public static void main(String [] args){
        JogoVelha  jv = new JogoVelha();
     
        /*jv.inserirPeca(jogadorX, 0 ,2);
        jv.inserirPeca(jogadorO, 0, 0);
        jv.inserirPeca(jogadorX, 2, 1);
        jv.inserirPeca(jogadorO, 2, 2);
        jv.inserirPeca(jogadorX, 1, 1);
        jv.inserirPeca(jogadorO, 2, 0);
        jv.inserirPeca(jogadorX, 0, 1);*/
        /*jv.inserirPeca(jogadorX, 0, 1);
        jv.inserirPeca(jogadorO, 1, 0);
        jv.inserirPeca(jogadorX, 0, 2);
        jv.inserirPeca(jogadorO, 1, 2);
        jv.inserirPeca(jogadorX, 0, 0);*/
        
        jv.inserirPeca(jogadorX, 0, 0);
        jv.inserirPeca(jogadorO, 1, 0);
        jv.inserirPeca(jogadorX, 1, 1);
        jv.inserirPeca(jogadorO, 1, 2);
        jv.inserirPeca(jogadorX, 2, 2);
        //System.out.println(jv.verificarVencedor(jv.tabuleiro));
        jv.verificarVencedor(); 
        jv.printTabuleiro();
    }
    
    
}
