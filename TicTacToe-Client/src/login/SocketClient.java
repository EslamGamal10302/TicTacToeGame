/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nada
 */
public class SocketClient {
    private Socket mySocket;
    private  DataInputStream dis ;
    private  PrintStream ps; 
    private boolean isInitialized;
    static private SocketClient socketClient ;
    static {
        socketClient = new SocketClient();
    }
    
    private SocketClient() {
       
            isInitialized=false;
       
    }
    
    static  public SocketClient getInstant(){
       return  socketClient;
    }
 
    public Socket getSocket() throws IOException {
        if(!isInitialized){
            mySocket = new Socket("127.0.0.1", 5005);
            System.out.println( "new Socket" );
            isInitialized = true;
          
        }

        return  mySocket;
    }
    public void CloseSocket() throws IOException{
       
            mySocket.close();
            isInitialized = false;
            System.out.println( "Socket is close" );
              
    }
    
}
