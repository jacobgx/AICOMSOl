/*
 * rotating_channel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:08 by COMSOL 6.3.0.290. */
public class rotating_channel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("omega", "100[rad/s]");
    model.param().descr("omega", "\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{10, 0.1, 0.1});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{20, 0, 0});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 2, 2});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0, 0.1, 0.1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"977"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"8.55e-4"});

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("renderwireframe", true);
    model.component("comp1").view("view2").camera().set("viewscaletype", "automatic");
    model.component("comp1").view("view2").camera().set("autocontext", "anisotropic");
    model.component("comp1").view("view2").camera().set("xweight", 6);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(17, 18, 19, 20);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Q", "intop1(u)");
    model.component("comp1").variable("var1").descr("Q", "\u603b\u6d41\u7387");

    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 3);
    model.component("comp1").physics("spf").feature("vf1").label("\u79bb\u5fc3\u529b");
    model.component("comp1").physics("spf").feature("vf1").selection().all();
    model.component("comp1").physics("spf").feature("vf1").set("F", new String[]{"spf.rho*x*omega^2", "0", "0"});
    model.component("comp1").physics("spf").create("vf2", "VolumeForce", 3);
    model.component("comp1").physics("spf").feature("vf2").label("\u79d1\u91cc\u5965\u5229\u529b");
    model.component("comp1").physics("spf").feature("vf2").selection().all();
    model.component("comp1").physics("spf").feature("vf2")
         .set("F", new String[]{"-2*spf.rho*omega*v", "2*spf.rho*omega*u", "0"});
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open1").selection().set(1, 4, 8, 11, 17, 18, 19, 20);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 4, 8, 11);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(1, 2, 4, 5, 7, 9, 10, 12, 13, 15, 17, 19);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 150);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(2, 3, 5, 7, 10, 14, 15, 16);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").run();
    model.result().dataset().create("edg1", "Edge3D");
    model.result().dataset("edg1").selection().set(14);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u538b\u964d");
    model.result("pg3").set("data", "edg1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "p");
    model.result("pg3").feature("lngr1").set("descr", "\u538b\u529b");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg3").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"Q"});
    model.result().numerical("gev1").set("descr", new String[]{"\u603b\u6d41\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"m^3/s"});
    model.result().numerical("gev1").setIndex("unit", "dm^3/s", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg2").run();

    model.title("\u65cb\u8f6c\u6d41\u9053");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u65cb\u8f6c\u82af\u7247\u5b9e\u9a8c\u5ba4 (LOAC) \u5e73\u53f0\u65f6\u4ea7\u751f\u7684\u5f84\u5411\u538b\u529b\u5206\u5e03\u548c\u6d41\u7387\u3002\u88c5\u7f6e\u4e2d\u7684\u6d41\u4f53\u663e\u793a\u51fa\u79bb\u5fc3\u529b\u6548\u5e94\u548c\u79d1\u91cc\u5965\u5229\u529b\u6548\u5e94\u3002\u7ed3\u679c\u4e0e\u53c2\u8003\u7684\u51fa\u7248\u7269\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("rotating_channel.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
