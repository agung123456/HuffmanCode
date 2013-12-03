import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Writer {
	ObjectOutput writer;
	int buffer = 0, N = 0;

	public Writer(File file) {
		try {
			writer = new ObjectOutputStream(new FileOutputStream(file));
		} catch (IOException e) {
		}
	}

	public void write(int value) {
		try {
			writer.write(value);
		} catch (IOException e) {
		}
	}

	public void writeHeader(int value) {
		try {
			writer.writeInt(value);
		} catch (IOException e) {
		}
	}

	public void write(boolean value) {

		buffer <<= 1;
		if (value)
			buffer |= 1;

		N++;
		if (N == 8) {
			clearBuffer();
		}
	}

	void clearBuffer() {
		if (N == 0)
			return;
		if (N > 0)
			buffer <<= (8 - N);

		write(buffer);
		N = 0;
		buffer = 0;
	}

	public void write(int[] value) {
		for (int i = 0; i < value.length; i++) {
			writeHeader(value[i]);
		}
	}

	public void close() {
		clearBuffer();
		try {
			writer.close();
		} catch (IOException e) {

		}
	}
}
