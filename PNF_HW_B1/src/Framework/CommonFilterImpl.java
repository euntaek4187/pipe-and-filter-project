/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public abstract class CommonFilterImpl implements CommonFilter {
    protected List<PipedInputStream> inputs = new ArrayList<>();
    protected List<PipedOutputStream> outputs = new ArrayList<>();
    public void connectOutputTo(CommonFilter nextFilter) throws IOException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);
        addOutputPipe(out);
        nextFilter.addInputPipe(in);
    }
    public void connectInputTo(CommonFilter previousFilter) throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);
        addInputPipe(in);
        previousFilter.addOutputPipe(out);
    }
    public void addOutputPipe(PipedOutputStream out) {
        outputs.add(out);
    }
    public void addInputPipe(PipedInputStream in) {
        inputs.add(in);
    }
    public List<PipedInputStream> getInputPipes() {
        return inputs;
    }
    public List<PipedOutputStream> getOutputPipes() {
        return outputs;
    }
    public abstract boolean specificComputationForFilter() throws IOException;
    public void run() {
        try {
            specificComputationForFilter();
        } catch (IOException e) {
            if (!(e instanceof EOFException)) {
                e.printStackTrace();
            }
        } finally {
            allPortClose();
        }
    }
    private void allPortClose() {
        try {
            for (PipedInputStream in : inputs) {
                in.close();
            }
            for (PipedOutputStream out : outputs) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}