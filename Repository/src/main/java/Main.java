import mpp.domain.entities.User;
import mpp.repo.repositories.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();

        try {
            for(User e : repo.getAll()){
                System.out.println(e.getId() + " cu username: "+ e.getUsername());
            }

            repo.save(new User("wow","paop"));

            for(User e : repo.getAll()){
                System.out.println(e.getId() + " cu username: "+ e.getUsername());
            }

            User u = repo.findByUsername("wow");
            System.out.println(u.getId());

            repo.update(u.getId(), new User("updated","pass"));

            for(User e : repo.getAll()){
                System.out.println(e.getId() + " cu username: "+ e.getUsername());
            }

            repo.delete(u.getId());

            for(User e : repo.getAll()){
                System.out.println(e.getId() + " cu username: "+ e.getUsername());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
