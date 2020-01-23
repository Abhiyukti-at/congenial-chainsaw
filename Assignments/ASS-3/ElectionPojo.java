package set;

public class ElectionPojo implements Comparable<ElectionPojo> {
	private int id;
	private String name;
	private String eDate;
	private String District;
	private int constitu;

	@Override
	public String toString() {
		return "\nElectionPojo [id=" + id + ", name=" + name + ", eDate=" + eDate + ", District=" + District
				+ ", constitu=" + constitu + ", countDate=" + countDate + "]";
	}

	private int countDate;

	public ElectionPojo(int id, String name, String eDate, String district, int constitu, int countDate) {
		super();
		this.id = id;
		this.name = name;
		this.eDate = eDate;
		District = district;
		this.constitu = constitu;
		this.countDate = countDate;
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

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public int getConstitu() {
		return constitu;
	}

	public void setConstitu(int constitu) {
		this.constitu = constitu;
	}

	public int getCountDate() {
		return countDate;
	}

	public void setCountDate(int countDate) {
		this.countDate = countDate;
	}

	public int hashCode() {
		return name.hashCode();
	}

	public boolean equals(Object o) {
		boolean flag = false;
		if (o instanceof ElectDetail) {
			ElectionPojo e = (ElectionPojo) o;
			if (e.id == this.id)
				flag = true;
		}
		return flag;
	}

	@Override
	public int compareTo(ElectionPojo e1) {
		int r = this.getName().compareTo(e1.getName());
		return r;
	}
}
