package bg.tuvarna.sit.usp_cars.business.services;

import bg.tuvarna.sit.usp_cars.data.entities.User;
import bg.tuvarna.sit.usp_cars.data.repositories.UserRepository;
import bg.tuvarna.sit.usp_cars.presentation.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final Logger log=Logger.getLogger(UserService.class);
    private final UserRepository repository= UserRepository.getInstance();
    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }
    private static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }
    public ObservableList<UserModel> getAllUsers() {
        List<User> users=repository.getAll();

        return FXCollections.observableList(
                users.stream().map(u -> new UserModel(
                        u.getUser_username(),
                        u.getUser_password()
                )).collect(Collectors.toList()));
    }

    //hashing a pass
    private static byte[] hashPassword(String password) { //algoritym za hash na paroli
        byte[] salt = new byte[16];
        byte[] hash=null;
        for (int i = 0; i < 16; i++) {
            salt[i] = (byte) i;
        }
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = f.generateSecret(spec).getEncoded();

        } catch (NoSuchAlgorithmException nsale) {
            nsale.printStackTrace();

        } catch (InvalidKeySpecException ikse) {
            ikse.printStackTrace();
        }
        return hash;
    }

    private boolean verifyPass(UserModel user) { //proverka za korektnost pri logvane
        if(findUser(user)==null)
            return false;
        String passToCheck=new String(hashPassword(user.getUser_password()));

        String hashedPassInDB = new String(findUser(user).getUser_password());//ot bazata parolata

        if(hashedPassInDB.equals(passToCheck)) {
            return true;
        } else {
           return false;
        }
    }

    public User findUser(UserModel userModel){ //tyrsene na potrebitel po ime
        User temp=new User(userModel.getUser_username(), userModel.getUser_password());
        for(User u: repository.getAll()){
            if(u.getUser_username().equals(temp.getUser_username())){
                return u;
            }
        }
        return null;
    }

    public boolean logIn(UserModel userModel){ //metod za logvane ot kontrolera
        return verifyPass(userModel);
    }

    public boolean registerNewUser(UserModel u){ //registraciq na potrebitel
        User user=new User(u.getUser_username(),u.getUser_password(),u.getIs_admin());
        if(findUser(u)!=null){
            log.info("User "+u+" already exists!");
            return false;
        }
        else {
            try{
                String pass=new String(hashPassword(user.getUser_password()));
                user.setUser_password(pass);
                repository.save(user);
                log.info("User "+user.getUser_username()+" created!");
            }
            catch (Exception e) {
                e.printStackTrace();
                log.error("Create user error!");
            }
            return true;

         }
    }
    public boolean deleteUser(UserModel userModel){
        if(findUser(userModel)==null){
            log.error("No such user!");
            return false;
        }
        User user=findUser(userModel);
        try{
            repository.delete(user);
            log.info("User "+user.getUser_username()+" deleted successfully!\n");
            return true;
        }catch(Exception e){
            log.error("Something went wrong deleting user!\n");
            e.printStackTrace();
            return false;
        }
    }
    public User findUserByUsername(String name){
        for(User u: repository.getAll()){
            if(u.getUser_username().equals(name))
                return u;
        }
        return null;
    }
    public boolean updateUser(UserModel usermodel){
        if(findUserByUsername(usermodel.getUser_username())==null){
            log.error("No such user!");
            return false;
        }
        try {
            User temp = findUserByUsername(usermodel.getUser_username());
            String pass = new String(hashPassword(usermodel.getUser_password()));
            temp.setUser_password(pass);
            repository.update(temp);
            log.info("Password for user "+temp.getUser_username()+" changed successfully!\n");
            return true;
        }catch(Exception e){
            log.error("Error updating password!\n");
            e.printStackTrace();
            return false;
        }
    }

}
