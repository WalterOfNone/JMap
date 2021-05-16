package main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.Future;

public class tester {
	private ArrayList<Integer> openports = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		//System.out.println("Started JMap");
		//Net test = new Net();
		//test.setIp("10.0.0.0");
		//test.portScan(1000);
		//test.serviceScan();
		GUI main = new GUI();
		main.GUInit();
		main.GUIview();
	}

}
