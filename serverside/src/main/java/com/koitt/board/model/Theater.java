package com.koitt.board.model;

import java.io.Serializable;

public class Theater implements Serializable {

	private Integer theNo;
	private String theName;

	public Theater() {
	}

	public Theater(Integer theNo, String theName) {
		super();
		this.theNo = theNo;
		this.theName = theName;
	}

	public Integer getTheNo() {
		return theNo;
	}

	public void setTheNo(Integer theNo) {
		this.theNo = theNo;
	}

	public String getTheName() {
		return theName;
	}

	public void setTheName(String theName) {
		this.theName = theName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((theName == null) ? 0 : theName.hashCode());
		result = prime * result + ((theNo == null) ? 0 : theNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theater other = (Theater) obj;
		if (theName == null) {
			if (other.theName != null)
				return false;
		} else if (!theName.equals(other.theName))
			return false;
		if (theNo == null) {
			if (other.theNo != null)
				return false;
		} else if (!theNo.equals(other.theNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Theater [theNo=");
		builder.append(theNo);
		builder.append(", theName=");
		builder.append(theName);
		builder.append("]");
		return builder.toString();
	}

}
