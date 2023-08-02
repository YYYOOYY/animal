package data.sido;

public class SidoHeader {
	long reqNo;
	String resultCode;
	String resultMsg;
	
	@Override
	public String toString() {
		return "header [reqNo=" + reqNo + ", resultCode=" + resultCode + ", resultMsg=" + resultMsg + "]";
	}

	public long getReqNo() {
		return reqNo;
	}

	public void setReqNo(long reqNo) {
		this.reqNo = reqNo;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	
}
