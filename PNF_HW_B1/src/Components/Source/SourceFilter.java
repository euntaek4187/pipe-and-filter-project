/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedOutputStream;
import Framework.CommonFilterImpl;
public class SourceFilter extends CommonFilterImpl{
    private String sourceFile;
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(sourceFile)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line += "\n";
                for (PipedOutputStream out : outputs) {
                    out.write(line.getBytes());
                    out.flush();
                }
            }
        }
        return true;
    }
}