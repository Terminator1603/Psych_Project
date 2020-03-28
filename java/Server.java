package Psych_Server;

import java.io.*;
import java.net.*;
import java.util.*;
class Server{
    public static void main(String[] args) {
        if (args.length < 1) return;
        ArrayList answers = new ArrayList<String>();
        int port = Integer.parseInt(args[0]);
        Player p = new Player();
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                DataInputStream in = new DataInputStream(socket.getInputStream());
                OutputStream output = socket.getOutputStream();
                DataOutputStream out = new DataOutputStream(output);
                PrintWriter writer = new PrintWriter(output, true);
                String text;
                while(true){
                    text = in.readUTF();
                    if(text.endsWith("//")){
                        p.username = text.substring(0,(text.length()-3));
                    }
                    if(text.startsWith("[ANS]")){
                        answers.add(text);
                        out.writeUTF("[VT01]");
                    }
                    //System.out.println("a");
                    out.writeUTF("[QSTN]What is your name?");
                    //System.out.println("b");
                    
                    if(text.equals("bye")){
                        break;
                    }
                }
                socket.close();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    static void seperateInput(String text,Player p){
        if(text.endsWith("//")){
            p.username = text.substring(0,(text.length()-3));
        }
        /*if(text.startsWith("[VTCL]")){
        answers.add(text.substring(6));
        }*/
    }
}