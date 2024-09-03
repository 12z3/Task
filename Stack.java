package dynamicsStructure.Node.Deque;

import java.util.EmptyStackException;

public class Stack<T> {
	Node<T> top;
	Node<T> end;
	int size;

	static class Node<T> {
		T data;
		Node<T> previous;
		Node<T> next;

		Node(T data) {
			this.data = data;
		}

		public T getEl() {
			return this.data;
		}
	}

	Stack() {
		this.top = null;
		this.end = null;
	}


	public void addFirst(T element) {                            //   3 -> <- 2 -> <- 1     1 -> 2 ->  3
		Node<T> newNode = new Node<>(element);
		if (this.top != null) {
			this.top.previous = newNode;
			this.top.previous.next = this.top;
			this.top = newNode;
		} else {
			this.top = newNode;
			this.end = newNode;
		}
		size++;
	}

	public T pop() {
		if (this.top == null) {
			throw new EmptyStackException();
		}

		T tmp = this.top.getEl();
		this.top = this.top.next;

		if (this.top != null) {
			this.top.previous = null;             // Актуализиране на връзката към предишния възел
		} else {
			this.end = null;                      // Ако стекът е празен, актуализирайте end
		}

		this.size--;
		return tmp;
	}

	public T popOld() {
		T tmp;
		if (this.top != null) {
			tmp = this.top.getEl();
			this.top = this.top.next;
			this.top.previous = null;
			this.size--;
			return tmp;
		} else {
			throw new EmptyStackException();
		}
	}

	public Stack<T> chainAddFirst(T element) {
		Node<T> newNode = new Node<>(element);
		if (this.top != null) {
			this.top.previous = newNode;
			this.top.previous.next = this.top;
			this.top = newNode;
		} else {
			this.top = newNode;
			this.end = newNode;
		}
		size++;
		return this;
	}

	public void endAdd(T element) {
		Node<T> newNode = new Node<>(element);
		if (this.end != null) {
			this.end.next = newNode;
			this.end = newNode;
		} else {
			this.end = newNode;
			this.top = newNode;
		}
	}

	public void printStack() {
		Node<T> itrNode = this.top;
		while (itrNode != null) {
			System.out.print(itrNode.getEl() + " ");
			itrNode = itrNode.next;
		}
		System.out.println();
	}

	public boolean isEmpty() {
		return this.top == null;
		//return this.size == 0;
	}

	public int getSize() {
		return this.size;
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.chainAddFirst(1)
				.chainAddFirst(2)
				.chainAddFirst(3)
				.chainAddFirst(5);

		stack.printStack();
		System.out.println(stack.getSize());

		System.out.println(stack.pop());

		stack.printStack();
		System.out.println(stack.getSize());
	}
}
