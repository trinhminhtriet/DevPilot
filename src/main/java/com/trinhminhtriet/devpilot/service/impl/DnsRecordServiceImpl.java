package com.trinhminhtriet.devpilot.service.impl;

import com.trinhminhtriet.devpilot.service.DnsRecordService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Type;
import org.xbill.DNS.Record;

public class DnsRecordServiceImpl implements DnsRecordService {

  @Override
  public List<Record> getAllRecords(String domain) throws Exception {
    List<Record> records = new ArrayList<>();
    int[] types = {Type.A, Type.AAAA, Type.CNAME, Type.MX, Type.NS, Type.SOA, Type.TXT, Type.SRV, Type.PTR};
    for (int type : types) {
      Lookup lookup = new Lookup(domain, type);
      Record[] recs = lookup.run();
      if (recs != null) {
        records.addAll(Arrays.asList(recs));
      }
    }
    return records;
  }
}
