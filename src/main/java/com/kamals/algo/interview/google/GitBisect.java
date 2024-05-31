package com.kamals.algo.interview.google;

/**
 * Git Bisect -
 * # Git Svn
 *  # C0, C1...CN
 *  # Good(C0, compiles), Good, G, G...error(bad, not compiles), bad....bad(Head)
 *  # first bad commit?
 *  # Given - boolean isBad(c_id); collection of commits
 * Ans. TC was able to point out the optimal algo of binary search and write out correct code, test it also.
 * They identified the right time and space complexities. TC was knowledgeable to explain the case of large inputs and
 * the difference between static and python type languages to handle variables.
 *
 *   input: [c0, c1, c2, ..cm,..cn]
 *   is_bad(commit_id) -> boolean is bad or not # predefined
 * def find_first_bad_commit(commits):
 *     low, high = 0, len(commits) # 0, 3
 *     while low < high:
 *       mid = low //2  + high// 2 # 2
 *       if is_bad(mid):
 *         high = mid # 2, 2
 *       else:
 *         low = mid + 1 # 2, 3
 *
 *     return low
 */
public class GitBisect
{
}
