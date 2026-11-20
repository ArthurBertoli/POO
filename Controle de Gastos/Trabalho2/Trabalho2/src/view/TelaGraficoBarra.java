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
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** Gráfico de barras com o total gasto em cada categoria, agrupado por mês. */
public class TelaGraficoBarra extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaGraficoBarra(String title, List<Gasto> gastos) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFreeChart barChart = ChartFactory.createBarChart(title, "Mês", "Valor", createDataset(gastos), PlotOrientation.VERTICAL, true, true, false);
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
        // Inclui o ano para não somar meses iguais de anos diferentes.
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM/yyyy", Locale.forLanguageTag("pt-BR"));
        return dateFormat.format(date);
    }

    private CategoryDataset createDataset(List<Gasto> gastos) {
        List<Gasto> ordenados = new ArrayList<>(gastos);
        ordenados.sort(Comparator.comparing(Gasto::getData));

        // Soma os valores de mesma categoria no mesmo mês (addValue substituiria o valor anterior).
        Map<String, Map<String, Double>> totais = new LinkedHashMap<>();
        for (Gasto gasto : ordenados) {
            Map<String, Double> porCategoria = totais.computeIfAbsent(obterNomeMes(gasto.getData()), m -> new LinkedHashMap<>());
            porCategoria.merge(gasto.getCategoria(), gasto.getValor(), Double::sum);
        }

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Map<String, Double>> mes : totais.entrySet()) {
            for (Map.Entry<String, Double> categoria : mes.getValue().entrySet()) {
                dataset.addValue(categoria.getValue(), categoria.getKey(), mes.getKey());
            }
        }
        return dataset;
    }

    public static void main(String[] args) {
        TelaGraficoBarra chart = new TelaGraficoBarra("Gráfico de Gastos Mensais", new ArrayList<>());
        chart.pack();
        chart.setVisible(true);
    }
}
