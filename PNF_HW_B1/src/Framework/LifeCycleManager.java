/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;
import Commponent.Prerequisite.PrerequisiteFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter studentSourceFilter = new SourceFilter("Students.txt");
            CommonFilter courseSourceFilter = new SourceFilter("Courses.txt");
            CommonFilter prerequisiteFilter = new PrerequisiteFilter();
            CommonFilter output1Filter = new SinkFilter("Output-1.txt");
            CommonFilter output2Filter = new SinkFilter("Output-2.txt");
            
            studentSourceFilter.connectOutputTo(prerequisiteFilter);
            courseSourceFilter.connectOutputTo(prerequisiteFilter);
            prerequisiteFilter.connectOutputTo(output1Filter);
            prerequisiteFilter.connectOutputTo(output2Filter);

            Thread studentThread = new Thread(studentSourceFilter);
            Thread courseThread = new Thread(courseSourceFilter);
            Thread prerequisiteThread = new Thread(prerequisiteFilter);
            Thread output1Thread = new Thread(output1Filter);
            Thread output2Thread = new Thread(output2Filter);

            studentThread.start();
            courseThread.start();
            prerequisiteThread.start();
            output1Thread.start();
            output2Thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
