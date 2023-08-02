package data.sido;

public class SidoItem {
	String orgCd;
	String orgdownNm;
	
	@Override
	public String toString() {
		return "item [orgCd=" + orgCd + ", orgdownNm=" + orgdownNm + "]";
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgdownNm() {
		return orgdownNm;
	}
	public void setOrgdownNm(String orgdownNm) {
		this.orgdownNm = orgdownNm;
	}
	
	
}
