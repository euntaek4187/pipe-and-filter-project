/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Sink;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import Framework.CommonFilterImpl;
public class SinkFilter extends CommonFilterImpl {
    private String sinkFile;
    public SinkFilter(String outputFile) {
        this.sinkFile = outputFile;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
        try (FileWriter fw = new FileWriter(this.sinkFile)) {
            for (PipedInputStream input : inputs) {
                int byte_read;
                while ((byte_read = input.read()) != -1) {
                    fw.write(byte_read);
                    fw.flush(); 
                }
            }
            return true; 
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}