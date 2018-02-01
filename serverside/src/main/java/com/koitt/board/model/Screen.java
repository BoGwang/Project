package com.koitt.board.model;

import java.io.Serializable;

public class Screen implements Serializable {

	private Integer scNo;
	private Integer theNo;
	private String scName;
	private Integer movieNo;
	private Integer scLine;
	private Integer scSeat;

	public Screen() {
	}

	public Screen(Integer scNo, Integer theNo, String scName, Integer movieNo, Integer scLine, Integer scSeat) {
		super();
		this.scNo = scNo;
		this.theNo = theNo;
		this.scName = scName;
		this.movieNo = movieNo;
		this.scLine = scLine;
		this.scSeat = scSeat;
	}

	public Integer getScNo() {
		return scNo;
	}

	public void setScNo(Integer scNo) {
		this.scNo = scNo;
	}

	public Integer getTheNo() {
		return theNo;
	}

	public void setTheNo(Integer theNo) {
		this.theNo = theNo;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public Integer getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(Integer movieNo) {
		this.movieNo = movieNo;
	}

	public Integer getScLine() {
		return scLine;
	}

	public void setScLine(Integer scLine) {
		this.scLine = scLine;
	}

	public Integer getScSeat() {
		return scSeat;
	}

	public void setScSeat(Integer scSeat) {
		this.scSeat = scSeat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieNo == null) ? 0 : movieNo.hashCode());
		result = prime * result + ((scLine == null) ? 0 : scLine.hashCode());
		result = prime * result + ((scName == null) ? 0 : scName.hashCode());
		result = prime * result + ((scNo == null) ? 0 : scNo.hashCode());
		result = prime * result + ((scSeat == null) ? 0 : scSeat.hashCode());
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
		Screen other = (Screen) obj;
		if (movieNo == null) {
			if (other.movieNo != null)
				return false;
		} else if (!movieNo.equals(other.movieNo))
			return false;
		if (scLine == null) {
			if (other.scLine != null)
				return false;
		} else if (!scLine.equals(other.scLine))
			return false;
		if (scName == null) {
			if (other.scName != null)
				return false;
		} else if (!scName.equals(other.scName))
			return false;
		if (scNo == null) {
			if (other.scNo != null)
				return false;
		} else if (!scNo.equals(other.scNo))
			return false;
		if (scSeat == null) {
			if (other.scSeat != null)
				return false;
		} else if (!scSeat.equals(other.scSeat))
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
		builder.append("Screen [scNo=");
		builder.append(scNo);
		builder.append(", theNo=");
		builder.append(theNo);
		builder.append(", scName=");
		builder.append(scName);
		builder.append(", movieNo=");
		builder.append(movieNo);
		builder.append(", scLine=");
		builder.append(scLine);
		builder.append(", scSeat=");
		builder.append(scSeat);
		builder.append("]");
		return builder.toString();
	}

}
