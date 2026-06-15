/*
 * cooling_flange.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:30 by COMSOL 6.3.0.290. */
public class cooling_flange {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Processing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("k", "26.2[mW/(m*K)]");
    model.param().descr("k", "\u7a7a\u6c14\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("T_inner", "363[K]");
    model.param().descr("T_inner", "\u5de5\u827a\u6d41\u4f53\u7684\u6e29\u5ea6");
    model.param().set("Hh", "15[W/(m^2*K)]");
    model.param().descr("Hh", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("nu", "18e-6[m^2/s]");
    model.param().descr("nu", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("r_inner", "8[mm]");
    model.param().descr("r_inner", "\u5185\u7ba1\u534a\u5f84");
    model.param().set("l1", "12[mm]");
    model.param().descr("l1", "\u7ba1\u957f\u5ea6\uff08\u4e0d\u5305\u62ec\u51f8\u7f18\uff09");
    model.param().set("t1", "3[mm]");
    model.param().descr("t1", "\u7ba1\u58c1\u539a");
    model.param().set("t2", "4[mm]");
    model.param().descr("t2", "\u7ba1\u58c1\u539a\uff0c\u51f8\u7f18\u622a\u9762");
    model.param().set("hf", "10[mm]");
    model.param().descr("hf", "\u51f8\u7f18\u9ad8\u5ea6");
    model.param().set("wf", "4[mm]");
    model.param().descr("wf", "\u51f8\u7f18\u5bbd\u5ea6");
    model.param().set("D", "2*(r_inner+t2+hf)");
    model.param().descr("D", "\u5916\u51f8\u7f18\u76f4\u5f84");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "f");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0.48, 0, 1);
    model.func("int1").setIndex("table", 90, 1, 0);
    model.func("int1").setIndex("table", 0.46, 1, 1);
    model.func("int1").setIndex("table", 100, 2, 0);
    model.func("int1").setIndex("table", 0.45, 2, 1);
    model.func("int1").setIndex("table", 110, 3, 0);
    model.func("int1").setIndex("table", 0.435, 3, 1);
    model.func("int1").setIndex("table", 120, 4, 0);
    model.func("int1").setIndex("table", 0.42, 4, 1);
    model.func("int1").setIndex("table", 130, 5, 0);
    model.func("int1").setIndex("table", 0.38, 5, 1);
    model.func("int1").setIndex("table", 140, 6, 0);
    model.func("int1").setIndex("table", 0.35, 6, 1);
    model.func("int1").setIndex("table", 150, 7, 0);
    model.func("int1").setIndex("table", 0.28, 7, 1);
    model.func("int1").setIndex("table", 160, 8, 0);
    model.func("int1").setIndex("table", 0.22, 8, 1);
    model.func("int1").setIndex("table", 180, 9, 0);
    model.func("int1").setIndex("table", 0.15, 9, 1);
    model.func("int1").setIndex("argunit", "deg", 0);
    model.func("int1").setIndex("fununit", 1, 0);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"2*wf", "t2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "r_inner"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "r_inner+t2", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "wf/2", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "r_inner+t2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", "wf/2", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1")
         .setIndex("p", "r_inner+t2+wf/2", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"wf/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"3*wf/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"wf/2", "r_inner+t2+wf/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"3*wf/2", "r_inner+t2+wf/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", "3*wf/2", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2")
         .setIndex("p", "r_inner+t2+wf/2", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", "3*wf/2", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", "r_inner+t2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", "2*wf", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", "r_inner+t2", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord1", new String[]{"0", "r_inner+t2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord2", new String[]{"2*wf", "r_inner+t2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("ls1", "ls2", "qb1", "qb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"wf", "hf-wf"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"wf/2", "r_inner+t2+wf/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "wf/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"wf", "r_inner+t2+hf-wf/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input")
         .set("c1", "csol1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("linearsize", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"2*wf", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "0 0 -l1 -l1 -l1 -l1 -2*l1/3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "r_inner+t2 r_inner r_inner r_inner r_inner+t1 r_inner+t1 r_inner+t1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "-2*l1/3", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "r_inner+t1", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "-l1/3", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "r_inner+t1", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "-l1/3", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "r_inner+t2", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "r_inner+t2", 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol2", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol2").selection("input")
         .set("cb1", "pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol2");

    model.component("comp1").view("view2").set("showlabels", true);

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection()
         .set(3, 7, 9, 10, 13, 19, 23, 25, 29, 32, 35, 38, 39, 40, 41, 42, 45, 51, 55, 57, 61, 64, 67, 70, 71, 72, 73, 74, 77, 83, 87, 89, 93, 96, 99, 102, 103, 104, 105, 106);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u5916\u8fb9\u754c");
    model.component("comp1").selection("sel1")
         .set(3, 7, 9, 10, 13, 19, 23, 25, 29, 32, 35, 38, 39, 40, 41, 42, 45, 51, 55, 57, 61, 64, 67, 70, 71, 72, 73, 74, 77, 83, 87, 89, 93, 96, 99, 102, 103, 104, 105, 106);

    model.component("comp1").variable("var1").selection().named("sel1");
    model.component("comp1").variable("var1").set("alphap", "1/ampr1.T_amb");
    model.component("comp1").variable("var1").descr("alphap", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Gr", "g_const*alphap*(T-ampr1.T_amb)*D^3/nu^2");
    model.component("comp1").variable("var1").descr("Gr", "\u683c\u62c9\u6653\u592b\u6570");
    model.component("comp1").variable("var1").set("theta", "atan(y/z)+90[deg]");
    model.component("comp1").variable("var1").descr("theta", "\u5165\u5c04\u89d2");
    model.component("comp1").variable("var1").set("Hc", "k*f(theta)*Gr^0.25/D");
    model.component("comp1").variable("var1").descr("Hc", "\u4f20\u70ed\u819c\u7cfb\u6570");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silica glass");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "298[K]");

    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "2s");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "323.15[K]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("ht").feature("sym1").selection()
         .set(2, 8, 12, 15, 21, 22, 24, 27, 33, 34, 44, 47, 53, 54, 56, 59, 65, 66, 76, 79, 85, 86, 88, 91, 97, 98);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4, 6, 16, 18, 48, 50, 80, 82);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "Hh");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_inner");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("sel1");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "Hc");
    model.component("comp1").physics("ht").feature("hf2").set("Text_src", "root.comp1.ampr1.T_amb");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(8, 21, 22, 33, 53, 54, 65, 85, 86, 97);
    model.component("comp1").mesh("mesh1").feature("map1").feature().create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(34, 66, 98);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();

    model.view("view3").set("locked", true);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u4f20\u70ed\u819c\u7cfb\u6570");
    model.result("pg2").set("view", "view3");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "Hc");
    model.result("pg2").feature("surf1").set("descr", "\u4f20\u70ed\u819c\u7cfb\u6570");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u6d41\u51fa\u7684\u70ed\u901a\u91cf");
    model.result().numerical("int1").selection().named("sel1");
    model.result().numerical("int1").set("expr", new String[]{"ht.ntflux"});
    model.result().numerical("int1").set("descr", new String[]{"\u6cd5\u5411\u603b\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u6d41\u51fa\u7684\u70ed\u901a\u91cf");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u5916\u90e8\u79ef\u5206");
    model.component("comp1").cpl("intop1").set("opname", "intop_outer");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel1");

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("P_cooling", "2*intop_outer(ht.q0)");
    model.component("comp1").variable("var2").descr("P_cooling", "\u51b7\u5374\u529f\u7387");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "k", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "W/(m*K)", 0);
    model.study("std2").feature("param").setIndex("pname", "k", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "W/(m*K)", 0);
    model.study("std2").feature("param").setIndex("pname", "r_inner", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(6,1,10)", 0);
    model.study("std2").feature("param").setIndex("punit", "mm", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg3").set("view", "view3");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u51b7\u5374\u529f\u7387");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"P_cooling"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u51b7\u5374\u529f\u7387"});
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u5185\u7ba1\u534a\u5f84 (m)");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u51b7\u5374\u51f8\u7f18\u7684\u6e29\u5ea6\u573a");

    model
         .description("\u8fd9\u4e00\u51b7\u5374\u51f8\u7f18\u793a\u4f8b\u5bf9\u7ba1\u9053\u5185\u7684\u5f3a\u5236\u5bf9\u6d41\u4e0e\u5916\u8868\u9762\u7684\u81ea\u7136\u5bf9\u6d41\u4f7f\u7528\u4f20\u70ed\u7cfb\u6570\u3002\u6a21\u578b\u8fd8\u4f7f\u7528\u4e86\u63d2\u503c\u548c\u6620\u5c04\u7f51\u683c\u7279\u5f81\uff0c\u4ee5\u53ca\u7ba1\u9053\u534a\u5f84\u7684\u53c2\u6570\u5316\u626b\u63cf\u7b49\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("cooling_flange.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
