package com.youngzy.book.cleancode.appendix.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MessageUtils {

	public static String getMessage(Socket socket) throws IOException {
		InputStream in = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(in);
		return ois.readUTF();
	}

	public static void sendMessage(Socket socket, String msg) throws IOException {

		OutputStream out = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeUTF(msg);
		oos.flush();
	}

}
