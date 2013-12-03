import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Reader {
	ObjectInput input;
	int buffer, N;

	public Reader(File file) {
		try {
			input = new ObjectInputStream(new FileInputStream(file));
		} catch (IOException e) {
		}
	}

	public void close() {
		try {
			input.close();
		} catch (IOException e) {
		}
	}
	
	public int readInt() {
		int data = 0;
		try {
			data = input.read();
		} catch (IOException e) {

		}
		return data;
	}

	public int[] readHeader() {
		int[] header = new int[256];
		try {
			for (int i = 0; i < header.length; i++) {
				header[i] = input.readInt();
			}
		} catch (IOException e) {
		}
		return header;
	}

	public void fillBuffer() {
		try {
			buffer = input.read();
			N = 8;
		} catch (IOException e) {
			buffer = -1;
			N = -1;
		}
	}

	public boolean readBoolean() {
		if (buffer != -1) {
			N--;
			boolean bit = ((buffer >> N) & 1) == 1;
			if (N == 0) {
				fillBuffer();
			}
			return bit;
		} else {
			throw new RuntimeException("Data Habis");
		}
	}
}
