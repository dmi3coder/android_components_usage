package io.github.dmi3coder.roommy.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by dim3coder on 6/28/17.
 */

@Database(entities = {GravityItem.class},version = 1)
public abstract class AppDatabase  extends RoomDatabase{
  public abstract GravityItemDao gravityItemDao();


}
