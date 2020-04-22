public class Bid {
	final public String name;
	final public int bid;

	public Bid(String name, int bid) {
		this.name = name;
		this.bid = bid;
	}

	public int hashCode() {
		return 1 + 23*bid + 31*name.hashCode();
	}

	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Bid)) return false;

		Bid bid = (Bid) obj;
		return bid.name.equals(name) && (this.bid == bid.bid);
	}
	
	public String toString() {
		return String.format("<Bid: %d by %s>", bid, name);
	}

	// quick and dirty test
//	public static void main(String[] args) {
//		Bid a = new Bid("Alice", 50);
//		Bid copy_a = new Bid("Alice", 50);
//		Bid b = new Bid("Bob", 50);
//
//		System.out.println(a.toString());
//		System.out.println(b);
//		System.out.println(a.equals(copy_a));
//		System.out.println(a.equals(a));
//		System.out.println(a.equals(b));
//	}
}

