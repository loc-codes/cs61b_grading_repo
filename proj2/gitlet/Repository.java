package gitlet;


import java.io.File;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static gitlet.Utils.*;

/** Represents a gitlet repository.
 *
 *  @author Lachlan Young
 */
public class Repository {
    /**
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGING_INDEX = join(GITLET_DIR, "staging");
    public static final File STAGING_AREA = join(STAGING_INDEX, "add");
    public static final File REMOVAL_AREA = join(STAGING_INDEX,  "remove");
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File BRANCHES_DIR = join(REFS_DIR, "heads");

    /** The directory for the master branch reference. */
    public static final File MASTER_BRANCH = join(BRANCHES_DIR,  "master");
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");


    /** Utils **/
    private static void updateReferences(Commit commit) {
        writeContents(HEAD_FILE, "refs/heads/master"); // this writes a string with the directory address
        writeContents(MASTER_BRANCH, commit.getSha1Hash());
    }

    /**
     * Stages the given file.
     * @param file The file to stage.
     */
    private static void stageFile(File file, String stagingType) {
        File stagedFile = join(checkStagingType(stagingType), file.getName());
        writeContents(stagedFile, readContents(file));
    }

    /**
     * Unstages the file with the given name.
     * @param fileName The name of the file to unstage.
     */
    private static void unstageFile(String fileName, String stagingType) {
        File stagedFile = join(checkStagingType(stagingType), fileName);
        if (stagedFile.exists()) {
            stagedFile.delete();
        }
    }

    private static File checkStagingType(String stagingType) {
        File stagingArea;
        if (stagingType.equals("add")) {
            stagingArea = STAGING_AREA;
        }
        else {
            stagingArea = REMOVAL_AREA;
        }
        return stagingArea;
    }

    /** Gets the commit with a given hash */
    private static Commit getCommit(String commitHash) {
        if (commitHash == null) {
            return null;
        }
        File commitPath = join(COMMITS_DIR, commitHash);
        boolean commitPathExists = commitPath.exists();
        if (commitPathExists) {
            return readObject(commitPath, Commit.class);
        }
        else  {
            System.out.println("No commit with that id exists.");
        }
        return null;
    }

    /** Gets the head commit */
    private static Commit getCurrentCommit() {
        String currentBranch = getCurrentBranch();
        String currentCommitHash = readContentsAsString(join(GITLET_DIR, currentBranch));
        return getCommit(currentCommitHash);
    }

    /** Get Current Branch */
    private static String getCurrentBranch() {
        String currentBranch = readContentsAsString(HEAD_FILE);
        return currentBranch;
    }

//    private static String formatDate(Date date) {
//        Formatter f = new Formatter();
//        f.format("Date: %tc", date);
//        return f.toString();
//    }


    private static String formatDate(Date date) {
        // Create a SimpleDateFormat that matches the required date format
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy Z");
        // Set the time zone if necessary, for example to UTC
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // Adjust this if you need a specific time zone

        // Format the date
        String formattedDate = "Date: " + sdf.format(date);
        return formattedDate;
    }

    private static String formatParents(List<String> parents) {
        if (parents.size() > 1) {
            String firstParent = parents.get(0).substring(0,6);
            String secondParent = parents.get(1).substring(0,6);
            return "Merge: " + firstParent + " " + secondParent + "\n";
        }
        return "";
    }

    private static String buildCommitMessage(Commit commit) {
        StringBuilder commitMessage = new StringBuilder();
        List<String> parents = commit.getParentHashs();
        String id = commit.getSha1Hash();
        Date date = commit.getCommitDate();
        String message = commit.getMessage();

        commitMessage.append(formatParents(parents));
        commitMessage.append("===\ncommit ").append(id).append("\n");;
        commitMessage.append(formatDate(date)).append("\n");
        commitMessage.append(message).append("\n\n");
        return String.valueOf(commitMessage);
    }

