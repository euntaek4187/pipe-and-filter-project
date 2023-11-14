/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Framework;

import Commponent.AddFilter.AddFilter;
import Components.Middle.MiddleFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter filter1 = new SourceFilter("Students.txt");
            CommonFilter filter2 = new SinkFilter("Output.txt");
            CommonFilter filter3 = new MiddleFilter();
            CommonFilter filter4 = new AddFilter();

            
            filter1.connectOutputTo(filter3);
            filter3.connectOutputTo(filter4);
            filter4.connectOutputTo(filter2);

            
            Thread thread1 = new Thread(filter1);
            Thread thread2 = new Thread(filter2);
            Thread thread3 = new Thread(filter3);
            Thread thread4 = new Thread(filter4);

            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
