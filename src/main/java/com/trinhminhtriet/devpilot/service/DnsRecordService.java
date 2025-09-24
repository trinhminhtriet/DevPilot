package com.trinhminhtriet.devpilot.service;

import java.util.List;
import org.xbill.DNS.Record;

public interface DnsRecordService {

  List<Record> getAllRecords(String domain) throws Exception;
}