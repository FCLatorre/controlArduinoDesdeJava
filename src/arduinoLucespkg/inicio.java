package arduinoLucespkg;

import java.util.List;

import org.zu.ardulink.Link;
import org.zu.ardulink.protocol.IProtocol;

public class inicio {
	public static void main (String[] args) {
		try {
			  Link link = Link.getDefaultInstance(); // 1

			  List<String> portList = link.getPortList(); // 2
			  if(portList != null && portList.size() > 0) {
			    String port = portList.get(0);
			    System.out.println("Connecting on port: " + port);
			    boolean connected = link.connect(port, 9600); // 3
			    System.out.println("Connected:" + connected);
			    Thread.sleep(2000); // 4
			    int power = IProtocol.HIGH;
			    while(true) {
			      System.out.println("Send power:" + power);
			      link.sendPowerPinSwitch(13, power); // 5
			      if(power == IProtocol.HIGH) {
			        power = IProtocol.LOW;
			      } else {
			        power = IProtocol.HIGH;
			      }
			      Thread.sleep(2000);
			    }
			  } else {
			    System.out.println("No port found!");
			  }

			} catch(Exception e) {
			  e.printStackTrace();
			}
	}
}
