package com.koitt.board.model;

import java.io.Serializable;

public class Schedule implements Serializable {

	private Integer schNo;
	private Integer movieNo;
	private String movietitle;
	private Integer scNo;
	private String scname;
	private Integer theNo;
	private String movieposter;
	private String schstarttime;

	public Schedule() {
	}

	public Schedule(Integer schNo, Integer movieNo, String movietitle, Integer scNo, String scname, Integer theNo,
			String movieposter, String schstarttime) {
		super();
		this.schNo = schNo;
		this.movieNo = movieNo;
		this.movietitle = movietitle;
		this.scNo = scNo;
		this.scname = scname;
		this.theNo = theNo;
		this.movieposter = movieposter;
		this.schstarttime = schstarttime;
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

	public String getScname() {
		return scname;
	}

	public void setScname(String scname) {
		this.scname = scname;
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

	public String getSchstarttime() {
		return schstarttime;
	}

	public void setSchstarttime(String schstarttime) {
		this.schstarttime = schstarttime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movieNo == null) ? 0 : movieNo.hashCode());
		result = prime * result + ((movieposter == null) ? 0 : movieposter.hashCode());
		result = prime * result + ((movietitle == null) ? 0 : movietitle.hashCode());
		result = prime * result + ((scNo == null) ? 0 : scNo.hashCode());
		result = prime * result + ((schNo == null) ? 0 : schNo.hashCode());
		result = prime * result + ((schstarttime == null) ? 0 : schstarttime.hashCode());
		result = prime * result + ((scname == null) ? 0 : scname.hashCode());
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
		if (schstarttime == null) {
			if (other.schstarttime != null)
				return false;
		} else if (!schstarttime.equals(other.schstarttime))
			return false;
		if (scname == null) {
			if (other.scname != null)
				return false;
		} else if (!scname.equals(other.scname))
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
		builder.append(", scname=");
		builder.append(scname);
		builder.append(", theNo=");
		builder.append(theNo);
		builder.append(", movieposter=");
		builder.append(movieposter);
		builder.append(", schstarttime=");
		builder.append(schstarttime);
		builder.append("]");
		return builder.toString();
	}

}
