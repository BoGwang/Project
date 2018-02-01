package com.koitt.board.model;

import java.io.Serializable;

public class Schedule implements Serializable {

	private Integer schNo;
	private Integer movieNo;
	private String movietitle;
	private Integer scNo;
	private String scName;
	private Integer theNo;
	private String movieposter;

	public Schedule() {
	}

	public Schedule(Integer schNo, Integer movieNo, String movietitle, Integer scNo, String scName, Integer theNo,
			String movieposter) {
		super();
		this.schNo = schNo;
		this.movieNo = movieNo;
		this.movietitle = movietitle;
		this.scNo = scNo;
		this.scName = scName;
		this.theNo = theNo;
		this.movieposter = movieposter;
	}

	public Integer getSchNo() {
		return schNo;
	}

	public void setSchNo(Integer schNo) {
		this.schNo = schNo;
	}

	public Integer getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(Integer movieNo) {
		this.movieNo = movieNo;
	}

	public String getMovietitle() {
		return movietitle;
	}

	public void setMovietitle(String movietitle) {
		this.movietitle = movietitle;
	}

	public Integer getScNo() {
		return scNo;
	}

	public void setScNo(Integer scNo) {
		this.scNo = scNo;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public Integer getTheNo() {
		return theNo;
	}

	public void setTheNo(Integer theNo) {
		this.theNo = theNo;
	}

	public String getMovieposter() {
		return movieposter;
	}

	public void setMovieposter(String movieposter) {
		this.movieposter = movieposter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieNo == null) ? 0 : movieNo.hashCode());
		result = prime * result + ((movieposter == null) ? 0 : movieposter.hashCode());
		result = prime * result + ((movietitle == null) ? 0 : movietitle.hashCode());
		result = prime * result + ((scName == null) ? 0 : scName.hashCode());
		result = prime * result + ((scNo == null) ? 0 : scNo.hashCode());
		result = prime * result + ((schNo == null) ? 0 : schNo.hashCode());
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
		Schedule other = (Schedule) obj;
		if (movieNo == null) {
			if (other.movieNo != null)
				return false;
		} else if (!movieNo.equals(other.movieNo))
			return false;
		if (movieposter == null) {
			if (other.movieposter != null)
				return false;
		} else if (!movieposter.equals(other.movieposter))
			return false;
		if (movietitle == null) {
			if (other.movietitle != null)
				return false;
		} else if (!movietitle.equals(other.movietitle))
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
		if (schNo == null) {
			if (other.schNo != null)
				return false;
		} else if (!schNo.equals(other.schNo))
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
		builder.append("Schedule [schNo=");
		builder.append(schNo);
		builder.append(", movieNo=");
		builder.append(movieNo);
		builder.append(", movietitle=");
		builder.append(movietitle);
		builder.append(", scNo=");
		builder.append(scNo);
		builder.append(", scName=");
		builder.append(scName);
		builder.append(", theNo=");
		builder.append(theNo);
		builder.append(", movieposter=");
		builder.append(movieposter);
		builder.append("]");
		return builder.toString();
	}

}
