/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import Framework.CommonFilterImpl;
public class SourceFilter extends CommonFilterImpl{
    private String sourceFile;
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byteRead;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(sourceFile)));
        while(true) {
            byteRead = bufferedInputStream.read();
            if (byteRead == -1) return true;
            out.write(byteRead);
        }
    }
}