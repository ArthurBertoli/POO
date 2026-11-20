package view;

import java.time.YearMonth;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Gráfico de dispersão "anual": total gasto em cada mês (1 a 12), com uma série por ano,
 * calculado a partir dos gastos cadastrados.
 */
public class Scatter {

    private Map<YearMonth, Double> valoresMensais;
    private final ChartPanel chartPanel;

    public Scatter(Map<YearMonth, Double> valoresMensais) {
        this.setValoresMensais(valoresMensais);

        XYSeriesCollection dataset = new XYSeriesCollection();
        Map<Integer, XYSeries> seriesPorAno = new TreeMap<>();
        for (Map.Entry<YearMonth, Double> entrada : new TreeMap<>(valoresMensais).entrySet()) {
            int ano = entrada.getKey().getYear();
            XYSeries serie = seriesPorAno.get(ano);
            if (serie == null) {
                serie = new XYSeries("Total mensal de " + ano);
                seriesPorAno.put(ano, serie);
            }
            serie.add(entrada.getKey().getMonthValue(), entrada.getValue());
        }
        for (XYSeries serie : seriesPorAno.values()) {
            dataset.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createScatterPlot("Valor Total Mensal", "Mês", "Valor Total", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
    }

    public Map<YearMonth, Double> getValoresMensais() {
        return valoresMensais;
    }

    public void setValoresMensais(Map<YearMonth, Double> valoresMensais) {
        this.valoresMensais = valoresMensais;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
}
