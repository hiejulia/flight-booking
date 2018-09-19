public class PassengerDao {

    @Autowired
    private final GraphDatabaseService graphDb;

    public long createUser(User entity) {
        long id = 0;
        try (Transaction tx = graphDb.beginTx()) {
            Node node = graphDb.createNode(ProjectLabels.User);
            node.setProperty("firstname", entity.getFirstname());
            node.setProperty("lastname", entity.getLastname());
            node.setProperty("email", entity.getEmail());
            tx.success();
            id = node.getId();
            tx.close();
        }        
        return id;
    }

    public boolean removeUser(long id) {
        try (Transaction tx = graphDb.beginTx()) {
            Map<String, Object> params = new HashMap<>();
            Node node = graphDb.getNodeById(id);
            if (node.hasRelationship()) {
                return false;
            }
            params.put("id", id);
            String remove = "MATCH (n:User) WHERE ID (n) = {id} DELETE n";
            Result result = graphDb.execute(remove,
                    params);
            tx.success();
        }
        return true;
    }

    public User findUserById(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        String query = "MATCH (a:User) "
                + " WHERE ID(a) = {id} "
                + " RETURN ID(a), a.firstname, a.lastname, a.email";

        Result result = graphDb.execute(query,
                params);

        User entity = null;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            entity = new User();
            for (Map.Entry<String, Object> column : row.entrySet()) {
                if (column.getKey().equals("ID(a)")) {
                    if (column.getValue() != null) {
                        entity.setId((long) column.getValue());
                    }
                }
                if (column.getKey().equals("a.firstname")) {
                    if (column.getValue() != null) {
                        entity.setFirstname((String) column.getValue());
                    }
                }
                if (column.getKey().equals("a.lastname")) {
                    if (column.getValue() != null) {
                        entity.setLastname((String) column.getValue());
                    }
                }
                if (column.getKey().equals("a.email")) {
                    if (column.getValue() != null) {
                        entity.setEmail((String) column.getValue());
                    }
                }
            }
        }
        return entity;
    }

    public void updateUser(User entity) {

        try (Transaction tx = graphDb.beginTx()) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", entity.getId());
            params.put("firstname", entity.getFirstname());
            params.put("lastname", entity.getLastname());
            params.put("email", entity.getEmail());
            
            String query = "MATCH (n:User) WHERE ID (n) = {id} SET  n.firstname={firstname}, n.lastname={lastname},  n.email={email} RETURN n";
            
            Result result = graphDb.execute(query,
                    params);

            tx.success();
        }
    }

    // get all passengers with booking request 
    public List<User> findAll() {
        Result result = graphDb.execute("MATCH (p:Passenger) RETURN e");
        Iterator<Node> nodes = result.columnAs("e");
        return fromUserNodes(nodes);
    }

    private LinkedList<User> fromUserNodes(Iterator<Node> nodes) {
        LinkedList<User> nodeList = new LinkedList<>();
        for (Node node : Iterators.asIterable(nodes)) {
            nodeList.add(User.fromNode(node));
        }
        return nodeList;
    }
    
        
}