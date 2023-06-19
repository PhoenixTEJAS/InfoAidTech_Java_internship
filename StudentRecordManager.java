import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRecordManager extends JFrame {

    private List<Student> studentList;
    private JTextField rollNumberTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField phoneNumberTextField;
    private JTextArea outputTextArea;

    public StudentRecordManager() {
        setTitle("Student Record Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberTextField = new JTextField();
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberTextField);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);

        JLabel addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        inputPanel.add(addressLabel);
        inputPanel.add(addressTextField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberTextField = new JTextField();
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberTextField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        // Text area for displaying output
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);

        loadStudentRecord(); // Load student records from file

        setVisible(true);
    }

    private void addStudent() {
        String rollNumber = rollNumberTextField.getText();
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        Student newStudent = new Student(rollNumber, name, address, phoneNumber);
        studentList.add(newStudent);

        outputTextArea.append("Student added: " + newStudent.toString() + "\n");

        saveStudentRecord(); // Save student records to file

        clearInputFields();
    }

    private void clearInputFields() {
        rollNumberTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
        phoneNumberTextField.setText("");
    }

    private void loadStudentRecord() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("student_record.dat"))) {
            studentList = (ArrayList<Student>) inputStream.readObject();
            outputTextArea.append("Student record loaded successfully.\n");
        } catch (FileNotFoundException e) {
            studentList = new ArrayList<>();
            outputTextArea.append("No existing student record found. Starting with an empty record.\n");
        } catch (IOException | ClassNotFoundException e) {
            outputTextArea.append("Error occurred while loading student record.\n");
        }
    }

    private void saveStudentRecord() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("student_record.dat"))) {
            outputStream.writeObject(studentList);
            outputTextArea.append("Student record saved successfully.\n");
        } catch (IOException e) {
            outputTextArea.append("Error occurred while saving student record.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentRecordManager();
            }
        });
    }
}
