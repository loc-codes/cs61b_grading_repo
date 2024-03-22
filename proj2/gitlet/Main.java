package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Lachlan Young
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String filename;
        String commitMessage;
        String command = args[0];
        switch(command) {
            case "init":
                Repository.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                filename = args[1];
                Repository.add(filename);
                break;
            case "commit":
                if (args.length < 2) {
                    System.out.println("Please enter a commit commitMessage.");
                    System.exit(0);
                }
                commitMessage = args[1];
                Repository.commit(commitMessage);
                break;
            case "rm":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                filename = args[1];
                Repository.rm(filename);
                break;
            case "log":
                Repository.log();
                break;
            case "global-log":
                Repository.globalLog();
                break;
            case "find":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                commitMessage = args[1];
                Repository.find(commitMessage);
                break;
            case "status":
                Repository.status();
                break;
            case "checkout":
                if (args[1].equals("--")) {
                    filename = args[2];
                    Repository.checkout(filename, null);
                } else if (args[2].equals("--")) {
                    String commitId = args[1];
                    filename = args[3];
                    Repository.checkout(filename, commitId);
                } else {
                    String branchName = args[2];
                    Repository.checkoutBranch(branchName);
                }
                break;
        }
    }
}
