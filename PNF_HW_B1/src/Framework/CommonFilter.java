/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
public interface CommonFilter extends Runnable {
    void connectOutputTo(CommonFilter filter) throws IOException;
    void connectInputTo(CommonFilter filter) throws IOException;
    void addOutputPipe(PipedOutputStream out);
    void addInputPipe(PipedInputStream in);
    List<PipedInputStream> getInputPipes();
    List<PipedOutputStream> getOutputPipes();
}
