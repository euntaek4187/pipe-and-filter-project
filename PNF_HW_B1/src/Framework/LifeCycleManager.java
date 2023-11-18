/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;
import Commponent.Prerequisite.PrerequisitCheckFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter studentSourceFilter = new SourceFilter("Students.txt");
            CommonFilter courseSourceFilter = new SourceFilter("Courses.txt");
            CommonFilter prerequisiteFilter = new PrerequisitCheckFilter();
            CommonFilter output1Filter = new SinkFilter("Output-1.txt");
            CommonFilter output2Filter = new SinkFilter("Output-2.txt");
            
            studentSourceFilter.connectOutputTo(prerequisiteFilter);
            courseSourceFilter.connectOutputTo(prerequisiteFilter);
            prerequisiteFilter.connectOutputTo(output1Filter);
            prerequisiteFilter.connectOutputTo(output2Filter);

            Thread thread1 = new Thread(studentSourceFilter);
            Thread thread2 = new Thread(courseSourceFilter);
            Thread thread3 = new Thread(prerequisiteFilter);
            Thread thread4 = new Thread(output1Filter);
            Thread thread5 = new Thread(output2Filter);

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}