package br.com.ibge.util;

import br.com.ibge.dto.MunicipioDTO;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.util.List;

@Slf4j
public class WriteCsvToResponse {

    public static void writeMunicipios(PrintWriter writer, List<MunicipioDTO> municipios) {
        try {
            StatefulBeanToCsv btcsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            btcsv.write(municipios);
        } catch (CsvException ex) {
            log.error("Error mapping Bean to CSV", ex);
        }
    }
}
