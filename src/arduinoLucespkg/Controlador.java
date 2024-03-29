package arduinoLucespkg;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Controlador {
	// Controlador siguiendo el patr�n singleton
	private static final Controlador INSTANCE = new Controlador();
	
	public static Date horaInicio;
	
	public static long getHoraInicio(){
		return horaInicio.getTime();
	}

	private Controlador() {
	}

	public static Controlador getInstance() {
		return INSTANCE;
	}

	// Arduino siguiendo el patr�n singleton
	private static PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();

	public static PanamaHitek_Arduino getArduino() {
		return arduino;
	}
	
	private static Vista view;
	
	public static Vista getView(){
		return view;
	}
	
	public static void setView(Vista vista){
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
	
	public void modificarConfiguracion(int umbralInferior, int umbralSuperior, int tiempoR�fagas){
		String data = "DATA:%"+Integer.toString(umbralInferior)+"%"+Integer.toString(umbralSuperior)+ "%"+Integer.toString(tiempoR�fagas);
		try{
			getArduino().sendData(data);
		}
		catch (SerialPortException | ArduinoException ex) {
		}
	}

	private static final SerialPortEventListener listener = new SerialPortEventListener() {
		@Override
		public void serialEvent(SerialPortEvent event) {
			try {
				if (getArduino().isMessageAvailable()) {
					String mensaje = getArduino().printMessage();
					String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
					long milisegundos = new Date().getTime()- getHoraInicio();
					long segundos = milisegundos/1000;
					if(mensaje.startsWith("MSG:")){
						mensaje = mensaje.split(":")[1];
						if(mensaje.indexOf("%")== -1){
							//Mensaje del modo autom�tico
							getView().addNewRow(timeStamp, mensaje, "AUTO", "AUTO");
						} else {
							//Mensaje de cambio de modo
							String [] aux = mensaje.split("%");
							String modo1 = aux[1];
							String modo2 = aux[2];
							getView().addNewRow(timeStamp, "Cambio modo",modo1, modo2);
						}
						
						
					} else if(mensaje.startsWith("STAT:")){
						String [] aux = mensaje.split("%");
						int valorDetectado = Integer.parseInt(aux[1]);
						int umbralSuperior = Integer.parseInt(aux[2]);
						int umbralInferior = Integer.parseInt(aux[3]);
						getView().addNewData(valorDetectado, umbralSuperior, umbralInferior, segundos);
					}
				}
			} catch (SerialPortException | ArduinoException ex) {
			}
		}
	};

	public static void main(String[] args) {
		horaInicio = new Date();
		try {
			// Iniciar una conexi�n con la placa Arduino
			getArduino().arduinoRXTX("COM3", 9600, listener);
			// El controlador llama a la vista para que se renderize
			Controlador controlador = getInstance();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Vista frame = new Vista(controlador);
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
