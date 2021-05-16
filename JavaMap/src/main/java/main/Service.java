package main;

public class Service {
	private int port;
	private String protocol;
	private String state;
	private String service;
	private String reason;
	private String product;
	private String version;
	private String info;

	public Service(int port, String protocol, String state, String service, String reason, String product, String version, String info) {
		this.port = port;
		this.protocol = protocol;
		this.state = state;
		this.service = service;
		this.reason = reason;
		this.product = product;
		this.version = version;
		this.info = info;
	}

	public int getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getState() {
		return state;
	}

	public String getService() {
		return service;
	}

	public String getReason() {
		return reason;
	}

	public String getProduct() {
		return product;
	}

	public String getVersion() {
		return version;
	}

	public String getInfo() {
		return info;
	}
	
	@Override
	public String toString() {
		return getPort()+" "+getProduct();
	}

}
