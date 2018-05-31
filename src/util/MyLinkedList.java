package util;
/**
 * 泛型链表实现
 * @author Administrator
 *
 * 
 */

public class MyLinkedList<E> extends MyAbstractList<E> {
	private Node<E> head,tail;
	
	public MyLinkedList(){
		
	}
	
	public MyLinkedList(E[] objects){
		super(objects);
	}
	/**
	 * 获取第一个元素
	 * @return E
	 */
	public E getFirst(){
		if(size == 0){
			return null;
		}
		else{
			return head.element;
		}
	}
	/**
	 * 获取最后一个元素
	 * @return E
	 */
	public E getLast(){
		if(size==0)
			return null;
		else return tail.element;
	}
	/**
	 * 添加元素到表头
	 * @param e 插入的元素
	 */
	public void addFirst(E e){
		Node<E> newNode = new Node<E>(e);
		newNode.next = head;
		head = newNode;
		size++;
		
		if(tail == null)
			tail = head;
	}
	/**
	 * 添加元素到表尾
	 * size==0时创建结点，tail,head指向新节点
	 * @param e 插入的元素
	 */
	public void addLast(E e){
		Node<E> newNode= new Node<E>(e);
		if(tail==null){
			head = tail = newNode;
		}
		else{
			tail.next = newNode;
			tail = tail.next;
		}
		
		size++;
	}
	/**
	 * 添加元素到index位,index小于等于0,index==0是添加到表头,index小于等于size添加到表尾
	 */
	public void add(int index,E e){
		if(index == 0) addFirst(e);
		else if (index>=size) addLast(e);
		else{
			Node<E> current = head;
			for(int i = 1; i < index; i++)
				current = current.next;
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			(current.next).next = temp;
			size++;
		}
	}
	/**
	 * 删除表头
	 * size == 0时返回null
	 * @return E
	 */
	public E removeFirst(){
		if(size == 0) return null;
		else{
			Node<E> temp = head;
			head = head.next;
			if(size == 1)
				tail = null;
			size--;
			return temp.element;
		}
	}
	/**
	 * 删除表尾
	 * size==0时返回null
	 * @return E
	 */
	public E removeLast(){
		if(size == 0) return null;
		else if(size == 1)
		{
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		}
		else {
			Node<E> current = head;
			
			for(int i = 0; i < size-2; i++)
				current = current.next;
			
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}
	/**
	 * 删除指定位置
	 * index小于0||index大于等于size时,返回null
	 */
	public E remove(int index){
		if(index<0||index>=size) return null;
		else if(index == 0) return removeFirst();
		else if(index == size-1) return removeLast();
		else{
			Node<E> previous = head;
			
			for(int i = 1; i < index; i++){
				previous = previous.next;
			}
			
			Node<E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}
	/**
	 * 返回数组长度
	 * @return size
	 */
	public int length(){
		return size;
	}
	/**
	 * 打印数组内容
	 * @return string
	 */
	public String tostring(){
		StringBuffer result = new StringBuffer("[");
		
		Node<E> current = head;
		for(int i = 0; i < size; i++){
			result.append(current.element);
			current = current.next;
			if(current != null){
				result.append(", ");
			}
			else {
				result.append("]");
			}
		}
		
		return result.toString();
	}
	/**
	 * 清空数组
	 */
	public void clear(){
		head = tail = null;
		size = 0;
	}
	
	public boolean contains(E e) {
		System.out.println("Implementaton left as an exercise");
		return true;
	}
	/**
	 * 获取指定位置元素，返回E
	 * index小于0||index大于size时，返回null
	 */
	public E get(int index) {
		if(index<0||index>size) return null;
		if(index == 0) return head.element;
		Node<E> temp = head;
		for(int i = 0; i < index; i++)
			temp = temp.next;
		return temp.element;
	}
	
	public int indexOf(E e) {
		System.out.println("Not implementation");
		return 0;
	}
	
	public int lastIndexOf(E e){
		System.out.println("Not implementation");
		return 0;
	}
	
	public E set(int index,E e){
		System.out.println("Not implementation");
		return null;
	}
	
	@Override
	public int lastIndex(E e) {
		System.out.println("Not implementation");
		return 0;
	}
	/**
	 * 链表节点，元素有element,next,element为内容，next指向下一个结点
	 * @author Administrator
	 *
	 * @param <E>
	 */
	private static class Node<E>{
		E element;
		Node<E> next;
		
		public Node(E element){
			this.element = element;
		}
	}


}

