package example;

import java.io.*;
import java.util.Map;

import org.beanio.*;


public class ExampleMain {

	public static void main(String[] args) {
		 // create a BeanIO StreamFactory
	      StreamFactory factory = StreamFactory.newInstance();
	      // load the mapping file from the working directory
	      factory.load("contacts.xml");

	      // create a BeanReader to read from "input.csv"
	      BeanReader in = factory.createReader("contacts", new File("input.csv"));
	      // create a BeanWriter to write to "output.csv"
	      BeanWriter out = factory.createWriter("contacts", new File("output.csv"));

	      Object record = null;

	      // read records from "input.csv"
	      while ((record = in.read()) != null) {

	         // process each record
	         if ("header".equals(in.getRecordName())) {
	            Map<String, Object> header = (Map<String, Object>) record;
	            System.out.println(header.get("fileDate"));
	         } else if ("contact".equals(in.getRecordName())) {
	            Contact contact = (Contact) record;
	            // process the contact...
	         } else if ("trailer".equals(in.getRecordName())) {
	            Integer recordCount = (Integer) record;
	            System.out.println(recordCount + " contacts processed");
	         }

	         // write the record to "output.csv"
	         out.write(record);
	      }

	      in.close();

	      out.flush();
	      out.close();
	   }
}

	


