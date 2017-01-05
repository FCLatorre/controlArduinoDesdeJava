package arduinoLucespkg;

import java.awt.EventQueue;
import java.util.List;

import org.zu.ardulink.Link;
import org.zu.ardulink.protocol.IProtocol;

public class inicio {
	private static void lightOn(Link link){
	      link.sendPowerPinSwitch(13, IProtocol.HIGH); // 5
	} 
	
	private static void lightOff(Link link){
	      link.sendPowerPinSwitch(13, IProtocol.LOW); // 5
	}
	
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
			    
			    //Call the interface from SwingMain
			    EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							SwingMain frame = new SwingMain(link);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			    
			  } else {
			    System.out.println("No port found!");
			  }

			} catch(Exception e) {
			  e.printStackTrace();
			}
	}
}
