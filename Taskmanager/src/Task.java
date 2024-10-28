class Task {
    private final int id; // Make id final
    private String name;
    private String description;

    public Task(int id, String name, String description) {
        this.id = id; // Set the final id in the constructor
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id; // Getter for id remains the same
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task ID: " + id + ", Name: " + name + ", Description: " + description;
    }

    // Convert task to a CSV string for file I/O
    public String toCSV() {
        return id + "," + name + "," + description;
    }

    // Static method to create a Task from a CSV string
    public static Task fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Task(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }
}
