package cz.cvut.wpa.forum.provider;

/**
 *
 * @author mickapa1
 */
public interface HashProvider {
    
    /**
     * Calculates hash of the given string
     * @param s string
     * @return hash(s)
     */
    public String computeHash(String s);
    
}