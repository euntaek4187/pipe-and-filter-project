/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.SelectFilter;
import java.io.IOException;
import Framework.CommonFilterImpl;
public class SelectFilter extends CommonFilterImpl{
	String studentYear;
	String departmentCode;
	boolean searchStatus;
	public SelectFilter(String studentYear, String DepartmentCode, Boolean searchStatus) {
		this.studentYear = studentYear;
		this.departmentCode = DepartmentCode;
		this.searchStatus = searchStatus;
	}
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int byteRead;
        int index = 0;
        byte[] buffer = new byte[64];
        while (true) {
            byteRead = in.read();
            if (byteRead != -1) buffer[index++] = (byte) byteRead;
            if (byteRead == '\n' || byteRead == -1) {
                if (index > 0) {
                    String studentRecord = new String(buffer, 0, index).trim();
                    writeToOutputPipes(studentRecord);
                }
                index = 0;
                if (byteRead == -1) return true;
            }
        }
    }
	private void writeToOutputPipes(String studentRecord) throws IOException {
		if (studentRecord.split(" ")[0].contains(studentYear)) {
		    if (searchStatus == true) {
		    	if(studentRecord.contains(departmentCode)) out.write((studentRecord + "\n").getBytes());
		    } else {
		    	if(!studentRecord.contains(departmentCode)) out.write((studentRecord + "\n").getBytes());
			}
		}
	}  
}