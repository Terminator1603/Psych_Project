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
        String vote="";
        int num=0;
        String content="";
        String responses[]=null;
        String answer="";
        String lboard="";

        //Client Starts
        System.out.println("Client Started");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter IP Address");
        String ip = in.readLine();
        Socket s = new Socket(ip,4444);
        String ServerIn="";
        String tag;
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        System.out.println("Enter Your Username");
        String username = in.readLine();
        dout.writeUTF(username);
        while(true)
        {
            ServerIn = din.readUTF();
            tag=ServerIn.substring(0,6);
            content = ServerIn.substring(6);
            if(tag.equals("[VTCL]"))
            {

            }
            else if(((ServerIn.substring(0,3)).equals("[VT")) && (!((ServerIn.substring(3,5)).equals("CL"))))
            {
                //SubPhase B: Vote For Answer
                num = Integer.parseInt((ServerIn.substring(3,5)));
                responses = convArray(getMessage(ServerIn),num);
                vote = voting(responses,num);
                dout.writeUTF(vote);

            }
            if(tag.equals("[QSTN]"))
            {
                //SubPhase A: Enter Answer To Question
                question = getMessage(ServerIn);
                answer = enterStuff(question);
                dout.writeUTF(answer);
            }
            if(tag.equals("[ENDG]"))
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
    static String voting(String response[],int num)throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        displayAnswers(response);
        int v=0;
        mloop:while(true)
        {
            System.out.println("Pick an answer:");
            v = Integer.parseInt(in.readLine());
            if((v>0) && (v<=num))
            {
                break mloop;
            }
            else
            {
                System.out.println("Please Enter A Valid Option");
            }
        }
        String vote = response[v-1].substring(0,1);
        return vote;
    }
    static void displayAnswers(String x[])
    {
        //beautify later
        int i;
        for(i=0;i<x.length;i++)
        {
            System.out.println((i+1)+" "+(x[i].substring(1)));
        }

    }
    static String getMessage(String ip)
    {
        String message = ip.substring(6);
        return message;

    }
    static String[] convArray(String p,int n)
    {
        //3hello 2seventy 1sdd
        int i;
        String el="";
        String a[]=new String[n];
        int k=0;
        p=p.trim();
        p=p+" 0";
        char ch;
        for(i=0;i<p.length();i++)
        {
            ch=p.charAt(i);
            if(Character.isDigit(ch))
            {
                if(!el.equals(""))
                {
                    el=el.trim();
                    a[k] = el;
                    k++;
                }
                el="";
                el=el+ch;

            }
            else
            {
                el = el + ch;
            }
        }
        return a;
    }
}
