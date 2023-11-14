/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Commponent.DeleteFilter;
import java.io.IOException;
import java.util.List;
import Framework.CommonFilterImpl;
public class DeleteFilter extends CommonFilterImpl {
    private List<String> courseList;
    public DeleteFilter(List<String> list) {
        this.courseList = list;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byteRead;
        byte[] buffer = new byte[1024];
        int idx = 0;

        while (true) {
            byteRead = in.read();
            if (byteRead != -1) buffer[idx++] = (byte) byteRead;
            if (byteRead == '\n' || byteRead == -1) {
                if (idx > 0) {
                    String studentRecord = new String(buffer, 0, idx).trim();
                    if(studentRecord.contains("2013")) {
	                    for (String course : courseList) {
	                        studentRecord = studentRecord.replaceAll(" " + course, "");
	                    }
	                    out.write((studentRecord + "\n").getBytes());
                    }
                }
                idx = 0;
                if (byteRead == -1) return true;
            }
        }
    }
}
