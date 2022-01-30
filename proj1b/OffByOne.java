public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y) {
        //题目要求
        return x - y == 1 || y - x == 1 ;
    }
}
