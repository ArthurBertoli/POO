package view;

import java.awt.Component;
import java.time.YearMonth;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Scatter {
	
    private Map<YearMonth, Double> valoresMensais;

	public Scatter(Map<YearMonth, Double> valoresMensais) {
		this.setValoresMensais(valoresMensais);
    	
    	double[] valoresMensais1 = new double[12];
  
        for (int i = 0; i < 12; i++) {
            String valorStr = JOptionPane.showInputDialog("Insira o valor para o mês " + (i + 1));
            try {
                valoresMensais1[i] = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira um valor válido para o mês " + (i + 1));
                i--;
            }
        }

        XYSeries series = new XYSeries("Valor Total Mensal");

        for (int mes = 1; mes <= 12; mes++) {
            series.add(mes, valoresMensais1[mes - 1]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createScatterPlot("Valor Total Mensal", "Mês", "Valor Total", dataset,PlotOrientation.VERTICAL, true, true, false); 
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        JFrame frame = new JFrame("Gráfico de Dispersão Anual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
	}
    

	public Map<YearMonth, Double> getValoresMensais() {
		return valoresMensais;
	}

	public void setValoresMensais(Map<YearMonth, Double> valoresMensais) {
		this.valoresMensais = valoresMensais;
	}


	public Component getChartPanel() {
		return null;
	}
}

