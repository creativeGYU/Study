package com.work.model.dto;

/**
 * * <pre>
 * 회원의 고객정보 도메인 클래스 
 * ## 회원 검증조건
 *	1. 아이디 : 8자리 ~ 20자리 이내
 *	2. 비밀번호 : 10자리 ~ 20자리 이내
 *	3. 마일리지 : 0 ~ 100,000
 *	4. 등급 : G, VIP, VVIP, A
 *	 문자열 비교 매서드
 *	 String
 *	 equals(Object anObject) : boolean
 * 
 *	1. 아이디
 *	2. 비밀번호
 *	3. 이름
 *	4. 생일
 *	5. 휴대폰 
 *	6. 가입일
 *	7. 등급
 *	8. 마일리지
 *	9. 소지금
 * </pre>
 * @author 이태규
 * @version ver.1.0
 * @since jdk1.8
 */
public class Member {
	/** 아이디, 식별키 */
	private String Id;
	
	/** 비밀번호, 필수 */
	private String pw;
	
	/** 이름, 필수 */
	private String name;
	
	/** 생일, 필수, 형식 2021-06-04 */
	private String birth;
	
	/** 휴대폰, 필수, 형식 010-1234-5678 */
	private String mobile;
	
	/** 휴대폰, 필수, 형식 010-1234-5678 */
	private String email;
	
	/** 가입일, 필수 , 형식 2021-05-26, 시스템 현재날짜 제공 */
	private String entryDate;
	
	/** 등급, 필수, 일반회원(G), VIP회원(VIP), VVIP회원(VVIP), 관리자(A), 시스템 제공 */
	private String grade;
	
	/** 마일리지, 필수, 시스템 제공 */
	private int mileage;
	
	/** 소지금, 시스템 제공 */
	private int money;
	
	
	/**
	 * <pre>
	 * 기본생성자
	 * </pre>
	 */
	public Member() {	
		
	}
	
	/**
	 * <pre>
	 * 사용자 입력데이터 초기화 생성자
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @param name 이름
	 * @param birth 생일
	 * @param mobile 휴대폰
	 * @param email 이메일
	 */
	public Member(String Id, String pw, String name, String birth, String mobile, String email) {
		this.Id = Id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.mobile = mobile;
		this.email = email;
	}

	/**
	 * <pre>
	 * 회원 전체 데이터 초기화 생성자
	 * </pre>
	 * @param Id 아이디
	 * @param pw 비밀번호
	 * @param name 이름
	 * @param birth 생일
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @param entryDate 가입일
	 * @param grade 등급
	 * @param mileage 마일리지
	 * @param money 소지금
	 */
	public Member(String Id, String pw, String name, String birth, String mobile, String email, String entryDate, String grade, int mileage,
			int money) {
		this(Id, pw, name, birth, mobile, email);
		this.entryDate = entryDate;
		this.grade = grade;
		this.mileage = mileage;
		this.money = money;
	}

	/**
	 * @return the Id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * <pre>
	 * 아이디 검증규칙 메서드
	 * - 최소 8자리 ~ 최대 20자리 이내
	 * </pre>
	 * @param Id the Id to set
	 */
	public void setId(String Id) {
		boolean result = isId(Id);
		
		if (result) {
			this.Id = Id;
		} else {
			System.out.println("[오류] 아이디는 8 ~ 20자리 이내로 입력하세요.");
		}
	}
	
	/**
	 * <pre>
	 * 아이디 검증 메서드
	 * 검증규칙 : 길이 8자리 ~ 20자리
	 * </pre>
	 * @param Id 아이디
	 * @return 아이디길이가 8 ~ 20자리 이내면 true, 그렇지 않으면 false
	 */
	private boolean isId(String Id) {
		if (Id.length() >= 8 && Id.length() <= 20) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return the pw
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * <pre>
	 * 비밀번호 검증규칙 메서드
	 * - 최소 6자리 ~ 최대 20자리 이내
	 * </pre>
	 * @param pw the pw to set
	 */
	public void setPw(String pw) {
		if (isPw(pw)) {
			this.pw = pw;
			return;
		}
		System.out.println("[오류] 비밀번호는 10 ~ 20자리 이내로 입력하세요.");
	}
	
	/**
	 * <pre>
	 * 비밀번호 검증 메서드
	 * 검증규칙 : 길이 10자리 ~ 20자리
	 * </pre>
	 * @param pw 비밀번호
	 * @return 비밀번호길이가 10 ~ 20자리 이내면 true, 그렇지 않으면 false
	 */
	private boolean isPw(String pw) {
		int length = pw.length();
		
		if (length >= 10 && length <= 20) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * <pre>
	 * 등급 검증규칙
	 * - G, VIP, VVIP, A
	 * </pre>
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		if (isGrade(grade)) {
		this.grade = grade;
		} else {
			System.out.println("[오류] 등급은 'G' or 'VIP' or 'VVIP' or 'A'만 입력하세요.");
		}
	}
	
	/**
	 * <pre>
	 * 등급 검증 메서드
	 * - 검증규칙 : G, VIP, VVIP, A
	 * </pre>
	 * @param grade
	 * @return 등급이 "G", "VIP", "VVIP", "A"면 true, 아니면 false
	 */
	private boolean isGrade(String grade) {
		switch(grade) {
		case "G" : 
		case "VIP" : 
		case "VVIP" : 
		case "A" :
			return true;
		default :
			return false;
		}
	}

	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * <pre>
	 * 마일리지 검증규칙 메서드
	 * - 마일리지 범위 0 ~ 100,000
	 * </pre>
	 * @param mileage the mileage to set
	 */
	public void setMileage(int mileage) {
		if(isMileage(mileage)) {
			this.mileage = mileage;
		} else {
			System.out.println("[오류] 마일리지는 0 ~ 100,000 값만 입력하세요.");
		}
	}
	
	/**
	 * <pre>
	 * 마일리지 검증 메서드
	 * - 마일리지 범위 0 ~ 100,000
	 * </pre>
	 * @param mileage 마일리지
	 * @return 마일리지 값이 0 ~ 100,000 이면 true, 그렇지 않으면 fasle
	 */
	private boolean isMileage(int mileage) {
		if (mileage >= 0 && mileage <= 100000) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Member other = (Member) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(Id);
		builder.append(", ");
		builder.append(pw);
		builder.append(", ");
		builder.append(name);
		builder.append(", ");
		builder.append(birth);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(email);
		builder.append(", ");
		builder.append(entryDate);
		builder.append(", ");
		builder.append(grade);
		builder.append(", ");
		builder.append(mileage);
		builder.append(", ");
		builder.append(money);
		return builder.toString();
	}

	
}
