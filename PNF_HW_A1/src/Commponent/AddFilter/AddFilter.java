package Commponent.AddFilter;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class AddFilter extends CommonFilterImpl {

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byteRead;
        byte[] buffer = new byte[64];
        int idx = 0;

        while (true) {
            byteRead = in.read();
            if (byteRead != -1) buffer[idx++] = (byte) byteRead;

            if (byteRead == '\n' || byteRead == -1) {
                if (idx > 0) {
                    String studentRecord = new String(buffer, 0, idx).trim();
                    String[] tokens = studentRecord.split(" ");
                    StringBuilder modifiedRecord = new StringBuilder(studentRecord);

                    if ("CS".equals(tokens[3])) {
                        boolean hasCourse12345 = studentRecord.contains(" 12345");
                        boolean hasCourse23456 = studentRecord.contains(" 23456");

                        if (!hasCourse12345) modifiedRecord.append(" 12345");
                        if (!hasCourse23456) modifiedRecord.append(" 23456");
                        studentRecord = modifiedRecord.toString();
                    }

                    out.write((studentRecord + "\n").getBytes());
                }
                idx = 0;
                if (byteRead == -1) return true;
            }
        }
    }
}
