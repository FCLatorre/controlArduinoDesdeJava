package arduinoLucespkg;

import java.awt.Color;

import javax.swing.JFrame;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica {
	public XYSeries observaciones= new XYSeries("Datos observados");
	
	public XYSeries umbralSuperior = new XYSeries("Umbral superior");
	
	public XYSeries umbralInferior = new XYSeries("Umbral inferior");
	
	public JFreeChart grafica;
	
	public ChartPanel getGrafica(){
		return new ChartPanel(this.grafica);
	}
	
	public void addNewData(int valorDetectado, int umbralSuperior, int umbralInferior, long milisegundos){
		System.out.println("PC:"+ milisegundos +" Valores recibidos: Detectado:" + valorDetectado
				+" Superior:"+umbralSuperior+" Inferior:"+umbralInferior);
		this.observaciones.add(milisegundos, valorDetectado);
		this.umbralSuperior.add(milisegundos, umbralSuperior);
		this.umbralInferior.add(milisegundos, umbralInferior);
	}
	
	public Grafica(){
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(this.observaciones);
		dataset.addSeries(this.umbralSuperior);
		dataset.addSeries(this.umbralInferior);
		
		this.grafica = ChartFactory.createXYLineChart("Sensor Arduino", "Tiempo (ms)", "Intensidad detectada (mA)", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		this.grafica.getPlot().setBackgroundPaint(new Color(255,255,255));
	}
}
