package by.bsuir.kravchenko.service;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataBaseService {
    private boolean flagDelimiter = false;

    private static final String BL_3NF_deployment = "C:\\Users\\viku_\\IdeaProjects\\ETLBibd\\src\\main\\resources\\scripts\\BL_3NF_deployment.txt";
    private static final String BL_CL_deployment = "C:\\Users\\viku_\\IdeaProjects\\ETLBibd\\src\\main\\resources\\scripts\\BL_CL_deployment.txt";
    private static final String BL_CL_dep_tables = "C:\\Users\\viku_\\IdeaProjects\\ETLBibd\\src\\main\\resources\\scripts\\BL_CL_dep_tables.txt";
    private static final String BL_CL_run = "C:\\Users\\viku_\\IdeaProjects\\ETLBibd\\src\\main\\resources\\scripts\\BL_CL_run.sql";
    private static final String BL_DM_deployment = "C:\\Users\\viku_\\IdeaProjects\\ETLBibd\\src\\main\\resources\\scripts\\BL_DM_deployment.txt";
    private static final String SA_SRC_deployment = "C:\\Users\\viku_\\IdeaProjects\\ETLBibd\\src\\main\\resources\\scripts\\SA_SRC_deployment.txt";

    public void initDataBase(List<String> logs) {
        List<String> list;
        list = takeListPaths(SA_SRC_deployment);
        runScript(list, "SA_SRC", "SA_SRC",logs);
        logs.add("SA_SRC init successful");
        list = takeListPaths(BL_3NF_deployment);
        runScript(list, "BL_3NF", "BL_3NF",logs);
        logs.add("BL_3NF init successful");
        list = takeListPaths(BL_DM_deployment);
        runScript(list, "BL_DM", "BL_DM",logs);
        logs.add("BL_DM init successful");
        list = takeListPaths(BL_CL_dep_tables);
        runScript(list, "BL_CL", "BL_CL",logs);
        flagDelimiter = true;
        list = takeListPaths(BL_CL_deployment);
        runScript(list, "BL_CL", "BL_CL",logs);
        logs.add("BL_CL init successful");
    }

    public void incrementLoadDB(List<String> logs) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "BL_CL", "BL_CL");
            logs.add("Connection to database BL_CL is open");
            new ScriptRunner(connection)
                    .runScript(new BufferedReader(new FileReader(BL_CL_run)));
            logs.add("Increment load is successful");
        } catch (Exception e) {
            logs.add("Increment load is failed");
            System.err.println(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    logs.add("Connection to database BL_CL is closed");
                    System.out.println("Connection is closed");
                }
            } catch (SQLException e) {
                System.out.println("Closing connection error");
            }
        }
    }

    private List<String> takeListPaths(String fileName) {
        List<String> paths = null;
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            paths = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    private void runScript(List<String> listFile, String nameDB, String passwordDB,List<String> logs) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", nameDB, passwordDB);
            logs.add("Connection to database" + nameDB + " is open");
            for (String listElement :
                    listFile) {
                File file = new File(listElement);
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                if (flagDelimiter) {
                    scriptRunner.setDelimiter("!");
                }
                scriptRunner.runScript(new BufferedReader(new FileReader(file)));
            }

        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                flagDelimiter = false;
                if (connection != null) {
                    connection.close();
                    logs.add("Connection to database" + nameDB + " is closed");
                    System.out.println("Connection is closed");
                }
            } catch (SQLException e) {
                System.out.println("Closing connection error");
            }
        }
    }
}
