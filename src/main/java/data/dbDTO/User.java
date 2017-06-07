package data.dbDTO;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class User {
	private String userName;
	private List<Role> roles;
	private Map<String, Permission> permissions = new HashMap();

	User(String userName){
		this.userName=userName;
	}

	@Embedded
	private LinkCollection generalLinks;


}
