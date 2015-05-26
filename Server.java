import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    public void runServer() {
	ServerSocket socket = null;

	try {
	    socket = new ServerSocket(23456);
	}
	catch (IOException e) {
	    System.err.println("IOException");
	    return;
	}

	Socket client = null;
	PrintWriter out = null;
	BufferedReader in = null;
	try {
	    client = socket.accept();
	    out = new PrintWriter(client.getOutputStream(), true);
	    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}
	catch (IOException e) {
	    System.err.println("Input Output Exception");
	    return;
	}

	String input;
	try {
	    while((input = in.readLine()) != null) {
		out.println(input);
		if (input.equals("bye")) {
		    out.println("&&");
		    break;
		}
	    }
	    
	}
	catch (IOException e) {
	    System.err.println("Error");
	}

	try {
	    out.close();
	    in.close();
	    client.close();
	    socket.close();
	}
	catch (IOException e) {
	    System.err.println("error");
	}
    }
}