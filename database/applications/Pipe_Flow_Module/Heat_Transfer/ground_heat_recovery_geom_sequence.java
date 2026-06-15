/*
 * ground_heat_recovery_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:57 by COMSOL 6.3.0.290. */
public class ground_heat_recovery_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("\u6a21\u5f0f 1");
    model.geom("part1").create("ls1", "LineSegment");
    model.geom("part1").feature("ls1").set("specify1", "coord");
    model.geom("part1").feature("ls1").set("specify2", "coord");
    model.geom("part1").feature("ls1").set("coord1", new double[]{2.5, 20});
    model.geom("part1").feature("ls1").set("coord2", new double[]{2.5, 2});
    model.geom("part1").run("ls1");
    model.geom("part1").create("pol1", "Polygon");
    model.geom("part1").feature("pol1").set("source", "table");
    model.geom("part1").feature("pol1").set("type", "open");
    model.geom("part1").feature("pol1").set("source", "vectors");
    model.geom("part1").feature("pol1").set("x", "2.5 4 4 4 4 5.5 5.5 5.5");
    model.geom("part1").feature("pol1").set("y", "2 2 2 16.5 16.5 16.5 16.5 2");
    model.geom("part1").run("pol1");
    model.geom("part1").create("copy1", "Copy");
    model.geom("part1").feature("copy1").selection("input").set("pol1");
    model.geom("part1").feature("copy1").set("displx", "3 6");
    model.geom("part1").run("copy1");
    model.geom("part1").create("pol2", "Polygon");
    model.geom("part1").feature("pol2").set("source", "table");
    model.geom("part1").feature("pol2").set("type", "open");
    model.geom("part1").feature("pol2").set("source", "vectors");
    model.geom("part1").feature("pol2").set("x", "11.5 13 13 13 13 3 3 3");
    model.geom("part1").feature("pol2").set("y", "2 2 2 17.5 17.5 17.5 17.5 20");
    model.geom("part1").run("pol2");
    model.geom("part1").create("uni1", "Union");
    model.geom("part1").feature("uni1").selection("input").set("copy1", "ls1", "pol1", "pol2");
    model.geom("part1").run("uni1");
    model.geom("part1").create("fil1", "Fillet");
    model.geom("part1").feature("fil1").selection("point")
         .set("uni1", 1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    model.geom("part1").feature("fil1").set("radius", 0.5);
    model.geom("part1").run("fil1");
    model.geom().create("part2", "Part", 2);
    model.geom("part2").label("\u6a21\u5f0f 2");
    model.geom("part2").create("pol1", "Polygon");
    model.geom("part2").feature("pol1").set("source", "vectors");
    model.geom("part2").feature("pol1").set("type", "open");
    model.geom("part2").feature("pol1").set("x", "2.5 2.5 2.5 13");
    model.geom("part2").feature("pol1").set("y", "20 2 2 2");
    model.geom("part2").run("pol1");
    model.geom("part2").create("pol2", "Polygon");
    model.geom("part2").feature("pol2").set("source", "table");
    model.geom("part2").feature("pol2").set("type", "open");
    model.geom("part2").feature("pol2").set("source", "vectors");
    model.geom("part2").feature("pol2").set("x", "13 13 13 5 5 5 5 13");
    model.geom("part2").feature("pol2").set("y", "2 4 4 4 4 5 5 5");
    model.geom("part2").run("pol2");
    model.geom("part2").create("copy1", "Copy");
    model.geom("part2").feature("copy1").selection("input").set("pol2");
    model.geom("part2").feature("copy1").set("disply", "3 6 9");
    model.geom("part2").run("copy1");
    model.geom("part2").create("pol3", "Polygon");
    model.geom("part2").feature("pol3").set("source", "table");
    model.geom("part2").feature("pol3").set("type", "open");
    model.geom("part2").feature("pol3").set("source", "vectors");
    model.geom("part2").feature("pol3").set("x", "13 13 13 4 4 4 4 12");
    model.geom("part2").feature("pol3").set("y", "14 16.5 16.5 16.5 16.5 15.5 15.5 15.5");
    model.geom("part2").run("pol3");
    model.geom("part2").create("pol4", "Polygon");
    model.geom("part2").feature("pol4").set("source", "table");
    model.geom("part2").feature("pol4").set("type", "open");
    model.geom("part2").feature("pol4").set("source", "vectors");
    model.geom("part2").feature("pol4").set("x", "12 12 12 4 4 4 4 12");
    model.geom("part2").feature("pol4").set("y", "15.5 14.5 14.5 14.5 14.5 12.5 12.5 12.5");
    model.geom("part2").run("pol4");
    model.geom("part2").create("copy2", "Copy");
    model.geom("part2").feature("copy2").selection("input").set("pol4");
    model.geom("part2").feature("copy2").set("disply", "-3 -6 -9");
    model.geom("part2").run("copy2");
    model.geom("part2").create("pol5", "Polygon");
    model.geom("part2").feature("pol5").set("source", "table");
    model.geom("part2").feature("pol5").set("type", "open");
    model.geom("part2").feature("pol5").set("source", "vectors");
    model.geom("part2").feature("pol5").set("x", "12 12 12 3 3 3");
    model.geom("part2").feature("pol5").set("y", "3.5 2.5 2.5 2.5 2.5 20");
    model.geom("part2").run("pol5");
    model.geom("part2").create("uni1", "Union");
    model.geom("part2").feature("uni1").selection("input")
         .set("copy1", "copy2", "pol1", "pol2", "pol3", "pol4", "pol5");
    model.geom("part2").run("uni1");
    model.geom("part2").create("fil1", "Fillet");
    model.geom("part2").feature("fil1").selection("point")
         .set("uni1", 1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42);
    model.geom("part2").feature("fil1").set("radius", 0.5);
    model.geom("part2").run("fil1");
    model.geom().create("part3", "Part", 2);
    model.geom("part3").label("\u6a21\u5f0f 3");
    model.geom("part3").create("pol1", "Polygon");
    model.geom("part3").feature("pol1").set("source", "table");
    model.geom("part3").feature("pol1").set("type", "open");
    model.geom("part3").feature("pol1").set("source", "vectors");
    model.geom("part3").feature("pol1")
         .set("x", "2.5 2.5 2.5 13 13 13 13 4 4 4 4 11 11 11 11 6 6 6 6 9 9 9 9 8 8 8 8 7 7 7 7 10 10 10 10 5 5 5 5 12 12 12 12 3 3 3");
    model.geom("part3").feature("pol1")
         .set("y", "20 2 2 2 2 17 17 17 17 4 4 4 4 15 15 15 15 6 6 6 6 13 13 13 13 7 7 7 7 14 14 14 14 5 5 5 5 16 16 16 16 3 3 3 3 20");
    model.geom("part3").run("pol1");
    model.geom("part3").create("fil1", "Fillet");
    model.geom("part3").feature("fil1").selection("pointinsketch")
         .set("pol1", 1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.geom("part3").feature("fil1").set("radius", 0.5);
    model.geom("part3").run("fil1");

    model.title(null);

    model.description("");

    model.label("ground_heat_recovery_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
