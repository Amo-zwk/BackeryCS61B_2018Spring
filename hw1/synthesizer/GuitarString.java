//  Make sure to make this class a part of the synthesizer package
package synthesizer;

//Make sure this class is public
public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /**
     * 按照提示来就好了
     */

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        //       Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity); //这个类有该构造方法
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    /**
     * 按照提示来就好了
     */

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        //队列不为空的时候，一直出队
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        //队列不满的时候，一直入队
        while (!buffer.isFull()) {
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /**
     * 按照提示来就好了
     */
    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        //       Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        //题目有图说明
        double r1 = buffer.dequeue();
        double r2 = buffer.peek();
        double update = DECAY * (r1 + r2) / 2;
        buffer.enqueue(update);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        //  Return the correct thing.
        return buffer.peek();
    }
}

