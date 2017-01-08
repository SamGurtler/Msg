import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class msg {
    private static ServerSocket Server = null;
    private static Socket socket = null; 
    private static String input = "Welcome!";
    private static String output = "Welcome!";
    public static void main(String[] args)throws UnknownHostException, IOException{
                int port = 0;
                String ip = "127.0.0.1";
                switch(args.length){
                    case 3: 
                        ip = args[2];
                    case 2:
                        port = Integer.parseInt(args[1]);
                    case 1:
                        switch(args[0]){
                            case "create":
				create(port);
                                break;
                            case "join":
				join(port,ip);
                                break;
                        }
                        break;
                    case 0:
                        switch(getInput("Type \"create\" to create a conversation or \"join\" to join one:").toLowerCase()){
                            case "create":
				create(Integer.parseInt(getInput("Port:")));
                                break;
                            case "join":
				join(Integer.parseInt(getInput("Port:")),getInput("Host IP:"));
                                break;
                        }
                        break;
                }
	}
	private static String getInput(String print){
        	Scanner scan = new Scanner(System.in);
			System.out.print(print);
			return scan.next();
        }
	private static String getInput2()throws IOException{
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		return is.readLine();
	}
        private static void create(int port) throws IOException{
            Server = new ServerSocket(port,5);
            System.out.print("Binding...");
            socket = Server.accept();
            System.out.println("Server has connected!");
            BufferedReader isc = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter osc = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){
                    if((input=isc.readLine())!= null){
                            System.out.println(input);
                    }
                    if(input.toLowerCase().contains("/exit"))break;
                    output = getInput2();
                    osc.println(output);
                    osc.flush();
                    if(output.toLowerCase().contains("/exit"))break;
            }
            isc.close();
            osc.close();
        }
        private static void join(int port,String ip) throws UnknownHostException, IOException{
            socket = new Socket(ip,port);
            System.out.println("Client has connected to Server!");
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true){
                    if(input.toLowerCase().contains("/exit"))break;
                    output = getInput2();
                    os.println(output);
                    os.flush();
                    if(output.toLowerCase().contains("/exit"))break;
                    if((input=is.readLine())!= null) {
                            System.out.println(input);
                    }
            }
            is.close();
            os.close();
        }
}