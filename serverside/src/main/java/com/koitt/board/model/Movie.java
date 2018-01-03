package com.koitt.board.model;

import java.sql.Date;

// JAVA Bean (VO, DTO)
public class Movie {
	
	private Integer mno;  // 영화 번호
	private String mtitle; // 영화 제목
	private String director; // 영화 감독
	private String mcontent;  // 영화 줄거리
	private String mposter; // 영화 포스터/ 사진
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Movie() {}

	//////////////////////////////////////////////////////////////////////////////////////
	public Movie(Integer mno, String mtitle, String director, String mcontent, String mposter) {
		this.mno = mno;
		this.mtitle = mtitle;
		this.director = director;
		this.mcontent = mcontent;
		this.mposter = mposter;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public String getMtitle() {
		return mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMcontent() {
		return mcontent;
	}

	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}

	public String getMposter() {
		return mposter;
	}

	public void setMposter(String mposter) {
		this.mposter = mposter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((mcontent == null) ? 0 : mcontent.hashCode());
		result = prime * result + ((mno == null) ? 0 : mno.hashCode());
		result = prime * result + ((mposter == null) ? 0 : mposter.hashCode());
		result = prime * result + ((mtitle == null) ? 0 : mtitle.hashCode());
		return result;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (mcontent == null) {
			if (other.mcontent != null)
				return false;
		} else if (!mcontent.equals(other.mcontent))
			return false;
		if (mno == null) {
			if (other.mno != null)
				return false;
		} else if (!mno.equals(other.mno))
			return false;
		if (mposter == null) {
			if (other.mposter != null)
				return false;
		} else if (!mposter.equals(other.mposter))
			return false;
		if (mtitle == null) {
			if (other.mtitle != null)
				return false;
		} else if (!mtitle.equals(other.mtitle))
			return false;
		return true;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Movie [mno=");
		builder.append(mno);
		builder.append(", mtitle=");
		builder.append(mtitle);
		builder.append(", director=");
		builder.append(director);
		builder.append(", mcontent=");
		builder.append(mcontent);
		builder.append(", mposter=");
		builder.append(mposter);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	


}