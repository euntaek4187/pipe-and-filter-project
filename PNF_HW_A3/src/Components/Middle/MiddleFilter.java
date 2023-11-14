/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import java.util.StringTokenizer;

import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
	@Override
	public boolean specificComputationForFilter() throws IOException {
	    int byte_read = 0;
	    byte[] buffer = new byte[1024];
	    int idx = 0;

	    while(true) {
	        byte_read = in.read();
	        if (byte_read != -1) buffer[idx++] = (byte) byte_read;

	        if (byte_read == '\n' || byte_read == -1) {
	            String studentRecord = new String(buffer, 0, idx);
	            StringTokenizer tokenizer = new StringTokenizer(studentRecord, " ");
	            if (tokenizer.hasMoreTokens()) {
	                String studentID = tokenizer.nextToken();
	                if (studentID.startsWith("2013")) {
	                    out.write(studentRecord.getBytes());
	                }
	            }
	            idx = 0;
	            if (byte_read == -1) return true;
	        }
	    }
	}

}