    /** MAIN COMMANDS **/
    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }

        // Create the initial structure for the Gitlet repository
        GITLET_DIR.mkdir();
        STAGING_INDEX.mkdir();
        STAGING_AREA.mkdir();
        REMOVAL_AREA.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
        join(GITLET_DIR, "refs").mkdir(); // Directory for branch references
        join(GITLET_DIR, "refs", "heads").mkdir(); // Directory for actual branches

        // Create the initial commit with no files and the message "initial commit"
        Commit initialCommit = new Commit("initial commit");

        // Save the initial commit to the commit directory
        File commitFile = join(COMMITS_DIR, initialCommit.getSha1Hash());
        writeObject(commitFile, initialCommit);

        // Update the HEAD and master branch to point to the initial commit
        updateReferences(initialCommit);
    }


    public static void add(String fileName) {
        File file = new File(CWD, fileName);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        // Compute the file's SHA-1 hash
        String fileHash = sha1(readContents(file));

        // Retrieve the current commit and its blobs
        Commit currentCommit = getCurrentCommit();

        // Check if the current version of the file is identical to the version in the current commit
        if (currentCommit.getBlobHash(fileName) != null && currentCommit.getBlobHash(fileName).equals(fileHash)) {
            // File has not changed, remove it from staging area if it's there
            unstageFile(fileName, "add");
            return;
        }

        // Stage the file for addition
        stageFile(file, "add");
    }


    public static void commit(String message) {
        File[] stagedFiles = STAGING_AREA.listFiles();
        File[] removeFiles = REMOVAL_AREA.listFiles();
        if (stagedFiles == null && removeFiles == null) {
            System.out.println("No changes added to the commit.");
            return;
        }

        Commit currentCommit = getCurrentCommit();
        Map<String, String> updatedBlobs = new HashMap<>(currentCommit.getBlobs()); // Clone current blobs

        // Update blobs with staged for addition files
        for (File file : stagedFiles) {
            String fileName = file.getName();
            Blob blob = new Blob(fileName, file);
            String fileHash = blob.getBlobHash();
            updatedBlobs.put(fileName, fileHash);
            File blobFile = join(BLOBS_DIR, fileHash);
            writeObject(blobFile, blob);
            file.delete();
        }

        // Update blobs with staged for removal files
        for (File file : removeFiles) {
            String fileName = file.getName();
            updatedBlobs.remove(fileName);
            file.delete();
        }

        // Create a new commit with the updated blobs
        Commit newCommit = new Commit(message, new Date(), currentCommit.getSha1Hash(), updatedBlobs);
        String commitHash = newCommit.getSha1Hash();
        File commitFile = join(COMMITS_DIR, commitHash);
        writeObject(commitFile, newCommit);

        // Update the HEAD to point to the new commit
        updateReferences(newCommit);
    }

    public static void rm(String fileName) {
        File fileToUnstage = join(STAGING_AREA, fileName);
        Commit currentCommit = getCurrentCommit();
        boolean isStaged = fileToUnstage.exists();
        boolean isTracked = currentCommit.getBlobs().containsKey(fileName);

        if (!isStaged && !isTracked) {
            System.out.println("No reason to remove the file.");
            return;
        }

        if (isStaged) {
            // If the file is currently staged, unstage it.
            unstageFile(fileName, "add");
        }

        if (isTracked) {
            String blobName = currentCommit.getBlobs().get(fileName);
            File blobPath = join(BLOBS_DIR, blobName);
            Blob blob = readObject(blobPath, Blob.class);
            String blobFileName = blob.getFileName();
            File fileToDelete = join(CWD, blobFileName);
            stageFile(fileToDelete, "remove");
            restrictedDelete(fileToDelete);
        }
    }

    public static void log() {
        StringBuilder logMessage = new StringBuilder();
        Commit currentCommit = getCurrentCommit();
        while (currentCommit != null) {
            String commitLog = buildCommitMessage(currentCommit);
            logMessage.append(commitLog);
            String firstParent = currentCommit.getFirstParent();
            currentCommit = getCommit(firstParent);
        }
        logMessage.deleteCharAt(logMessage.length() - 1);
        System.out.println(logMessage);
    }

    public static void globalLog() {
        StringBuilder logMessage = new StringBuilder();
        List<String> fileNames = plainFilenamesIn(COMMITS_DIR);
        for (String filename: fileNames) {
            Commit commit = getCommit(filename);
            String commitLog = buildCommitMessage(commit);
            logMessage.append(commitLog);
        }
        System.out.println(logMessage);
    }

    public static void find(String searchMessage) {
        StringBuilder findOutput = new StringBuilder();
        List<String> fileNames = plainFilenamesIn(COMMITS_DIR);
        for (String filename: fileNames) {
            Commit commit = getCommit(filename);
            String commitMessage = commit.getMessage();
            if (commitMessage.equals(searchMessage)) {
                findOutput.append(commit.getSha1Hash()).append("\n");
            }
        }
        if (findOutput.length() > 0) {
            System.out.println(findOutput);
            return ;
        }
        System.out.println("Found no commit with that message.");
    }

    public static void status() {
//        === Branches ===
//        *master
//        other-branch
//
//        === Staged Files ===
//        wug.txt
//        wug2.txt
//
//        === Removed Files ===
//        goodbye.txt
//
//        === Modifications Not Staged For Commit ===
//        junk.txt (deleted)
//        wug3.txt (modified)
//
//        === Untracked Files ===
//        random.stuff

        StringBuilder status = new StringBuilder();
        status.append("=== Branches ===\n");

        // Find HEAD
        String currentBranch = getCurrentBranch();
        String currentBranchName = currentBranch.substring(currentBranch.lastIndexOf('/') + 1);
        status.append("*").append(currentBranchName).append("\n");

        // Find Branches
        List<String> branches = plainFilenamesIn(REFS_DIR);
        Collections.sort(branches);
        for (String branch: branches) {
            if (branch != currentBranchName) {
                status.append(branch).append("\n");
            }
        }

        status.append("\n=== Staged Files ===\n");
        // Find Staged Files
        List<String> stagedForAdditionFiles = plainFilenamesIn(STAGING_AREA);
        if (!stagedForAdditionFiles.isEmpty()) {
            // Sort the list alphabetically
            Collections.sort(stagedForAdditionFiles);
            for (String stagedFile : stagedForAdditionFiles) {
                status.append(stagedFile).append("\n");
            }
        }


        // Find Removed Files
        status.append("\n=== Removed Files ===\n");
        List<String> stagedForRemovalFiles = plainFilenamesIn(REMOVAL_AREA);
        if (!stagedForRemovalFiles.isEmpty()) {
            // Sort the list alphabetically
            Collections.sort(stagedForRemovalFiles);
            for (String stagedFile : stagedForRemovalFiles) {
                status.append(stagedFile).append("\n");
            }
        }

        System.out.println(status);
    }

    private static void checkoutBlob(Commit checkoutCommit, String filename) {
        String blobHash = checkoutCommit.getBlobHash(filename);
        if (blobHash == null) {
            System.out.println("File does not exist in that commit.");
            return ;
        }
        File blobPath = join(BLOBS_DIR, blobHash);
        Blob blob = readObject(blobPath, Blob.class);
        File filePath = join(CWD, blob.getFileName());
        restrictedDelete(filePath);
        writeContents(filePath, blob.getContents());
    }

    public static void checkout(String filename, String commitId) {
        //Takes the version of the file as it exists in the head commit
        if (commitId == null) {
            Commit currentCommit = getCurrentCommit();
            checkoutBlob(currentCommit, filename);
        }
        else {
            Commit checkoutCommit = getCommit(commitId);
            checkoutBlob(checkoutCommit, filename);
        }
        unstageFile(filename, "add");
    }

    public static void checkoutBranch(String branchName) {
    }
}
