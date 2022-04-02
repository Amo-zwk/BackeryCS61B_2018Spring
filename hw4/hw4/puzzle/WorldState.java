package hw4.puzzle;

public interface WorldState {

    /** Provides an estimate of the number of moves to reach the goal.
     * 提供达到目标的步数的估计
     * Must be less than or equal to the correct distance.
     * 必须小于或等于正确的距离
     * */
    int estimatedDistanceToGoal(); //返回该word到goal的最小编辑距离,因为编辑的距离有多个答案

    /** Provides an iterable of all the neighbors of this WorldState.
     * 提供该WorldState的所有邻居的可迭代对象
     * */
    Iterable<WorldState> neighbors(); //返回该word的neighbors

    //判断是否找到了goal,因为最小编辑距离为0了
    default boolean isGoal() {
        return estimatedDistanceToGoal() == 0;
    }


    // 1 . Keep a priority queue of “move sequences 把所有neighbor加入优先队列
    // 2 . Remove the “best” move sequence from the PQ 让优先级最低的出队,
    // 优先级计算已经给出,称优先级最低的WorldState为BMS
    // 3 . 设 F 为 BMS 中的最后一个状态
    // 4 . 如果 F 是目标状态，你就完成了，所以返回 BMS
    // 5 . 如果 F 不是目标，则对于 F 的每个 neighbor N，创建一个由
    // BMS + N 组成的新移动序列并将其放入 PQ(优先队列)





    //["house", "worse", "hose", "horses"]
    //horse --->   horses --->  worse --->
    //current WorldState mug() the goal is rugs ----- (mug,2)  -->
    // its neighbors are bug and mag and mud and rug
    //recursive solve the bug and the mag and the mud
    //example : (bug,2) , (mag,3) and so on
    // PQ :
    // 优先级 ：initial state (初始世界状态到现在世界状态的移动次数)
    // plus (加上) the WorldState’s estimatedDistanceToGoal(到达到目标的距离)
}
