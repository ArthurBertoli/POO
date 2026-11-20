package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Conversões de texto usadas pelas telas de cadastro e alteração de gastos. */
final class Formatos {

    static final String PADRAO_DATA = "dd/MM/yyyy";

    private Formatos() {
    }

    /** Converte "dd/MM/yyyy" em data, recusando datas impossíveis como 31/02/2024. */
    static Date lerData(String texto) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat(PADRAO_DATA);
        formato.setLenient(false);
        String limpo = texto.trim();
        if (!limpo.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            throw new ParseException(limpo, 0);
        }
        return formato.parse(limpo);
    }

    static String formatarData(Date data) {
        return data == null ? "" : new SimpleDateFormat(PADRAO_DATA).format(data);
    }

    /** Aceita "12.50" e "12,50". */
    static double lerValor(String texto) throws NumberFormatException {
        return Double.parseDouble(texto.trim().replace(',', '.'));
    }
}
