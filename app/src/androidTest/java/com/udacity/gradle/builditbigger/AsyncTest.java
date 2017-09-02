package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.paid.EndpointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


/**
 * Created by Dell on 02-09-2017.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTest{
    @Test
    public void test(){
        String string=null;
        EndpointsAsyncTask task=new EndpointsAsyncTask(getTargetContext(),null);
        task.execute();

        try {
            string = task.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Time out");
        }

        assertNotNull(string);
    }
    }

