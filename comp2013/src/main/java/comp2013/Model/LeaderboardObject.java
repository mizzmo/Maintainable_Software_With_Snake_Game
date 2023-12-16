package comp2013.Model;

/**
 * Contains information about each leaderboard item.
 *
 * @param username The username associated with this leaderboard item.
 * @param score    The score associated with this leaderboard item.
 * @author Toby Surtees
 */
public record LeaderboardObject(String username, int score) implements Comparable<LeaderboardObject> {
    /**
     * Constructs a new LeaderboardItem with the specified username and score.
     *
     * @param username the username of the user
     * @param score    the score achieved by the user
     */
    public LeaderboardObject {
    }

    /**
     * Gets the username associated with this leaderboard item.
     *
     * @return the username
     */
    @Override
    public String username() {
        return username;
    }

    /**
     * Gets the score associated with this leaderboard item.
     *
     * @return the score
     */
    @Override
    public int score() {
        return score;
    }

    /**
     * Compares this leaderboard item with another leaderboard item based on their scores.
     *
     * @param other the other leaderboard item to compare
     * @return a negative, zero, or a positive if this item is less than,
     * equal to, or greater than the other item
     */
    @Override
    public int compareTo(LeaderboardObject other) {
        // Compare scores in descending order
        return Integer.compare(other.score(), this.score);
    }
}
