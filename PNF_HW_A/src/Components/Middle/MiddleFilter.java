/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
	String departmentCode;
	boolean searchStatus;
	public MiddleFilter(String DepartmentCode, Boolean searchStatus) {
		this.departmentCode = DepartmentCode;
		this.searchStatus = searchStatus;
	}
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int byteRead;
        int idx = 0;
        byte[] buffer = new byte[64];
        while (true) {
            byteRead = in.read();
            if (byteRead != -1) buffer[idx++] = (byte) byteRead;
            if (byteRead == '\n' || byteRead == -1) {
                if (idx > 0) {
                    String studentRecord = new String(buffer, 0, idx).trim();
                    if (searchStatus == true) {
                    	if(studentRecord.contains(departmentCode)) {
                    		out.write((studentRecord + "\n").getBytes());
                    	}
                    } else {
                    	if(!studentRecord.contains(departmentCode)) {
                    		out.write((studentRecord + "\n").getBytes());
                    	}
					}
                    
                }
                idx = 0;
                if (byteRead == -1) return true;
            }
        }
    }  
}
