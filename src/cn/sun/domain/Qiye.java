package cn.sun.domain;

import java.sql.Timestamp;
import com.vividsolutions.jts.geom.Point; 
import com.vividsolutions.jts.geom.Geometry; 

/**
 * TQyb entity. @author MyEclipse Persistence Tools
 */

public class Qiye  implements java.io.Serializable{

	// Fields
	private User user; // 标准创建用户 （关联用户表）
	private String qyId;
	private String qyYhbh;
	private String name;
	private String qyxz;
	private String zcdz;
	private String frdb;
	private String szd;
	private String sshy;
	private String qyjj;
	private String ywly;
	private Integer qyLlcs;
	private Double qyWdz;
	private Double qyJdz;
	private String geoWkt;
	public String getGeoWkt() {
		return geoWkt;
	}

	public void setGeoWkt(String geoWkt) {
		this.geoWkt = geoWkt;
	}

	private Geometry  qyGeo;
	public Geometry getQyGeo() {
		return qyGeo;
	}

	public void setQyGeo(Geometry qyGeo) {
		this.qyGeo = qyGeo;
	}

	private String qyShzt="0";
	private String qyBz;
	private String qyCzr;
	private Timestamp qyLrsj;
	private Timestamp qyXgsj;
	private String qyScbj="0";
	private String qyYlzd1;
	private String qyYlzd2;

	// Constructors

	/** default constructor */
	public Qiye() {
	}
	

	/** minimal constructor */
	public Qiye(String qyId) {
		this.qyId = qyId;
	}

	/** full constructor */
	public Qiye(User user,String qyId, String qyYhbh, String name, String qyxz,
			String zcdz, String frdb, String szd, String sshy,
			String qyjj, String ywly, Integer qyLlcs, Double qyWdz,
			Double qyJdz, String qyShzt, String qyBz, String qyCzr,
			Timestamp qyLrsj, Timestamp qyXgsj, String qyScbj, String qyYlzd1,
			String qyYlzd2) {
		this.user=user;
		this.qyId = qyId;
		this.qyYhbh = qyYhbh;
		this.name = name;
		this.qyxz = qyxz;
		this.zcdz = zcdz;
		this.frdb = frdb;
		this.szd = szd;
		this.sshy = sshy;
		this.qyjj = qyjj;
		this.ywly = ywly;
		this.qyLlcs = qyLlcs;
		this.qyWdz = qyWdz;
		this.qyJdz = qyJdz;
		this.qyShzt = qyShzt;
		this.qyBz = qyBz;
		this.qyCzr = qyCzr;
		this.qyLrsj = qyLrsj;
		this.qyXgsj = qyXgsj;
		this.qyScbj = qyScbj;
		this.qyYlzd1 = qyYlzd1;
		this.qyYlzd2 = qyYlzd2;
	}

	// Property accessors

	public String getQyId() {
		return this.qyId;
	}

	public void setQyId(String qyId) {
		this.qyId = qyId;
	}

	public String getQyYhbh() {
		return this.qyYhbh;
	}

	public void setQyYhbh(String qyYhbh) {
		this.qyYhbh = qyYhbh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQyxz() {
		return qyxz;
	}

	public void setQyxz(String qyxz) {
		this.qyxz = qyxz;
	}

	public String getZcdz() {
		return zcdz;
	}

	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}

	public String getFrdb() {
		return frdb;
	}

	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}

	public String getSzd() {
		return szd;
	}

	public void setSzd(String szd) {
		this.szd = szd;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getQyjj() {
		return qyjj;
	}

	public void setQyjj(String qyjj) {
		this.qyjj = qyjj;
	}

	public String getYwly() {
		return ywly;
	}

	public void setYwly(String ywly) {
		this.ywly = ywly;
	}








	public Integer getQyLlcs() {
		return this.qyLlcs;
	}

	public void setQyLlcs(Integer qyLlcs) {
		this.qyLlcs = qyLlcs;
	}

	public Double getQyWdz() {
		return this.qyWdz;
	}

	public void setQyWdz(Double qyWdz) {
		this.qyWdz = qyWdz;
	}

	public Double getQyJdz() {
		return this.qyJdz;
	}

	public void setQyJdz(Double qyJdz) {
		this.qyJdz = qyJdz;
	}

	public String getQyShzt() {
		return this.qyShzt;
	}

	public void setQyShzt(String qyShzt) {
		this.qyShzt = qyShzt;
	}

	public String getQyBz() {
		return this.qyBz;
	}

	public void setQyBz(String qyBz) {
		this.qyBz = qyBz;
	}

	public String getQyCzr() {
		return this.qyCzr;
	}

	public void setQyCzr(String qyCzr) {
		this.qyCzr = qyCzr;
	}

	public Timestamp getQyLrsj() {
		return this.qyLrsj;
	}

	public void setQyLrsj(Timestamp qyLrsj) {
		this.qyLrsj = qyLrsj;
	}

	public Timestamp getQyXgsj() {
		return this.qyXgsj;
	}

	public void setQyXgsj(Timestamp qyXgsj) {
		this.qyXgsj = qyXgsj;
	}

	public String getQyScbj() {
		return this.qyScbj;
	}

	public void setQyScbj(String qyScbj) {
		this.qyScbj = qyScbj;
	}

	public String getQyYlzd1() {
		return this.qyYlzd1;
	}

	public void setQyYlzd1(String qyYlzd1) {
		this.qyYlzd1 = qyYlzd1;
	}

	public String getQyYlzd2() {
		return this.qyYlzd2;
	}

	public void setQyYlzd2(String qyYlzd2) {
		this.qyYlzd2 = qyYlzd2;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}



}