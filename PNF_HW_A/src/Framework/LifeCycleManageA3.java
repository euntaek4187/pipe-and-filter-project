/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;
import java.util.Arrays;
import Commponent.DeleteFilter.DeleteFilter;
import Components.SelectFilter.SelectFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;
public class LifeCycleManageA3 {
    public static void main(String[] args) {
        try {
            CommonFilter studentSourceFilter = new SourceFilter("Students.txt");
            CommonFilter outputFilter = new SinkFilter("Output.txt");
            CommonFilter selectFilter = new SelectFilter("2013", "CS", false);
            CommonFilter deleteFilter = new DeleteFilter(Arrays.asList("17651", "17652"));

            studentSourceFilter.connectOutputTo(selectFilter);
            selectFilter.connectOutputTo(deleteFilter);
            deleteFilter.connectOutputTo(outputFilter);

            Thread thread1 = new Thread(studentSourceFilter);
            Thread thread2 = new Thread(outputFilter);
            Thread thread3 = new Thread(selectFilter);
            Thread thread4 = new Thread(deleteFilter);

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}