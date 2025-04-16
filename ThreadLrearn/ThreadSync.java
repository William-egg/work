package ThreadLrearn;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 1. synchronized 同步代码块
 *    一般来说，syncchronized要锁住不同对象的操作的，所以一般是用this
 * 2. synchronized 同步方法
 *      其实这个就是同步类了
 * 3. Lock锁 用ReentrantLock实例化
 *
 * 他们的优缺点显而易见啦，sync的话会方便一点，但是lock的话虽然不方便但是可操作性大，里面还有内置函数实现公平锁，不公平锁
 *
 *
 * */
public class ThreadSync {
    static class Account {
        int balance = 1000;
        Lock lock = new ReentrantLock();
        public void withdraw(int amount) throws InterruptedException {
//                lock.lock();
//            synchronized (this) { // 锁住当前对象
                if (balance >= amount) {
                    sleep(10);
                    balance -= amount;
                    System.out.println(Thread.currentThread().getName()+" withdraw " + amount + ", remaining balance: " + balance);
                } else {
                    System.out.println(Thread.currentThread().getName()+" withdraw " + amount + ", insufficient balance");
                }
//                lock.unlock();
            }
//        }

    }
    public static void main(String[] args) {
        Account account = new Account();
        for(int i = 0;i<4;i++){
            new Thread(()->{
                try {
                    account.withdraw(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
