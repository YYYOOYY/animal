package data.animal;

public class AnimalHeader {
	int reqNo;
	String resultCode;
	String resultMsg;
	
	@Override
	public String toString() {
		return "Aheader [reqNo=" + reqNo + ", resultCode=" + resultCode + ", resultMsg=" + resultMsg + "]";
	}

	public int getReqNo() {
		return reqNo;
	}

	public void setReqNo(int reqNo) {
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
