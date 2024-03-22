package gitlet;

import java.io.Serializable;
import java.util.*;

import static gitlet.Utils.*;

/** Represents a gitlet commit object. */
public class Commit implements Serializable {
    /** The message of this Commit. */
    private String message;
    /** The timestamp when this Commit was made. */
    private Date commitDate;
    /** The SHA-1 hash of this Commit. */
    private String sha1Hash;
    /** The mapping of file names to blob hashes. */
    private Map<String, String> blobs;
    /** The SHA-1 hash of this Commit's parent. */
    private List<String> parentHashs;

    // Constructor for initial commit
    public Commit(String message) {
        this.message = message;
        this.commitDate = new Date(0); // Unix epoch timestamp
        this.blobs = new HashMap<>();
        this.parentHashs = new ArrayList<>();
        this.parentHashs.add(null);
        this.sha1Hash = sha1(this.message, this.commitDate.toString());
    }

    // Constructor for non-initial commits
    public Commit(String message, Date commitDate, String parentHash, Map<String, String> blobs) {
        this.message = message;
        this.commitDate = commitDate;
        this.parentHashs = new ArrayList<>();
        this.parentHashs.add(parentHash);
        this.blobs = new HashMap<>(blobs); // Create a copy of the blobs map
        this.sha1Hash = sha1(this.message, this.commitDate.toString(), this.getFirstParent(), this.blobs.toString());
    }

    // Getters and setters as needed
    public String getMessage() {
        return message;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public String getSha1Hash() {
        return sha1Hash;
    }

    public Map<String, String> getBlobs() {
        return blobs;
    }

    public String getFirstParent() {
        return parentHashs.get(0);
    }

    public List<String> getParentHashs() {
        return parentHashs;
    }

    public void setBlobs(Map<String, String> blobs) {
        this.blobs = blobs;
    }

    // Helper methods
    public String getBlobHash(String fileName) {
        return blobs.get(fileName);
    }

    // This method is used for the serialization of Commit objects.
    // Override this method as needed for the project requirements.
    public void dump() {
        System.out.println("Commit: " + sha1Hash);
        System.out.println("Date: " + commitDate);
        System.out.println("Message: " + message);
        System.out.println("First Parent: " + getFirstParent());
        System.out.println("Blobs" + blobs);
    }
}
