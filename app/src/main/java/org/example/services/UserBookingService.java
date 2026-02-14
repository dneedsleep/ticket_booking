package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.utils.UserServiceUtil;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private static final String USERS_PATH = "app/src/main/java/org/example/localDb/users.json";

    private ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    public UserBookingService() throws IOException{
            loadUsers();
    }

    public List<User> loadUsers() throws IOException{
        File users = new File(USERS_PATH);
        userList = OBJECT_MAPPER.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }
    private void saveUserListToFile() throws  IOException{
        File usersFile = new File(USERS_PATH);
        OBJECT_MAPPER.writeValue(usersFile,userList);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId){
        // to : Complete cancel booking
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source , String destination){
        try{
            TrainService
        }
    }
}
