import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class msg {
    public static void main(String[] args) throws UnknownHostException, IOException {
		ServerSocket Server = null;
		Socket socket = null; 
		String input,output = "Welcome!";
		switch(args[0].toLowerCase()){
			case "create":
				Server = new ServerSocket(Integer.parseInt(args[1]),5);
			 	System.out.print("Binding...");
			 	socket = Server.accept();
			 	System.out.println("Server has connected!");
			 	BufferedReader isc = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 	PrintWriter osc = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			 	while(output != null||!output.toLowerCase().contains("/exit")){
				 	if((input=isc.readLine())!= null){
						System.out.println(input);
				   	}
					output = getInput2();
					osc.println(output);
					osc.flush();
				}
				isc.close();
				osc.close();
			case "join":
				socket = new Socket(InetAddress.getByName(args[2]),Integer.parseInt(args[1]));
				System.out.println("Client has connected to Server!");
				BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				while(output != null||!output.toLowerCase().contains("/exit")){
					output = getInput2();
					os.println(output);
					os.flush();
					if((input=is.readLine())!= null) {
						System.out.println(input);
					}
				}
				is.close();
				os.close();
		}
	}
	private static String getInput(){
        	Scanner scan = new Scanner(System.in);
		return scan.next();
	} 
	private static String getInput2()throws IOException{
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		return is.readLine();
	}
}
