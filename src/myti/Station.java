package myti;

import constants.StringConstants;

public class Station {

	private int type;
	private String name;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		String result = this.getName() + StringConstants.COMMA + this.getType(); 				
		return result;		
	}
	
}
