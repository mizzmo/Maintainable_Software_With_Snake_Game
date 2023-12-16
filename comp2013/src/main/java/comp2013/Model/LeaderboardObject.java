package comp2013.Model;

/**
 * @author Toby Surtees
 * Contains information about each leaderboard item.
 */
public class LeaderboardObject implements Comparable<LeaderboardObject> {
    /**
     * The username associated with this leaderboard item.
     */
    private String username;

    /**
     * The score associated with this leaderboard item.
     */
    private int score;

    /**
     * Constructs a new LeaderboardItem with the specified username and score.
     * @param username the username of the user
     * @param score    the score achieved by the user
     */
    public LeaderboardObject(String username, int score) {
        this.username = username;
        this.score = score;
    }

    /**
     * Gets the username associated with this leaderboard item.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the score associated with this leaderboard item.
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares this leaderboard item with another leaderboard item based on their scores.
     * @param other the other leaderboard item to compare
     * @return a negative, zero, or a positive if this item is less than,
     * equal to, or greater than the other item
     */
    @Override
    public int compareTo(LeaderboardObject other) {
        // Compare scores in descending order
        return Integer.compare(other.getScore(), this.score);
    }
}
