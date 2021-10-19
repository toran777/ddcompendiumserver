package it.ddcompendium.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class Utils {
	public static String read(BufferedReader reader) throws IOException {
		StringBuilder string = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			string.append(line);
		}

		return string.toString();
	}
}
