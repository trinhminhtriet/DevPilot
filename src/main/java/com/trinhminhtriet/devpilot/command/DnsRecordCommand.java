package com.trinhminhtriet.devpilot.command;

import com.trinhminhtriet.devpilot.service.DnsRecordService;
import com.trinhminhtriet.devpilot.service.impl.DnsRecordServiceImpl;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.xbill.DNS.Record;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "dns", description = "Print and export DNS records of a domain.")
public class DnsRecordCommand implements Runnable {

  @Parameters(index = "0", description = "Domain to query")
  private String domain;

  @Option(names = {"-e", "--export"}, description = "Export type: csv or xlsx")
  private String exportType;

  @Option(names = {"-o", "--output"}, description = "Output file path")
  private String exportPath;

  private final DnsRecordService dnsRecordService = new DnsRecordServiceImpl();

  @Override
  public void run() {
    try {
      List<Record> records = dnsRecordService.getAllRecords(domain);
      if (exportType == null || exportType.isEmpty()) {
        for (Record record : records) {
          System.out.println(record);
        }
      } else if (exportType.equalsIgnoreCase("csv")) {
        exportToCsv(records, exportPath);
        System.out.println("Exported DNS records to CSV: " + exportPath);
      } else if (exportType.equalsIgnoreCase("xlsx")) {
        exportToExcel(records, exportPath);
      } else {
        throw new IllegalArgumentException("Unsupported export type: " + exportType);
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void exportToCsv(List<Record> records, String path) throws IOException {
    try (FileWriter writer = new FileWriter(path)) {
      writer.write("Type,Name,TTL,Data\n");
      for (Record record : records) {
        writer.write(String.format("%s,%s,%d,%s\n",
            record.getType(),
            record.getName(),
            record.getTTL(),
            record.rdataToString()));
      }
    }
  }

  private void exportToExcel(List<Record> records, String path) throws IOException {
    // TODO: Implement Excel export using Apache POI or similar library
    throw new UnsupportedOperationException("Excel export not implemented yet.");
  }
}
