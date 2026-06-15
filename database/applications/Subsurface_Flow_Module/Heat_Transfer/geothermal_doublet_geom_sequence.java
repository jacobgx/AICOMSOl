/*
 * geothermal_doublet_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:30 by COMSOL 6.3.0.290. */
public class geothermal_doublet_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("r_bore", "1[m]");
    model.param().descr("r_bore", "\u4e95\u773c\u8868\u76ae\u6548\u5e94\u5730\u5e26\u534a\u5f84");
    model.param().set("l_well", "20[m]");
    model.param().descr("l_well", "\u4e95\u957f");
    model.param().set("length", "500[m]");
    model.param().descr("length", "\u57df\u5927\u5c0f");
    model.param().set("depth_w", "-900[m]");
    model.param().descr("depth_w", "\u4e95\u6df1");
    model.param().set("alpha", "10[deg]");
    model.param().descr("alpha", "\u4e95\u659c\u5ea6");
    model.param().set("dist", "250[m]");
    model.param().descr("dist", "\u6a2a\u5411\u4e95\u8ddd\u79bb");
    model.param().set("depth", "-length-650[m]");
    model.param().descr("depth", "\u50a8\u5c42\u6df1\u5ea6");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "geothermal_doublet_geom_sequence_interpolation.txt");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"length", "length", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "length", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-length-650[m]"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("ps1", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax1", 10);
    model.component("comp1").geom("geom1").feature("ps1").set("parmax2", 10);
    model.component("comp1").geom("geom1").feature("ps1")
         .set("coord", new String[]{"50*s1", "50*s2", "30*int1(s1,s2)+depth+300"});
    model.component("comp1").geom("geom1").feature("ps1").set("rtol", "5e-2");
    model.component("comp1").geom("geom1").feature("ps1").set("maxknots", 200);
    model.component("comp1").geom("geom1").feature().duplicate("ps2", "ps1");
    model.component("comp1").geom("geom1").feature("ps2")
         .set("coord", new String[]{"50*s1", "50*s2", "60*int1(s1,s2)+depth+100"});
    model.component("comp1").geom("geom1").feature().duplicate("ps3", "ps2");
    model.component("comp1").geom("geom1").feature("ps3")
         .set("coord", new String[]{"50*s1", "50*s2", "15*int1(s1,s2)+depth"});
    model.component("comp1").geom("geom1").feature().duplicate("ps4", "ps3");
    model.component("comp1").geom("geom1").feature("ps4")
         .set("coord", new String[]{"50*s1", "50*s2", "17*int1(s1,s2)+depth+425"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ps1", "ps2", "ps3", "ps4");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1, 5);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ps5", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps5").set("parmax1", 20);
    model.component("comp1").geom("geom1").feature("ps5").set("parmin2", -5);
    model.component("comp1").geom("geom1").feature("ps5").set("parmax2", 15);
    model.component("comp1").geom("geom1").feature("ps5")
         .set("coord", new String[]{"50*s1", "50*s2", "25*int1(s1,s2)+depth+450"});
    model.component("comp1").geom("geom1").feature("ps5").set("pos", new int[]{-50, 500, 0});
    model.component("comp1").geom("geom1").feature("ps5").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("ps5").set("ax3", new int[]{-2, 0, 1});
    model.component("comp1").geom("geom1").feature("ps5").set("rtol", 0.02);
    model.component("comp1").geom("geom1").feature("ps5").set("maxknots", 200);
    model.component("comp1").geom("geom1").run("ps5");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("del1", "ps5");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "del1+del1*ps5");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"250-dist/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", 250, 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "depth_w+l_well", 2);
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"250-dist/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", 250, 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "depth_w", 2);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "depth_w", 2);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "depth_w+l_well", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "alpha");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"250", "250", "depth_w"});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord1", new String[]{"250+dist/2", "250", "depth_w"});
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord2", "250+dist/2", 0);
    model.component("comp1").geom("geom1").feature().duplicate("rot2", "rot1");
    model.component("comp1").geom("geom1").runPre("rot2");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("ls2");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "-alpha");
    model.component("comp1").geom("geom1").feature("rot2").set("pos", new String[]{"300", "250", "depth_w"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("clf1", "CollapseFaces");
    model.component("comp1").geom("geom1").feature("clf1").selection("input").set("fin", 30);
    model.component("comp1").geom("geom1").run("clf1");

    model.title(null);

    model.description("");

    model.label("geothermal_doublet_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
