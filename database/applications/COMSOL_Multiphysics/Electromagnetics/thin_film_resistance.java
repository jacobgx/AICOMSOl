/*
 * thin_film_resistance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class thin_film_resistance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.1);
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 0.6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("int1").selection("input").set("c1", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("int1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1.sq2");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", -0.1, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{1, 1, 0.1});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0, 0, -0.1});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("blk1", "ext1", "wp1.int1");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 1.5);
    model.component("comp1").geom("geom1").feature("copy1").set("disply", -1);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", -0.02);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{1, 1, 0.02});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0, 0, -0.02});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ec").feature("cucn1").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn1").set("sigma", new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
    model.component("comp1").physics("ec").create("ncd1", "NormalCurrentDensity", 2);
    model.component("comp1").physics("ec").feature("ncd1").selection().set(3, 20);
    model.component("comp1").physics("ec").feature("ncd1").set("nJ", 0.3);
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(11, 25);
    model.component("comp1").physics("ec").create("ci1", "ContactImpedance", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("ec").feature("ci1").selection().set(23);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").physics("ec").feature("ci1").set("ds", 0.02);
    model.component("comp1").physics("ec").feature("ci1").set("sigmabnd_mat", "userdef");
    model.component("comp1").physics("ec").feature("ci1").set("epsilonrbnd_mat", "userdef");
    model.component("comp1").physics("ec").create("cucn2", "CurrentConservation", 3);
    model.component("comp1").physics("ec").feature("cucn2").selection().set(2);
    model.component("comp1").physics("ec").feature("cucn2").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn2")
         .set("sigma", new double[]{0.01, 0, 0, 0, 0.01, 0, 0, 0, 0.01});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("colorlegend", false);
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0.5, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.5, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", -0.1, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 0.5, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.5, 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0.1, 1, 2);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").setIndex("genpoints", 2, 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", -0.5, 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", 2, 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", -0.5, 1, 1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", -0.1);
    model.result("pg2").set("xmax", 0.1);
    model.result("pg2").set("ymin", 0.2);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("data", "cln1");
    model.result("pg2").feature("lngr1").set("xdataexpr", "z");
    model.result("pg2").feature("lngr1").set("xdatadescr", "z \u5750\u6807");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u5168\u4e09\u7ef4\u6a21\u578b", 0);
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("data", "cln2");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u8584\u819c\u8fd1\u4f3c", 0);
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u8584\u819c\u7535\u963b");

    model
         .description("\u672c\u4f8b\u4ecb\u7ecd\u6269\u6563\u7c7b\u578b\u95ee\u9898\u4e2d\u7684\u8584\u5c42\u8868\u9762\u8fd1\u4f3c\u6280\u672f\uff0c\u6b64\u6280\u672f\u53ef\u663e\u8457\u51cf\u5c11\u5185\u5b58\u9700\u6c42\u3002\u672c\u4f8b\u7814\u7a76\u7ed3\u6784\u4e2d\u5bfc\u7535\u6027\u76f8\u5bf9\u8f83\u4f4e\u7684\u8584\u5c42\u5bf9\u7535\u52bf\u5206\u5e03\u7684\u5f71\u54cd\uff0c\u7ed3\u679c\u663e\u793a\u8584\u5c42\u8fd1\u4f3c\u6280\u672f\u5728\u5c42\u539a\u5ea6\u5c0f\u4e8e\u7ed3\u6784\u603b\u9ad8\u5ea6\u7684 10% \u65f6\u90fd\u662f\u6709\u6548\u7684\u3002");

    model.label("thin_film_resistance.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
