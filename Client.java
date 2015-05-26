import java.util.*;
import java.io.*;
import java.net.*;

public class Client {
    private final String serverAddress = "127.0.0.1"; // IP of the server to connect to
    private final int port = 23456; // Port of the server to connect to, pick an unused port, a high one is usually unused, note, this has to be below 65535

    public boolean runClient() { // This is the 'main' function of this client -- to be called by the ClientRun class
	Socket serverSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	try {
	    serverSocket = new Socket(serverAddress, port);
	    out = new PrintWriter(serverSocket.getOutputStream(), true);
	    in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
	}
	catch (UnknownHostException e) {
	    System.err.println("Unknown Host Exception");
	    return false;
	}
	catch (IOException e) {
	    System.err.println("Input Output Exception");
	    return false;
	}

	Scanner console = new Scanner(System.in);

	String input = "";

	System.out.print("input: ");
	while ((input = console.nextLine()) != null) {
	    if (!input.equals("&&")) {
		out.println(input);
	    }
	    else {
		break;
	    }
	    try {
		System.out.println("Server: " + in.readLine());
	    }
	    catch (IOException e) {
		System.err.println("IOException");
	    }
	    System.out.print("input: ");
	}

	try {
	    out.close();
	    in.close();
	    console.close();
	    serverSocket.close();
	}
	catch (IOException e) {
	    System.err.println("Error IO");
	}
	return true;
    }
}