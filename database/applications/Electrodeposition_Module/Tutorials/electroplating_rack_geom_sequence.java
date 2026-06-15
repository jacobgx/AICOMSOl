/*
 * electroplating_rack_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:58 by COMSOL 6.3.0.290. */
public class electroplating_rack_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D_opc", "40[mm]", "\u6cb9\u6cf5\u76d6\u76f4\u5f84");
    model.param().set("d_opc", "10[mm]", "\u6cb9\u7ba1\u76f4\u5f84");
    model.param().set("t_opc", "15[mm]", "\u6cb9\u6cf5\u76d6\u539a\u5ea6");
    model.param().set("n_x", "4", "x \u65b9\u5411\u9635\u5217");
    model.param().set("n_y", "5", "y \u65b9\u5411\u9635\u5217");
    model.param().set("dist_x", "0.3*D_opc", "x \u65b9\u5411\u7684\u96f6\u4ef6\u95f4\u8ddd");
    model.param().set("z_out", "10[mm]", "+z \u65b9\u5411\u7684\u4f4d\u79fb");
    model.param()
         .set("part_in", "3.4", "-z \u65b9\u5411\u7684\u96f6\u4ef6\u79fb\u52a8\uff08\u7b26\u53f7\u8868\u793a x.y\uff09");
    model.param()
         .set("part_out", "2.2", "+z \u65b9\u5411\u7684\u96f6\u4ef6\u79fb\u52a8\uff08\u7b26\u53f7\u8868\u793a x.y\uff09");
    model.param().set("dist_y", "0.4*D_opc", "y \u65b9\u5411\u7684\u96f6\u4ef6\u95f4\u8ddd");
    model.param().set("H_tank", "10*t_opc", "\u69fd\u9ad8\u5ea6");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").label("\u6cb9\u6cf5\u76d6");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1").set("r", "D_opc/2");
    model.geom("part1").feature("cyl1").set("h", "t_opc");
    model.geom("part1").run("cyl1");
    model.geom("part1").create("cyl2", "Cylinder");
    model.geom("part1").feature("cyl2").set("r", "d_opc/2");
    model.geom("part1").feature("cyl2").set("h", "t_opc");
    model.geom("part1").feature("cyl2").set("pos", new String[]{"d_opc*4/5", "0", "0"});
    model.geom("part1").run("cyl2");
    model.geom("part1").create("mir1", "Mirror");
    model.geom("part1").feature("mir1").selection("input").set("cyl2");
    model.geom("part1").feature("mir1").set("keep", true);
    model.geom("part1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.geom("part1").run("mir1");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").set("quickz", "t_opc/2");
    model.geom("part1").feature("wp1").geom().create("c1", "Circle");
    model.geom("part1").feature("wp1").geom().feature("c1").set("r", "d_opc");
    model.geom("part1").feature("wp1").geom().feature("c1").set("pos", new String[]{"d_opc*4/5", "0"});
    model.geom("part1").feature("wp1").geom().run("c1");
    model.geom("part1").feature("wp1").geom().create("mir1", "Mirror");
    model.geom("part1").feature("wp1").geom().feature("mir1").selection("input").set("c1");
    model.geom("part1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.geom("part1").feature("wp1").geom().run("mir1");
    model.geom("part1").feature("wp1").geom().create("uni1", "Union");
    model.geom("part1").feature("wp1").geom().feature("uni1").selection("input").set("c1", "mir1");
    model.geom("part1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.geom("part1").run("wp1");
    model.geom("part1").feature().create("ext1", "Extrude");
    model.geom("part1").feature("ext1").set("workplane", "wp1");
    model.geom("part1").feature("ext1").selection("input").set("wp1");
    model.geom("part1").feature("ext1").setIndex("distance", "t_opc/2", 0);
    model.geom("part1").run("ext1");
    model.geom("part1").create("cyl3", "Cylinder");
    model.geom("part1").feature("cyl3").set("r", "d_opc");
    model.geom("part1").feature("cyl3").set("h", "t_opc/2");
    model.geom("part1").feature("cyl3").set("pos", new String[]{"0", "d_opc*4/5", "t_opc*3/5"});
    model.geom("part1").run("cyl3");
    model.geom("part1").create("wp2", "WorkPlane");
    model.geom("part1").feature("wp2").set("unite", true);
    model.geom("part1").feature("wp2").set("quickz", "t_opc*3/5");
    model.geom("part1").feature("wp2").geom().create("pol1", "Polygon");
    model.geom("part1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.geom("part1").feature("wp2").geom().feature("pol1")
         .set("x", "d_opc*4/5 0 0 -d_opc*8/5 -d_opc*8/5 -d_opc*4/5 -d_opc*4/5 -d_opc*4/5 -d_opc*4/5 -d_opc*4/5");
    model.geom("part1").feature("wp2").geom().feature("pol1")
         .set("y", "-d_opc -D_opc/2 -D_opc/2 -D_opc/2 -D_opc/2 -d_opc -d_opc 0 0 0");
    model.geom("part1").run("wp2");
    model.geom("part1").feature().create("ext2", "Extrude");
    model.geom("part1").feature("ext2").set("workplane", "wp2");
    model.geom("part1").feature("ext2").selection("input").set("wp2");
    model.geom("part1").feature("ext2").set("specify", "vertices");
    model.geom("part1").feature("ext2").selection("vertex").set("cyl3", 6);
    model.geom("part1").run("ext2");
    model.geom("part1").create("cyl4", "Cylinder");
    model.geom("part1").feature("cyl4").set("r", "0.3*d_opc/2");
    model.geom("part1").feature("cyl4").set("h", "t_opc");
    model.geom("part1").feature("cyl4").set("pos", new String[]{"0.43*D_opc/sqrt(2)", "0", "0"});
    model.geom("part1").feature("cyl4").setIndex("pos", "0.43*D_opc/sqrt(2)", 1);
    model.geom("part1").run("cyl4");
    model.geom("part1").create("rot1", "Rotate");
    model.geom("part1").feature("rot1").selection("input").set("cyl4");
    model.geom("part1").feature("rot1").set("keep", true);
    model.geom("part1").feature("rot1").set("rot", "90,180,270");
    model.geom("part1").run("rot1");
    model.geom("part1").create("dif1", "Difference");
    model.geom("part1").feature("dif1").selection("input").set("cyl1");
    model.geom("part1").feature("dif1").selection("input2")
         .set("cyl2", "cyl3", "cyl4", "ext1", "ext2", "mir1", "rot1");
    model.geom("part1").run("dif1");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("\u7535\u9540\u69fd");
    model.geom("part2").create("blk1", "Block");
    model.geom("part2").feature("blk1").set("size", new String[]{"n_x*D_opc+(n_x+1)*dist_x", "1", "1"});
    model.geom("part2").feature("blk1").setIndex("size", "n_y*D_opc+(n_y+1)*dist_y", 1);
    model.geom("part2").feature("blk1").setIndex("size", "H_tank", 2);
    model.geom("part2").feature("blk1").set("base", "center");
    model.geom("part2").feature("blk1").set("pos", new String[]{"0", "0", "H_tank/2"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1")
         .set("displ", new String[]{"((D_opc+dist_x)*(1-n_x))/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi1").setIndex("displ", "((D_opc+dist_y)*(1-n_y))/2", 1);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "0", "-z_out*6"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("arr1", "Array");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"n_x", "n_y", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"D_opc+dist_x", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("displ", "D_opc+dist_x", 1);
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("cylsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("cylsel1").set("input", new String[]{"arr1"});
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "D_opc/2");
    model.component("comp1").geom("geom1").feature("cylsel1")
         .set("pos", new String[]{"-(n_x*D_opc+(n_x+1)*dist_x)/2+floor(part_in)*(dist_x+D_opc)-D_opc/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cylsel1")
         .setIndex("pos", "-(n_y*D_opc+(n_y+1)*dist_y)/2+((part_in-floor(part_in))*10)*(dist_y+D_opc)-D_opc/1.25", 1);
    model.component("comp1").geom("geom1").run("cylsel1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("cylsel1");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "z_out");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").named("arr1");
    model.component("comp1").geom("geom1").feature("rot1").selection("input")
         .set("arr1(1,1,1)", "arr1(1,2,1)", "arr1(1,3,1)", "arr1(1,4,1)", "arr1(1,5,1)", "arr1(2,1,1)", "arr1(2,2,1)", "arr1(2,3,1)", "arr1(2,4,1)", "arr1(2,5,1)", "arr1(3,1,1)", "arr1(3,2,1)", "arr1(3,3,1)", "arr1(3,5,1)", "arr1(4,1,1)", "arr1(4,2,1)", "arr1(4,3,1)", "arr1(4,4,1)", "arr1(4,5,1)", "mov1", "pi2");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").run("rot1");

    model.title(null);

    model.description("");

    model.label("electroplating_rack_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
