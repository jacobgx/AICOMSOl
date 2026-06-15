/*
 * tumor_ablation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:23 by COMSOL 6.3.0.290. */
public class tumor_ablation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Medical_Technology");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "BioHeat", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ec", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("rho_b", "1000[kg/m^3]");
    model.param().descr("rho_b", "\u5bc6\u5ea6\uff0c\u8840\u6db2");
    model.param().set("c_b", "4180[J/(kg*K)]");
    model.param().descr("c_b", "\u70ed\u5bb9\uff0c\u8840\u6db2");
    model.param().set("omega_b", "6.4e-3[1/s]");
    model.param().descr("omega_b", "\u8840\u6db2\u704c\u6ce8\u901f\u7387");
    model.param().set("T_b", "37[degC]");
    model.param().descr("T_b", "\u52a8\u8109\u8840\u6e29\u5ea6");
    model.param().set("T0", "37[degC]");
    model.param().descr("T0", "\u521d\u59cb\u6e29\u5ea6\u548c\u8fb9\u754c\u6e29\u5ea6");
    model.param().set("V0", "22[V]");
    model.param().descr("V0", "\u7535\u538b");
    model.param().set("xc_v", "26[mm]");
    model.param()
         .descr("xc_v", "\u8840\u7ba1\u8ddd\u5706\u67f1\u4e2d\u5fc3\u7684\u8ddd\u79bb\uff08x \u5750\u6807\uff09");
    model.param().set("a_time", "10[min]");
    model.param().descr("a_time", "\u6d88\u878d\u65f6\u95f4");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.9144);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 60);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{0, 0, 60});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", 10, 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", 7.5);
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", 0.2667);
    model.component("comp1").geom("geom1").feature("tor1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("tor1").set("pos", new int[]{8, 0, 60});
    model.component("comp1").geom("geom1").feature("tor1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("tor1").set("rot", -90);
    model.component("comp1").geom("geom1").run("tor1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("tor1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,90,270)");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 5);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 120);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"xc_v", "0", "0"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 50);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 120);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u809d\u810f\u7ec4\u7ec7");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8840\u7ba1");
    model.component("comp1").selection("sel3").set(8);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u7535\u6781");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel4").set(2, 5, 6, 7);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u5957\u7ba1\u9488\u5c16\u7aef");
    model.component("comp1").selection("sel5").set(3);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5957\u7ba1\u9488\u57fa\u90e8");
    model.component("comp1").selection("sel6").set(4);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5957\u7ba1\u9488");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel4", "sel5", "sel6"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u809d\u810f\u7ec4\u7ec7\u548c\u5957\u7ba1\u9488");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel2", "uni1"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1")
         .label("\u809d\u810f\u7ec4\u7ec7\u548c\u5957\u7ba1\u9488\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"uni2"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2")
         .label("\u5957\u7ba1\u9488\u5c16\u7aef\u548c\u7535\u6781\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel4", "sel5"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Liver (human)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "3540[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1079[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.52[W/(m*K)]", "0", "0", "0", "0.52[W/(m*K)]", "0", "0", "0", "0.52[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("frequencyfactor", "7.39e39[1/s]");
    model.component("comp1").material("mat1").propertyGroup("def").set("activationenergy", "2.577e5[J/mol]");
    model.component("comp1").material("mat1").selection().named("sel2");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.333[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u8840\u6db2");
    model.component("comp1").material("mat2").selection().named("sel3");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.667[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.543[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho_b"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"c_b"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u7535\u6781");
    model.component("comp1").material("mat3").selection().named("sel4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e8[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"18[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"6450[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"840[J/(kg*K)]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u5957\u7ba1\u9488\u5c16\u7aef");
    model.component("comp1").material("mat4").selection().named("sel5");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"4e6[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"71[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"21500[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"132[J/(kg*K)]"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u5957\u7ba1\u9488\u57fa\u90e8");
    model.component("comp1").material("mat5").selection().named("sel6");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-5[S/m]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.026[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"70[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"1045[J/(kg*K)]"});

    model.component("comp1").physics("ec").prop("EquationForm").setIndex("form", "Stationary", 0);
    model.component("comp1").physics("ec").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("sel1");
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().named("adj2");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "V0");
    model.component("comp1").physics("ht").selection().named("uni2");
    model.component("comp1").physics("ht").feature("bt1").create("tdam1", "ThermalDamage", 3);
    model.component("comp1").physics("ht").feature("bt1").feature("tdam1")
         .set("TransformationModel", "ArrheniusKinetics");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("Tb", "T_b");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("Cp_b", "c_b");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("omegab", "omega_b");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("rhobl", "rho_b");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid1").selection().named("uni1");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("adj1");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_b");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2, 5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.38);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", 0.35);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 1.3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmin", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,a_time/4,a_time)");

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords3", -4, 0);
    model.component("comp1").probe("pdom1").setIndex("coords3", 65, 2);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "ht.theta_d");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u635f\u4f24\u5206\u6570");
    model.component("comp1").probe("pdom1").feature("ppb1").set("window", "window1");
    model.component("comp1").probe("pdom1").feature("ppb1").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().duplicate("pdom2", "pdom1");
    model.component("comp1").probe("pdom2").setIndex("coords3", -12, 0);
    model.component("comp1").probe().duplicate("pdom3", "pdom1");
    model.component("comp1").probe("pdom3").setIndex("coords3", -20, 0);
    model.component("comp1").probe().create("pdom4", "DomainPoint");
    model.component("comp1").probe("pdom4").setIndex("coords3", -0.2667, 0);
    model.component("comp1").probe("pdom4").setIndex("coords3", 15.5, 1);
    model.component("comp1").probe("pdom4").setIndex("coords3", 60, 2);
    model.component("comp1").probe("pdom4").feature("ppb4").set("expr", "T");
    model.component("comp1").probe("pdom4").feature("ppb4").set("descr", "\u6e29\u5ea6");
    model.component("comp1").probe("pdom4").feature("ppb4").set("window", "window2");
    model.component("comp1").probe("pdom4").feature("ppb4").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe("pdom4").feature("ppb4").set("unit", "\u00b0C");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");
    model.component("comp1").probe("pdom2").genResult("none");
    model.component("comp1").probe("pdom3").genResult("none");
    model.component("comp1").probe("pdom4").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ec)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("colortable", "Dipole");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ec)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "ec.normE");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u53d7\u635f\u7ec4\u7ec7\uff0c\u4e00\u7ef4");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u63a2\u9488\u4f4d\u7f6e\u5904\u635f\u4f24\u5206\u6570\u968f\u65f6\u95f4\u7684\u6f14\u5316");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u635f\u4f24\u5206\u6570 (1)");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u70b9\uff1a(-4, 0, 65)", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u70b9\uff1a(-12, 0, 65)", 1);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u70b9\uff1a(-20, 0, 65)", 2);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").label("\u5176\u4e2d\u4e00\u4e2a\u7535\u6781\u5c16\u7aef\u5904\u7684\u6e29\u5ea6");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u6e29\u5ea6 (\u00b0C)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u5176\u4e2d\u4e00\u4e2a\u7535\u6781\u5c16\u7aef\u5904\u6e29\u5ea6\u968f\u65f6\u95f4\u7684\u6f14\u5316");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u70b9\uff1a(-0.2667, 15.5, 60)", 0);
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().remove("vol1");
    model.result("pg3").run();
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("znumber", "0");
    model.result("pg3").feature("mslc1").set("colortable", "Dipole");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "number");
    model.result("pg4").feature("mslc1").set("znumber", "0");
    model.result("pg4").run();
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "number");
    model.result("pg4").feature("strmsl1").set("znumber", "0");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6\uff0c\u591a\u5207\u9762 (ht)");
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "T");
    model.result("pg5").feature("mslc1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").label("\u6e29\u5ea6\uff0c\u591a\u5207\u9762 (ht)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("mslc1").set("znumber", "0");
    model.result("pg5").feature("mslc1").set("unit", "\u00b0C");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg6").feature().create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("solutionparams", "parent");
    model.result("pg6").feature("iso1").set("expr", "T");
    model.result("pg6").feature("iso1").set("number", 10);
    model.result("pg6").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("iso1").set("smooth", "internal");
    model.result("pg6").feature("iso1").set("showsolutionparams", "on");
    model.result("pg6").feature("iso1").set("data", "parent");
    model.result("pg6").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("iso1").set("unit", "\u00b0C");
    model.result("pg6").feature("iso1").set("levelmethod", "levels");
    model.result("pg6").feature("iso1").set("levels", 50);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u53d7\u635f\u7ec4\u7ec7\uff0c\u591a\u5207\u9762 (ht)");
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("solutionparams", "parent");
    model.result("pg7").feature("mslc1").set("expr", "ht.theta_d");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result("pg7").label("\u53d7\u635f\u7ec4\u7ec7\uff0c\u591a\u5207\u9762 (ht)");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("mslc1").set("znumber", "0");
    model.result("pg7").run();
    model.result("pg7").run();

    model.title("\u809d\u810f\u80bf\u7624\u6d88\u878d");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u7535\u5b50\u63a2\u9488\u4f7f\u809d\u810f\u80bf\u7624\u7ec4\u7ec7\u53d7\u70ed\u6d88\u878d\uff0c\u5176\u4e2d\u4f7f\u7528\u4e86\u751f\u7269\u70ed\u65b9\u7a0b\u6765\u63cf\u8ff0\u77ac\u6001\u4f20\u70ed\uff0c\u5e76\u4f7f\u7528\u201c\u7535\u6d41\u201d\u63a5\u53e3\u6765\u6a21\u62df\u70ed\u6e90\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("tumor_ablation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
