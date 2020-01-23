package set;

public class Author {
	private int id;
	private String name;
	private String Address;
	public Author(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		Address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "\nAuthor [id=" + id + ", name=" + name + ", Address=" + Address + "]";
	}
	
}
