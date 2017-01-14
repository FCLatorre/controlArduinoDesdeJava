package arduinoLucespkg;

import java.awt.EventQueue;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Controller {
	// Controlador siguiendo el patrón singleton
	private static final Controller INSTANCE = new Controller();

	private Controller() {
	}

	public static Controller getInstance() {
		return INSTANCE;
	}

	// Arduino siguiendo el patrón singleton
	private static PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();

	public static PanamaHitek_Arduino getArduino() {
		return arduino;
	}

	public void actionFor(Estados buttonPressed) {
		try {
			switch (buttonPressed) {
			case OFF:
				getArduino().sendData("OFF");
				break;
			case AUTO:
				getArduino().sendData("AUTO");
				break;
			case POSICION:
				getArduino().sendData("POSICION");
				break;
			case CRUCE:
				getArduino().sendData("CRUCE");
				break;
			case CARRETERA:
				getArduino().sendData("CARRETERA");
				break;
			case RAFAGA:
				getArduino().sendData("RAFAGA");
				break;
			}
		} catch (SerialPortException | ArduinoException ex) {
		}
	}

	private static final SerialPortEventListener listener = new SerialPortEventListener() {
		@Override
		public void serialEvent(SerialPortEvent event) {
			try {
				if (getArduino().isMessageAvailable()) {
					System.out.println(getArduino().printMessage());
				}
			} catch (SerialPortException | ArduinoException ex) {
			}
		}
	};

	public static void main(String[] args) {
		try {
			// Iniciar una conexión con la placa Arduino
			getArduino().arduinoRXTX("COM3", 9600, listener);
			// El controlador llama a la vista para que se renderize
			Controller controlador = getInstance();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SwingMain frame = new SwingMain(controlador);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		} catch (ArduinoException e) {
			e.printStackTrace();
		}
	}
}
