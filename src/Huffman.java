import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Huffman {
	int R = 256;

	void compress(String source) {
		String dest = source.replace(".txt", ".bin");

		File fileSource = new File(source);
		File fileDest = new File(dest);

		String data = readFile(fileSource);
		Writer writer = new Writer(fileDest);
		char[] input = data.toCharArray();
		int freq[] = new int[R];
		for (int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}

		Node root = buildTree(freq);

		String[] st = new String[R];
		writeCode(st, root, "");

		writer.write(freq);

		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				writer.write(code.charAt(j) == '1');
			}
		}
		writer.close();
	}

	void writeCode(String[] st, Node root, String s) {
		if (!root.isLeaf()) {
			writeCode(st, root.left, s + '0');
			writeCode(st, root.right, s + '1');
		} else {
			st[root.data] = s;
		}
	}

	Node buildTree(int[] freq) {
		PriorityQueue pq = new PriorityQueue();
		for (char i = 0; i < R; i++) {
			if (freq[i] > 0) {
				pq.add(new Node(i, freq[i], null, null));
			}
		}
		while (pq.size > 1) {
			Node left = pq.pull();
			Node right = pq.pull();
			Node parent = new Node('\0', left.freq + right.freq, left, right);
			pq.add(parent);
		}
		return pq.pull();
	}

	void readFreq(Node root) {
		if (root != null) {
			readFreq(root.left);
			System.out.println(root.freq);
			readFreq(root.right);
		}
	}

	String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tmp = reader.readLine();
			while (tmp != null) {
				sb.append(tmp);
				tmp = reader.readLine();
				if (tmp != null) {
					sb.append("\r\n");
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	void decompress(String source) {
		String dest = source.replace(".bin", ".txt");

		File fileSource = new File(source);
		File fileDest = new File(dest);

		Reader reader = new Reader(fileSource);
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(fileDest));
		} catch (IOException e) {
		}

		int[] header = reader.readHeader();
		Node root = buildTree(header);

		int length = 0;

		for (int i = 0; i < header.length; i++) {
			length += header[i];
		}

		reader.fillBuffer();

		for (int i = 0; i < length; i++) {
			Node x = root;
			while (!x.isLeaf()) {
				boolean bit = reader.readBoolean();
				if (bit) {
					x = x.right;
				} else {
					x = x.left;
				}
			}
			try {
				writer.append(x.data);
			} catch (IOException e) {
			}
		}
		try {
			writer.close();
		} catch (IOException e) {

		}
		reader.close();
	}

}
