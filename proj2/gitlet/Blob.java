package gitlet;

import java.io.Serializable;
import java.io.File;
import static gitlet.Utils.readContents;
import static gitlet.Utils.sha1;

public class Blob implements Serializable {
    private String blobHash;
    private String fileName;
    private byte[] contents;

    public Blob(String filename, File contents) {
        this.contents = readContents(contents);
        this.blobHash = sha1((Object) this.contents);;
        this.fileName = filename;
    }

    public String getBlobHash() {
        return this.blobHash;
    }

    public String getFileName() {
        return this.fileName;
    }

    public byte[] getContents() {
        return this.contents;
    }
}
