/*
 * busbar_assembly_groups_geometry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class busbar_assembly_groups_geometry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Geometry_Tutorials");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c_g_w", "400[mm]", "Cell grid top width");
    model.param().set("c_g_l", "800[mm]", "Cell grid top length");
    model.param().set("c_g_h", "5[mm]", "Cell grid top height");
    model.param().set("s_l", "c_g_l/2-2*s_di", "Spine length");
    model.param().set("s_w", "c_g_w/2-2*s_di", "Spine width");
    model.param().set("s_h", "5[mm]", "Spine height");
    model.param().set("s_di", "10[mm]", "Spine to cell grid boundary distance");
    model.param().set("s_c_w", "65[mm]", "Spine center width");
    model.param().set("s_c_l", "60[mm]", "Spine cut out length");
    model.param().set("c_c_r", "40[mm]", "Central column radius");
    model.param().set("c_c_h", "70[mm]", "Central column height");
    model.param().set("c_c_d", "25[mm]", "Central column center hole depth");
    model.param().set("r_c_h", "6[mm]", "Rod connector height");
    model.param().set("r_c_w", "150[mm]", "Rod connector width");
    model.param().set("e_c_h", "10[mm]", "Elbow connector height");
    model.param().set("e_c_lx", "60[mm]", "Elbow connector length x-direction");
    model.param().set("e_c_lz", "80[mm]", "Elbow connector length z-direction");
    model.param().set("a_c_h", "6[mm]", "Angle connector height");
    model.param().set("a_c_w", "90[mm]", "Angle connector width");
    model.param().set("r_d", "20[mm]", "Rod diameter");
    model.param().set("r_l", "160[mm]", "Rod length");
    model.param().set("i_b_h", "10[mm]", "Intercell busbar height");
    model.param().set("i_b_l", "820[mm]", "Intercell busbar length");
    model.param().set("i_b_w", "120[mm]", "Intercell busbar width");
    model.param().set("b_di", "20[mm]", "Bolt to boundary distance");
    model.param().set("b_r", "6[mm]", "Bolt radius");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u5355\u5143\u6805\u683c\u9876\u90e8");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"c_g_w", "c_g_l", "c_g_h"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "c_g_h/2"});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u949b");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("blk1", 4);
    model.component("comp1").geom("geom1").feature("wp1").set("displ", new String[]{"-c_g_w/2+s_di", "0"});
    model.component("comp1").geom("geom1").feature("wp1").setIndex("displ", "-c_g_l/2+s_di", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_c_l", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_w/2-s_c_w/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_c_l", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_w/2-s_c_w/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_l-s_c_l", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_l-s_c_l", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_l", 5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_w", 6, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_l", 6, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_w", 7, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_l-s_c_l", 7, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_w/2+s_c_w/2", 8, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_l-s_c_l", 8, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "s_w/2+s_c_w/2", 9, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_c_l", 9, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_w", 10, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_c_l", 10, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "s_w", 11, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 11, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "s_h", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("ext1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u810a\u7ebf");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("wp2", false);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", "c_g_l/4");
    model.component("comp1").geom("geom1").feature("wp2").set("displ", new String[]{"c_g_h+s_h", "c_g_w/4"});
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_h-c_c_d", 1, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_h-c_c_d", 2, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "r_d/2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "c_c_h", 3, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "r_d/2", 3, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "c_c_h", 4, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "0.7*r_d", 4, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_h-0.8*r_d", 5, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "0.7*r_d", 5, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_h-0.8*r_d", 6, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "c_c_r", 6, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_h-1.1*r_d", 7, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "c_c_r", 7, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_h-1.1*r_d", 8, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_r-r_d/2", 8, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 9, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "c_c_r-r_d/2", 9, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", "0.3*r_d");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("point")
         .set("pol1", 5, 7);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil2").set("radius", "0.15*r_d");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil2").selection("point")
         .set("fil1", 7, 9);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "edge");
    model.component("comp1").geom("geom1").feature("rev1").selection("edge").set("wp2", 2);
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter("blk1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("rev1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("\u4e2d\u5fc3\u67f1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u6746");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_d/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "r_l");
    model.component("comp1").geom("geom1").feature("cyl1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"c_c_h-c_c_d", "0", "0"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").remove("cyl1", false);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").label("\u6746\u8fde\u63a5\u5668");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp3");
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("cyl1", 4);
    model.component("comp1").geom("geom1").feature("wp3").set("displ", new String[]{"0", "r_c_w/2-2*s_di"});
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cro1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("cro1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"a_c_w", "r_c_w"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("fil1").set("radius", "5[mm]");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dif1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dif1").selection("input2").set("cro1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "r_c_h", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").nodeGroup("grp3").remove("arr1", false);
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl1", "ext1", "ext2", "rev1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{2, 2, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"-c_g_w/2", "-c_g_l/2", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "c_g_h+s_h+c_c_h-c_c_d+r_l");
    model.component("comp1").geom("geom1").feature("wp4").set("displ", new String[]{"c_g_w/4-3*b_di+r_c_w", "0"});
    model.component("comp1").geom("geom1").feature("wp4").setIndex("displ", "c_g_l/4-a_c_w/2", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"e_c_lx", "a_c_w"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 4);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("fil1").set("radius", "5[mm]");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "e_c_h", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp5").set("quickx", "c_g_w/4-3*b_di+r_c_w+e_c_lx+2*e_c_h");
    model.component("comp1").geom("geom1").feature("wp5").set("displ", new String[]{"c_g_l/4-a_c_w/2", "0"});
    model.component("comp1").geom("geom1").feature("wp5").setIndex("displ", "c_g_h+s_h+c_c_h-c_c_d+r_l+2*e_c_h", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("size", new String[]{"a_c_w", "e_c_lz"});
    model.component("comp1").geom("geom1").feature("wp5").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 3, 4);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("fil1").set("radius", "5[mm]");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("workplane", "wp5");
    model.component("comp1").geom("geom1").feature("ext4").selection("input").set("wp5");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "e_c_h", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp6")
         .set("displ", new String[]{"c_g_w/4-3*b_di+r_c_w+e_c_lx", "0"});
    model.component("comp1").geom("geom1").feature("wp6").setIndex("displ", "c_g_h+s_h+c_c_h-c_c_d+r_l+2*e_c_h", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").set("r", "e_c_h");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").feature("wp6").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c2").set("r", "e_c_h*2");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("workplane", "wp6");
    model.component("comp1").geom("geom1").feature("ext5").selection("input").set("wp6");
    model.component("comp1").geom("geom1").feature("ext5").set("specify", "vertices");
    model.component("comp1").geom("geom1").feature("ext5").selection("vertex").set("ext3", 10, 12);
    model.component("comp1").geom("geom1").feature("ext5").set("includeinput", false);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext3", "ext4", "ext5");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp4");
    model.component("comp1").geom("geom1").nodeGroup("grp4").label("\u5f2f\u5934\u8fde\u63a5\u5668");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("ext5");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp6");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("ext4");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp5");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("ext3");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("wp4");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp7").set("quicky", "c_g_l/4-a_c_w/2");
    model.component("comp1").geom("geom1").feature("wp7")
         .set("displ", new String[]{"c_g_h+s_h+c_c_h-c_c_d+r_l", "0"});
    model.component("comp1").geom("geom1").feature("wp7").setIndex("displ", "-c_g_w/4-b_di*3+r_c_w", 1);
    model.component("comp1").geom("geom1").nodeGroup("grp4").remove("wp7", false);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "60[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "e_c_h", 2, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "90[mm]", 2, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "e_c_h", 3, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1")
         .setIndex("table", "c_g_w/2+b_di*2", 3, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1")
         .setIndex("table", "e_c_h+a_c_h", 4, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1")
         .setIndex("table", "c_g_w/2+b_di*2", 4, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1")
         .setIndex("table", "e_c_h+a_c_h", 5, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "90[mm]", 5, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "a_c_h", 6, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "60[mm]", 6, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", "a_c_h", 7, 0);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pol1").setIndex("table", 0, 7, 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("fil1").selection("point")
         .set("pol1", 2, 7);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("fil1").set("radius", "20[mm]");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("fil2").selection("point")
         .set("fil1", 5, 6);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("fil2").set("radius", "20[mm]-a_c_h");
    model.component("comp1").geom("geom1").feature("wp7").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").set("workplane", "wp7");
    model.component("comp1").geom("geom1").feature("ext6").selection("input").set("wp7");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "a_c_w", 0);
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("wp8", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp8").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp8").set("quickz", "c_g_h+s_h+c_c_h-c_c_d+r_l");
    model.component("comp1").geom("geom1").feature("wp8").set("displ", new String[]{"-c_g_w/4-b_di*3+r_c_w", "0"});
    model.component("comp1").geom("geom1").feature("wp8").setIndex("displ", "c_g_w/2-a_c_w/2", 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("r1")
         .set("size", new String[]{"c_g_w/2+b_di*2", "1"});
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("r1").setIndex("size", "a_c_w", 1);
    model.component("comp1").geom("geom1").feature("wp8").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp8").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("fil1").set("radius", "5[mm]");
    model.component("comp1").geom("geom1").feature("wp8").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp8");
    model.component("comp1").geom("geom1").feature().create("ext7", "Extrude");
    model.component("comp1").geom("geom1").feature("ext7").set("workplane", "wp8");
    model.component("comp1").geom("geom1").feature("ext7").selection("input").set("wp8");
    model.component("comp1").geom("geom1").feature("ext7").setIndex("distance", "2*e_c_h", 0);
    model.component("comp1").geom("geom1").run("ext7");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("ext6", "ext7");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp5");
    model.component("comp1").geom("geom1").nodeGroup("grp5").label("\u89d2\u8fde\u63a5\u5668");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("int1");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("ext7");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("wp8");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("ext6");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("wp7");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u7535\u6c60\u95f4\u6bcd\u7ebf\u677f");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"i_b_h", "i_b_l", "i_b_w"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-c_g_l/4+a_c_w/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "i_b_w/2", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "i_b_h/2", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("workplane", "wp5");
    model.component("comp1").geom("geom1").feature("blk2").set("axistype", "y");
    model.component("comp1").geom("geom1").nodeGroup("grp5").remove("blk2", false);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u87ba\u6813\u77ed");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "b_r");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "r_c_h+a_c_h");
    model.component("comp1").geom("geom1").feature("cyl2").set("workplane", "wp8");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"b_di", "a_c_w/4", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "-r_c_h", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("cyl2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").label("\u87ba\u6813\u957f");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "b_r");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "r_c_h+a_c_h+e_c_h");
    model.component("comp1").geom("geom1").feature("cyl3").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"b_di", "a_c_w/4", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").setIndex("pos", "-r_c_h", 2);
    model.component("comp1").geom("geom1").feature("cyl3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").label("\u87ba\u6813\u4e2d\u7b49\u957f\u5ea6");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "b_r");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "e_c_h+i_b_h");
    model.component("comp1").geom("geom1").feature("cyl4").set("workplane", "wp5");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"a_c_w/4", "b_di", "0"});
    model.component("comp1").geom("geom1").feature("cyl4").setIndex("pos", "-e_c_h", 2);
    model.component("comp1").geom("geom1").feature("cyl4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("cyl4");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "40[mm]");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("cyl2", "cyl3", "cyl4", "mov1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "c_g_l/4", "0"});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input")
         .set("cyl2", "cyl3", "cyl4", "int1", "mir1", "mov1", "uni1");
    model.component("comp1").geom("geom1").feature("mov2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov2").set("specify", "pos");
    model.component("comp1").geom("geom1").feature("mov2").selection("oldposvertex").set("arr1(2,1,1,3)", 10);
    model.component("comp1").geom("geom1").feature("mov2").selection("newposvertices").set("arr1(2,2,1,3)", 10);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u94dc");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"csel1"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"csel1", "comsel1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u7535\u89e3\u8d28\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u63a5\u5730\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 556, 601);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u70ed\u901a\u91cf\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1", "sel2"});

    model.title(null);

    model.description("");

    model.label("busbar_assembly_groups_geometry.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
