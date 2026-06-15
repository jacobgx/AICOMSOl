/*
 * stack_cooling_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:11 by COMSOL 6.3.0.290. */
public class stack_cooling_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_plate", "24[cm]", "\u6d3b\u6027\u533a\u57df\u957f\u5ea6");
    model.param().set("W_plate", "10[cm]", "\u6d3b\u6027\u533a\u57df\u5bbd\u5ea6");
    model.param().set("W_manifold", "0.9*W_plate", "\u6b67\u7ba1\u5bbd\u5ea6");
    model.param().set("L_manifold", "0.1*W_plate", "\u6b67\u7ba1\u957f\u5ea6");
    model.param().set("D_cc", "10[mm]", "\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("D_bpp", "1[mm]", "\u53cc\u6781\u677f\u539a\u5ea6");
    model.param().set("D_gdl", "200[um]", "\u6c14\u4f53\u6269\u6563\u5c42\u539a\u5ea6");
    model.param().set("D_mem", "30[um]", "\u819c\u539a\u5ea6");
    model.param().set("D_cell", "D_bpp+2*D_gdl+D_mem", "\u7535\u6c60\u5355\u5143\u539a\u5ea6");
    model.param().set("N_cells", "5", "\u7535\u6c60\u5355\u5143\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_plate+L_manifold*2", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .setIndex("size", "L_plate+L_manifold*2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-L_manifold", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "-L_manifold", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1").setIndex("p", "-L_manifold", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "W_plate/2+W_manifold/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt2").setIndex("p", "-L_manifold", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt2")
         .setIndex("p", "L_plate-(W_plate/2+W_manifold/2)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pt2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u9988\u6d41\u4f53\u677f");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "D_cc", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "D_cc");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"W_plate", "L_plate"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", "W_plate", 1, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "W_plate/2+W_manifold/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "-L_manifold", 2, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "W_plate/2-W_manifold/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .setIndex("table", "-L_manifold", 3, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1")
         .set("pos", new String[]{"W_plate/2", "L_plate/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("rot1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2")
         .label("\u672b\u7aef\u51b7\u5374\u677f\uff0c\u8d1f\u6781\u4fa7");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "D_bpp/2", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u8d1f\u6781\u672b\u7aef\u57df");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"ext1", "ext2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "D_cc+D_bpp/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"W_plate", "L_plate"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", "-L_manifold", 1, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", "W_plate/2-W_manifold/2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", "-L_manifold", 2, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", "W_plate/2+W_manifold/2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", "W_plate", 3, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").setIndex("table", "W_plate", 1, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .setIndex("table", "W_plate/2+W_manifold/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .setIndex("table", "-L_manifold", 2, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .setIndex("table", "W_plate/2-W_manifold/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .setIndex("table", "-L_manifold", 3, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").selection("input")
         .set("pol1", "pol2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1")
         .set("pos", new String[]{"W_plate/2", "L_plate/2"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls1").selection("vertex1").set("pol1", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls1").selection("vertex2").set("pol2", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls2").selection("vertex1")
         .set("rot1(2)", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls2").selection("vertex2")
         .set("rot1(1)", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls2");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u6c22\u6c14\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("wp3", 1, 7);
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "D_bpp/2", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().duplicate("ext4", "ext3");
    model.component("comp1").geom("geom1").feature("ext4").label("\u51b7\u5374\u6b67\u7ba1\uff0c\u6c22\u6c14\u4fa7");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").clear("wp3");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").set("wp3", 5, 3);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().duplicate("ext5", "ext4");
    model.component("comp1").geom("geom1").feature("ext5").label("\u6c22\u6c14\u901a\u9053\uff0cx \u65b9\u5411");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").clear("wp3");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("wp3", 2, 6);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").feature().duplicate("ext6", "ext5");
    model.component("comp1").geom("geom1").feature("ext6").label("\u6c22\u6c14\u901a\u9053\uff0cy \u65b9\u5411");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").clear("wp3");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").set("wp3", 4);
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("wp3");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").feature().create("ext7", "Extrude");
    model.component("comp1").geom("geom1").feature("ext7").label("\u6c22\u6c14 GDL");
    model.component("comp1").geom("geom1").feature("ext7").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext7").selection("inputface").set("ext5", 4, 9);
    model.component("comp1").geom("geom1").feature("ext7").selection("inputface").set("ext6", 4);
    model.component("comp1").geom("geom1").feature("ext7").setIndex("distance", "D_gdl", 0);
    model.component("comp1").geom("geom1").feature("ext7").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext7").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").run("ext7");
    model.component("comp1").geom("geom1").feature().create("ext8", "Extrude");
    model.component("comp1").geom("geom1").feature("ext8").label("\u819c\uff0c\u6c22\u6c14\u4fa7");
    model.component("comp1").geom("geom1").feature("ext8").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext8").selection("inputface").set("ext7(1)", 4, 9);
    model.component("comp1").geom("geom1").feature("ext8").selection("inputface").set("ext7(2)", 4);
    model.component("comp1").geom("geom1").feature("ext8").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext8").setIndex("distance", "D_mem/2", 0);
    model.component("comp1").geom("geom1").feature("ext8").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext8");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2")
         .label("\u91cd\u590d\u7535\u6c60\uff0c\u6c22\u6c14\u4fa7");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"ext3", "ext4", "ext5", "ext6", "ext7", "ext8"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "D_cc+D_bpp/2+D_cell");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"W_plate", "L_plate"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "W_plate", 0, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "W_plate", 1, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").setIndex("table", "W_plate", 1, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "W_plate+L_manifold", 2, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "W_plate/2+W_manifold/2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "W_plate+L_manifold", 3, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .setIndex("table", "W_plate/2-W_manifold/2", 3, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2").setIndex("table", "W_plate", 1, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2")
         .setIndex("table", "W_plate/2+W_manifold/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2")
         .setIndex("table", "-L_manifold", 2, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2")
         .setIndex("table", "W_plate/2-W_manifold/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2")
         .setIndex("table", "-L_manifold", 3, 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("rot1").selection("input")
         .set("pol1", "pol2");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("rot1")
         .set("pos", new String[]{"W_plate/2", "L_plate/2"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls1").selection("vertex1").set("pol2", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls1").selection("vertex2").set("pol1", 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls2").selection("vertex1")
         .set("rot1(1)", 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls2").selection("vertex2")
         .set("rot1(2)", 1);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext9", "Extrude");
    model.component("comp1").geom("geom1").feature("ext9").label("\u6c27\u6c14\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("ext9").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext9").selection("inputface").set("wp4", 1, 7);
    model.component("comp1").geom("geom1").feature("ext9").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext9").setIndex("distance", "D_bpp/2", 0);
    model.component("comp1").geom("geom1").feature("ext9").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext9").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext9");
    model.component("comp1").geom("geom1").feature().duplicate("ext10", "ext9");
    model.component("comp1").geom("geom1").feature("ext10")
         .label("\u51b7\u5374\u6b67\u7ba1\uff0c\u6c27\u6c14\u4fa7");
    model.component("comp1").geom("geom1").feature("ext10").selection("inputface").clear("wp4");
    model.component("comp1").geom("geom1").feature("ext10").selection("inputface").set("wp4", 6, 3);
    model.component("comp1").geom("geom1").run("ext10");
    model.component("comp1").geom("geom1").feature().duplicate("ext11", "ext10");
    model.component("comp1").geom("geom1").feature("ext11").label("\u6c27\u6c14\u901a\u9053\uff0cx \u65b9\u5411");
    model.component("comp1").geom("geom1").feature("ext11").selection("inputface").clear("wp4");
    model.component("comp1").geom("geom1").feature("ext11").selection("inputface").set("wp4", 4, 5);
    model.component("comp1").geom("geom1").run("ext11");
    model.component("comp1").geom("geom1").feature().duplicate("ext12", "ext11");
    model.component("comp1").geom("geom1").feature("ext12").label("\u6c27\u6c14\u901a\u9053\uff0cy \u65b9\u5411");
    model.component("comp1").geom("geom1").feature("ext12").selection("inputface").clear("wp4");
    model.component("comp1").geom("geom1").feature("ext12").selection("inputface").set("wp4", 2);
    model.component("comp1").geom("geom1").run("ext12");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init();
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("wp4");
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").feature().create("ext13", "Extrude");
    model.component("comp1").geom("geom1").feature("ext13").label("\u6c27\u6c14 GDL");
    model.component("comp1").geom("geom1").feature("ext13").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext13").selection("inputface").set("ext11", 3, 7);
    model.component("comp1").geom("geom1").feature("ext13").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext13").setIndex("distance", "D_gdl", 0);
    model.component("comp1").geom("geom1").feature("ext13").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext13").selection("inputface").set("ext11", 3, 7);
    model.component("comp1").geom("geom1").feature("ext13").selection("inputface").set("ext12", 3);
    model.component("comp1").geom("geom1").run("ext13");
    model.component("comp1").geom("geom1").feature().create("ext14", "Extrude");
    model.component("comp1").geom("geom1").feature("ext14").label("\u819c\uff0c\u6c27\u6c14\u4fa7");
    model.component("comp1").geom("geom1").feature("ext14").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext14").selection("inputface").set("ext13(1)", 3, 7);
    model.component("comp1").geom("geom1").feature("ext14").selection("inputface").set("ext13(2)", 3);
    model.component("comp1").geom("geom1").feature("ext14").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext14").setIndex("distance", "D_mem/2", 0);
    model.component("comp1").geom("geom1").feature("ext14").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext14");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3")
         .label("\u91cd\u590d\u5355\u5143\uff0c\u6c27\u6c14\u4fa7");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"ext9", "ext10", "ext11", "ext12", "ext13", "ext14"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "1", "N_cells"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "D_cell"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").feature().duplicate("arr2", "arr1");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").named("unisel3");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickz", "N_cells*D_cell+D_cc*2+D_bpp");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("size", new String[]{"W_plate+L_manifold*2", "1"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .setIndex("size", "L_plate+L_manifold*2", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("pos", new String[]{"-L_manifold", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1").setIndex("pos", "-L_manifold", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pt1").setIndex("p", "-L_manifold", 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pt1")
         .setIndex("p", "W_plate/2+W_manifold/2", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pt2").setIndex("p", "-L_manifold", 0);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pt2")
         .setIndex("p", "L_plate-(W_plate/2+W_manifold/2)", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("pt2");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext15", "Extrude");
    model.component("comp1").geom("geom1").feature("ext15").label("\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("ext15").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext15").setIndex("distance", "D_cc", 0);
    model.component("comp1").geom("geom1").feature("ext15").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext15").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext15");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickz", "N_cells*D_cell+D_cc+D_bpp");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("size", new String[]{"W_plate", "L_plate"});
    model.component("comp1").geom("geom1").feature("wp6").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").setIndex("table", "W_plate", 1, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1")
         .setIndex("table", "W_plate/2+W_manifold/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1")
         .setIndex("table", "-L_manifold", 2, 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1")
         .setIndex("table", "W_plate/2-W_manifold/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pol1")
         .setIndex("table", "-L_manifold", 3, 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("rot1")
         .set("pos", new String[]{"W_plate/2", "L_plate/2"});
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext16", "Extrude");
    model.component("comp1").geom("geom1").feature("ext16")
         .label("\u672b\u7aef\u51b7\u5374\u677f\uff0c\u6b63\u6781\u4fa7");
    model.component("comp1").geom("geom1").feature("ext16").set("inputhandling", "remove");
    model.component("comp1").geom("geom1").feature("ext16").setIndex("distance", "D_bpp/2", 0);
    model.component("comp1").geom("geom1").feature("ext16").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext16").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext16");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u6b63\u6781\u672b\u7aef\u57df");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"ext15", "ext16"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").label("\u6c22\u6c14\u4fa7\u57df");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel5").set("input", new String[]{"unisel1", "unisel2"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("unisel6").label("\u6c27\u6c14\u4fa7\u57df");
    model.component("comp1").geom("geom1").feature("unisel6").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel6").set("input", new String[]{"unisel3", "unisel4"});
    model.component("comp1").geom("geom1").run("unisel6");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("unisel5");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("unisel6");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("unisel7", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel7").label("\u819c");
    model.component("comp1").geom("geom1").feature("unisel7").set("input", new String[]{"ext8", "ext14"});
    model.component("comp1").geom("geom1").run("unisel7");
    model.component("comp1").geom("geom1").create("unisel8", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel8").label("\u6c22\u6c14\u901a\u9053\u548c\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel8").set("input", new String[]{"ext3", "ext5", "ext6"});
    model.component("comp1").geom("geom1").run("unisel8");
    model.component("comp1").geom("geom1").create("unisel9", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel9").label("\u6c27\u6c14\u901a\u9053\u548c\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel9").set("input", new String[]{"ext9", "ext11", "ext12"});
    model.component("comp1").geom("geom1").run("unisel9");
    model.component("comp1").geom("geom1").create("unisel10", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel10").label("\u51b7\u5374\u901a\u9053\u548c\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel10")
         .set("input", new String[]{"ext2", "ext4", "ext5", "ext6", "ext10", "ext11", "ext12", "ext16"});
    model.component("comp1").geom("geom1").run("unisel10");
    model.component("comp1").geom("geom1").create("unisel11", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel11")
         .label("\u96c6\u6d41\u4f53\u548c\u9988\u6d41\u4f53\u677f");
    model.component("comp1").geom("geom1").feature("unisel11").set("input", new String[]{"ext1", "ext15"});
    model.component("comp1").geom("geom1").run("unisel11");
    model.component("comp1").geom("geom1").create("unisel12", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel12").label("\u672b\u7aef\u51b7\u5374\u677f");
    model.component("comp1").geom("geom1").feature("unisel12").set("input", new String[]{"ext2", "ext16"});
    model.component("comp1").geom("geom1").run("unisel12");
    model.component("comp1").geom("geom1").create("unisel13", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel13").label("\u5185\u90e8\u51b7\u5374\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel13").set("input", new String[]{"ext4", "ext10"});
    model.component("comp1").geom("geom1").run("unisel13");
    model.component("comp1").geom("geom1").create("unisel14", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel14")
         .label("\u975e\u6c14\u4f53\u51b7\u5374\u901a\u9053\u548c\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel14").set("input", new String[]{"unisel12", "unisel13"});
    model.component("comp1").geom("geom1").run("unisel14");
    model.component("comp1").geom("geom1").create("unisel15", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel15").label("GDL");
    model.component("comp1").geom("geom1").feature("unisel15").set("input", new String[]{"ext7", "ext13"});
    model.component("comp1").geom("geom1").run("unisel15");
    model.component("comp1").geom("geom1").create("unisel16", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel16").label("\u6c14\u4f53\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel16").set("input", new String[]{"ext3", "ext9"});
    model.component("comp1").geom("geom1").run("unisel16");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("\u6c22\u6c14\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"ext7", "ext8"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").label("\u6c27\u6c14\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("intsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"ext13", "ext14"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u51b7\u5374\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "W_plate");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "-L_manifold/2");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u51b7\u5374\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "L_plate+L_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u6c22\u6c14\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "-L_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "L_plate/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "D_cc");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "N_cells*D_cell+D_cc+D_bpp*2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel4", "boxsel3");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u6c22\u6c14\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "W_plate+L_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "L_plate/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel5", "boxsel4");
    model.component("comp1").geom("geom1").feature("boxsel5").label("\u6c27\u6c14\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymin", Double.NEGATIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymax", "L_plate/2");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel6", "boxsel5");
    model.component("comp1").geom("geom1").feature("boxsel6").label("\u6c27\u6c14\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmin", Double.NEGATIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmax", "-L_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymin", "L_plate/2");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").label("\u9988\u6d41\u4f53\u6781\u8033");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmax", "-L_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymin", "L_plate/2-W_manifold");
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymax", "L_plate/2+W_manifold");
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmax", "D_cc+D_cell");
    model.component("comp1").geom("geom1").feature("boxsel7").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel7");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel8", "boxsel7");
    model.component("comp1").geom("geom1").feature("boxsel8").label("\u96c6\u6d41\u4f53\u6781\u8033");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmin", "(N_cells-1)*D_cell+D_cc");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel8");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u819c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel7"});
    model.component("comp1").geom("geom1").run("adjsel1");

    model.title(null);

    model.description("");

    model.label("stack_cooling_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
