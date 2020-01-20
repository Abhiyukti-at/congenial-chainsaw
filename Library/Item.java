
package Library;

import java.io.*;

public abstract class Item {
	public Item(int identity_no, String title, int noC) {
		super();
		Identity_no = identity_no;
		this.title = title;
		NoC = noC;
	}

	
	private int Identity_no;
	private String title;
	private int NoC;

	public int getIdentity_no() {
		return Identity_no;
	}

	public void setIdentity_no(int identity_no) {
		Identity_no = identity_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNoC() {
		return NoC;
	}

	public void setNoC(int noC) {
		NoC = noC;
	}

	public abstract void display();
};
