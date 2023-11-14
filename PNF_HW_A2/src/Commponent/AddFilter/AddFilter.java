package Commponent.AddFilter;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class AddFilter extends CommonFilterImpl {

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byteRead = 0;
        byte[] buffer = new byte[64];
        int idx = 0;

        while (true) {
            byteRead = in.read();
            if (byteRead != -1) buffer[idx++] = (byte) byteRead;

            if (byteRead == '\n' || byteRead == -1) {
                String studentRecord = new String(buffer, 0, idx).trim();
                String[] tokens = studentRecord.split(" ");

                if ("EE".equals(tokens[3]) && !studentRecord.contains(" 23456")) {
                    studentRecord += " 23456";
                }

                out.write((studentRecord + "\n").getBytes());
                idx = 0;

                if (byteRead == -1) return true;
            }
        }
    }
}
