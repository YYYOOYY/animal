package data.sido;

public class SidoResponse {
	SidoHeader header;
	SidoBody body;
	
	@Override
	public String toString() {
		return "Response [header=" + header + ", body=" + body + "]";
	}

	public SidoHeader getHeader() {
		return header;
	}

	public void setHeader(SidoHeader header) {
		this.header = header;
	}

	public SidoBody getBody() {
		return body;
	}

	public void setBody(SidoBody body) {
		this.body = body;
	}
	
	
}
