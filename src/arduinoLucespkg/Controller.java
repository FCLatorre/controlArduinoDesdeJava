package arduinoLucespkg;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private static SwingMain view;
	
	public static SwingMain getView(){
		return view;
	}
	
	public static void setView(SwingMain vista){
		view= vista;
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
					String mensaje = getArduino().printMessage();
					String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
					if(mensaje.startsWith("MSG:")){
						mensaje = mensaje.split(":")[1];
						if(mensaje.indexOf("%")== -1){
							//Mensaje del modo automático
							System.out.println("PC:" + mensaje);
						} else {
							//Mensaje de cambio de modo
							String [] aux = mensaje.split("%");
							String modo1 = aux[1];
							String modo2 = aux[2];
							getView().addNewRow(modo1, modo2, timeStamp);
						}
						
						
					} else if(mensaje.startsWith("STAT:")){
						String [] aux = mensaje.split("%");
						int valorDetectado = Integer.parseInt(aux[1]);
						int umbralSuperior = Integer.parseInt(aux[2]);
						int umbralInferior = Integer.parseInt(aux[3]);
						getView().addNewData(valorDetectado, umbralSuperior, umbralInferior, timeStamp);
					}
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
						controlador.setView(frame);
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
