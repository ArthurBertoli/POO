package view;

import dados.Gasto;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TelaGraficoBarra extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaGraficoBarra(String title, List<Gasto> gastos) {
        super(title);
        JFreeChart barChart = ChartFactory.createBarChart(title, "Gastos", "Valor", createDataset(gastos), PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();      
        renderer.setSeriesPaint(0, Color.GRAY);       
        CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private String obterNomeMes(java.util.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        return dateFormat.format(date);
    }

    private CategoryDataset createDataset(List<Gasto> gastos) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (Gasto gasto : gastos) {
            String mes = obterNomeMes(gasto.getData());
            dataset.addValue(gasto.getValor(), gasto.getCategoria(), mes);
        }
        
        return dataset;
    }

    public static void main(String[] args) {
        TelaGraficoBarra chart = new TelaGraficoBarra("Gráfico de Gastos Mensais", new ArrayList<>());
        chart.setVisible(true);
    }
}
