package com.work.model.dto;

/**
 * <pre>
 * 영화정보 도메인 클래스 
 *	1. 영화제목
 *	2. 연령제한
 *	3. 줄거리
 *	4. 상영시간
 * </pre>
 * @author 이태규
 * @version ver.1.0
 * @since jdk1.8
 */
public class Movie {
	/** 영화제목, 식별키 */
	private String title;
	
	/** 연령제한, 필수, 전체관람가("00") / 12세 관람가("12") / 15세 관람가 ("15") / 청소년 관람불가("19") */
	private String movieAge;
	
	/** 줄거리, 필수 */
	private String summary;
	
	/** 상영시간, 필수 */
	private String runningTime;
	
	
	/**
	 * <pre>
	 * 기본생성자
	 * </pre>
	 */
	public Movie() {}

	/**
	 * <pre>
	 * 영화 전체 데이터 초기화 생성자
	 * </pre>
	 * @param title 영화제목
	 * @param movieAge 연령제한
	 * @param summary 줄거리
	 * @param runningTime 상영시간
	 */
	public Movie(String title, String movieAge, String summary, String runningTime) {
		this.title = title;
		this.movieAge = movieAge;
		this.summary = summary;
		this.runningTime = runningTime;
	}

	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the movieAge
	 */
	public String getMovieAge() {
		return movieAge;
	}

	/**
	 * @param movieAge the movieAge to set
	 */
	public void setMovieAge(String movieAge) {
		this.movieAge = movieAge;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the runningTime
	 */
	public String getRunningTime() {
		return runningTime;
	}

	/**
	 * @param runningTime the runningTime to set
	 */
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie other = (Movie) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(title);
		builder.append(", ");
		builder.append(movieAge);
		builder.append(", ");
		builder.append(summary);
		builder.append(", ");
		builder.append(runningTime);
		return builder.toString();
	}
	
}
