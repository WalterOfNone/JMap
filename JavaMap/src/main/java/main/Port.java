package main;

public final class Port {
	private final int port;
	private final boolean open;

	public Port(int port, boolean open) {
		this.port = port;
		this.open = open;
	}

	public int getPort() {
		return port;
	}

	public boolean isOpen() {
		return open;
	}

}
