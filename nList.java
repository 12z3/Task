package dynamicsStructure.Node;

import java.util.Iterator;

public class nList<T> {
	private Node<T> top;
	private Node<T> end;
	int size;

	static class Node<T> {
		private T element;
		Node<T> next;

		Node(T element) {
			this.element = element;
		}

		public T getElement() {
			return this.element;
		}
	}

	nList() {
		this.top = null;
		size = 0;
	}
	/*
	1. this.end.next = newNode;
	Тази линия прави следното:

	Възелът this.end е последният възел в списъка в момента.
	this.end.next е полето, което сочи към следващия възел в списъка.
	С тази команда ние свързваме последния възел в списъка (който е представен от this.end) с новия възел (newNode),
	като правим така, че next полето на this.end да сочи към newNode.
	2. this.end = newNode;
	Тази линия прави следното:

	След като сме свързали предишния последен възел със новия възел, трябва да актуализираме this.end,
	за да сочи към новия последен възел в списъка.
	С тази команда казваме, че новият възел (newNode) вече е последният възел в списъка.
	 */

	public T add(T element) {
		Node<T> newNode = new Node<>(element);           // newNode.getElement() = 5, newNode.next = null
		if (this.top == null) {
			this.top = newNode;                          // thi.top.getElement() =  5, this.top.next = null
			this.end = newNode;                          // thi.end.getElement() =  5, this.end.next = null
		} else {
			//System.out.println(this.end.getElement());   // 5 ->  null
			//System.out.println(this.end.next);           // this.end.next -> адреса на NewNode в heap-a
			this.end.next = newNode;                     // 5 -> 10 -> null. ! newNode.next -> null
			//System.out.println(this.end.next);
			this.end = newNode;
			//System.out.println(this.end.next);
			//System.out.println(this.end.getElement());
		}
		this.size++;
		return newNode.getElement();
	}

	/*
	this.end.next - Node{this.end.next.next също е валидно} който е указател за това кой е следващият Node по веригата.
	this.end - също е Node. И като такъв и при него е достъпно this.end.next.
	 */

	/*
	Node  e Obejct. Като такъв може да се каже, че използваш this.end.next = newNod; в смисъл:
	Че, нода this.end.next "сочи" или препраща към newNode в памета, той е указател към следващият.
	this.end също сочи към този адрес.
	this.end.next -> към адреса и приема стойността на newNode.
	this.end също -> адреса и приема стоинистта на newNod;

	this.end -> текущият Node с ел = 1 при добавянето на 2. this.end.next = newNode: this.end.next -> newNode (2), но
	this.end -> Node със елемент 1.
	След this.end = newNode: this.end.next -> newNode със стойност 2.
	 */

	public nList<T> addN(T element) {
		Node<T> newNod = new Node<>(element);
		if (this.top == null) {
			this.top = newNod;
		} else {
			this.end.next = newNod;
		}
		this.end = newNod;
		size++;
		return this;
	}

	protected void print() {
		Node<T> tmp = this.top;
		while (tmp != null) {
			System.out.println(tmp.getElement());
			tmp = tmp.next;
		}
	}


	protected void printInfo() {
		Node<T> node = this.top;
		while (node != null) {
			if (node.next != null) {
				System.out.print(
						node.getElement() + " -> " + node.next + " : " + node.next.getElement() + " -> ");
				if (node.next.next != null) {
					System.out.println(node.next.next.getElement());
				}
			}
			node = node.next;
		}
	}

	public static void main(String[] args) {
		nList<Integer> list = new nList<>();
		list.add(5);
		list.add(10);
		list.add(11);
		list.add(12);


		nList<Integer> nList = new nList<>();
		nList.addN(1)
				.addN(2)
				.addN(3)
				.addN(4);
		nList.print();

		list.printInfo();
		System.out.println("\nsize = " + list.size);
	}
}
