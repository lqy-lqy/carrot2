package org.carrot2.dcs;

import org.carrot2.util.tests.CarrotTestCase;
import org.junit.Test;

public class RateLimiterTest extends CarrotTestCase
{
    @Test
    public void noLimits() throws InterruptedException
    {
        final RateLimiter<String> limiter = RateLimiter.<String> create().build();
        for (int i = 0; i < randomIntBetween(50, 5000); i++)
        {
            assertTrue(limiter.check("X"));
        }
    }

    @Test
    public void oneKeySingleLimit() throws InterruptedException
    {
        final RateLimiter<String> limiter = RateLimiter.<String> create().limitTo(2, 200)
            .build();

        for (int i = 0; i < randomIntBetween(2, 5); i++)
        {
            assertTrue(limiter.check("X"));
            assertTrue(limiter.check("X"));
            assertFalse(limiter.check("X"));
            assertFalse(limiter.check("X"));
            Thread.sleep(250);
        }
    }

    @Test
    public void manyKeysSingleLimit() throws InterruptedException
    {
        final RateLimiter<String> limiter = RateLimiter.<String> create().limitTo(2, 200)
            .build();

        for (int i = 0; i < randomIntBetween(2, 5); i++)
        {
            assertTrue(limiter.check("X"));
            assertTrue(limiter.check("Y"));
            assertTrue(limiter.check("X"));
            assertTrue(limiter.check("Y"));
            assertFalse(limiter.check("X"));
            assertFalse(limiter.check("Y"));
            Thread.sleep(250);
        }
    }

    @Test
    public void oneKeyManyLimits() throws InterruptedException
    {
        final RateLimiter<String> limiter = RateLimiter.<String> create().limitTo(2, 100)
            .limitTo(3, 400).limitTo(4, 2500).build();

        assertTrue(limiter.check("X"));
        assertTrue(limiter.check("X"));
        assertFalse(limiter.check("X")); // 2 / 100 exceeded
        Thread.sleep(2500);              // reset

        assertTrue(limiter.check("X"));
        assertTrue(limiter.check("X"));
        Thread.sleep(120);               // don't exceed 2 / 100
        assertTrue(limiter.check("X"));
        assertFalse(limiter.check("X")); // 3 / 400 exceeded
        Thread.sleep(2500);              // reset

        assertTrue(limiter.check("X"));
        assertTrue(limiter.check("X"));
        Thread.sleep(120);               // don't exceed 2 / 100
        assertTrue(limiter.check("X"));
        Thread.sleep(400);               // don't exceed 3 / 400
        assertTrue(limiter.check("X"));
        assertFalse(limiter.check("X")); // 4 / 2500 exceeded
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nonIncreasingPeriods()
    {
        RateLimiter.<String>create().limitTo(2, 100).limitTo(3, 50);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nonIncreasingRates()
    {
        RateLimiter.<String>create().limitTo(2, 100).limitTo(3, 120);
    }
}
