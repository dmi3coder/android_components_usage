package io.github.dmi3coder.roommy.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by dim3coder on 6/28/17.
 */

@Dao
public interface GravityItemDao {

  @Query("SELECT * FROM gravityItem")
  List<GravityItem> getAll();

  @Insert
  void insertAll(GravityItem... gravityItems);

}
