package io.github.dmi3coder.roommy.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by dim3coder on 6/28/17.
 */

@Entity
public class GravityItem {

  @PrimaryKey
  private int uid;

  @ColumnInfo(name = "x_data")
  private Double x;

  @ColumnInfo(name = "y_data")
  private Double y;

  public GravityItem() {
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("GravityItem{");
    sb.append("uid=").append(uid);
    sb.append(", x=").append(x);
    sb.append(", y=").append(y);
    sb.append('}');
    return sb.toString();
  }
}
