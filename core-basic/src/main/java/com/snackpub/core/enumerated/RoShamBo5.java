//: enumerated/RoShamBo5.java
// Multiple dispatching using an EnumMap of EnumMaps.
package com.snackpub.core.enumerated;

import java.util.EnumMap;

import static com.snackpub.core.enumerated.Outcome.*;

/**
 * 使用 EnumMap 分发
 * @date 2021/10/03
 */
enum RoShamBo5 implements Competitor<RoShamBo5> {
  PAPER, SCISSORS, ROCK;
  static EnumMap<RoShamBo5,EnumMap<RoShamBo5,Outcome>> table = new EnumMap<>(RoShamBo5.class);
  // 使用静态语句来初始化EnumMap对象
  static {
    for(RoShamBo5 it : RoShamBo5.values())
      // 将enum实例化作为key存入enumMap.
      table.put(it, new EnumMap<>(RoShamBo5.class));
    initRow(PAPER, DRAW, LOSE, WIN);
    initRow(SCISSORS, WIN, DRAW, LOSE);
    initRow(ROCK, LOSE, WIN, DRAW);
  }	
  static void initRow(RoShamBo5 it,
    Outcome vPAPER, Outcome vSCISSORS, Outcome vROCK) {
    EnumMap<RoShamBo5,Outcome> row = RoShamBo5.table.get(it);
    row.put(RoShamBo5.PAPER, vPAPER);
    row.put(RoShamBo5.SCISSORS, vSCISSORS);
    row.put(RoShamBo5.ROCK, vROCK);
  }
  public Outcome compete(RoShamBo5 it) {
    // 注意这里，在一行语句中发生了两次分发.
    return table.get(this).get(it);
  }
  public static void main(String[] args) {
    RoShamBo.play(RoShamBo5.class, 20);
  }
} /* Same output as RoShamBo2.java *///:~
