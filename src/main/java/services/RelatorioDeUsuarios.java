package services;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;


public class RelatorioDeUsuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private String caminhoArquivoRelatorio = null;
	private JRExporter exporter = null;
	private String caminhoSubReport_Dir = "";
	private File arquivoGerado = null;	
	
	
	public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext, String tipoRelatorio) throws Exception{
		
		//cria lista de Collection Datasource
		JRBeanCollectionDataSource jrbCollectionDS = new JRBeanCollectionDataSource(listaDataBeanCollection);
		
		//Caminho fisico até a pasta que contem os relatórios
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
		
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
		//verificação de caminho em servidores
		if(caminhoRelatorio == null 
				|| caminhoRelatorio != null && caminhoRelatorio.isEmpty()
				|| !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}
		
		//caminho para imagens
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
		//caminho completo até o relatório compilado
		String caminhoArquivosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
		
		//Faz o carregamento do relatório
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);
		
		//seta parametro SUBREPORT_DIR com o caminho fisico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
		
		//carrega arquivo
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbCollectionDS);

		if(tipoRelatorio.equalsIgnoreCase("pdf")) {
			exporter = new  JRPdfExporter();
		}else if(tipoRelatorio.equalsIgnoreCase("xls")) {
			exporter = new  JRXlsExporter();
		}
		
		
		//caminho do relatório exportado
		
		
		caminhoArquivoRelatorio = caminhoRelatorio + SEPARATOR + nomeRelatorioSaida + "." + tipoRelatorio;
		
		//criar novo arquivo exportado
		arquivoGerado = new File(caminhoArquivoRelatorio);
		
		//prepara a impressão
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
		
		//executa exportação
		exporter.exportReport();
		
		//Deleta arquivo gerado para liberar espaço do servidor apos download
		arquivoGerado.deleteOnExit();
		
		return caminhoArquivoRelatorio;
	}
	
	
}
