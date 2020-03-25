//Client Side Vibe Back End
//Incomplete, yet to add server inputs
import java.io.*;
import java.net.*;
public class Client
{
    static int r;
    Client()
    {
        r=0;
    }
    public static void main(String args[])throws IOException
    {
        //Initializations
        String question="";
        int vote=0;
        int num=0;
        String content="";
        String responses[]=null;
        String answer="";

        //Client Starts
        System.out.println("Client Started");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter IP Address");
        String ip = in.readLine();
        Socket s = new Socket(ip,4444);
        String ServerIn="";
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        System.out.println("Enter Your Username");
        String username = in.readLine();
        //Send Username to Server
        while(true)
        {
            ServerIn = din.readUTF();
            content = ServerIn.substring(6);
            if(ServerIn.equals("[VTCL]"))
            {
                //SubPhase A: Enter Answer To Question
                //question=
                answer = enterStuff(question);
                dout.writeUTF(answer);
            }
            else if(((ServerIn.substring(0,3)).equals("[VT")) && (!((ServerIn.substring(3,5)).equals("CL"))))
            {
                //SubPhase B: Vote For Answer
                num=Integer.parseInt((ServerIn.substring(3,5)));
                vote=voting(responses,num);
                dout.writeUTF(Integer.toString(vote));

            }
            if(ServerIn.equals("QSTN"))
            {

            }
        }


    }
    static String enterStuff(String question)throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(question);
        String UserAns = in.readLine();
        return UserAns; // send user input back
    }
    static int voting(String resp[],int num)throws IOException
    {
        //user sent answers go in resp array, sent by the server
        displayAnswers(resp);
        BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
        while(true)
        {
            System.out.println("Enter Answer Number:");
            int opt = Integer.parseInt(in.readLine());
            if((opt>0) && (opt<=num))
            {
                return (opt - 1);
            }
            else
            {
                System.out.println("Please Enter A Valid Option");
            }
        }
    }
    static void displayAnswers(String x[])
    {
        //beautify later
        int i;
        for(i=0;i<x.length;i++)
        {
            System.out.println((i+1)+" "+x[i]);
        }

    }
}
