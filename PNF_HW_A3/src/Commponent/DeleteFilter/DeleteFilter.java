package Commponent.DeleteFilter;

import java.io.IOException;
import java.util.StringTokenizer;
import Framework.CommonFilterImpl;

public class DeleteFilter extends CommonFilterImpl {

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read = 0;
        byte[] buffer = new byte[1024];
        int idx = 0;

        while (true) {
            byte_read = in.read();
            if (byte_read != -1) buffer[idx++] = (byte) byte_read;

            if (byte_read == '\n' || byte_read == -1) {
                String studentRecord = new String(buffer, 0, idx);
                String[] tokens = studentRecord.split(" ");

                if (tokens.length > 3 && !"CS".equals(tokens[3])) {
                    studentRecord = studentRecord.replace(" 17651", "").replace(" 17652", "");
                    out.write(studentRecord.getBytes());
                }
                idx = 0;
                if (byte_read == -1) return true;
            }
        }
    }
}
