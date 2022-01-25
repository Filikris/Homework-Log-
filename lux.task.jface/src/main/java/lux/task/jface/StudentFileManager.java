package lux.task.jface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class StudentFileManager implements IStudentListListener {

    private String path = Paths.get(System.getProperty("user.home"), "Desktop", "Students.csv").toString();
    private StudentList list;
    private static final String[] STUDENTS_ARRAY = { "Nancy", "Larry", "Joe" };

    public StudentFileManager(StudentList list) {
        this.list = list;
    }

    private void saveFile() throws IOException {

        File fileToSave = new File(path);
        System.out.println("Save as file: " + fileToSave.getAbsolutePath());

        try (FileWriter fw = new FileWriter(fileToSave); BufferedWriter bw = new BufferedWriter(fw)) {

            String[] titles = { "Name", "Group", "SWT Done" };
            for (String title : titles) {
                bw.write(title);
                bw.write("\t");
            }
            bw.newLine();

            for (Student s : list.getStudents()) {
                bw.write(s.getName());
                bw.write("\t");
                bw.write(s.getGroup());
                bw.write("\t");
                bw.write(s.isTaskDone() ? "DONE" : "NOT DONE");
                bw.newLine();
            }
        } catch (IOException ex) {
            throw ex;
        }
    }

    public void setPath(String path) throws IOException {
        this.path = path;
        saveFile();
    }

    public void readFile() {
        try {
            File r = new File(path);
            if (r.exists() && r.canWrite() && r.canRead()) {
                BufferedReader in = new BufferedReader(new LineNumberReader(new FileReader(path)));
                String line;
                line = in.readLine();
                while ((line = in.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line, "\t");
                    Student student = null;
                    if (st.hasMoreTokens()) {
                        student = list.create();
                        student.setName(st.nextToken());
                    }

                    if (st.hasMoreTokens()) {
                        student.setGroup(st.nextToken());
                    }

                    if (st.hasMoreTokens()) {
                        student.setIsTaskDone(st.nextToken().equals("DONE"));
                    }

                    if (student != null) {
                        list.saveStudent(student);
                    }
                }
                in.close();
            } else {
                initData();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void initData() {
        Student student;
        for (int i = 0; i < 5; i++) {
            student = list.create();
            student.setName(STUDENTS_ARRAY[i % 3]);
            student.setGroup("Group " + i);
            list.saveStudent(student);
        }
    };

    @Override
    public void studentAdded(Student student) {
        try {
            saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void studentRemoved(Student student) {
        try {
            saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void studentUpdated(Student student) {
        try {
            saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
