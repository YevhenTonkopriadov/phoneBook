package com.lardi.repository;

import com.lardi.model.Record;
import com.lardi.model.User;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ellik on 12.03.2017.
 */
public class SaveInFileUser implements UserRepository {

    private final String fileName = "user.csv";
    private ArrayList<User> users = new ArrayList<>();

    @Override
    public User findByUsername(String username) {
        User findUser =null;
        users = read(fileName);
        for (User user:users) {
            if(user.getUsername().equals(username)){
                findUser=user;
            }
        }
        return findUser;
    }

    @Override
    public User save(User user) {
        int size = users.size();
        if(size==0){
            user.setId(new Long(1));
            users.add(user);
        } else {
            Long id = users.get(size-1).getId()+1;
            user.setId(id);
            users.add(user);
        }
        wrire(fileName,users);
        return user;
    }

    private static ArrayList<User> read(String fileName)  {
        ArrayList <User> users = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()){
            return users;
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( file.getAbsoluteFile()),"utf8"));
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    String [] recordComponent = line.split(";");
                    User user = new User();
                    user.setId(Long.parseLong(recordComponent[0]));
                    user.setUsername(recordComponent[1]);
                    user.setPassword(recordComponent[2]);
                    user.setFio(recordComponent[3]);
                    users.add(user);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    private static void wrire(String fileName, ArrayList<User> users){
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try(FileWriter writer = new FileWriter(file, false)) {
            for (User user:users ) {
                String text = user.getId()+";"+user.getUsername()+";"+user.getPassword()+";"+user.getFio()+";";
                writer.write(text);
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();;
        }
    }
}
