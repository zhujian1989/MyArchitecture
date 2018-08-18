package jzhu.com.libdata;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import jzhu.com.libdata.sharepreference.SharedPreferencesHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class SPSimpleTest {


    @Test
    public void crud() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        SharedPreferencesHelper.put(appContext,"name","jzhu");

        String name = (String)SharedPreferencesHelper.get(appContext,"name","");
        assertEquals(name,"jzhu");

        SharedPreferencesHelper.clear(appContext);
        String name1 = (String)SharedPreferencesHelper.get(appContext,"name","");
        assertNotEquals(name1,"jzhu");

        SharedPreferencesHelper.put(appContext,"name","jzhu");
        SharedPreferencesHelper.put(appContext,"age",29);
        Map<String,?> map = SharedPreferencesHelper.getAll(appContext);

        assertEquals("jzhu",map.get("name"));
        assertEquals(29,map.get("age"));


    }


}
