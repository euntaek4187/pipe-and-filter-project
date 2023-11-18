/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Commponent.Prerequisite;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import Framework.CommonFilterImpl;
import java.util.*;
public class PrerequisitCheckFilter extends CommonFilterImpl {
    private List<String> studentData = new ArrayList<>();
    private List<String> courseData = new ArrayList<>();
    private Map<String, Set<String>> prerequisites = new HashMap<>();
    @Override
    public boolean specificComputationForFilter() throws IOException {
        processDataFromInputs();
        validatePrerequisites();
        return true;
    }
    private void processDataFromInputs() throws IOException {
        byte[] buffer = new byte[1024];
        int byteRead, index = 0;
        for (PipedInputStream input : this.getInputPipes()) {
            while (true) {
                byteRead = input.read();
                if (byteRead != -1) buffer[index++] = (byte) byteRead;
                if (byteRead == '\n' || byteRead == -1) {
                    if (index > 0) {
                        String line = new String(buffer, 0, index).trim();
                        addData(line);
                        index = 0;
                    }
                    if (byteRead == -1) break;
                }
            }
        }
        parseCoursePrerequisites();
    }
    private void addData(String line) {
        if (!line.isEmpty()) {
            if (line.split(" ")[0].length() == 5) courseData.add(line);
            else studentData.add(line);
        }
    }
    private void parseCoursePrerequisites() {
        for (String courseLine : courseData) {
            String[] courseParts = courseLine.split(" ");
            String courseId = courseParts[0];
            Set<String> requiredCourses = new HashSet<>();
            if (courseParts.length > 3) requiredCourses.addAll(Arrays.asList(courseParts).subList(3, courseParts.length));
            prerequisites.put(courseId, requiredCourses);
        }
    }
    private void validatePrerequisites() throws IOException {
        for (String studentLine : studentData) {
            String[] studentInfo = studentLine.split(" ");
            List<String> coursesTaken = Arrays.asList(studentInfo).subList(3, studentInfo.length);
            boolean isPrerequisitesSatisfy = true;
            for (String course : coursesTaken) {
                Set<String> requiredCourses = prerequisites.get(course);
                if (requiredCourses != null) {
                    for (String required : requiredCourses) {
                        if (!coursesTaken.contains(required)) {
                            isPrerequisitesSatisfy = false;
                            break;
                        }
                    }
                }
                if (!isPrerequisitesSatisfy) break;
            }
            if (isPrerequisitesSatisfy) writeToOutputPipes(studentLine, getOutputPipes().get(0));
            else writeToOutputPipes(studentLine, getOutputPipes().get(1));
        }
    }
    private void writeToOutputPipes(String data, PipedOutputStream output) throws IOException {
        String studentDataLine = data + "\n";
        output.write(studentDataLine.getBytes());
        output.flush();
    }
}