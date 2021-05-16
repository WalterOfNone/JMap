package main;

//I am suing whoever created this many packages for me to misutilize
//TODO: FIX THE FUTURES PORT BEFORE ANYTHING ELSE
//Add other service probes
//Add OS detection
//Add cli intepreter for methods
//slap gui interface on it
//add export options
//Branding
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Net {
	private String ip;
	private ArrayList<Port> allports = new ArrayList<>();
	private ArrayList<Port> openports = new ArrayList<>();
	private ArrayList<Service> services = new ArrayList<>();
	private ArrayList<String> profiles = new ArrayList<>();

	// Returns port and prints out discovered port
	public Future<Boolean> isOpen(final ExecutorService executorService, final int port) throws IOException {
		final String finalip = ip;
		return executorService.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(finalip, port), 300);
					socket.close();
					System.out.println("Discovered open port " + port + "/tcp on " + finalip);
					allports.add(new Port(port, true));
					return true;
				} catch (IOException e) {
					allports.add(new Port(port, false));
					return false;
				}
			}
		});
	}

	// Returns ports open and prints out
	public void portScan(final int ports) throws IOException {
		Ping(ip);
		// creates X amount of threads to asyncronously go through the ports
		System.out.println("Initiating DNS Resolution of 1 host");
		System.out.println("Scanning " + getHostName() + " (" + ip + ") [" + ports + " ports]");
		long start = System.currentTimeMillis();
		final ExecutorService executorService = Executors.newFixedThreadPool(200);
		for (int i = 1; i <= ports; i++) {
			isOpen(executorService, i);
		}
		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException ex) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("Completed TCP Scan at " + getTime() + ", " + (double) timeElapsed / 1000 + "s elapsed");
		for (Port currport : allports) {
			if (currport.isOpen()) {
				openports.add(currport);
			}
		}

	}

	// returns service along with printing out findings
	public Service GET(int port) throws InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://" + ip + ":" + port)).build();
		HttpResponse<String> response;
		String service = "";
		String version = "";
		String info = "";
		try {
			response = client.send(request, BodyHandlers.ofString());
			HttpHeaders headers = response.headers();
			int status = response.statusCode();
			Map<String, List<String>> newmap = headers.map();
			Set<String> headername = newmap.keySet();

			if (newmap.containsKey("server")) {
				service = headers.allValues("server").get(0);
			}
			if (newmap.containsKey("version")) {
				version = headers.allValues("version").get(0);
			}
			if (newmap.containsKey("content-type")) {
				info = headers.allValues("content-type").get(0);
			}
			System.out.println(port + "/tcp open  http    " + service);
			System.out.println("|  GETRequest:");
			System.out.println("|     Status code: " + status);
			for (String header : headername) {
				System.out.println("|     " + header + ": " + newmap.get(header));
			}

		} catch (IOException e) {
			System.out.println("Connection refused");
			return null;
		}

		return new Service(port, "tcp", "open", "http", "tcp-handshake", service, version, info);
	}

	public Service FTP(int port) {

		return null;
	}

	public Service SSH(int port) {
		return null;
	}

	public void serviceScan() throws InterruptedException {
		System.out.println("Starting service scan at "+getTime());
		System.out.println("PORT   STATE SERVICE VERSION");
		for (Port currentport : openports) {
			Service get = GET(currentport.getPort());
			if (get != null) {
				services.add(get);
			}
		}
		System.out.println(services.toString());
	}

	public void Ping(String ipv4) {
		InetAddress addr;
		try {
			addr = InetAddress.getByName(ipv4);
			long currentTime = System.currentTimeMillis();
			boolean isreachable = addr.isReachable(200);
			currentTime = System.currentTimeMillis() - currentTime;
			if (isreachable) {
			System.out.println("Jmap scan report for " + ipv4);
			System.out.println("Host is up (" +(double)currentTime/1000+"s latency)");
			}
		} catch (UnknownHostException e) {
			
		} catch (IOException e) {
			
		}
	}

	public String getHostName() throws UnknownHostException {
		InetAddress addr = InetAddress.getByName(ip);
		return addr.getCanonicalHostName();
	}

	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(cal.getTime());
	}
	
	public ArrayList<String> getProfiles(){
		profiles.add("test");
		return profiles;
	}
	
	

}