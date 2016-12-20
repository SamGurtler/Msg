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
		String input = "Welcome!";
		String output = "Welcome!";
		switch(getInput("Type \"create\" to create a conversation or \"join\" to join one:").toLowerCase()){
			case "create":
				Server = new ServerSocket(Integer.parseInt(getInput("Port:")),5);
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
				break;
			case "join":
				socket = new Socket(InetAddress.getByName(getInput("Host IP:")),Integer.parseInt(getInput("Port:")));
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
}