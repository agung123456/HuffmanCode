public class PriorityQueue {

	class Queue {
		Node data;
		Queue next, prev;

		public Queue(Node data) {
			this.data = data;
		}
	}

	Queue head;
	int size = 0;

	void add(Node node) {

		Queue data = new Queue(node);

		if (head == null) {
			head = data;
		} else {
			Queue current = head;
			while (current.data.freq <= data.data.freq && current.next != null) {
				current = current.next;
			}
			if (current.next == null && current.data.freq <= data.data.freq) {
				current.next = data;
				data.prev = current;
			} else if (current.prev == null) {
				data.next = current;
				current.prev = data;
				head = data;
			} else {
				data.next = current;
				data.prev = current.prev;
				current.prev = data;
				data.prev.next = data;
			}
		}
		size++;
	}

	Node pull() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Queue data = head;
			head = null;
			size--;
			return data.data;
		} else {
			Queue data = head;
			head = head.next;
			head.prev = null;
			data.next = null;
			size--;
			return data.data;
		}
	}

	void display() {
		Queue current = head;
		while (current != null) {
			System.out.print(current.data.freq + "-"+(int)current.data.data+" ");
			current = current.next;
		}
		System.out.println("\n--------------------------------");
	}
}
